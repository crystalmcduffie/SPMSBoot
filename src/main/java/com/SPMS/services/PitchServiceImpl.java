package com.SPMS.services;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SPMS.beans.Person;
import com.SPMS.beans.Pitch;
import com.SPMS.data.PitchDAO;


@Service
public class PitchServiceImpl implements PitchService{
	
	@Autowired
	PitchDAO pitchDAO;

	@Override
	public Pitch getPitch(Integer id) {
		return pitchDAO.getOne(id);
	}

	@Override
	public Pitch createPitch(Person author, Pitch p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String calculatePriority(Pitch p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void approvePitch(Pitch p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void assignPitch(Pitch p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePitch(Pitch p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelPitch(Pitch p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rejectPitch(Pitch p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Pitch> listPitchesInOrder(Set<Pitch> pitches) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pitch> getAuthorPitches(Person author) {
		return pitchDAO.findByAuthorId(author.getId());
	}

}
