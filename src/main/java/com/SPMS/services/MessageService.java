package com.SPMS.services;

import java.util.List;
import java.util.Set;

import com.SPMS.beans.Message;

public interface MessageService {
	public void sendMessage(Message m);
	public void updateMessage(Message m);
}
