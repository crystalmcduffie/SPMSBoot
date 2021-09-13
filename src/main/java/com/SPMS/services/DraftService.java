package com.SPMS.services;

import java.util.List;
import java.util.Set;

import com.SPMS.beans.Draft;
import com.SPMS.beans.Person;
import com.SPMS.beans.Pitch;


public interface DraftService{
	public Draft getDraft(Integer d);
	public void submitDraft(Person author, Draft d, Pitch p);
	public void updateDraft(Draft d);
	public void approveDraft(Person editor, Draft d);
	public Set<Draft> getAll();
	public void assignDraft(Person editor);
	public List<Draft> listDraftsInOrder(Set<Draft> drafts);

}
