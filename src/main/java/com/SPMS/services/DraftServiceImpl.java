package com.SPMS.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.SPMS.beans.Draft;
import com.SPMS.beans.Person;
import com.SPMS.beans.Pitch;



@Service
public class DraftServiceImpl implements DraftService{

	@Override
	public Draft getDraft(Integer d) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void submitDraft(Person author, Draft d, Pitch p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateDraft(Draft d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void approveDraft(Person editor, Draft d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<Draft> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void assignDraft(Person editor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Draft> listDraftsInOrder(Set<Draft> drafts) {
		// TODO Auto-generated method stub
		return null;
	}


}
