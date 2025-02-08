# JobPortal - A Scalable Job Posting Platform

## **Overview**
**JobPortal** is a **Spring Boot-powered** job platform that connects **job seekers, employers, and administrators** seamlessly. This project follows a **scalable microservices-like architecture** with **secure authentication, job listings, applications, and user management**.

## **Features**
- **User Management**: Employees and employers can register, login, and manage profiles.
- **Job Listings**: Employers can create, update, and delete job postings.
- **Job Applications**: Employees can apply for jobs, track status, and view applications.
- **Secure Authentication**: OAuth2 for Google/GitHub login, JWT for API security.
- **DTO-Based Response Handling**: Clean, structured API responses with Data Transfer Objects (DTOs).
- **Spring Boot & JPA**: Backend built with Spring Boot, Spring Security, and MySQL for data persistence.

---

## **Tech Stack**
- **Backend**: Spring Boot, Spring Data JPA, Hibernate, MySQL
- **Security**: Spring Security (JWT, OAuth2 for Google/GitHub authentication)

---

## **Installation & Setup**

### **1. Clone the Repository**
```bash
git clone https://github.com/Aspect022/jobportal.git
cd jobportal
```

### **2. Configure Database**
Modify `application.properties` to match your MySQL configuration.
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/jobportal
spring.datasource.username=root
spring.datasource.password=root
```

### **3. Run the Application**
```bash
mvn spring-boot:run
```

---

## **Authentication & Security**

### **OAuth2 Login**
- Google Login
- GitHub Login

### **JWT Authentication**
- JWT is used for securing API endpoints.
- Users must include a **Bearer Token** in request headers.
- Token Expiration: `36000000 ms` (10 hours)

Example:
```http
Authorization: Bearer your-jwt-token
```

---

## **API Endpoints**

### **User Management** (`/api/users`)
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/users/{id}` | Get user by ID |
| POST | `/api/users/register` | Register a new user |

### **Employee APIs** (`/api/employees`)
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/employees/{id}` | Get employee details |
| POST | `/api/employees/register` | Register a new employee |

### **Employer APIs** (`/api/employers`)
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/employers/{id}` | Get employer details |
| POST | `/api/employers/register` | Register a new employer |

### **Job Listings** (`/api/jobs`)
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/jobs/{id}` | Get job details |
| GET | `/api/jobs/employer/{employerId}` | Get jobs posted by employer |
| POST | `/api/jobs` | Create a new job |

### **Job Applications** (`/api/job-applications`)
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/job-applications/apply` | Apply for a job |
| GET | `/api/job-applications/job/{jobId}` | Get applications for a job |

---

## **DTO (Data Transfer Objects) Usage**
- DTOs are used to **structure API responses** and **prevent exposing sensitive data**.
- Example: `EmployeeDTO`, `EmployerDTO`, `JobDTO`, `JobApplicationDTO`.

---

## **Future Enhancements**
- **Frontend Development**: React-based UI.
- **Admin Panel**: Role-based access control.
- **Advanced Search & Filters**: AI-powered job recommendations.
- **Resume Analysis**: AI-powered resume scanning & ranking.

---

## **Contributing**
Pull requests are welcome! For major changes, please open an issue first to discuss what you’d like to change.

```bash
git checkout -b feature-branch
git commit -m "Add new feature"
git push origin feature-branch
```

## **Contact**
**Jayesh RL**  
[GitHub](https://github.com/Aspect022)  
[LinkedIn](https://www.linkedin.com/in/jayesh-rl-748059291/)  
[Email](jayeshrl2005@gmail.com)

