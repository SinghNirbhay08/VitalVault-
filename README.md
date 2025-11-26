
# 🏥 **VitalVault – Secure, Offline-First Medical Record Manager**

*A modern Android app for Patients & Doctors built with Kotlin + Jetpack Compose.*

---

## 🚀 **Overview**

**VitalVault** is a dual-role Android application (Patient + Doctor) designed to make medical record management **simple, private, and secure**.

Unlike cloud-based health apps, **VitalVault stores all sensitive data locally** using encrypted storage via Room Database — giving full control to the user.

The app provides:

* A **Medical Vault** for patients to store and access all reports
* A **Doctor Dashboard** for clinicians to view shared patient data
* A **QR-based sharing system** with user approval
* A clean, modern UI built using **Jetpack Compose + Material 3**

---

## ✨ **Features**

### 👤 Patient Experience

* **Medical Vault Dashboard**
  View counts of saved reports, recent uploads, and overview cards.

* **Document Management**
  Add, view, and organize blood tests, prescriptions, scans, X-rays, and more.

* **Grid-Based Gallery View**
  Browse documents in a visual grid layout for easier navigation.

* **Secure Sharing System**
  Generate QR codes or deep links for doctors to request access.

* **Access Management**
  Approve, revoke, or set automatic expiry for doctor access sessions.

* **Profile Setup**
  Add blood group, age, emergency contacts, and other essential info.

---

### 👨‍⚕️ Doctor Experience

* **Simplified Practitioner Dashboard**
  See pending patient access requests, recent interactions, and summaries.

* **Medical License Verification Flow**
  Dedicated registration for specialized doctors.

* **Patient Access Monitoring**
  Track active sessions, expired sessions, and patient interactions.

* **Record Viewer**
  Quickly access shared reports with expiration timers and revoke options.

---

## 🛠️ **Tech Stack**

### **Frontend**

* **Kotlin (100%)**
* **Jetpack Compose (Material 3)**
* **Navigation Compose**
* **Kotlin Coroutines + StateFlow**
* **Coil** (image loading)
* **Lottie Animations** (optional)

### **Architecture**

* **MVVM + Clean Architecture**
* **Single-Activity Architecture**
* **Hilt (Dagger) for Dependency Injection**

### **Local Database**

* **Room Database**
* **Coroutines Flow for reactive updates**

### **Development Tools**

* **Gradle (Kotlin DSL)**
* **Version Catalogs (libs.versions.toml)**
* **Android Studio Giraffe / Hedgehog**

---

## 📁 **Project Structure**

```
com.nirbhay.vitalvault01
├── data/                
│   ├── AppDatabase.kt         # Room Database
│   ├── ReportDao.kt           # DAO Interface
│   └── ReportEntity.kt        # Data Model
│
├── di/                       
│   └── AppModule.kt           # Hilt Modules
│
├── navigation/                
│   ├── AppNavigation.kt       # Nav Graph
│   └── Screen.kt              # Typed Routes
│
├── ui/
│   ├── auth/                  # Login / Signup / Role Selection
│   ├── components/            # Reusable UI (Cards, NavBar)
│   ├── doctor/                # Doctor Dashboard + Profile
│   ├── home/                  # Patient Dashboard + Reports
│   ├── share/                 # Sharing Modules (QR, Approvals)
│   ├── splashScreen/          # App Splash UI
│   ├── theme/                 # Material 3 Theme Files
│   └── viewmodel/             # Hilt ViewModels
│
└── MainActivity.kt            # Entry Point
```

---

## ⚙️ **Installation & Setup**

### **Prerequisites**

* Android Studio Giraffe or newer
* JDK 17
* Minimum SDK: 24
* Recommended: Pixel 6 / 7 Emulator

### **Clone Repository**

```bash
git clone https://github.com/yourusername/VitalVault.git
cd VitalVault
```

### **Build Project**

```bash
./gradlew assembleDebug
```

### **Run App**

* Open Android Studio → Run on emulator/device

---

## 🔐 **Security & Data Privacy**

VitalVault follows a **“Privacy First”** design:

* All data is stored **locally** using Room Database
* No cloud server required
* Secure sharing uses **temporary access sessions**
* Users can revoke access at any time
* Optional biometric lock (coming soon)

---

## 🧭 **Roadmap**

### 🔜 Coming Soon

* 📤 **Cloud Backup** (Encrypted)
* 🔎 **OCR Text Extraction from Paper Reports**
* 📸 **CameraX Live Scanning**
* 🩺 **Doctor–Patient Real-Time Sync**
* 🔐 **Biometric Unlock**

### ⭐ Future Enhancements

* PDF annotation tools
* Medication reminders
* Cross-device sync
* Auto categorize reports using ML

---

## 🤝 **Contributing**

We welcome PRs that improve:

* UI/UX
* Accessibility
* Security
* Performance

To contribute:

```bash
git checkout -b feature/my-feature
git commit -m "Add my feature"
git push origin feature/my-feature
```

---

## ⭐ **If you like this project, give it a star on GitHub!**

