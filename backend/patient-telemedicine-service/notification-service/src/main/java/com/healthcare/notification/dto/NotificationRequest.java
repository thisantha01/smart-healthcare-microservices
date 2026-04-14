package com.healthcare.notification.dto;

import lombok.Data;

/**
 * Data Transfer Object (DTO) for handling notification requests.
 * Used to transfer notification data such as contact details and the message content
 * from incoming API requests.
 */
@Data
public class NotificationRequest {
    /**
     * The recipient's email address.
     */
    private String email;

    /**
     * The recipient's phone number.
     */
    private String phone;

    /**
     * The text content of the notification message.
     */
    private String message;
}
