# Core Banking Solution Application - Java Spring Boot

This **Core Banking Solution Application** is a full-stack project developed using **Java Spring Boot** for the backend and **React** for the frontend. It provides essential banking functionalities such as user authentication, account management, transaction processing, and transaction history viewing. The backend is built with a robust three-layer architecture and uses **PostgreSQL** as the database server.

## Features

### 1. **Authentication & Registration**
- Secure client registration and login functionalities.
- Password encryption using Spring Security.

### 2. **Bank Transactions**
- Perform **deposit** and **withdrawal** operations on bank accounts.
- Track and retrieve all transactions for each client.

### 3. **Account Management**
- Open and close bank accounts.
- A client can have multiple accounts.

### 4. **Transaction History**
- View transaction history for each client, including deposits and withdrawals.

## Architecture

The backend follows a **3-layer architecture**:
1. **Controller Layer**: Handles HTTP requests and responses.
   - **TransactionController**: Manages transaction-related operations.
   - **ClientController**: Handles client authentication and registration.
   - **AccountController**: Oversees account creation and closure.

2. **Service Layer**: Contains business logic.
   - **TransactionService**: Manages transactions and transaction history.
   - **ClientService**: Handles client-related operations, including authentication.
   - **AccountService**: Manages account operations like opening and closing accounts.

3. **Repository Layer**: Interfaces with the PostgreSQL database.
   - **TransactionRepository**: Manages transaction data.
   - **ClientRepository**: Stores and manages client information.
   - **AccountRepository**: Handles account-related data.

## API Endpoints

The application exposes **REST APIs** to interact with the system:
- **POST**: Create resources (e.g., register a client, make a deposit).
- **GET**: Retrieve resources (e.g., view transaction history, account details).
- **PUT**: Update resources (e.g., modify account details).
- **DELETE**: Remove resources (e.g., close an account).

## Technology Stack
- **Java Spring Boot**: Backend framework for building RESTful APIs.
- **PostgreSQL**: Relational database for storing client, account, and transaction data.
- **Spring Security**: For secure authentication and password encoding.
- **React**: Frontend framework for building the user interface.
- **CORS**: To allow the frontend (React) and backend (Spring Boot) to communicate seamlessly.

## Setup & Installation

### Prerequisites
- JDK 11 or higher
- PostgreSQL
- Node.js and npm (for React frontend)

### Backend Setup (Spring Boot)
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/core-banking-solution.git
   cd core-banking-solution
