package com.cashman.serviceImpl;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cashman.model.Amount;
import com.cashman.model.CashMan;
import com.cashman.model.CashMan.Note;
import com.cashman.model.ServiceResponse;
import com.cashman.repos.AmountRepo;
import com.cashman.repos.CashManRepo;
import com.cashman.services.CashManService;

@RestController
public class CashManServiceImpl implements CashManService {

	@Autowired
	public CashManRepo cashManRepo;

	@Autowired
	public AmountRepo amountRepo;

	@Value("${mail.username}")
	public String username;

	@Value("${password}")
	public String password;

	@Value("${notifyUser}")
	public String notifyUser;

	@Override
	public ServiceResponse<Collection<CashMan>> getTotalAmount() {

		ServiceResponse<Collection<CashMan>> response = new ServiceResponse<>();
		try {
			Collection<CashMan> getAllNotes = cashManRepo.findAll();
			response.setData(getAllNotes);
		} catch (Throwable th) {
			response.setError("Unable to get all notes " + th.getMessage());
		}
		return response;
	}

	@Override
	public ServiceResponse<CashMan> addCash(@RequestBody CashMan cash) {

		ServiceResponse<CashMan> response = new ServiceResponse<>();
		CashMan noteExists = cashManRepo.findByNote(cash.getNote());
		try {
			if (noteExists == null) {
				CashMan saveNote = new CashMan();
				saveNote.setNote(cash.getNote());
				saveNote.setCurrentNotes(cash.getCurrentNotes());
				cashManRepo.save(saveNote);
				getTotalCash();
				response.setData(saveNote);
			} else {
				noteExists.setPreviousNotes(noteExists.getCurrentNotes());
				noteExists.setCurrentNotes(noteExists.getCurrentNotes() + cash.getCurrentNotes());
				cashManRepo.save(noteExists);
				getTotalCash();
				response.setData(noteExists);
			}
		} catch (Throwable th) {
			response.setError("Unable to add the cash " + th.getMessage());
		}
		return response;
	}

	public void getTotalCash() {

		Collection<CashMan> getAllNotes = cashManRepo.findAll();

		try {
			long totalCash = 0;
			for (CashMan cashMan : getAllNotes) {
				totalCash = totalCash + (cashMan.getCurrentNotes() * cashMan.getNote().getValue());
			}
			Amount currentAmount = new Amount();
			currentAmount.setCurrentAmount(totalCash);
			currentAmount.setLastUpdateTime(ZonedDateTime.now());
			amountRepo.save(currentAmount);
		} catch (Throwable th) {
			System.out.println(th.getMessage());
		}
	}

	@Override
	public ServiceResponse<Amount> getCurrentAmount() {

		ServiceResponse<Amount> response = new ServiceResponse<>();
		try {
			Collection<Amount> amount = amountRepo.findAll();
			for (Amount amt : amount) {
				response.setData(amt);
			}
		} catch (Throwable th) {
			response.setError("unable to get amount: " + th.getMessage());
		}
		return response;
	}

	@Override
	public ServiceResponse<String> withdrawAMount(@PathVariable int amount) throws MessagingException {

		ServiceResponse<String> response = new ServiceResponse<>();
		try {
			int reqWithdraw = amount;
			int rupees[] = { 50, 20 };
			int count = 0;
			int fiftyCount = 0, twentyCount = 0;
			for (int i = 0; i < 2; i++) {
				count = amount / rupees[i];
				if (count != 0) {
					if (rupees[i] == 50) {
						fiftyCount = fiftyCount + count;
					} else {
						twentyCount = twentyCount + count;
					}
				}
				amount = amount % rupees[i];
			}
			int countAmount = (fiftyCount * 50 + twentyCount * 20);
			Note.valueOf("FIFTY");
			CashMan getFiftyNotes = cashManRepo.findByNote(Note.valueOf("FIFTY"));
			getFiftyNotes.getCurrentNotes();
			CashMan getTwentyNotes = cashManRepo.findByNote(Note.valueOf("TWENTY"));
			getTwentyNotes.getCurrentNotes();
			if (countAmount == reqWithdraw) {
				if (fiftyCount < getFiftyNotes.getCurrentNotes()) {
					if (twentyCount < getTwentyNotes.getCurrentNotes()) {
						getFiftyNotes.setCurrentNotes(getFiftyNotes.getCurrentNotes() - fiftyCount);
						getFiftyNotes.setPreviousNotes(getFiftyNotes.getCurrentNotes());
						getTwentyNotes.setCurrentNotes(getTwentyNotes.getCurrentNotes() - twentyCount);
						getTwentyNotes.setPreviousNotes(getTwentyNotes.getCurrentNotes());
						cashManRepo.save(getFiftyNotes);
						cashManRepo.save(getTwentyNotes);
						getTotalCash();
						if (getTwentyNotes.getCurrentNotes() < 10 && getFiftyNotes.getCurrentNotes() < 10) {
							notifyAdmin();
						}
						response.setData("Amount dispended");
					}
				} else {
					response.setData("unable to  dispense Amount ");
				}
			} else {
				response.setData("ATM with only $20 and $50 notes, it is not possible to dispense : " + reqWithdraw);
			}
		} catch (Throwable th) {
			response.setError("Error in Amount dispense: " + th.getMessage());
		}
		return response;
	}

	@Async
	public void notifyAdmin() throws MessagingException {
		String host = "smtp.gmail.com";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(notifyUser));
			message.setSubject("ATM  Notification");
			message.setText("ATM out off cash ");
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
