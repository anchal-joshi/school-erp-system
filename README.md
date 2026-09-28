# School ERP

A multi-tenant School ERP backend built with Java and Spring Boot to manage core school operations for School Admins, Teachers, and Students.

## Features

- 🔐 JWT-based authentication and Spring Security
- 👥 Role-based access for School Admins, Teachers, and Students
- 🏫 Multi-tenant school data isolation
- 👨‍🎓 Student management
- 👨‍🏫 Teacher management
- 📚 Class and section management
- 📖 Subject management
- 🔗 Teacher-subject assignments
- 📝 Student attendance management
- 📋 Examination management
- 📊 Marks management
- 📱 Role-specific dashboards

## User Roles

### School Admin

- Manage school information
- Manage students and teachers
- Manage classes and sections
- Manage subjects
- Assign teachers to subjects and sections
- Manage examinations
- View school overview through the Admin Dashboard

### Teacher

- View assigned sections and subjects
- Record student attendance
- Enter and manage student marks
- View assigned work through the Teacher Dashboard

### Student

- View personal profile
- View subjects
- View attendance summary
- View examination results and subject-wise marks
- Access the Student Dashboard

## Tech Stack

- **Language:** Java
- **Framework:** Spring Boot
- **Security:** Spring Security, JWT, BCrypt
- **Database:** MySQL
- **ORM:** Spring Data JPA, Hibernate
- **Build Tool:** Maven
- **Version Control:** Git, GitHub

## Project Architecture

The application follows a layered architecture:

```
Controller
    ↓
Service
    ↓
Repository
    ↓
Database

com.example.schoolerp
├── controller
├── service
├── repository
├── entity
├── dto
├── security
└── exception

Layer Responsibilities
- Controller: Handles HTTP requests and API responses.
- Service: Contains business logic and authorization checks.
- Repository: Handles database operations using Spring Data JPA.
- Entity: Represents database entities.
- DTO: Defines request and response objects.
- Security: Handles JWT authentication, Spring Security, and the current authenticated user.
- Exception: Contains custom application exceptions.
```
## Authentication & Security

The application uses **Spring Security and JWT** for authentication and role-based authorization.

### Authentication Flow

```
User Login
    ↓
Authentication
    ↓
JWT Token Generated
    ↓
Client Sends Bearer Token
    ↓
JWT Authentication Filter
    ↓
Authenticated User
    ↓
Role-Based Authorization
    ↓
Protected API Endpoint

Security Features
- Passwords are securely hashed using BCrypt.
- JWT is used for stateless authentication.
- Protected endpoints require a valid Bearer token.
- Role-based access control is implemented using Spring Security.
- School-level authorization checks help prevent cross-school data access.
- The currently authenticated user and their school are used when performing protected operations.
```

## Dashboards

The system provides role-specific dashboards:

- **School Admin Dashboard** — School profile and overview of students, teachers, classes, sections, and subjects.
- **Teacher Dashboard** — Teacher profile and assigned sections and subjects.
- **Student Dashboard** — Student profile, subjects, attendance summary, and examination results.

## How to Run

### Prerequisites

Make sure you have the following installed:

- Java
- Maven
- MySQL
- Git

### Clone the Repository

```bash
git clone https://github.com/anchal-joshi/school-erp.git
cd school-erp
```

## Testing

The application has been tested for:

- JWT authentication
- Role-based authorization
- CRUD operations
- Multi-tenant school data access
- Attendance management
- Examination and marks management
- Duplicate data validation
- Student Dashboard
- Teacher Dashboard
- School Admin Dashboard
- Unauthorized role access

---

## API Overview

| Module | Endpoint | Role |
|---|---|---|
| Authentication | `/api/auth` | Public |
| Student Dashboard | `/api/student/dashboard` | Student |
| Teacher Dashboard | `/api/teacher/dashboard` | Teacher |
| School Admin Dashboard | `/api/schooladmin` | School Admin |
| Attendance | `/api/attendance` | Teacher |
| Exams | `/api/exams` | School Admin |
| Marks | `/api/marks` | Teacher |

Additional endpoints are available for managing schools, users, students, teachers, classes, sections, subjects, and teacher assignments.

---

## Future Improvements

- Frontend dashboard
- Pagination and filtering
- Improved exception handling
- Global API error handling
- Detailed reports and analytics
- Swagger/OpenAPI documentation
- Notifications
- Additional school management features

---

## Project Scope

This project focuses on the core academic management workflow of a school.

The current MVP includes:

- Authentication and authorization
- Multi-tenant school management
- Student and teacher management
- Classes and sections
- Subjects and teacher assignments
- Attendance
- Examinations
- Marks
- Role-specific dashboards

Features such as fees, homework, and leave management are outside the current MVP scope.

---

## Author

**Anchal Joshi**

BCS Student | Backend Development

Interested in Java, Spring Boot, REST APIs, Spring Security, and backend development.
