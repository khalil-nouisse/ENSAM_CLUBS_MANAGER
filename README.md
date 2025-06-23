# Ensam Clubs Manager

Welcome to the **Clubs Manager** system, an innovative platform designed to streamline the management and coordination of student clubs at ENSAM Meknès. Known for its vibrant student life and diverse range of clubs.

## Features

- **User Authentication**: Login and registration for students and admin/manager.
- **Role-based Access**:
  - **Admin/Manager**: Can add, edit, delete, and manage clubs, including club details and images. Only admin can access the manager page.
  - **User/Member**: Can view clubs, see club details, and join clubs.
- **Club Management**: Add, update, and remove clubs with details such as name, category, state, description, and logo.
- **Dynamic Club List**: Club list updates in real-time for both users and managers.
- **About Page**: Information about the platform and ENSAM Meknès.
- **Modern JavaFX UI**: Clean, responsive interface with FXML-based views for login, user home, admin home, manager page, and about screens.

## Screenshots

![image](https://github.com/user-attachments/assets/1355c5b3-0eb6-4c95-b2e4-6b08dddcb575)

![image](https://github.com/user-attachments/assets/18a765f3-2d04-4081-afe3-94f97e5938ef)


## Getting Started

### Prerequisites
- Java 17 or higher (tested with JDK 23)
- Maven

### Installation
1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-username/Ensam_Clubs2.git
   cd Ensam_Clubs2
   ```
2. **Build the project:**
   ```bash
   mvn clean install
   ```
3. **Run the application:**
   ```bash
   mvn javafx:run
   ```

### Default Admin Credentials
- **Username:** `admin`
- **Password:** `admin`

## Project Structure

- `src/main/java/com/ensam/`
  - `Controllers/` - JavaFX controllers for UI logic
    - `Admin/` - Manager/admin controllers
    - `User/` - User/member controllers
  - `Views/` - View factory and helpers
  - `Backend/` - Data models and database logic
- `src/main/resources/Fxml/` - FXML files for UI screens
  - `Admin/` - Admin/manager screens
  - `User/` - User/member screens
- `pom.xml` - Maven project file with dependencies

## Main Dependencies
- JavaFX (controls, FXML)
- FontAwesomeFX
- FormsFX

## Data Model
Each club contains:
- ID
- Name
- Category (e.g., Technical, Art & Culture, Entrepreneurship, Creation, Sociale)
- State (Active/Inactive)
- Description
- Logo/Image
- Members and executive office (backend)

## Acknowledgements
- ENSAM Meknès

