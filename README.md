# Tactical DDD with Java & Spring Modulith

Welcome to the official repository for the "Tactical Domain-Driven Design with Java & Spring" workshop\! This space contains all the source code, examples, and materials for our session. Let's dive into building well-structured, modular applications.

## 📋 Table of Contents

- [🧐 About the Workshop](#-about-the-workshop)
- [👨‍🏫 Your Instructor](#-your-instructor)
- [🎯 What You'll Learn](#-what-youll-learn)
- [🛠️ Getting Started](#️-getting-started)
- [📝 Core Concepts & Fundamentals](#-core-concepts--fundamentals)
- [🚀 Tools & Technologies Deep Dive](#-tools--technologies-deep-dive)
- [🎯 Event-Driven Architecture & Communication Patterns](#-event-driven-architecture--communication-patterns)
- [❓ Framework Comparison & Integration](#-framework-comparison--integration)
- [📚 Resources & References](#-resources--references)

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

## 📝 Core Concepts & Fundamentals

### **Core Terminology & Object Lifecycle**

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

### **🏷️ Annotations vs Interfaces for Type Marking**

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

## 🚀 Tools & Technologies Deep Dive

This section explores the key tools we'll be using throughout the workshop, their benefits, and how they work together to enable effective Domain-Driven Design in Java applications.

### **jMolecules Benefits**
- **Compile-time Validation**: The APT dependency provides immediate feedback for DDD rule violations
- **Framework Integration**: Seamless integration with Spring, Jackson, and persistence frameworks
- **Type Safety**: Generic interfaces ensure compile-time verification of domain relationships
- **Boundary Handling**: While value objects (e.g., `Email` instead of `String`) require additional serialization work at boundaries, jMolecules simplifies this through built-in methods like `writeValueAsJson()` on interfaces, reducing boilerplate code
- **JPA Integration**: Excellent JPA integration reduces heavy persistence annotations on domain models, eliminating the need for separate Record/persistence classes and reducing mapping layers

### **Spring Modulith Benefits**
- **Package-based Modularity**: Adds modularity based on package structure, making logical boundaries explicit
- **Selective Module Startup**: Enables starting specific verticals (modules) independently for development and testing
- **Isolated Testing**: Supports running tests only within module boundaries and their dependencies, improving test focus and performance
- **Module Verification**: Validates module boundaries and dependencies at build time to prevent architectural violations
- **Smart Test Execution**: JUnit extension analyzes the module dependency graph and executes only tests that require execution based on changed modules and their transitive dependencies, optimizing test runtime

-----

## 🎯 Event-Driven Architecture & Communication Patterns

Understanding how modules communicate is fundamental to building maintainable modular applications. This section explores the benefits of event-driven architecture and the different communication patterns available in Spring Modulith.

### **Decoupling Through Events**
Consider a typical checkout scenario where a traditional approach might have the checkout service directly orchestrating calls to:
- **Inventory Service** (to update stock levels)
- **Notification Service** (to send confirmation emails)
- **Rewards Service** (to update loyalty points)

**The Orchestration Problem**: Why should the checkout service be responsible for knowing about and coordinating all these downstream concerns? This creates tight coupling and makes the checkout service responsible for business logic that doesn't belong to it.

**Event-Driven Solution**: Instead of direct orchestration, the checkout service publishes a `CheckoutCompleted` event. Each interested service:
- **Listens** for the event autonomously
- **Decides** independently whether and how to handle it
- **Maintains** its own business logic without external dependencies

This approach promotes loose coupling, better separation of concerns, and easier system evolution as new services can subscribe to events without modifying existing code.

### **Communication Patterns Between Modules**
Spring Modulith supports different communication patterns, each with distinct characteristics:

#### **1. Direct Calls (Synchronous)**
- **Transaction Scope**: Single transaction spans across modules
- **Coupling**: Tight coupling between caller and callee
- **Use Case**: When immediate consistency is required and modules are closely related

#### **2. Synchronous Events**
- **Transaction Scope**: Single transaction spans both publisher and subscriber modules
- **Coupling**: Looser coupling through event abstraction
- **Use Case**: When you need immediate processing but want to decouple through events

#### **3. Asynchronous Events**
- **Transaction Scope**: No transaction spanning between modules
- **Persistence**: Events are persisted for reliability
- **Consistency**: Eventually consistent across modules
- **Use Case**: When modules can operate independently and eventual consistency is acceptable

This flexibility allows you to choose the appropriate communication pattern based on your consistency requirements and coupling preferences.

-----

## ❓ Framework Comparison & Integration

As we explore both jMolecules and Spring Modulith in this workshop, it's natural to wonder about their relationship and how they complement each other. Here are key questions we'll address:

### **Spring Modulith vs jMolecules**
- **Feature Overlap**: Do both frameworks provide similar compile-time rule checking capabilities?
- **Complementary Usage**: If using jMolecules, is Spring Modulith still necessary?
- **Evolution Path**: Is one framework the successor to the other, or do they serve different purposes?

These questions highlight the importance of understanding the relationship between these complementary DDD tooling approaches.

-----

## 📚 Resources & References

### **Official Documentation**
* **[Spring Modulith Reference Documentation](https://docs.spring.io/spring-modulith/reference/index.html)** - Complete guide to Spring Modulith features and capabilities
* **[jMolecules Official Site](https://jmolecules.org/)** - Library for expressing DDD patterns in Java code

### **Code Examples & Repositories**
* **[odrotbohm/tactical-ddd-workshop](https://github.com/odrotbohm/tactical-ddd-workshop)** - Workshop materials and exercises
* **[Spring RESTBucks](https://github.com/odrotbohm/spring-restbucks)** - Comprehensive examples of Spring tooling including Spring Modulith implementations
* **[Oliver Drotbohm's Blog](https://odrotbohm.de/)** - Articles on software architecture, DDD, and Spring ecosystem

### **Essential Reading**
* **[Sustainable Software Architecture: Analyze and Reduce Technical Debt](https://www.amazon.de/-/en/Sustainable-Software-Architecture-Analyze-Technical/dp/3864906733)** by Carola Lilienthal (August 2019)

  Contains excellent insights into the cognitive reasons behind software complexity and its impact on development teams.

### **Recommended Video**
* **[Power Use of Value Objects in DDD](https://www.infoq.com/presentations/Value-Objects-Dan-Bergh-Johnsson/)** by Dan Bergh Johnsson

  An essential presentation demonstrating how proper use of value objects can revolutionize program architecture, making it simpler, more readable, and testable.

### **Must-Read Resource**
* **[Modular Monoliths](https://static.simonbrown.je/modular-monoliths.pdf)** by Simon Brown

  Essential reading on cohesion principles and different architectural approaches to building modular systems.

-----

## 🎓 Conclusion

This workshop provides a comprehensive exploration of modern Java development practices that bridge the gap between Domain-Driven Design theory and practical implementation. By combining the expressive power of jMolecules with the structural benefits of Spring Modulith, you'll learn to build applications that are not only technically sound but also closely aligned with business domain concepts.

The techniques and patterns covered here will help you create applications that are:
- **Maintainable**: Clear boundaries and explicit domain concepts reduce cognitive load
- **Testable**: Modular architecture enables focused, fast-running tests
- **Evolvable**: Event-driven communication and loose coupling support system growth
- **Expressive**: Code that reflects domain language and business rules

We hope you enjoy this journey into tactical DDD with modern Java tooling!

---

**Happy Coding!** 🚀

