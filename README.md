# MultiThreaded-WebServer
A robust multithreaded TCP web server in Java that efficiently manages multiple client connections with personalized responses.

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Running the Server](#running-the-server)
  - [Running the Client](#running-the-client)
- [Code Structure](#code-structure)
- [Usage](#usage)
- [Contributing](#contributing)
- [Acknowledgments](#acknowledgments)

## Introduction
The **Multithreaded Web Server** is a simple yet powerful TCP server written in Java, designed to handle multiple client connections simultaneously. It utilizes multithreading to ensure that each client is served in its own thread, providing a responsive and efficient user experience.

## Features
- Handles multiple clients using a thread pool.
- Each client receives a personalized greeting message.
- Graceful server shutdown to finish current connections.
- Configurable thread pool size for optimal performance.

## Technologies Used
- Java SE
- Socket Programming
- Multithreading
- ExecutorService

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- An IDE or text editor (e.g., IntelliJ IDEA, Eclipse, Visual Studio Code)

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/multithreaded-webserver.git
   cd multithreaded-webserver
2. Compile the server and client:
   ```bash
   javac Server.java Client.java
   
### Running the Server
To start the server, navigate to the directory containing the compiled Server.class and run: ***java Server***
The server will start listening on port 8010. You can adjust the port in the Server class if needed.

### Running the Client
To connect to the server, navigate to the directory containing the compiled Client.class and run: ***java Client***
You can modify the number of clients in the Client class to test the server's handling of multiple connections.

### Code Structure
multithreaded-webserver/
└──> Server.java        # Contains the server implementation
└──> Client.java        # Contains the client implementation

### Usage
Start the server before running the client.
Each client will connect to the server and receive a greeting message.
Monitor the server console for connection logs.

### Contributing
Contributions are welcomed! Please follow these steps:

1. Fork the repository.
2. Create a new branch:
   bash: git checkout -b feature/YourFeature
3. Make your changes and commit:
   bash: git commit -m "Add your feature"
4. Push to the branch:
   bash: git push origin feature/YourFeature
5. Create a pull request.

### Acknowledgments
Inspired by examples of TCP servers and multithreading concepts in Java.
Special thanks to online communities for their valuable resources and support.

### Developer
This project is built by Me: ***Shobhit Singh***
