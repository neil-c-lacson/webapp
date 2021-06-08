package com.luv2code.springboot.thymeleafdemo.controller;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springboot.thymeleafdemo.entity.Reservation;
import com.luv2code.springboot.thymeleafdemo.entity.Room;
import com.luv2code.springboot.thymeleafdemo.service.ReservationService;
import com.luv2code.springboot.thymeleafdemo.service.RoomService;

@Controller
@RequestMapping("/reservations")
public class ReservationController {
	
	private ReservationService reservationService;
	
	public ReservationController(ReservationService theReservationService) {
			reservationService = theReservationService;
	}
	
	@GetMapping("/listReservation")
	public String listReservation(Model theModel) {
		
		String username;
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		} else {
			username = principal.toString();
		}
		
		// get room from db
		List<Reservation> theReservations = reservationService.findByUsername(username);
		
		// add to the spring model
		theModel.addAttribute("reservations", theReservations);
		
		return "/reservations/list-reservations";
	}
	
	@GetMapping("/showFormForAddReservation")
	public String showFormForAddReservation(Model theModel) {
		
		// create model attribute to bind form data
		Reservation theReservation = new Reservation();
				
		theModel.addAttribute("reservation", theReservation);
		
		return "/reservations/reservation-form";
	}

	@GetMapping("/showFormForUpdateReservation")
	public String showFormForUpdateReservation(@RequestParam("reservationId") int theId,
									Model theModel) {
		
		// get the reservation from the service
		Reservation theReservation = reservationService.findById(theId);
		
		// set employee as a model attribute to pre-populate the form
		theModel.addAttribute("reservation", theReservation);
		
		// send over to our form
		return "/reservations/reservation-form";			
	}
	
	@PostMapping("/saveReservation")
	public String saveReservation(@ModelAttribute("reservation") Reservation theReservation) {
		
		// save the employee
		reservationService.save(theReservation);
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/reservations/listReservation";
	}
	
	@GetMapping("/deleteReservation")
	public String deleteRoom(@RequestParam("reservationId") int theId) {
		
		reservationService.deleteById(theId);
		
		// redirect to /employees/list
		return "redirect:/reservations/listReservation";
		
	}

}
