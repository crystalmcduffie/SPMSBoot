package com.SPMS.data;

import com.SPMS.beans.Editor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.SPMS.beans.Role;


@Repository
public interface RoleDAO extends JpaRepository<Role, Integer>{
	public Role findByName(String name);

}
