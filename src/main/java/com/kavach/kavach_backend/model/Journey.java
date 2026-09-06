package com.kavach.kavach_backend.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Journey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userPhone;
    private String startLocation;
    private String destination;
    private String currentLocation;
    private LocalDateTime startTime;
    private LocalDateTime expectedArrivalTime;
    private LocalDateTime endTime;
    private String status; // "ONGOING", "COMPLETED", "SOS_TRIGGERED"

    public Journey() {}

    public Journey(String userPhone, String startLocation, String destination,
                    LocalDateTime startTime, LocalDateTime expectedArrivalTime) {
        this.userPhone = userPhone;
        this.startLocation = startLocation;
        this.destination = destination;
        this.currentLocation = startLocation;
        this.startTime = startTime;
        this.expectedArrivalTime = expectedArrivalTime;
        this.status = "ONGOING";
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUserPhone() { return userPhone; }
    public void setUserPhone(String userPhone) { this.userPhone = userPhone; }

    public String getStartLocation() { return startLocation; }
    public void setStartLocation(String startLocation) { this.startLocation = startLocation; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public String getCurrentLocation() { return currentLocation; }
    public void setCurrentLocation(String currentLocation) { this.currentLocation = currentLocation; }

    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }

    public LocalDateTime getExpectedArrivalTime() { return expectedArrivalTime; }
    public void setExpectedArrivalTime(LocalDateTime expectedArrivalTime) { this.expectedArrivalTime = expectedArrivalTime; }

    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}