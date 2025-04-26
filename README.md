# BudgetBuddy

BudgetBuddy is a service for managing user expenses. It allows multiple users to manage multiple accounts, track their spending. 
The application consists of a backend server built with Java Spring Boot and a frontend Android application. 
The database is hosted in a Dockerized MySQL container.

## Features
- Multi-user support with secure authentication.
- Manage multiple accounts and track expenses.
- Responsive Android application for easy access.
- Scalable backend with RESTful APIs.

## Technologies Used
- **Backend**: Java Spring Boot
- **Frontend**: Android (Java/Kotlin)
- **Database**: MySQL (Dockerized)
- **Containerization**: Docker

## Requirements
- Java (JDK 11 or higher)
- Docker
- Android Studio (for frontend development)

## Setup Instructions

### Backend
1. Clone the repository:
   ```bash
    git clone <repository-url>
    cd BudgetBuddy
   ```

2. Start the Dockerized MySQL container:
   ```bash
    docker-compose -f ./db/docker-compose.yml up -d
   ```

3. Build the Spring Boot application:
   ```bash
    ./budget_buddy/mvnw clean install
   ```

4. Run the Spring Boot application:
   ```bash
    java -jar ./budget_buddy/target/budgetbuddy-<version>.jar
   ```

### Frontend
1. Open the Android project in Android Studio.
2. Sync the Gradle files and resolve dependencies.
3. Build and run the application on an emulator or physical device.

## Contributing
Contributions are welcome! Please fork the repository and submit a pull request.

## License
This project is licensed under the MIT License. See the `LICENSE` file for details.