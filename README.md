# 📋 ToDoCompose  
📝 Una aplicación de lista de tareas creada con **Jetpack Compose** y **Kotlin**.  

<p align="center">
  <img src="https://img.shields.io/github/stars/Shmbles/ToDoCompose?style=for-the-badge" alt="GitHub stars">
  <img src="https://img.shields.io/github/forks/Shmbles/ToDoCompose?style=for-the-badge" alt="GitHub forks">
  <img src="https://img.shields.io/github/issues/Shmbles/ToDoCompose?style=for-the-badge" alt="GitHub issues">
  <img src="https://img.shields.io/github/license/Shmbles/ToDoCompose?style=for-the-badge" alt="License">
</p>

## 🚀 Características  
- ✔️ Diseño moderno con **Jetpack Compose**  
- ✔️ Agregar, editar y eliminar tareas fácilmente  
- ✔️ Almacenamiento local con **Room Database**  
- ✔️ Arquitectura basada en **MVVM**  
- ✔️ Soporte para temas claros y oscuros  

## 📸 Capturas de Pantalla  
*(Agrega aquí imágenes o GIFs de la app en funcionamiento 📷)*  

## 🛠️ Tecnologías Utilizadas  
<p align="left">
  <img src="https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white" alt="Kotlin">
  <img src="https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white" alt="Jetpack Compose">
  <img src="https://img.shields.io/badge/Room%20Database-FF6F00?style=for-the-badge&logo=sqlite&logoColor=white" alt="Room Database">
  <img src="https://img.shields.io/badge/Dagger%20Hilt-007ACC?style=for-the-badge&logo=dagger&logoColor=white" alt="Dagger Hilt">
  <img src="https://img.shields.io/badge/Data%20Store-673AB7?style=for-the-badge&logo=google&logoColor=white" alt="Data Store">
</p>

## 📦 Instalación  
1. Clona el repositorio:  
   ```sh
   git clone https://github.com/Shmbles/ToDoCompose.git
   ```
2. Ábrelo en **Android Studio**  
3. Compila y ejecuta en un emulador o dispositivo físico  

## 🏗️ Arquitectura  
Este proyecto sigue el patrón **MVVM** para mantener un código modular y escalable.  

## 🏗️ Estructura del Proyecto  
El proyecto sigue la arquitectura **MVVM (Model-View-ViewModel)** con las siguientes capas:  

```
ToDoCompose/
│── app/
│   ├── src/main/java/com/example/todocompose/
│   │   ├── data/            # Capa de datos
│   │   │   ├── model/       # Definición de entidades y objetos de datos
│   │   │   ├── repository/  # Lógica de acceso a datos y repositorios
│   │   │   ├── local/       # Base de datos Room y DAO
│   │   │   ├── datastore/   # Persistencia con DataStore
│   │   │   ├── di/          # Inyección de dependencias con Dagger Hilt
│   │   ├── ui/              # Capa de presentación (Jetpack Compose)
│   │   │   ├── components/  # Componentes reutilizables de la UI
│   │   │   ├── screens/     # Pantallas de la aplicación
│   │   ├── viewmodel/       # ViewModels para gestionar la lógica de UI
│   │   ├── utils/           # Clases auxiliares y extensiones
│   ├── AndroidManifest.xml
│── build.gradle.kts
│── settings.gradle.kts
```

### 📌 Explicación de Paquetes:  
- **data/model/** → Define las entidades que usa Room y la UI  
- **data/repository/** → Contiene la lógica de acceso a datos  
- **data/local/** → Implementa la base de datos Room y los DAO  
- **data/datastore/** → Maneja la persistencia de preferencias con DataStore  
- **data/di/** → Configura **Dagger Hilt** para la inyección de dependencias  
- **ui/components/** → Contiene elementos reutilizables como botones y tarjetas  
- **ui/screens/** → Define las diferentes pantallas de la aplicación  
- **viewmodel/** → Implementa los **ViewModels**, usando Flow y LiveData  
- **utils/** → Contiene funciones auxiliares y extensiones  

## 📌 Próximas Mejoras  
- 📌 Integración con **Firebase** para sincronización en la nube  
- 📌 Notificaciones para tareas pendientes  
- 📌 Widgets en pantalla de inicio  

## 📝 Contribuciones  
¡Las contribuciones son bienvenidas! Si deseas mejorar la app, sigue estos pasos:  
1. Haz un **fork** del repositorio  
2. Crea una nueva rama:  
   ```sh
   git checkout -b feature-nueva
   ```
3. Realiza tus cambios y haz un **commit**:  
   ```sh
   git commit -m "Añadir nueva función"
   ```
4. Sube los cambios:  
   ```sh
   git push origin feature-nueva
   ```
5. Abre un **Pull Request**  

## 📄 Licencia  
Este proyecto está bajo la licencia **MIT**.  

📌 *¡Dale una ⭐ al repositorio si te gusta!* 🚀  
