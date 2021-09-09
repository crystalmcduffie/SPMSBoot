package com.SPMS.services;

import java.util.List;
import java.util.Set;

import com.SPMS.beans.Editor;
import com.SPMS.exceptions.IncorrectTypeException;

public interface EditorService {
	public Editor create(Editor e) throws IncorrectTypeException;
	public Editor getByUsername(String username);
	public Editor update(Editor e);
	public List<Editor> getAll();
}
