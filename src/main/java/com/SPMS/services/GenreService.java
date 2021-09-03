package com.SPMS.services;

import java.util.Set;

import com.SPMS.beans.Draft;
import com.SPMS.beans.Editor;
import com.SPMS.beans.Genre;
import com.SPMS.beans.GenreCommittee;

public interface GenreService {
	public Set<GenreCommittee> getGenreCommittee(Genre g);
	public Set<Set<GenreCommittee>> getAllGenreCommittees();
	public Genre getGenre (String name);
	public Set<Genre> getAllGenres();
	public Set<Draft> getDraftsInGenre(Editor e);
}
