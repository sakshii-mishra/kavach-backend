package com.kavach.kavach_backend.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kavach.kavach_backend.model.Journey;
import com.kavach.kavach_backend.service.JourneyService;

@RestController
@RequestMapping("/api/journey")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
public class JourneyController {

    @Autowired
    private JourneyService journeyService;

    @PostMapping("/start")
    public Map<String, Object> startJourney(@RequestBody Map<String, String> request) {
        String userPhone = request.get("userPhone");
        String startLocation = request.get("startLocation");
        String destination = request.get("destination");
        String expectedArrivalStr = request.get("expectedArrivalTime"); // format: "2026-07-26T15:30:00"

        LocalDateTime expectedArrival = LocalDateTime.parse(expectedArrivalStr);

        Journey journey = journeyService.startJourney(userPhone, startLocation, destination, expectedArrival);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Journey started successfully");
        response.put("journey", journey);
        return response;
    }

    @PutMapping("/update-location")
    public Map<String, Object> updateLocation(@RequestBody Map<String, String> request) {
        String userPhone = request.get("userPhone");
        String currentLocation = request.get("currentLocation");

        Journey journey = journeyService.updateLocation(userPhone, currentLocation);

        Map<String, Object> response = new HashMap<>();
        if (journey == null) {
            response.put("success", false);
            response.put("message", "No active journey found");
        } else {
            response.put("success", true);
            response.put("message", "Location updated");
            response.put("journey", journey);
        }
        return response;
    }

    @PostMapping("/end")
    public Map<String, Object> endJourney(@RequestBody Map<String, String> request) {
        String userPhone = request.get("userPhone");

        Journey journey = journeyService.endJourney(userPhone);

        Map<String, Object> response = new HashMap<>();
        if (journey == null) {
            response.put("success", false);
            response.put("message", "No active journey found");
        } else {
            response.put("success", true);
            response.put("message", "Journey ended successfully");
            response.put("journey", journey);
        }
        return response;
    }

    @GetMapping("/status/{userPhone}")
    public Map<String, Object> getJourneyStatus(@PathVariable String userPhone) {
        Journey journey = journeyService.getJourneyStatus(userPhone);

        Map<String, Object> response = new HashMap<>();
        if (journey == null) {
            response.put("success", false);
            response.put("message", "No active journey found");
        } else {
            response.put("success", true);
            response.put("journey", journey);
        }
        return response;
    }
}
