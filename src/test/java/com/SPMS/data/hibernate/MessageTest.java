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

import com.SPMS.beans.Draft;
import com.SPMS.beans.Editor;
import com.SPMS.beans.Message;
import com.SPMS.beans.Person;
import com.SPMS.beans.Pitch;
import com.SPMS.data.EditorDAO;
import com.SPMS.data.MessageDAO;
import com.SPMS.data.hibernate.EditorHibernate;
import com.SPMS.services.EditorService;
import com.SPMS.services.EditorServiceImpl;
import com.SPMS.services.PersonService;

@SpringBootTest
public class MessageTest {
	//private static EditorService editorServ;
	
	private Logger log = Logger.getLogger(MessageTest.class);
	
	@Autowired
	EditorService editorService;
	@Autowired
	PersonService personService;
	@Autowired
	MessageHibernate messageDAO;

	
	
	@Test
	public void getMessages() {
		Person p = personService.getByUsername("gabriel");
		System.out.println(p.getUsername() + " " + p.getPassword() );
		List<Message> receivedMessages = messageDAO.getReceivedMessages(p.getId());
		List<Message> sentMessages = messageDAO.getSentMessages(p.getId());
		System.out.println(receivedMessages);
		System.out.println(sentMessages);
	}




}
