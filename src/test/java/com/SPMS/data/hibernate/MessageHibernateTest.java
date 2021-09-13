package com.SPMS.data.hibernate;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.SPMS.beans.Message;
import com.SPMS.beans.Person;
import com.SPMS.services.PersonService;

@SpringBootTest
public class MessageHibernateTest {
	
	private static Logger log = Logger.getLogger(MessageHibernateTest.class);
	
	@Autowired
	PersonService personService;
	@Autowired
	MessageHibernate messageDAO;

	@Test
	public void getMessages() {
		Person p = personService.getByUsername("gabriel");
		log.debug(p.getUsername() + " " + p.getPassword() );
		List<Message> receivedMessages = messageDAO.getReceivedMessages(p.getId());
		List<Message> sentMessages = messageDAO.getSentMessages(p.getId());
		printMessages(sentMessages);
		printMessages(receivedMessages);
	}


	public static void printMessages(List<Message> messages) {
		for(Message m : messages) {
			log.info("Sender: " + m.getSender().getUsername());
			log.info("Receiver: " + m.getReceiver().getUsername());
			log.info("Title: " + m.getTitle());
			log.info("Message: " + m.getMessage());
		}
	}

}
