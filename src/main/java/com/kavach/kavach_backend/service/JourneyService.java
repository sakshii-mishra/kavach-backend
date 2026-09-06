package com.kavach.kavach_backend.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kavach.kavach_backend.model.Journey;
import com.kavach.kavach_backend.repository.JourneyRepository;

@Service
public class JourneyService {

    @Autowired
    private JourneyRepository journeyRepository;

    public Journey startJourney(String userPhone, String startLocation, String destination, LocalDateTime expectedArrivalTime) {
        Journey journey = new Journey(userPhone, startLocation, destination, LocalDateTime.now(), expectedArrivalTime);
        return journeyRepository.save(journey);
    }

    public Journey updateLocation(String userPhone, String currentLocation) {
        Optional<Journey> ongoing = journeyRepository.findTopByUserPhoneAndStatusOrderByIdDesc(userPhone, "ONGOING");

        if (ongoing.isEmpty()) {
            return null;
        }

        Journey journey = ongoing.get();
        journey.setCurrentLocation(currentLocation);
        return journeyRepository.save(journey);
    }

    public Journey endJourney(String userPhone) {
        Optional<Journey> ongoing = journeyRepository.findTopByUserPhoneAndStatusOrderByIdDesc(userPhone, "ONGOING");

        if (ongoing.isEmpty()) {
            return null;
        }

        Journey journey = ongoing.get();
        journey.setStatus("COMPLETED");
        journey.setEndTime(LocalDateTime.now());
        return journeyRepository.save(journey);
    }

    public Journey getJourneyStatus(String userPhone) {
        Optional<Journey> ongoing = journeyRepository.findTopByUserPhoneAndStatusOrderByIdDesc(userPhone, "ONGOING");
        return ongoing.orElse(null);
    }
}