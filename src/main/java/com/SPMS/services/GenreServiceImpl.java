package com.SPMS.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.SPMS.beans.Draft;
import com.SPMS.beans.Genre;
import com.SPMS.beans.GenreCommittee;
import com.SPMS.beans.Person;
import com.SPMS.data.DraftDAO;
import com.SPMS.data.GenreDAO;
import com.SPMS.data.hibernate.GenreHibernate;

@Service
public class GenreServiceImpl implements GenreService {

	@Override
	public List<GenreCommittee> getGenreCommitteesByGenre(Genre g) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GenreCommittee> getGenreCommitteesByEditor(Person editor) {
		// TODO Auto-generated method stub
		return null;
	}

}
