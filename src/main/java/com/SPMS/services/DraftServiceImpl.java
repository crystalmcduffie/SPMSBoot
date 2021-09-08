package com.SPMS.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.SPMS.beans.Author;
import com.SPMS.beans.AuthorDraft;
import com.SPMS.beans.Draft;
import com.SPMS.beans.DraftLog;
import com.SPMS.beans.DraftStage;
import com.SPMS.beans.Editor;
import com.SPMS.beans.GenreCommittee;
import com.SPMS.beans.Pitch;
import com.SPMS.beans.PitchStage;
import com.SPMS.beans.StoryType;
import com.SPMS.data.AuthorDAO;
import com.SPMS.data.DAOFactory;
import com.SPMS.data.DraftDAO;
import com.SPMS.data.GenreDAO;
import com.SPMS.data.PitchDAO;
import com.SPMS.data.hibernate.DraftHibernate;

@Service
public class DraftServiceImpl implements DraftService{

}
