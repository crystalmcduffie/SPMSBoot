package com.SPMS.services;

import java.util.List;
import java.util.Set;

import com.SPMS.beans.Draft;
import com.SPMS.beans.Editor;
import com.SPMS.beans.Genre;
import com.SPMS.beans.GenreCommittee;

public interface GenreService {
	public List<GenreCommittee> getGenreCommitteesByGenre(Genre g);
	public List<GenreCommittee> getGenreCommitteesByEditor(Editor e);
}
