package com.SPMS.data;

import com.SPMS.data.hibernate.*;

public class DAOFactory {
	
	
	public static DraftDAO getDraftDAO() {
		return new DraftHibernate();
	}
	
	public static EditorDAO getEditorDAO() {
		return new EditorHibernate();
	}

	public static GenreDAO getGenreDAO() {
		return new GenreHibernate();
	}
	
	public static MessageDAO getMessageDAO() {
		return new MessageHibernate();
	}
	
	public static PitchDAO getPitchDAO() {
		return new PitchHibernate();
	}
}
