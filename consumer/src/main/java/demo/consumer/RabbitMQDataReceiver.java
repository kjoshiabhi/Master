package demo.consumer;

import java.util.Map;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.stereotype.Service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Service
@EnableRabbit
class RabbitMQDataReceiver extends MappingJackson2MessageConverter {

	@Autowired
	private RabbitMessagingTemplate rabbitMessagingTemplate;

	@Autowired
	private MappingJackson2MessageConverter mappingJackson2MessageConverter;

	public int count = 0;

	@RabbitListener(queues = "history.que")
	public void receiveMessage(Map<String, Object> logData) throws JsonMappingException, JsonProcessingException {

		count++;
		if (count >= 3) {
			count = 0;
			System.out.println("if");
			putHistoryData(logData);
		} else {
			System.out.println("else");
			System.out.println((Integer) logData.get("logId"));
		}
	}

	public void putHistoryData(Map<String, Object> logData) {

		Object newobj = new Object();
		newobj = logData;

		this.rabbitMessagingTemplate.setMessageConverter(this.mappingJackson2MessageConverter);

		this.rabbitMessagingTemplate.convertAndSend("exchange", "message.que", newobj);
	}
}