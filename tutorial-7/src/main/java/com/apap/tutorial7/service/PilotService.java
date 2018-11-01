package com.apap.tutorial7.service;

import com.apap.tutorial7.model.PilotModel;

public interface PilotService {
	PilotModel getPilotDetailByLicenseNumber (String licenseNumber);
	void deletePilot(PilotModel pilot);
	PilotModel addPilot(PilotModel pilot);
	void updatePilot(PilotModel pilot, Long id);
	PilotModel getPilotDetailById (Long id);
}
