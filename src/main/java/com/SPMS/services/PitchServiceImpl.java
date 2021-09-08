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

import org.springframework.stereotype.Service;

import com.SPMS.beans.Author;
import com.SPMS.beans.AuthorPitch;
import com.SPMS.beans.Draft;
import com.SPMS.beans.Editor;
import com.SPMS.beans.EditorPitch;
import com.SPMS.beans.Genre;
import com.SPMS.beans.GenreCommittee;
import com.SPMS.beans.Pitch;
import com.SPMS.beans.PitchLog;
import com.SPMS.beans.PitchStage;
import com.SPMS.beans.StoryType;
import com.SPMS.data.AuthorDAO;
import com.SPMS.data.DAOFactory;
import com.SPMS.data.EditorDAO;
import com.SPMS.data.GenreDAO;
import com.SPMS.data.PitchDAO;
import com.SPMS.data.hibernate.PitchHibernate;

import sun.jvm.hotspot.oops.java_lang_Class;

@Service
public class PitchServiceImpl implements PitchService{

}
