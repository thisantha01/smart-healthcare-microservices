package com.healthcare.notification.controller;

import  com.healthcare.notification.dto.NotificationRequest;
import com.healthcare.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notify")
@CrossOrigin(origins = "*") // Allow frontend access
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/send")
    public ResponseEntity<String> sendNotification(@RequestBody NotificationRequest request) {
        try {
            notificationService.processNotification(request);
            return ResponseEntity.ok("Notification dispatched successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Failed to send notification: " + e.getMessage());
        }
    }
}
