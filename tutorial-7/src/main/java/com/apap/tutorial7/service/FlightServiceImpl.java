package com.apap.tutorial7.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tutorial7.model.FlightModel;
import com.apap.tutorial7.repository.FlightDb;

@Service
@Transactional
public class FlightServiceImpl implements FlightService {
	@Autowired
	private FlightDb flightDb;

	@Override
	public FlightModel addFlight(FlightModel flight) {
		// TODO Auto-generated method stub
		return flightDb.save(flight);
	}

	@Override
	public FlightModel getFlightDetailByLicenseNumber(String pilot_licenseNumber) {
		// TODO Auto-generated method stub
		return flightDb.findByPilotLicenseNumber(pilot_licenseNumber);
	}

	@Override
	public List<FlightModel> getFlightList() {
		// TODO Auto-generated method stub
		return flightDb.findAll();
	}

	@Override
	public void deleteFlight(FlightModel flight) {
		// TODO Auto-generated method stub
		flightDb.delete(flight);
	}

	@Override
	public FlightModel getFlight(Long id) {
		// TODO Auto-generated method stub
		return flightDb.findFlightById(id);
	}

	@Override
	public void updateFlight(FlightModel flight, Long id) {
		// TODO Auto-generated method stub
		FlightModel flightToUpdate = flightDb.findFlightById(id);
		flightToUpdate.setFlightNumber(flight.getFlightNumber());
		flightToUpdate.setOrigin(flight.getOrigin());
		flightToUpdate.setDestination(flight.getDestination());
		flightToUpdate.setTime(flight.getTime());
		flightDb.save(flightToUpdate);
		
	}

	@Override
	public FlightModel findFilghtByFlightNumber(String flightNumber) {
		// TODO Auto-generated method stub
		return flightDb.findFlightByFlightNumber(flightNumber);
	}

	@Override
	public FlightModel getFlightDetailById(Long id) {
		// TODO Auto-generated method stub
		return flightDb.findFlightById(id);
	}

}
