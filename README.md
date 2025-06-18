# 🌟 Starz Play – Movie DB Demo

A modern Android demo application for browsing and viewing movies, built with **best practices** and a **modular, scalable architecture**.

---

## ✨ Features

- 🔍 Browse a list of movies fetched from **The Movie Database (TMDb) API**
- 🎬 View detailed information about each movie
- 📺 Watch movie trailers using an integrated video player
- 🔎 Search for movies with real-time results
- 🖼️ Modern, responsive UI built with **Jetpack Compose**
- 🧭 Navigation powered by **Compose Destinations**
- 💉 Dependency injection with **Hilt**
- 🧱 Modularized codebase following **Clean Architecture**
- ⚙️ Gradle with **Version Catalog** for managing dependencies

---

## 🛠️ Tech Stack

| Category               | Technology                                         |
|------------------------|----------------------------------------------------|
| **Language**           | Kotlin                                             |
| **Minimum SDK**        | 24                                                 |
| **Target/Compile SDK** | 34                                                 |
| **UI Framework**       | Jetpack Compose, Material3                         |
| **Navigation**         | Compose Destinations                               |
| **Dependency Injection** | Hilt                                             |
| **Networking**         | Retrofit 2 with Gson converter                     |
| **Image Loading**      | Coil                                               |
| **Video Playback**     | ExoPlayer Compose                                  |
| **Async Programming**  | Kotlin Coroutines                                  |
| **Architecture**       | Modular, Clean Architecture (core & app modules)   |
| **Testing**            | JUnit, MockK, Turbine, AndroidX Test               |
| **Other**              | SplashScreen API, ViewModel, Lifecycle, Parcelize, KSP/KAPT |

---

## 🧱 Project Structure
starz-play/
├── app/         # Main application module (UI, navigation, DI entry point)
├── core/        # Core module (network, domain, DI, models)
├── build.gradle.kts
├── settings.gradle.kts
└── …other configuration files

---

## 🚀 Getting Started

1. **Clone the repository**
   ```bash
   git clone https://github.com/aliahmad3937/starz-play.git


2.	Open the project in Android Studio
3.	Add your TMDb API key and token
	•	Locate the Constants file inside the core module.
	•	Insert your TMDb API key and token in the specified location.
4.	Build and Run
	•	Choose an emulator or a physical device.
	•	Run the project from Android Studio.

📌 Notes
	•	This app is for demonstration purposes and not intended for production use.
	•	You must comply with TMDb’s terms of use when using their API.


# Pictures
<p float="left">
<img src="https://github.com/aliahmad3937/Starz-Play/blob/main/asset/screen1.jpg" width="150" height="280">
<img src="https://github.com/aliahmad3937/Starz-Play/blob/main/asset/screen2.jpg" width="150" height="280">
<img src="https://github.com/aliahmad3937/Starz-Play/blob/main/asset/screen3.jpg" width="150" height="280">
<img src="https://github.com/aliahmad3937/Starz-Play/blob/main/asset/screen4.jpg" width="150" height="280">
<img src="https://github.com/aliahmad3937/Starz-Play/blob/main/asset/screen5.jpg" width="150" height="280">
</p>


# License
This project is for demo purposes only.
