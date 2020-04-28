package com.exam.apiAdtomic.dao;

import java.util.List;

import com.exam.apiAdtomic.entity.model.User;

public interface UserDAO {
	
	public List<User> findAll();
	
	public User findById(int id);
	
	public void save(User user);
	
	public void deleteById(int id);
}