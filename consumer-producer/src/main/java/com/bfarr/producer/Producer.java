package com.bfarr.producer;

import javax.jms.Queue;
import javax.jms.Topic;
import javax.jms.Session;
import javax.jms.Message;
import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import com.bfarr.dto.Student;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping("/produce")
public class Producer {

	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	private Queue testQueue;

	@Autowired
	private Queue simpleQueue;

	@Autowired
	private Queue binaryQueue;

	@Autowired
	private Topic binaryTopic;

	@Autowired
	private Topic simpleTopic;


	@GetMapping("/sendBinaryTopic/{topic}/{times}")
	public Student sendBinaryTopic(@PathVariable String topic, @PathVariable String times) {
		Topic targetTopic = getTopic(topic);
		if (targetTopic == null) {
            return null;
        }
		Integer i = 1;
		if (times != null) {
			i = Integer.valueOf(times);
		} 
		Student student = new Student();
		for (int j = 0; j < i; j++) {

			
			student.setName("Robert James");
			student.setRollNumber("r"+j);
			student.setStudentId(""+j);
			try {
				ObjectMapper mapper = new ObjectMapper();

				String studentAsJson = mapper.writeValueAsString(student);
				
				// Send a message
				MessageCreator messageCreator = new MessageCreator() {

					public Message createMessage(Session session) throws JMSException {
						return session.createObjectMessage(student);
					}
				};

				jmsTemplate.send(targetTopic, messageCreator);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return student;
	}

	@GetMapping("/sendTopic/{topic}/{value}/{times}")
	public String sendToTopic(@PathVariable String topic, @PathVariable String value, @PathVariable String times) {
		Topic targetTopic = getTopic(topic);
		if (targetTopic == null) {
            return null;
        }
		Integer i = 1;
		if (times != null) {
			i = Integer.valueOf(times);
		} 
		try {
			for (int j = 0; j < i; j++) {
            	jmsTemplate.convertAndSend(targetTopic, value);
			}
        } catch (Exception e) {
            e.printStackTrace();
        }
		return value;
	}

	@GetMapping("/send/{queue}/{value}/{times}")
	public String sendMessage(@PathVariable String queue, @PathVariable String value, @PathVariable String times) {
		Queue targetQueue = getQueue(queue);
        if (targetQueue == null) {
            return null;
        }
		Integer i = 1;
		if (times != null) {
			i = Integer.valueOf(times);
		} 
        try {
			for (int j = 0; j < i; j++) {
            	jmsTemplate.convertAndSend(targetQueue, value);
			}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
	}

	@GetMapping("/sendBinary/{queue}/{times}")
	public Student sendBinary(@PathVariable String queue, @PathVariable String times) {
		Queue targetQueue = getQueue(queue);
		if (targetQueue == null) {
			return null;
		}

		Integer i = 1;
		if (times != null) {
			i = Integer.valueOf(times);
		} 

		Student student = new Student();
		for (int j = 0; j < i; j++) {

			
			student.setName("Robert James");
			student.setRollNumber("r"+j);
			student.setStudentId(""+j);
			try {
				ObjectMapper mapper = new ObjectMapper();

				String studentAsJson = mapper.writeValueAsString(student);
				
				// Send a message
				MessageCreator messageCreator = new MessageCreator() {

					public Message createMessage(Session session) throws JMSException {
						return session.createObjectMessage(student);
					}
				};

				jmsTemplate.send(targetQueue, messageCreator);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return student;
	}

	private Queue getQueue(String value) {
		if (value.equals("testQueue")) {
            return testQueue;
        } else if (value.equals("simpleQueue")) {
            return simpleQueue;
        } else if (value.equals("binaryQueue")) {
            return binaryQueue;
        } 
        return null;
    }

	private Topic getTopic(String value) {
		if (value.equals("binaryTopic")) {
            return binaryTopic;
        } else if (value.equals("simpleTopic")) {
            return simpleTopic;
        }
        return null;
    }
	  
	
}
