# Challenge 1 – Web Application with Image Upload

This project is a simple web application built with **Java Spring Boot** and **HTML**, allowing users to upload and delete images. It was created as part of Challenge 1.

## 🚀 Features
- Upload images through the web interface  
- Delete previously uploaded images  
- Display uploaded files  
- Basic validation  
- Access control (IP whitelisting)

## 🔐 IP Whitelisting
Access to the web application is strictly limited to the following VPN IP address:

**20.218.226.24**

Only requests coming from this IP are allowed to access the application.  
This restriction can be configured or modified in the application’s security layer (e.g., filter, interceptor, or Spring Security config).

## 🗂️ Technology Stack
- Java 17+
- Spring Boot
- HTML / CSS
- Maven
- Thymeleaf (optional)
- Local file storage for images


