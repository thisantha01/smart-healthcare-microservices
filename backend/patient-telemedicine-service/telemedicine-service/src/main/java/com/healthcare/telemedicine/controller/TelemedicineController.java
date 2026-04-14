package com.healthcare.telemedicine.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * REST Controller for managing telemedicine operations.
 * It provides endpoints to generate and retrieve Jitsi meet URLs for patient-doctor appointments.
 */
@RestController
@RequestMapping("/api/telemedicine")
@CrossOrigin(origins = "*") // Allow frontend access
public class TelemedicineController {

    // Base URL for generating unique Jitsi meeting rooms
    private static final String JITSI_BASE_URL = "https://meet.jit.si/SLIIT-Health-";

    /**
     * Endpoint to retrieve a Jitsi meeting link for a specific appointment.
     * 
     * @param appointmentId The unique identifier of the appointment, passed as a path variable.
     * @return A ResponseEntity containing a map with the appointment ID and the generated Jitsi meeting URL.
     */
    @GetMapping("/jitsi/{appointmentId}")
    public ResponseEntity<Map<String, String>> getJitsiLink(@PathVariable String appointmentId) {
        // Construct the full Jitsi meeting URL by appending the appointment ID
        String meetUrl = JITSI_BASE_URL + appointmentId;
        
        // Prepare the response payload dictionary
        Map<String, String> response = new HashMap<>();
        response.put("appointmentId", appointmentId);
        response.put("jitsiUrl", meetUrl);
        
        // Return the response map with an HTTP 200 OK status
        return ResponseEntity.ok(response);
    }
}
