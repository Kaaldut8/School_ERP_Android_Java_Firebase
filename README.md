# 🏫 School ERP Android Application

> A real-time Android ERP system built with **Java & Firebase** — supporting **100+ concurrent users** with role-based access control across student, teacher, and admin roles.

---

## 🚀 Tech Stack

| Component | Technology |
|-----------|-----------|
| Language | Java |
| Platform | Android (Android Studio) |
| Backend | Firebase Firestore (NoSQL) |
| Auth | Firebase Authentication |
| Build System | Gradle |
| Version Control | Git |

---

## ✨ Key Features

- ✅ **Firebase Authentication** — secure login for multiple user roles
- ✅ **Role-Based Access Control (RBAC)** — distinct dashboards for Admin, Teacher & Student
- ✅ **Real-time Firestore** — live data sync across all connected users
- ✅ **Attendance Tracking System** — mark and monitor student attendance
- ✅ **Full CRUD** on student records — add, view, update, delete
- ✅ **Optimized Firestore queries** — reduced data retrieval latency
- ✅ Supports **100+ concurrent users** reliably

---

## 👥 User Roles

| Role | Permissions |
|------|-------------|
| **Admin** | Full access — manage users, records, and settings |
| **Teacher** | Mark attendance, view class records |
| **Student** | View own profile, attendance & results |

---

## 🏗️ Architecture

```
App/
├── Authentication      → Firebase Auth (role-based login)
├── Admin Module        → User & record management
├── Teacher Module      → Attendance & class management
├── Student Module      → Personal dashboard & records
└── Firestore Database  → Real-time cloud data storage
```

---

## 📁 Project Structure

```
School_ERP_Android_Java_Firebase/
├── app/
│   ├── src/main/java/     # Java source files
│   │   ├── activities/    # UI screens per role
│   │   ├── models/        # Data models
│   │   └── utils/         # Helper classes
│   └── src/main/res/      # Layouts, drawables, strings
├── build.gradle           # Project dependencies
└── google-services.json   # Firebase config (not committed)
```

---

## ⚙️ How to Run

**Prerequisites:** Android Studio, Firebase account

```bash
# 1. Clone the repository
git clone https://github.com/Kaaldut8/School_ERP_Android_Java_Firebase.git

# 2. Open in Android Studio

# 3. Connect Firebase
#    - Create a project at console.firebase.google.com
#    - Enable Firestore & Authentication
#    - Download google-services.json → place in /app directory

# 4. Run on emulator or physical device
#    Click ▶ Run in Android Studio
```

---

## 🔮 Planned Improvements

- [ ] Push notifications via Firebase Cloud Messaging (FCM)
- [ ] Cloud Functions for server-side business logic
- [ ] REST API backend version using Spring Boot
- [ ] Fee management module
- [ ] Timetable & scheduling features

---

## 👨‍💻 Author

**Raghav Maheshwari**
- 📧 raghavmaheshwari0803@gmail.com
- 💼 [LinkedIn](https://www.linkedin.com/in/raghavmaheshwari0803/)
- 🐙 [GitHub](https://github.com/Kaaldut8)
