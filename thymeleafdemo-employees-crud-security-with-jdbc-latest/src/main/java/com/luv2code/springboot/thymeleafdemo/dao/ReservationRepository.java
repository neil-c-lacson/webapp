package com.luv2code.springboot.thymeleafdemo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.luv2code.springboot.thymeleafdemo.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer>{
	
	public List<Reservation> findByUsernameAllIgnoreCase(String username);
	
	@Query(value = "SELECT * FROM reservation \r\n"
			+ "WHERE room_number=?1 AND username=?2",
			nativeQuery = true)
	public List<Reservation> findByUserAndRoom(int num, String user);

}
