package com.SPMS.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SPMS.beans.Editor;
import com.SPMS.data.EditorDAO;

@Service
public class EditorServiceImpl implements EditorService {
	EditorDAO editorDAO;
	
	@Autowired
	public EditorServiceImpl(EditorDAO e) {
		this.editorDAO = e;
	}
	
	public Editor getByUsername(String username) {
		return editorDAO.getByUsername(username);
	}

	@Override
	public void update(Editor e) {
		editorDAO.save(e);
		
	}

	@Override
	public List<Editor> getAll() {
		return editorDAO.findAll();
	}
	
}
