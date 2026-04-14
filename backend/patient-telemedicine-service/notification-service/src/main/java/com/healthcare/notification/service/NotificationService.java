package com.healthcare.notification.service;

import com.healthcare.notification.dto.NotificationRequest;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${sms.mock.enabled:true}")
    private boolean smsMockEnabled;

    @Value("${twilio.account.sid}")
    private String twilioAccountSid;

    @Value("${twilio.auth.token}")
    private String twilioAuthToken;

    @Value("${twilio.phone.number}")
    private String twilioPhoneNumber;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @PostConstruct
    public void initTwilio() {
        // Only initialize if proper tokens are supplied to avoid startup crash with placeholders
        if (!twilioAccountSid.startsWith("AC_")) {
            Twilio.init(twilioAccountSid, twilioAuthToken);
        }
    }

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

//    public void sendSms(String toPhone, String text) {
//        if (!twilioAccountSid.startsWith("AC_")) { // Prevent real call if fake credentials
//            Message.creator(
//                    new PhoneNumber(toPhone),
//                    new PhoneNumber(twilioPhoneNumber),
//                    text
//            ).create();
//        } else {
//            System.out.println("Mock SMS sent to " + toPhone + ": " + text);
//        }
//    }

    //Mock sms service becouse Twilio not free
    private void sendSms(String toPhone, String message) {
        if (smsMockEnabled) {
            System.out.println("=== MOCK SMS ===");
            System.out.println("To: " + toPhone);
            System.out.println("Message: " + message);
            System.out.println("================");
        } else {
            // Real Twilio code
            try {
                Message.creator(
                                new PhoneNumber(toPhone),
                                new PhoneNumber(twilioPhoneNumber),
                                message)
                        .create();
                System.out.println("SMS sent to "+ toPhone);
            } catch (Exception e) {
                System.out.println("Failed to send SMS: " + e.getMessage());
            }
        }
    }

    public void processNotification(NotificationRequest request) {
        if (request.getEmail() != null && !request.getEmail().isEmpty()) {
            try {
                sendEmail(request.getEmail(), "Booking Confirmed - Healthcare Platform", request.getMessage());
            } catch (Exception e) {
                System.err.println("Failed to send email: " + e.getMessage());
            }
        }
        if (request.getPhone() != null && !request.getPhone().isEmpty()) {
            try {
                sendSms(request.getPhone(), request.getMessage());
            } catch (Exception e) {
                System.err.println("Failed to send sms: " + e.getMessage());
            }
        }

    }
}
