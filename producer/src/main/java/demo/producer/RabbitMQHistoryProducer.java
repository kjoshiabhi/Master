package demo.producer;

import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitMQHistoryProducer extends MappingJackson2MessageConverter {

	@Autowired
	private RabbitMessagingTemplate rabbitMessagingTemplate;

	@Autowired
	private MappingJackson2MessageConverter mappingJackson2MessageConverter;

//	@PostMapping(value = "/produce")
//	public void sendToRabbitmq() {
//
//		Employee emp = new Employee();
//		emp.setEmpId("1");
//		emp.setEmpName("MC");
//
//		this.rabbitMessagingTemplate.setMessageConverter(this.mappingJackson2MessageConverter);
//		this.rabbitMessagingTemplate.convertAndSend("exchange", "history.que", emp);
//	}

	
	@PostMapping(value = "/historyLog" ,consumes = MediaType.APPLICATION_JSON)
	@ResponseBody
	public void putHistoryData(@RequestBody Map<String, Object> logData) {

		Object newobj = new Object();
		newobj = logData;

		this.rabbitMessagingTemplate.setMessageConverter(this.mappingJackson2MessageConverter);

		this.rabbitMessagingTemplate.convertAndSend("exchange", "dwe.history.queue", newobj);
	}

}
