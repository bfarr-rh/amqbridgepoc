package com.bfarr.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.bfarr.dto.Student;

@Component
public class Consumer {

	private static final Logger logger = LoggerFactory.getLogger(Consumer.class);
	
	//@JmsListener(destination = "TestQueue", containerFactory = "jmsListenerContainerFactory")
	@JmsListener(destination = "TestQueue")
	public void consumeMessage1(String msg) {
	logger.info("Message received from TestQueue "+ msg);
	}

	//@JmsListener(destination = "SimpleQueue::SimpleQueue", containerFactory = "jmsListenerContainerFactory")
	@JmsListener(destination = "SimpleQueue")
	public void consumeMessage2(String msg) {
	logger.info("Message received from SimpleQueue "+ msg);
	}

	//@JmsListener(destination = "BinaryQueue", containerFactory = "jmsListenerContainerFactory")
	@JmsListener(destination = "BinaryQueue")
	public void consumeMessage3(Student student) {
	logger.info("Message received from BinaryQueue " + student.toString());
	}

//	@JmsListener(destination = "SimpleTopic::SimpleTopic", containerFactory = "topicListenerContainerFactory")
	@JmsListener(destination = "SimpleTopic::SimpleTopic")
	public void consumeMessage4(String msg) {
	logger.info("Message received from SimpleTopic "+ msg);
	}

//	@JmsListener(destination = "BinaryTopic", containerFactory = "topicListenerContainerFactory")
	@JmsListener(destination = "BinaryTopic::BinaryTopic")
	public void consumeMessage5(Student student) {
	logger.info("Message received from BinaryTopic " + student.toString());
	}
 
}
