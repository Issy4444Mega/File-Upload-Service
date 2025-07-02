# File-Upload-Service

## Description 
Web application for uploading files with automatic classification by type (videos, photos, other files). Built with Spring Boot and simple UI.

## Features
✔ Upload files in 3 categories  
✔ Organize into named folders  
✔ Preview file information before upload  
✔ Automatic backup naming  
✔ Remove files from queue   

## Technology Stack
- **Backend**: 
  - Java 17
  - Spring Boot 3
- **Frontend**:
  - HTML5/CSS3
  - Vanilla JS
- **Storage**: Local filesystem storage

## Configuration
Settings (`application.properties`):
```properties
# Application port
server.port=8080

# Storage paths (relative to app root)
storage.photos=./uploads/photos
storage.videos=./uploads/videos
storage.others=./uploads/others

# File size limits
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB
```

## Installation
1. Clone repository:
```bash
git clone https://github.com/your-repo/file-upload-service.git
cd file-upload-service
```
2. Build application:
```
mvn clean package
```
3. Run application:
```
java -jar target/file-upload-service-0.0.1-SNAPSHOT.jar
```
4. Access in browser:
```
http://localhost:8080
```

## Usage Instructions
1. Select file type 
- Video
- Photo
- Other
2. Specify folder name (optional)
3. Click "Select files" and choose files
4. Review selected files list
5. Click "Upload all" to complete

Files will be saved in:
- ```./uploads/photos/```
- ```./uploads/videos/```
- ```./uploads/others/```

<div align="center">
  <img src="![image](https://github.com/user-attachments/assets/5f4398ff-4619-461a-a4f4-3c90c0bea74b)">
</div>


