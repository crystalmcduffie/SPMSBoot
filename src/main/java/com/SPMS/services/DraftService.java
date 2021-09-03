package com.SPMS.services;

import java.util.List;
import java.util.Set;

import com.SPMS.beans.Author;
import com.SPMS.beans.Draft;
import com.SPMS.beans.Editor;
import com.SPMS.beans.Pitch;


public interface DraftService{
	public Draft getDraft(Integer d);
	public void submitDraft(Author a, Draft d, Pitch p);
	public void updateDraft(Draft d);
	public void approveDraft(Editor e, Draft d);
	public Set<Draft> getAll();
	public Set<Draft> getAllPendingDrafts();
	public Set<Draft> getPendingDraftsForEditor(Editor e);
	public List<Draft> listDraftsInOrder(Set<Draft> drafts);

}
