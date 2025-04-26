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

# Android app screenshots
![image](https://github.com/user-attachments/assets/b651ef1d-a7a8-4157-bdd5-3e08b749839b)
![image](https://github.com/user-attachments/assets/d6c42590-4f8f-49ea-b156-6775829bcf98)
![image](https://github.com/user-attachments/assets/fcb1854e-e09c-4e2f-ad2e-2fe78092dcd2)
![image](https://github.com/user-attachments/assets/7efd3dba-bf77-47d2-bb83-47f9e90863e0)
![image](https://github.com/user-attachments/assets/982033e3-66eb-4b81-9a05-f3e1d96b2038)


