package com.apap.tutorial7.service;

import java.util.List;
import java.util.Optional;

import com.apap.tutorial7.model.FlightModel;

public interface FlightService {
	FlightModel getFlightDetailByLicenseNumber (String licenseNumber);
	FlightModel getFlight(Long id);
	FlightModel findFilghtByFlightNumber(String flightNumber);
	List<FlightModel> getFlightList();
	void deleteFlight(FlightModel flight);
	FlightModel addFlight(FlightModel flight);
	void updateFlight(FlightModel flight, Long id);
	FlightModel getFlightDetailById(Long id);
}
