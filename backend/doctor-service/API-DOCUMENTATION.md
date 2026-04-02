# Doctor Service API Documentation

## Overview
This is a Spring Boot 3 microservice for managing doctors, schedules, and prescriptions in a smart healthcare system.

## Base URL
`http://localhost:8082`

## Database
- MySQL database: `doctor_db`
- Auto-creation enabled via Hibernate DDL

## API Endpoints

### Doctor Management (`/api/doctors`)

#### Create Doctor
- **POST** `/api/doctors`
- **Body**: `DoctorRequestDTO`
- **Response**: `DoctorResponseDTO` (201 Created)

#### Get All Doctors
- **GET** `/api/doctors`
- **Response**: `List<DoctorResponseDTO>`

#### Get Doctor by ID
- **GET** `/api/doctors/{id}`
- **Response**: `DoctorResponseDTO`

#### Get Doctors by Specialty
- **GET** `/api/doctors/search?specialty={specialty}`
- **Response**: `List<DoctorResponseDTO>`

#### Update Doctor
- **PUT** `/api/doctors/{id}`
- **Body**: `DoctorRequestDTO`
- **Response**: `DoctorResponseDTO`

#### Update Doctor Status
- **PUT** `/api/doctors/{id}/status?status={status}`
- **Response**: `DoctorResponseDTO`

#### Approve Doctor
- **PUT** `/api/doctors/{id}/approve`
- **Response**: `DoctorResponseDTO`

#### Reject Doctor
- **PUT** `/api/doctors/{id}/reject`
- **Response**: `DoctorResponseDTO`

#### Delete Doctor
- **DELETE** `/api/doctors/{id}`
- **Response**: 204 No Content

### Schedule Management (`/api/schedules`)

#### Create Schedule
- **POST** `/api/schedules`
- **Body**: `ScheduleDTO`
- **Response**: `ScheduleDTO` (201 Created)

#### Get Schedule by Doctor ID
- **GET** `/api/schedules/doctor/{doctorId}`
- **Response**: `List<ScheduleDTO>`

#### Update Schedule
- **PUT** `/api/schedules/{id}`
- **Body**: `ScheduleDTO`
- **Response**: `ScheduleDTO`

#### Delete Schedule
- **DELETE** `/api/schedules/{id}`
- **Response**: 204 No Content

### Prescription Management (`/api/prescriptions`)

#### Create Prescription
- **POST** `/api/prescriptions`
- **Body**: `PrescriptionRequestDTO`
- **Response**: `PrescriptionDTO` (201 Created)

#### Get Prescriptions by Patient ID
- **GET** `/api/prescriptions/patient/{patientId}`
- **Response**: `List<PrescriptionDTO>`

#### Get Prescriptions by Doctor ID
- **GET** `/api/prescriptions/doctor/{doctorId}`
- **Response**: `List<PrescriptionDTO>`

## Data Models

### DoctorRequestDTO
```json
{
  "name": "string (2-100 chars, required)",
  "email": "string (valid email, required)",
  "specialty": "string (2-50 chars, required)",
  "hospital": "string (max 100 chars, optional)",
  "consultationFee": "number (positive, optional)"
}
```

### DoctorResponseDTO
```json
{
  "doctorId": "number",
  "name": "string",
  "email": "string",
  "specialty": "string",
  "hospital": "string",
  "consultationFee": "number",
  "status": "PENDING|APPROVED|REJECTED"
}
```

### ScheduleDTO
```json
{
  "scheduleId": "number",
  "doctorId": "number (required, positive)",
  "day": "string (Monday-Sunday, required)",
  "startTime": "string (HH:MM format, required)",
  "endTime": "string (HH:MM format, required)"
}
```

### PrescriptionRequestDTO
```json
{
  "doctorId": "number (required, positive)",
  "patientId": "number (required, positive)",
  "medicines": "string (max 1000 chars, required)",
  "notes": "string (max 1000 chars, optional)"
}
```

### PrescriptionDTO
```json
{
  "prescriptionId": "number",
  "doctorId": "number",
  "patientId": "number",
  "medicines": "string",
  "notes": "string",
  "createdAt": "datetime"
}
```

## Error Responses
All errors return a standardized format:
```json
{
  "timestamp": "datetime",
  "status": "number",
  "error": "string",
  "message": "string"
}
```

## Features Implemented
✅ Doctor Management (CRUD + Status Management)
✅ Schedule Management (CRUD)
✅ Prescription Management (Create + Read by Patient/Doctor)
✅ Input Validation
✅ Exception Handling
✅ DTO Pattern (No direct entity exposure)
✅ RESTful API Design
✅ MySQL Database Integration
✅ Spring Boot 3 + Java 21
✅ Lombok Integration
