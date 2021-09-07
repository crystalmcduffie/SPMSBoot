package com.SPMS.data;

import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.SPMS.beans.Message;

@Repository
public interface MessageDAO extends JpaRepository<Message, Integer>{

	//List<Message> getSentMessages(Integer id);

	//List<Message> getReceivedMessages(Integer id);

}
