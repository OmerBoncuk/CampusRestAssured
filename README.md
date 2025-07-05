# 📚 Campus API - Automated API Testing with Rest Assured

This repository contains automated API tests for the **Campus API** (`https://test.mersys.io`) implemented in **Java** using the **Rest Assured** library.  
The aim is to validate all major backend functionalities through API endpoints to ensure they work as expected, with quick feedback on regressions or integration issues.

---

## 🚀 Technologies Used

- **Java 17+**
- **Rest Assured** – HTTP requests & assertions
- **JUnit 5** – test structure and execution
- **Maven** – dependency & build management

---

## ⚙️ Environment & Security

- **Environment URL:** `https://test.mersys.io`
- **API Base Endpoint:** `/school-service/api/{...}`
- **Content Type:** `application/json`
- **User credentials for Admin role:**
  - **Username:** `turkeyts`
  - **Password:** `TechnoStudy123`

All requests and data are handled securely. Authentication is typically performed by logging in and obtaining an **access token**, which is then used in subsequent API calls.

---

## 📦 Installation & Setup


git clone https://github.com/OmerBoncuk/Postman_Campus_Api.git
cd Postman_Campus_Api

- java -version
- mvn -version
- mvn clean install
- mvn test

## 📊 Test Summary Table
| ID        | User Story Description             | Endpoint(s)                            | Expected Status Codes            | Notes                                             |
| --------- | ---------------------------------- | -------------------------------------- | -------------------------------- | ------------------------------------------------- |
| **US001** | Login to API to obtain admin token | `/auth/login`                          | `200` (success), `400` (invalid) | Tests login with valid & invalid credentials      |
| **US002** | Add a country with a state         | `/countries`                           | `201`                            | Verifies country, code, and state details         |
| **US003** | CRUD operations on states          | `/states`                              | `200`, `201`, `204`              | Includes list (<1000ms), add, edit, delete        |
| **US004** | CRUD operations on cities          | `/cities`                              | `200`, `201`, `204`              | List all <1000ms, specific city <200ms            |
| **US005** | CRUD operations on exams           | `/exams`                               | `200`, `201`, `400`, `404`       | Checks creation, update, deletion, validation     |
| **US006** | CRUD on custom fields              | `/custom-field-groups`                 | `200`, `201`, `400`, `204`       | Prevents duplicate `name` or `orderNo`            |
| **US007** | CRUD on student groups             | `/student-group`                       | `200`, `201`, `400`              | Includes validations on name & description length |
| **US008** | Add students to groups             | `/student-groups/{id}/add-students`    | `200`                            | Verify by listing students in group               |
| **US009** | Remove students from groups        | `/student-groups/{id}/remove-students` | `200`                            | Ensure students are removed from group            |
| **US010** | CRUD on education standards        | `/education-standard`                  | `200`, `201`, `204`, `400`       | Lists standards by school ID                      |
| **US011** | CRUD on grading schemes            | `/grading-schemes`                     | `200`, `201`, `204`, `400`       | Validates name, type, and school link             |
| **US012** | CRUD on incident types             | `/incident-type`                       | `200`, `201`, `204`, `400`       | Ensures proper incident data & error handling     |

* tests contains individual feature tests matching each user story.
* utils provides helpers for authentication and data generation.

  
## 🗂 Project Structure

```bash

src
└── test
    └── java
        └── com
            └── campusapi
                ├── tests
                │   ├── LoginTest.java
                │   ├── CountryTest.java
                │   ├── StateTest.java
                │   ├── CityTest.java
                │   ├── ExamTest.java
                │   ├── CustomFieldTest.java
                │   ├── StudentGroupTest.java
                │   ├── EducationStandardTest.java
                │   ├── GradingSchemeTest.java
                │   └── IncidentTest.java
                └── utils
                    └── AuthHelper.java
```
## 🧪 Performance Expectations

- List endpoints should respond within:
    - 1000 ms for bulk listings (states, cities, custom fields)
    - 200 ms for fetching specific details (city, custom field)
- Ensures system performance is aligned with UX requirements.
## 🔄 CI/CD Integration
This suite can be seamlessly integrated into:

- **GitHub Actions**
- **GitLab CI/CDs**
- **Jenkins**

for running automated API tests on each push, merge, or schedule, ensuring robust regression detection.
## 👥 Team Members

| Member | Role |
|-----------|----------------|
| 🧑‍💻 [Erdem Özkan](https://github.com/ErdemOzkann) | QA |
| 👩‍💻 [Diyar Ölmez](https://github.com/diyar-olmez) | QA |
| 👨‍💻 [Barış Saydam](https://github.com/BarisSaydam) | QA |
| 👨‍💻 [Ömer Boncuk](https://github.com/OmerBoncuk) | QA |
| 👨‍💻 [Atilla Toros Avcı](https://github.com/AtillaTorosAvci) | QA |
| 👩‍💻 [Gamze Batmaz](https://github.com/GAMZE3845) | QA |

> Team coordination was conducted via RocketChat. 

## 📝 Notes
- This automated suite is built on top of the manual tests initially developed in **Postman**.
- The Campus application provides Swagger documentation under its setup, which is used to verify endpoints.
- Future enhancements may include data-driven tests from CSV/Excel or integration with reporting tools like Allure.
