package com.apap.tutorial7.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.apap.tutorial7.model.FlightModel;
import com.apap.tutorial7.model.PilotModel;
import com.apap.tutorial7.rest.PilotDetail;
import com.apap.tutorial7.rest.Setting;
import com.apap.tutorial7.service.FlightService;
import com.apap.tutorial7.service.PilotService;

@RestController
@RequestMapping("/pilot")
public class PilotController {
	@Autowired
	private PilotService pilotService;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Bean
	public RestTemplate rest() {
		return new RestTemplate();
	}
	
	@GetMapping(value = "/status/{licenseNumber}")
	public String getStatus(@PathVariable("licenseNumber") String licenseNumber) throws Exception {
		String path = Setting.pilotUrl + "/pilot?licenseNumber=" + licenseNumber;
		return restTemplate.getForEntity(path, String.class).getBody();
	}
	
	@GetMapping(value = "/full/{licenseNumber}")
	public PilotDetail postStatus (@PathVariable("licenseNumber") String licenseNumber) throws Exception{
		String path = Setting.pilotUrl + "/pilot";
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		PilotDetail detail = restTemplate.postForObject(path, pilot, PilotDetail.class);
		return detail;
	}
	
	/*
	@Autowired
	private FlightService flightService;
	**/
	
	@PostMapping(value = "/add")
	public PilotModel addPilotSubmit(@RequestBody PilotModel pilot) {
		return pilotService.addPilot(pilot);
	}
	
	@GetMapping(value = "/view/{licenseNumber}")
	public PilotModel pilotView(@PathVariable("licenseNumber") String licenseNumber) {
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		return pilot;
	}
	
	@DeleteMapping(value="/delete")
	public String deletePilot(@RequestParam("pilotId") long id) {
		PilotModel pilot = pilotService.getPilotDetailById(id);
		pilotService.deletePilot(pilot);
		return "deletePilot";
	}
	
	@PutMapping(value = "/update/{id}")
	public String updatePilotSubmit(@PathVariable("pilotId") long id,
			@RequestParam("name") String name,
			@RequestParam("flyHour") int flyHour) {
		PilotModel pilot = pilotService.getPilotDetailById(id);
		if (pilot.equals(null)) {
			return "error";
		}
		pilot.setName(name);
		pilot.setFlyHour(flyHour);
		pilotService.updatePilot(pilot, id);
		return "pilotUpdated";
	}
	
	
	/**
	@RequestMapping("/")
	private String home(Model model) {
		return "home";
	}
	
	@RequestMapping(value = "/pilot/add", method = RequestMethod.GET)
	private String add (Model model) {
		model.addAttribute("pilot", new PilotModel());
		
		return "addPilot";
	}
	
	@RequestMapping(value = "/pilot/add", method = RequestMethod.POST)
	private String addPilotSubmit(@ModelAttribute PilotModel pilot) {
		pilotService.addPilot(pilot);
		return "add";
	}
	
	/*
	@RequestMapping(value = "/pilot/view", method = RequestMethod.GET)
	private String view(@RequestParam String licenseNumber, Model model) {
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		model.addAttribute("pilot", pilot);
		return "view-pilot";
	}
	
	@RequestMapping(value = "/pilot/view", method = RequestMethod.GET)
	private String view(@RequestParam String licenseNumber, Model model) {
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		model.addAttribute("pilot", pilot);
		
		List <FlightModel> archiveToGo = new ArrayList();
		List <FlightModel> archive = flightService.getFlightList();
		int i =0;
		for (FlightModel search : archive) {
			if (search.getPilot().getLicenseNumber().equalsIgnoreCase(licenseNumber)) {
				archiveToGo.add(search);
				i++;
				
			}
			
		}
		
		//FlightModel flight = flightService.getFlightDetailByLicenseNumber(licenseNumber);
		if (pilot == null) {
			return "error";
		}
		else if(pilot != null && archive.isEmpty()){
			return "emptyFlight";
		}
		model.addAttribute("flight_list", archiveToGo);
		return "view-pilot";
	}
	
	@RequestMapping(value = "/pilot/delete/{id}", method = RequestMethod.GET)
	private String deletePilot(@PathVariable(value = "id") Long id, Model model) {
		pilotService.deletePilot(id);
		return "deletePilot";
	}
	
	@RequestMapping(value = "/pilot/update/{licenseNumber}", method = RequestMethod.GET)
	private String updatePilot(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		model.addAttribute("currPilot", pilot);
		return "updatePilot";
	}
	
	@RequestMapping(value = "pilot/update", method = RequestMethod.POST)
	private String updatePilotSubmit(@ModelAttribute PilotModel pilot) {
		pilotService.updatePilot(pilot, pilot.getLicenseNumber());
		return "pilotUpdated";
	}
	*/
}
