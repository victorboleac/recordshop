# **Record Shop API**

Welcome to the **Record Shop API**, a backend application designed to manage the inventory of a record shop. The API allows users to create, read, update, delete, and search for albums.

## **Features**
- List all albums in the shop's inventory.
- Retrieve details of a specific album by its ID.
- Add new albums to the inventory.
- Update album information.
- Delete albums from the inventory.
- Search for albums by artist, release year, genre, or album name.
- Health check endpoint for monitoring.

## **Technologies Used**
- **Java 21**
- **Spring Boot 3.2.1**
- **H2 In-Memory Database** (for testing)
- **PostgreSQL** (for production)
- **Docker** (for containerization)
- **Google Cloud Platform (GCP)** (for deployment)
- **Swagger** (for API documentation)

## **Setup Instructions**

### **Prerequisites**
- **Java 21** installed.
- **Maven** installed.
- **Docker** installed.
- **GCP CLI** configured (if deploying to GCP).
- **PostgreSQL** installed and running locally (optional for production setup).

### **Running Locally**
1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd recordshop
2. Open project in Intellij
3. Run the application
4. Access the API:
- **API Endpoints**: [http://localhost:8080](http://localhost:8080)
- **Swagger Documentation**: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)


## **Endpoints**

### **Albums**
| Method | Endpoint                | Description                |
|--------|-------------------------|----------------------------|
| GET    | `/api/v1/albums`        | List all albums.           |
| GET    | `/api/v1/albums/{id}`   | Get album by ID.           |
| POST   | `/api/v1/albums`        | Add a new album.           |
| PUT    | `/api/v1/albums/{id}`   | Update an album by ID.     |
| DELETE | `/api/v1/albums/{id}`   | Delete an album by ID.     |
| GET    | `/api/v1/albums/search` | Search for albums.         |

### **Health Check**
| Method | Endpoint       | Description                  |
|--------|----------------|------------------------------|
| GET    | `/api/v1/health` | Returns the health of the API.|

---

## **Swagger Documentation**

- Swagger provides interactive API documentation, accessible at:  
  [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## **Future Enhancements**

- **Add User Authentication and Authorization**  
  Implement a secure authentication and authorization mechanism to restrict access to certain API endpoints based on user roles.

- **Implement Rate-Limiting for API Endpoints**  
  Add rate-limiting to prevent abuse and ensure fair usage of the API.

