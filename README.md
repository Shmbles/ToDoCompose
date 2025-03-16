# 📋 ToDoCompose  
📝 A task list application built with **Jetpack Compose** and **Kotlin**.  

<p align="center">
  <img src="https://img.shields.io/github/stars/Shmbles/ToDoCompose?style=for-the-badge" alt="GitHub stars">
  <img src="https://img.shields.io/github/forks/Shmbles/ToDoCompose?style=for-the-badge" alt="GitHub forks">
  <img src="https://img.shields.io/github/issues/Shmbles/ToDoCompose?style=for-the-badge" alt="GitHub issues">
  <img src="https://img.shields.io/github/license/Shmbles/ToDoCompose?style=for-the-badge" alt="License">
</p>

## 🚀 Features  
- ✔️ Modern design with **Jetpack Compose**  
- ✔️ Easily add, edit, and delete tasks
- ✔️ Local storage with **Room Database**  
- ✔️ Architecture based on **MVVM**  
- ✔️ Light and dark theme support 

## 📸 Application  
| ![part1](https://github.com/user-attachments/assets/a3268fc3-527f-4091-b684-da2dbcb238c0) | ![part2](https://github.com/user-attachments/assets/468be3d3-9dca-4718-ac59-44ae2391ab9d) | ![part3](https://github.com/user-attachments/assets/18d98b23-1158-4f8e-97b5-b0bc8ff48554)
|---|---|---|
| Screenrecord 1 | Screenrecord 2 | Screenrecord 3


## 🛠️ Technologies Used  
<p align="left">
  <img src="https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white" alt="Kotlin">
  <img src="https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white" alt="Jetpack Compose">
  <img src="https://img.shields.io/badge/Room%20Database-FF6F00?style=for-the-badge&logo=sqlite&logoColor=white" alt="Room Database">
  <img src="https://img.shields.io/badge/Dagger%20Hilt-007ACC?style=for-the-badge&logo=dagger&logoColor=white" alt="Dagger Hilt">
  <img src="https://img.shields.io/badge/Data%20Store-673AB7?style=for-the-badge&logo=google&logoColor=white" alt="Data Store">
</p>

## 📦 Installation  
1. Clone the repository
   ```sh
   git clone https://github.com/Shmbles/ToDoCompose.git
   ```
2. Open it in **Android Studio**  
3. Build and run it on an emulator or physical device

## 🏗️ Architecture  
This project follows the **MVVM** pattern to keep the code modular and scalable.

## 🏗️ Project Structure
The project follows the **MVVM (Model-View-ViewModel)** architecture with the following layers:  

```
ToDoCompose/
│── app/
│   ├── src/main/java/com/example/todocompose/
│   │   ├── data/            # Data layer
│   │   │   ├── model/       # Entity and data object definitions
│   │   │   ├── repository/  # Data access logic and repositories
│   │   │   ├── local/       # Room Database and DAO
│   │   │   ├── datastore/   # Persistence with DataStore
│   │   │   ├── di/          # Dependency injection with Dagger Hilt
│   │   ├── ui/              # Presentation layer (Jetpack Compose)
│   │   │   ├── components/  # Reusable UI components
│   │   │   ├── screens/     # Application screens
│   │   ├── viewmodel/       # ViewModels for UI logic
│   │   ├── utils/           # Helper classes and extensions
│   ├── AndroidManifest.xml
│── build.gradle.kts
│── settings.gradle.kts
```

### 📌 Package Explanation:  
- **data/model/** → Defines entities used by Room and the UI  
- **data/repository/** → Contains data access logic
- **data/local/** → Implements Room Database and DAO's
- **data/datastore/** → Handles preferences persistence with DataStore
- **data/di/** → Sets up **Dagger Hilt** for dependency injection
- **ui/components/** → Contains reusable UI elements like buttons and cards
- **ui/screens/** → Defines the different application screends
- **viewmodel/** → Implements **ViewModels**, using Flow and LiveData
- **utils/** → Contains helper functions and extensions

## 📌 Upcoming Improvements
- 📌 Notifications for pending tasks
- 📌 Home screen widgets

## 📝 Contributions  
Contrinutions are welcome if yud'd like to improve the app, follow these steps:
1. **Fork** the repository
2. Create a new branch:  
   ```sh
   git checkout -b feature-new
   ```
3. Make your changes and **commit**:  
   ```sh
   git commit -m "Add new feature"
   ```
4. Push the changes:  
   ```sh
   git push origin feature-new
   ```
5. Open a **Pull Request**  

## 📄 License  
This project is under the **MIT** license.  

📌 *Give the repository a ⭐ if you like it!* 🚀  
