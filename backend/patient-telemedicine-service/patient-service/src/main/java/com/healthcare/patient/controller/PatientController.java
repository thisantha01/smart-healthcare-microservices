package com.healthcare.patient.controller;

import com.healthcare.patient.entity.MedicalReport;
import com.healthcare.patient.repository.MedicalReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin(origins = "*") // Allow frontend access
public class PatientController {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Autowired
    private MedicalReportRepository medicalReportRepository;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadReport(@RequestParam("file") MultipartFile file,
                                          @RequestParam("patientId") Long patientId,
                                          @RequestParam(value = "description", required = false) String description) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty");
        }

        try {
            // Create uploads directory if not exists
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Generate unique filename to avoid overriding
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);

            // Save file logically to local machine
            Files.write(filePath, file.getBytes());

            // Create report entity
            MedicalReport report = new MedicalReport();
            report.setPatientId(patientId);
            report.setFileUrl(filePath.toAbsolutePath().toString());
            report.setUploadDate(LocalDateTime.now());
            report.setDescription(description);

            // Save in DB
            MedicalReport savedReport = medicalReportRepository.save(report);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedReport);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not upload the file: " + e.getMessage());
        }
    }
    
    @GetMapping("/{patientId}/reports")
    public ResponseEntity<List<MedicalReport>> getPatientReports(@PathVariable Long patientId) {
        return ResponseEntity.ok(medicalReportRepository.findByPatientId(patientId));
    }
}
