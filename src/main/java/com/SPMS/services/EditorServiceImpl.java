package com.SPMS.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SPMS.beans.Editor;
import com.SPMS.data.EditorDAO;
import com.SPMS.exceptions.IncorrectTypeException;

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
	public List<Editor> getAll() {
		return editorDAO.findAll();
	}

	@Override
	public Editor create(Editor e) throws IncorrectTypeException {
		if(e.getRole().getName().equals("editor")==false) {
			IncorrectTypeException IncorrectTypeException = new IncorrectTypeException();
			throw IncorrectTypeException;
		}
		return editorDAO.save(e);
	}

	@Override
	public Editor update(Editor e) {
		return editorDAO.save(e);
	}
	
}
