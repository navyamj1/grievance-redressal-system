# Citizen Grievance Redressal System (CGRS)

## Product Requirements Document (PRD)

---

# 1. Overview

The Citizen Grievance Redressal System (CGRS) is a centralized platform that enables citizens to report public grievances, track their status, and receive updates until resolution.

The system aims to improve transparency, accountability, and communication between citizens and departments through a structured complaint management workflow.

---

# 2. Problem Statement

Citizens often face difficulties when reporting issues such as:

- Road damage
- Water leakage
- Streetlight failures
- Garbage accumulation
- Drainage issues
- Public infrastructure maintenance

## Current Challenges

- Lack of a centralized reporting platform
- Difficulty identifying the responsible department
- No visibility into complaint progress
- Delayed or unclear resolutions
- Limited communication between citizens and departments

The proposed system addresses these challenges by providing a unified grievance submission and tracking platform.

---

# 3. Goals

| Goal | Description |
|--------|-------------|
| Centralized Reporting | Provide a single platform for grievance submission |
| Transparency | Allow citizens to track complaint progress |
| Accountability | Ensure departments manage and update complaints |
| Communication | Keep citizens informed through status updates |
| Accessibility | Make complaint submission simple and user-friendly |

---

# 4. User Roles

## 4.1 Citizen

Citizens can:

- Create an account
- Login to the system
- Submit complaints
- Upload supporting images
- Select the relevant department
- View complaint history
- Track complaint progress
- Receive complaint updates

---

## 4.2 Department Officer

Department officers can:

- Login to the system
- View assigned complaints
- View complaint details
- Access complaint locations
- Update complaint status
- Mark complaints as resolved

---

# 5. Functional Requirements

## FR-01 User Authentication

### Description

Users must be able to securely access the platform.

### Requirements

- User Registration
- User Login
- User Logout

---

## FR-02 Complaint Submission

### Description

Citizens must be able to submit grievances through a structured form.

### Required Information

| Field | Required |
|---------|----------|
| Complaint Title | Yes |
| Description | Yes |
| Department Selection | Yes |
| Image Upload | Optional |
| Location | Optional |

### Expected Outcome

- Complaint is successfully submitted
- Unique Complaint ID is generated
- Initial status is set to **Submitted**

---

## FR-03 Department Selection

### Description

Users must select the department responsible for handling the complaint.

### Example Departments

| Department |
|------------|
| Water Department |
| Electricity Department |
| Road Maintenance |
| Sanitation Department |
| Drainage Department |
| Public Works Department |

---

## FR-04 Complaint Tracking

### Description

Citizens must be able to monitor complaint progress in real time.

### Complaint Lifecycle

| Stage |
|---------|
| Submitted |
| Received |
| Under Review |
| In Progress |
| Resolved |
| Closed |

### Requirements

- Current status should always be visible
- Status updates should appear chronologically
- Citizens should be able to view the complete complaint timeline

---

## FR-05 Complaint History

### Description

Citizens must be able to view all previously submitted complaints.

### Active Complaints

Displays:

- Submitted complaints
- Under Review complaints
- In Progress complaints

### Completed Complaints

Displays:

- Resolved complaints
- Closed complaints

### Requirements

- Active complaints appear first
- Completed complaints are displayed separately
- Complaints should be sortable by date

---

## FR-06 Citizen Dashboard

### Description

The citizen dashboard serves as the primary workspace for complaint management.

### Components

| Component | Purpose |
|------------|----------|
| Complaint Submission Form | Submit new complaints |
| Active Complaints Section | View ongoing complaints |
| Complaint History Section | View past complaints |
| Complaint Tracking View | Track complaint progress |

---

## FR-07 Department Dashboard

### Description

Department officers require a dedicated workspace to manage complaints.

### Components

| Component | Purpose |
|------------|----------|
| Assigned Complaints List | View incoming complaints |
| Complaint Details View | Review complaint information |
| Status Update Controls | Update complaint progress |
| Location View | View complaint location |

---

## FR-08 Complaint Status Management

### Description

Department officers must be able to update complaint progress.

### Allowed Status Updates

| Status |
|----------|
| Received |
| Under Review |
| In Progress |
| Resolved |
| Closed |

### Requirements

- Citizens should immediately see updated statuses
- Status history must be preserved

---

## FR-09 Location Visualization

### Description

Departments should be able to view complaint locations.

### Requirements

- Display complaint location on a map
- Allow quick identification of complaint area
- Location should be accessible from complaint details

---

## FR-10 Notifications

### Description

Users should receive updates whenever complaint progress changes.

### Notification Events

| Event |
|---------|
| Complaint Submitted |
| Complaint Received |
| Complaint Under Review |
| Complaint In Progress |
| Complaint Resolved |
| Complaint Closed |

### Requirements

- Users should be notified of status changes
- Notifications should be visible within the platform

---

# 6. User Journey

## Citizen Flow

```text
Login
   ↓
Dashboard
   ↓
Submit Complaint
   ↓
Complaint Created
   ↓
Track Status
   ↓
Receive Updates
   ↓
Complaint Resolved
```

## Department Flow

```text
Login
   ↓
View Assigned Complaints
   ↓
Review Complaint
   ↓
Update Status
   ↓
Resolve Complaint
   ↓
Close Complaint
```

---

# 7. Success Criteria

The product will be considered successful if:

- Citizens can submit complaints without difficulty
- Departments can efficiently manage assigned complaints
- Complaint progress remains visible throughout the lifecycle
- Citizens receive timely updates
- Resolution status is transparent to all stakeholders

---

# 8. Future Enhancements

The following features are outside the scope of Version 1 but may be considered in future releases:

- AI-based department suggestion
- Automatic complaint categorization
- Escalation workflows
- SMS notifications
- Analytics dashboard
- Citizen feedback and ratings
- Mobile application
- Chatbot assistance
