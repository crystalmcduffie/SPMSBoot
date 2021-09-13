package com.SPMS.services;

import java.util.List;
import java.util.Set;

import com.SPMS.beans.Person;
import com.SPMS.beans.Pitch;

public interface PitchService{
//CRUD
	//If CreatePitch returns "insufficient points", the pitch
	//was created and placed on hold.
	//If it returns "pitch created" then the pitch process
	//has started
	//If it returns "error" then something went wrong in the DB
	public Pitch getPitch(Integer id);
	public Pitch createPitch(Person author, Pitch p);
	public String calculatePriority(Pitch p);
	public void approvePitch(Pitch p);
	//general editor cannot be the same as the assistant editor
	public void assignPitch(Pitch p);
	public void updatePitch(Pitch p);
	public void cancelPitch(Pitch p);
	public void rejectPitch(Pitch p);
	public List<Pitch> listPitchesInOrder(Set<Pitch> pitches);
	public Set<Pitch> getAuthorPitches(Person author);
}
