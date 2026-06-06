# Technical Requirements Document (TRD)

## Project: Government Grievance Redressal Portal

### 1. Executive Summary & Objectives

The purpose of this platform is to provide citizens with a transparent, efficient, and secure digital interface to lodge complaints, track their status, and allow various government departments to review, assign, and resolve these grievances.

---

### 2. System Architecture & Tech Stack

The application will follow a decoupled, multi-tier architecture to ensure high scalability, security, and maintainability.

| Layer | Technology | Purpose |
| --- | --- | --- |
| **Frontend** | React.js (with Redux/Context API for state management) | User interface, client-side routing, and interactive citizen/admin dashboards. |
| **Backend API** | Java Spring Boot (Spring Security, Spring Data JPA) | Core business logic, RESTful API endpoints, authentication, and role-based access control. |
| **Database** | PostgreSQL | Relational storage for user profiles, grievance data, audit logs, and department structures. |
| **Version Control** | Git (GitHub / GitLab / Bitbucket) | Code collaboration, branching strategy, and version tracking. |

---

### 3. Functional Requirements

### 3.1 User Roles & Permissions

The system must support three distinct user roles managed via Role-Based Access Control (RBAC):

- **Citizen:** Can register, submit grievances, upload supporting documents, track status, and provide feedback upon resolution.
- **Department Official (Nodal Officer):** Can view grievances assigned to their department, update processing status, leave internal remarks, and mark issues as resolved.
- **Super Admin:** Can manage users, onboard new government departments, view system-wide analytics, and reassign escalations.

### 3.2 Core Feature Modules

```
                        +---------------------------------------+
                        |            Citizen Client             |
                        +---------------------------------------+
                                            |
                                  REST API Requests (JSON)
                                            v
+---------------------------------------------------------------------------------------+
|                                  Spring Boot Backend                                  |
|                                                                                       |
|  +--------------------------+  +--------------------------+  +---------------------+  |
|  |    Auth & Security       |  |     Grievance Engine     |  | Notification Engine |  |
|  |  (JWT / Spring Security) |  |   (Routing & Tracking)   |  |  (Email / SMS API)  |  |
|  +--------------------------+  +--------------------------+  +---------------------+  |
+---------------------------------------------------------------------------------------+
                                            |
                                    Spring Data JPA
                                            v
                        +---------------------------------------+
                        |          PostgreSQL Database          |
                        +---------------------------------------+
```

### 1. Authentication & User Management

- Secure registration and login for citizens (optionally integrating mobile/OTP or email verification).
- JWT (JSON Web Token) stateless authentication for API endpoints.

### 2. Grievance Lodgment Engine

- A dynamic form allowing citizens to select a category/department, enter description text, and upload supporting attachments (PDF, PNG, JPEG up to 5MB).
- Automated generation of a unique **Grievance Tracking ID**.

### 3. Workflow & Routing Automation

- Automatic routing of complaints to the respective department based on the user's selection.
- Status transitions: `Submitted` $\rightarrow$ `Under Review` $\rightarrow$ `In Progress` $\rightarrow$ `Resolved` / `Rejected`.

### 4. Dashboard & Analytics

- **Citizen View:** List of historic complaints with a real-time progress timeline tracking status updates.
- **Official View:** Kanban or tabular queue of pending tickets sorted by submission date or priority.

---

### 4. Non-Functional Requirements

### 4.1 Security

- **Data Encryption:** Use HTTPS with TLS 1.3 for data in transit. Encrypt sensitive user data at rest within PostgreSQL.
- **Input Validation:** Strict server-side validation using Spring Validation (`@Valid`) to protect against SQL Injection, Cross-Site Scripting (XSS), and Remote Code Execution (RCE).
- **Password Hashing:** Passwords must be hashed using `BCryptPasswordEncoder`.

### 4.2 Performance & Scalability

- **Database Optimization:** Implement connection pooling (HikariCP) and indexing on frequently searched columns like `grievance_id`, `citizen_id`, and `department_id`.
- **Caching:** Utilize Spring Cache (e.g., Redis or Caffeine) for static data such as the list of government departments and categories to minimize database hits.

---

### 5. High-Level Database Schema Blueprint

To support the core requirements, the initial relational structure in PostgreSQL will consist of the following primary tables:

```sql
-- 1. Users Table (Handles Citizens, Officials, and Admins)
CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL, -- CITIZEN, OFFICIAL, ADMIN
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. Departments Table
CREATE TABLE departments (
    department_id SERIAL PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL,
    description TEXT
);

-- 3. Grievances Table
CREATE TABLE grievances (
    grievance_id VARCHAR(50) PRIMARY KEY, -- Unique generated tracking ID
    citizen_id INT REFERENCES users(user_id),
    department_id INT REFERENCES departments(department_id),
    title VARCHAR(150) NOT NULL,
    description TEXT NOT NULL,
    status VARCHAR(20) DEFAULT 'Submitted', -- Submitted, Under Review, In Progress, Resolved
    attachment_url VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

---

### 6. Version Control & Git Branching Strategy

To maintain a clean codebase across the frontend and backend repositories, the team will adhere to the **GitFlow** model:

- 'main`: Holds the production-ready code. Direct commits are strictly prohibited.
- `develop`: The integration branch for features. All completed feature branches merge here.
- `feature/*`: Temporary branches created off `develop` for specific tasks (e.g., `feature/jwt-auth`, `feature/grievance-form`).
- **Pull Requests (PRs):** Merging into `develop` or `main` requires at least one peer code review and passing build checks.
