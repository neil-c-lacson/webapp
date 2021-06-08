package com.luv2code.springboot.thymeleafdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luv2code.springboot.thymeleafdemo.dao.ReservationRepository;
import com.luv2code.springboot.thymeleafdemo.entity.Reservation;

@Service
public class ReservationServiceImpl implements ReservationService{
	
	private ReservationRepository reservationRepository;
	
	@Autowired
	public ReservationServiceImpl(ReservationRepository theReservationRepository) {
		reservationRepository = theReservationRepository;
	}

	@Override
	public List<Reservation> findAll() {
		return reservationRepository.findAll();
	}

	@Override
	public Reservation findById(int theId) {
		Optional<Reservation> result = reservationRepository.findById(theId);
		
		Reservation theReservation = null;
		
		if (result.isPresent()) {
			theReservation = result.get();
		}
		else {
			// we didn't find the employee
			throw new RuntimeException("Did not find reservation id - " + theId);
		}
		
		return theReservation;
	}

	@Override
	public List<Reservation> findByUsername(String theUsername) {
		
		List<Reservation> results = null;
		
		if (theUsername != null && (theUsername.trim().length() > 0)) {
			results = reservationRepository.findByUsernameAllIgnoreCase(theUsername);
		}
		else {
			throw new RuntimeException("No reservations for" + theUsername);
		}
		
		return results;
	}

	@Override
	public void save(Reservation theReservation) {
		reservationRepository.save(theReservation);
		
	}

	@Override
	public void deleteById(int theId) {
		reservationRepository.deleteById(theId);
		
	}
	
	

}
