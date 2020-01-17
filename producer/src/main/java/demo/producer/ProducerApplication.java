package demo.producer;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;

@SpringBootApplication
public class ProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProducerApplication.class, args);
	}

//	@Bean
//	Queue historyQue() {
//		return new Queue("history.que", false);
//	}
//
//	@Bean
//	Queue failureque() {
//		return new Queue("failure.que", false);
//	}
//
//	@Bean
//	TopicExchange exchange() {
//		return new TopicExchange("exchange");
//	}
//
//	@Bean
//	Binding bindingExchangehistoryQue(Queue historyQue, TopicExchange exchange) {
//		return BindingBuilder.bind(historyQue).to(exchange).with("history.que");
//	}
//
//	@Bean
//	Binding bindingExchangefailureQue(Queue failureque, TopicExchange exchange) {
//		return BindingBuilder.bind(failureque).to(exchange).with("failure.que");
//	}
//
//	@Bean
//	public MappingJackson2MessageConverter jackson2Converter() {
//		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
//		return converter;
//	}

//	@Autowired
//	private Sender sender;

//	@Override
//	public void run(String... args) throws Exception {
//		sender.sendToRabbitmq();
//	}
}

//@Service
//class Sender {
//
//	@Autowired
//	private RabbitMessagingTemplate rabbitMessagingTemplate;
//	@Autowired
//	private MappingJackson2MessageConverter mappingJackson2MessageConverter;
//
//	public void sendToRabbitmq() {
//
//		Employee emp = new Employee();
//		emp.setEmpId("1");
//		emp.setEmpName("Holder");
//
//		this.rabbitMessagingTemplate.setMessageConverter(this.mappingJackson2MessageConverter);
//
//		this.rabbitMessagingTemplate.convertAndSend("exchange", "history.que", "historyservice");
//
//	}
//}
//
//class Bar {
//	public int age = 33;
//}
//
//class Foo {
//	public String name = "gustavo";
//}
