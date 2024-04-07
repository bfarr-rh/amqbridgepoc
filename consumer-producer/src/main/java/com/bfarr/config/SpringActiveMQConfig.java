package com.bfarr.config;

import javax.jms.Queue;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@EnableJms
public class SpringActiveMQConfig {

	@Value("${activemq.broker.url}")
	private String brokerUrl;

	@Bean
	public Queue testQueue() {
		return new ActiveMQQueue("TestQueue");
	}

	@Bean
	public Queue simpleQueue() {
		return new ActiveMQQueue("SimpleQueue");
	}
	@Bean
	public Queue binaryQueue() {
		return new ActiveMQQueue("BinaryQueue");
	}
	@Bean
	public Topic simpleTopic() {
		return new ActiveMQTopic("SimpleTopic");
	}
	@Bean
	public Topic binaryTopic() {
		return new ActiveMQTopic("BinaryTopic");
	}
	@Bean
	public ActiveMQConnectionFactory activeMQConnectionFactory() {
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
		activeMQConnectionFactory.setBrokerURL(brokerUrl);
		activeMQConnectionFactory.setUserName("admin");
		activeMQConnectionFactory.setPassword("admin");
		activeMQConnectionFactory.setTrustAllPackages(true);
		return activeMQConnectionFactory;
	}

	@Bean
	public JmsTemplate jmsTemplate() {
		return new JmsTemplate(activeMQConnectionFactory());
	}

	/*
	@Bean
	public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(){
    	DefaultJmsListenerContainerFactory defaultJmsListenerContainerFactory = new DefaultJmsListenerContainerFactory();
                defaultJmsListenerContainerFactory.setConnectionFactory(activeMQConnectionFactory());
		defaultJmsListenerContainerFactory.setPubSubDomain(false);
		return defaultJmsListenerContainerFactory;
    }

	@Bean
	public DefaultJmsListenerContainerFactory topicListenerContainerFactory(){
    	DefaultJmsListenerContainerFactory defaultJmsListenerContainerFactory = new DefaultJmsListenerContainerFactory();
                defaultJmsListenerContainerFactory.setConnectionFactory(activeMQConnectionFactory());
		defaultJmsListenerContainerFactory.setPubSubDomain(true);
		return defaultJmsListenerContainerFactory;
    }
 */
}
