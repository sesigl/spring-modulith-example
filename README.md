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

### **Recommended Video**
* **[Power Use of Value Objects in DDD](https://www.infoq.com/presentations/Value-Objects-Dan-Bergh-Johnsson/)** - Dan Bergh Johnsson refreshes the listeners' memory on using value objects showing by example how their good use can revolutionize a program's architecture, simplifying it, making it more readable and testable, in a word, better.

### **Must-Read Resource**
* **[Modular Monoliths](https://static.simonbrown.je/modular-monoliths.pdf)** - Essential reading on cohesion and different architectural approaches to building modular systems

-----

## 📝 Core Terminology & Object Lifecycle

Understanding the lifecycle and management patterns of domain objects is crucial for implementing effective DDD patterns:

### **Injectable Objects**
- **Definition**: Container-managed components with singleton lifecycle
- **Examples**: Services, Repositories, Domain Services, Application Services
- **Characteristics**: Initialized once by the DI container, shared across requests
- **Usage**: Stateless operations, coordinating business logic, data persistence

### **Newable Objects**
- **Definition**: Application-controlled objects created per request/operation
- **Examples**: Aggregates, Entities, Value Objects, Domain Events
- **Characteristics**: Created fresh for each business operation, encapsulate state and behavior
- **Usage**: Representing business concepts, enforcing invariants, modeling domain logic

This distinction helps maintain clear separation between infrastructure concerns (injectables) and domain modeling (newables).

-----

## 📖 Book Recommendations

https://www.amazon.de/-/en/Sustainable-Software-Architecture-Analyze-Technical/dp/3864906733

Sustainable Software Architecture: Analyze and Reduce Technical Debt Taschenbuch – 22. August 2019
Englisch Ausgabe  von Carola Lilienthal (Autor)

→ contains a good section about cognitive reasons about the impact of complex software

-----

## 🏷️ Annotations vs Interfaces for Type Marking

When marking classes as domain types, you have two primary approaches, each with distinct trade-offs:

### **Annotations Approach**
- **Classpath Requirement**: Annotations must **not** be present in the runtime classpath
- **Use Case**: Compile-time metadata that doesn't affect runtime behavior
- **Example**: `@Entity`, `@ValueObject` for build-time validation only

### **Interfaces Approach**
- **Classpath Requirement**: Interfaces **must** be present in the runtime classpath
- **Compiler Benefits**: Generic interfaces enable early validation through type checking
- **Example**: `AggregateRoot<Order, OrderId>` allows the compiler to verify type relationships

### **Annotation Processing Trade-offs**
The choice between immediate compiler feedback (APT) and deferred validation involves strategic considerations:

- **APT Benefits**: Faster feedback loop, immediate error detection
- **Deferred Validation Benefits**: Flexibility to accept temporarily invalid states during development
- **ArchUnit Strategy**: Snapshot-based validation where only new violations fail the build

-----

## 🚀 Key Benefits

### **jMolecules**
- **Compile-time Validation**: The APT dependency provides immediate feedback for DDD rule violations
- **Framework Integration**: Seamless integration with Spring, Jackson, and persistence frameworks
- **Type Safety**: Generic interfaces ensure compile-time verification of domain relationships
- **Boundary Handling**: While value objects (e.g., `Email` instead of `String`) require additional serialization work at boundaries, jMolecules simplifies this through built-in methods like `writeValueAsJson()` on interfaces, reducing boilerplate code
- **JPA Integration**: Excellent JPA integration reduces heavy persistence annotations on domain models, eliminating the need for separate Record/persistence classes and reducing mapping layers

### **Spring Modulith**
- **Package-based Modularity**: Adds modularity based on package structure, making logical boundaries explicit
- **Selective Module Startup**: Enables starting specific verticals (modules) independently for development and testing
- **Isolated Testing**: Supports running tests only within module boundaries and their dependencies, improving test focus and performance
- **Module Verification**: Validates module boundaries and dependencies at build time to prevent architectural violations

-----

## ❓ Framework Comparison Questions

### **Spring Modulith vs jMolecules**
- **Feature Overlap**: Do both frameworks provide similar compile-time rule checking capabilities?
- **Complementary Usage**: If using jMolecules, is Spring Modulith still necessary?
- **Evolution Path**: Is one framework the successor to the other, or do they serve different purposes?

These questions highlight the importance of understanding the relationship between these complementary DDD tooling approaches.

