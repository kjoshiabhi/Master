package demo.consumer;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

@SpringBootApplication
@EnableRabbit
public class ConsumerApplication implements RabbitListenerConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}

	@Bean
	public MappingJackson2MessageConverter jackson2Converter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		return converter;
	}

	@Bean
	public DefaultMessageHandlerMethodFactory myHandlerMethodFactory() {
		DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
		factory.setMessageConverter(jackson2Converter());
		return factory;
	}

	@Override
	public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
		registrar.setMessageHandlerMethodFactory(myHandlerMethodFactory());
	}

//	@Autowired
//	private Receiver receiver;

}

//@Service
//class Receiver {
//	/*
//	 * @RabbitListener(queues = "queue.foo") public void receiveMessage(Foo foo) {
//	 * System.out.println("Received Foo<" + foo.name + ">"); }
//	 * 
//	 * @RabbitListener(queues = "queue.bar") public void receiveMessage(Bar bar) {
//	 * System.out.println("Received Bar<" + bar.age + ">"); }
//	 */
//
//	@RabbitListener(queues = "history.que")
//	public void receiveMessage(Employee emp) {
//		System.out.println("Received Bar<" + emp.getEmpName() + ">");
//	}
//}
//
//class Foo {
//	public String name;
//}
//
//class Bar {
//	public int age;
//}
