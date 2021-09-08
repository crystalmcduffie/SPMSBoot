package com.SPMS.services;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DraftServiceTest {
	
	private static Logger log = Logger.getLogger(DraftServiceTest.class);
	
	@Autowired
	DraftService draftService;
/*	public Draft getDraft(Integer d);
	public void submitDraft(Author a, Draft d, Pitch p);
	public void updateDraft(Draft d);
	public void approveDraft(Editor e, Draft d);
	public Set<Draft> getAll();
	public void assignDraft(Editor e);
	public List<Draft> listDraftsInOrder(Set<Draft> drafts);*/



}
