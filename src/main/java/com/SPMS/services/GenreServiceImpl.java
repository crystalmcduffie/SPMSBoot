package com.SPMS.services;

import java.util.HashSet;
import java.util.Set;

import com.SPMS.beans.Draft;
import com.SPMS.beans.Editor;
import com.SPMS.beans.Genre;
import com.SPMS.beans.GenreCommittee;
import com.SPMS.data.DAOFactory;
import com.SPMS.data.DraftDAO;
import com.SPMS.data.GenreDAO;
import com.SPMS.data.hibernate.GenreHibernate;

public class GenreServiceImpl implements GenreService {
	private GenreDAO genreDAO;
	private DraftDAO draftDAO;
	
	public GenreServiceImpl() {
		genreDAO = DAOFactory.getGenreDAO();
		draftDAO = DAOFactory.getDraftDAO();
	}
	
	@Override
	public Set<GenreCommittee> getGenreCommittee(Genre g) {
		return genreDAO.getGenreCommittee(g);
	}

	@Override
	public Set<Set<GenreCommittee>> getAllGenreCommittees() {
		// TODO Auto-generated method stub
		return genreDAO.getAllGenreCommittees();
	}

	@Override
	public Genre getGenre(String name) {
		return genreDAO.getGenre(name);
	}

	@Override
	public Set<Genre> getAllGenres() {
		return genreDAO.getAllGenres();
	}

	@Override
	public Set<Draft> getDraftsInGenre(Editor e) {
		Set<Set<GenreCommittee>> allGenres = getAllGenreCommittees();
		Set<Draft> allDrafts = draftDAO.getAll();
		Set<Draft> relevantDrafts = new HashSet<>();
		Set<GenreCommittee> editorGC = new HashSet<>();
		for(Set<GenreCommittee> SetGC : allGenres) {
			Set<GenreCommittee> GC = SetGC;
			for(GenreCommittee gc : GC) {
				if(gc.getEditor().getId() == e.getId()) {
					editorGC.add(gc);
				}
			}
		}
		
		for(Draft d : allDrafts) {
			for(GenreCommittee gc: editorGC) {
				if(d.getGenre().getId() == gc.getGenre().getId()) {
					if(d.getStage().getId()==1) {
						//1 is pending
						relevantDrafts.add(d);
					}
					
				}
			}
		}
		
		return relevantDrafts;
	}
	

}
