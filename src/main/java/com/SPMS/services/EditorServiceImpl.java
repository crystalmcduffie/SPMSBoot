package com.SPMS.services;

import java.util.Set;

import com.SPMS.beans.Editor;
import com.SPMS.data.DAOFactory;
import com.SPMS.data.EditorDAO;
import com.SPMS.data.hibernate.EditorHibernate;

public class EditorServiceImpl implements EditorService {
	private EditorDAO editorDAO;
	
	public EditorServiceImpl() {
		editorDAO  = DAOFactory.getEditorDAO();
	}
	
	public Editor getByUsername(String username) {
		return editorDAO.getByUsername(username);
	}

	@Override
	public void update(Editor ed) {
		editorDAO.update(ed);
		
	}

	@Override
	public Set<Editor> getAll() {
		return editorDAO.getAll();
	}
	
}
