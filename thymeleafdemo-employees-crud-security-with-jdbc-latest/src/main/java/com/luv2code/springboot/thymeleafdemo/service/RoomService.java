package com.luv2code.springboot.thymeleafdemo.service;

import java.util.List;

import com.luv2code.springboot.thymeleafdemo.entity.Room;


public interface RoomService {
	
	public List<Room> findAll();
	
	public Room findById(int theId);
	
	public void save(Room theRoom);
	
	public void deleteById(int theId);

}
