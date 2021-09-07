package com.SPMS.services;

import java.util.List;
import java.util.Set;

import com.SPMS.beans.Editor;

public interface EditorService {
	public Editor getByUsername(String username);
	public void update(Editor ed);
	public List<Editor> getAll();
}
