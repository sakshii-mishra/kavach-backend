package com.kavach.kavach_backend.service;

import com.kavach.kavach_backend.model.OtpVerification;
import com.kavach.kavach_backend.repository.OtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class OtpService {

    @Autowired
    private OtpRepository otpRepository;

    public String generateOtp(String phone) {
        String otp = String.valueOf(100000 + new Random().nextInt(900000)); // 6-digit OTP
        LocalDateTime expiry = LocalDateTime.now().plusMinutes(5);

        OtpVerification otpEntry = new OtpVerification(phone, otp, expiry);
        otpRepository.save(otpEntry);

        // Mock SMS — real gateway ke bajaye console pe print (demo ke liye)
        System.out.println("OTP for " + phone + " is: " + otp);

        return otp; // Demo purpose — response mein bhi bhej rahe hain
    }

    public boolean verifyOtp(String phone, String enteredOtp) {
        Optional<OtpVerification> latestOtp = otpRepository.findTopByPhoneOrderByIdDesc(phone);

        if (latestOtp.isEmpty()) return false;

        OtpVerification otpEntry = latestOtp.get();

        if (otpEntry.isVerified()) return false; // already used
        if (otpEntry.getExpiryTime().isBefore(LocalDateTime.now())) return false; // expired
        if (!otpEntry.getOtp().equals(enteredOtp)) return false; // mismatch

        otpEntry.setVerified(true);
        otpRepository.save(otpEntry);
        return true;
    }
}