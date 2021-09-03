package com.SPMS.data.hibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.SPMS.beans.Genre;
import com.SPMS.beans.GenreCommittee;
import com.SPMS.data.GenreDAO;
import com.SPMS.utils.HibernateUtil;

public class GenreHibernate implements GenreDAO{
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	@Override
	public Set<GenreCommittee> getGenreCommittee(Genre g) {
		Session s = hu.getSession();
		String query = "FROM GenreCommittee where genre.name = :genre";
		Query<GenreCommittee> q = s.createQuery(query, GenreCommittee.class);
		q.setParameter("genre", g.getName());
		List<GenreCommittee> genreCommitteeList = q.getResultList();
		Set<GenreCommittee> genreCommittee = new HashSet<>();
		genreCommittee.addAll(genreCommitteeList);
		s.close();
		return genreCommittee;
	}


	@Override
	public Set<Set<GenreCommittee>> getAllGenreCommittees() {
		Set<Genre> allGenres= getAllGenres();
		Set<Set<GenreCommittee>> allGC = new HashSet<>();
		for(Genre g : allGenres) {
			Set<GenreCommittee> gc = getGenreCommittee(g);
			allGC.add(gc);
		}
		return allGC;
	}

	@Override
	public Genre getGenre(String name) {
		Session s = hu.getSession();
		String query = "FROM Genre where name = :name";
		Query<Genre> q = s.createQuery(query, Genre.class);
		q.setParameter("name", name);
		Genre g = q.getSingleResult();
		s.close();
		return g;
	}

	@Override
	public Set<Genre> getAllGenres() {
		Session s = hu.getSession();
		String query = "FROM Genre";
		Query<Genre> q = s.createQuery(query, Genre.class);
		List<Genre> genresList = q.getResultList();
		Set<Genre> genres = new HashSet<>();
		genres.addAll(genresList);
		s.close();
		return genres;
	}

}
