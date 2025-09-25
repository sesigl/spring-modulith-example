# Tactical DDD with Java & Spring Modulith

Welcome to the official repository for the "Tactical Domain-Driven Design with Java & Spring" workshop\! This space contains all the source code, examples, and materials for our session. Let's dive into building well-structured, modular applications.

-----

## 🧐 About the Workshop

The core building blocks of **Tactical Domain-Driven Design (DDD)** provide a powerful vocabulary for creating sophisticated domain models. They define semantics, enforce rules, and guide developers in structuring domain code to implement complex business logic. However, translating these concepts into Java code presents unique technical challenges.

In this hands-on workshop, we will explore modern approaches and tools that empower developers to build rich, expressive domain models in Java:

* **jMolecules**: This library enables you to express fundamental DDD patterns directly in your code. We will explore its integrations with widely-used technologies like Spring, Jackson, and various persistence frameworks.
* **Spring Modulith**: A project from the Spring ecosystem designed to help developers build well-structured, modular applications. We will cover how it supports the implementation of logical modules, their interaction via events, how to test modules individually, and how to automatically generate documentation from your application's structure.

-----

## 👨‍🏫 Your Instructor

**Oliver Drotbohm** has been a key member of the Spring engineering team for 15 years. His work focuses on software architecture, Domain-Driven Design, REST, and persistence technologies. He is the author of the upcoming book, **“Modulithic Applications with Spring”**, scheduled for release in 2025.

-----

## 🎯 What You'll Learn

* How to apply the fundamental patterns of **Tactical DDD** (e.g., Aggregates, Entities, Value Objects) in a modern Spring application.
* How to use **jMolecules** to make your domain concepts explicit and verifiable in your codebase.
* How to structure a Spring Boot application as a **well-defined monolith (Modulith)**.
* Techniques for **asynchronous communication between modules** using application events.
* How to write focused **integration tests for individual modules** to ensure they are self-contained and functionally correct.
* How to leverage Spring Modulith to **automatically generate documentation** for your application's modular architecture.

-----

## 🛠️ Getting Started

To get started with the code in this repository, please follow these steps.

### **Prerequisites**

* Java 17+
* Maven 3.8+ or Gradle 8+
* Your favorite IDE (e.g., IntelliJ IDEA, VSCode)

### **Clone and Build**

1.  **Clone the repository:**

    ```bash
    git clone <repository-url>
    cd <repository-directory>
    ```

2.  **Build the project:**
    Each exercise is a standard Spring Boot application. You can build it from the root or within its specific directory.

    ```bash
    mvn clean install
    ```

3.  **Run an exercise:**
    Navigate to an exercise module and run it using the Spring Boot Maven plugin:

    ```bash
    cd 01-first-module
    mvn spring-boot:run
    ```

-----

## 📚 Resources

* **[Spring Modulith Reference Documentation](https://docs.spring.io/spring-modulith/reference/index.html)**
* **[jMolecules Official Site](https://jmolecules.org/)**
* **[Oliver Drotbohm's Blog](https://odrotbohm.de/)**
* **[odrotbohm/tactical-ddd-workshop](https://github.com/odrotbohm/tactical-ddd-workshop)**
