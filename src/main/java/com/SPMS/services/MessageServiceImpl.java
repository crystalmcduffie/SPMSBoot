package com.SPMS.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.SPMS.beans.Author;
import com.SPMS.beans.AuthorDraft;
import com.SPMS.beans.AuthorPitch;
import com.SPMS.beans.DChangeA2E;
import com.SPMS.beans.DChangeE2A;
import com.SPMS.beans.DMessageA2E;
import com.SPMS.beans.DMessageE2A;
import com.SPMS.beans.Draft;
import com.SPMS.beans.DraftChange;
import com.SPMS.beans.DraftMessage;
import com.SPMS.beans.Editor;
import com.SPMS.beans.EditorPitch;
import com.SPMS.beans.PMessageA2E;
import com.SPMS.beans.PMessageE2A;
import com.SPMS.beans.PMessageE2E;
import com.SPMS.beans.PReviewA2E;
import com.SPMS.beans.PReviewE2A;
import com.SPMS.beans.PReviewE2E;
import com.SPMS.beans.Pitch;
import com.SPMS.beans.PitchMessage;
import com.SPMS.beans.PitchReview;
import com.SPMS.data.AuthorDAO;
import com.SPMS.data.DAOFactory;
import com.SPMS.data.DraftDAO;
import com.SPMS.data.EditorDAO;
import com.SPMS.data.MessageDAO;
import com.SPMS.data.PitchDAO;
import com.SPMS.data.hibernate.AuthorHibernate;
import com.SPMS.data.hibernate.EditorHibernate;
import com.SPMS.data.hibernate.MessageHibernate;
import com.SPMS.data.hibernate.PitchHibernate;

@Service
public class MessageServiceImpl implements MessageService{
	
}
