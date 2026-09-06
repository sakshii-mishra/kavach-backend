package com.kavach.kavach_backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kavach.kavach_backend.model.Journey;

public interface JourneyRepository extends JpaRepository<Journey, Long> {
    Optional<Journey> findTopByUserPhoneAndStatusOrderByIdDesc(String userPhone, String status);
}