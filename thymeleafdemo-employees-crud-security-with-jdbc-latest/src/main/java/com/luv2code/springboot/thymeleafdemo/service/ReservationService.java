package com.luv2code.springboot.thymeleafdemo.service;

import java.util.List;

import com.luv2code.springboot.thymeleafdemo.entity.Reservation;

public interface ReservationService {
	
	public List<Reservation> findAll();
	
	public Reservation findById(int theId);
	
	public List<Reservation> findByUsername(String theUsername);
	
	public void save(Reservation theReservation);
	
	public void deleteById(int theId);

}
