# 📊 mga-records-crud-service

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4%2F4.0-6DB33F?logo=springboot)](https://spring.io/)
[![Java Version](https://img.shields.io/badge/Java-25-007396?logo=openjdk)](https://openjdk.org/)
[![Build Tool](https://img.shields.io/badge/Build-Gradle%209.4.1-066DA5?logo=gradle)](https://gradle.org/)
[![GraalVM](https://img.shields.io/badge/GraalVM-Native%20Image-orange?logo=graalvm)](https://www.graalvm.org/)
[![Database](https://img.shields.io/badge/Database-MongoDB-47A248?logo=mongodb)](https://www.mongodb.com/)
[![Network](https://img.shields.io/badge/Security-Cluster--Internal-red)](https://kubernetes.io/docs/concepts/services-networking/network-policies/)

## 📌 Service Overview

`mga-records-crud-service` is a cluster-internal MongoDB data-access microservice responsible for persistence, attendance history storage, posture analysis feedback logging, and interaction persistence across records domain entities.

It operates strictly beneath `mga-records-service` and `mga-chat-bot-trainer-service` in a CQRS/Data-Layer Separation pattern, firewalled behind Kubernetes NetworkPolicy.

### 🏛️ Architectural Features
* Firewalled CQRS Isolation: Completely firewalled from public ingress; invoked strictly via internal RestClient calls from mga-records-service and mga-chat-bot-trainer-service.
* GraalVM Native Image Packaging: Built Ahead-Of-Time (AOT) with Java 25 & Gradle 9.4.1 for sub-second startup and low memory footprint in K8s.
* Dedicated Persistence Layer: Direct access to isolated Records MongoDB database for check-ins, AI chatbot interactions, and posture analysis records.

---

## 🛠️ Technical Specifications
* Port: 8082 (Cluster-Internal)
* Language/Build: Java 25 | Gradle 9.4.1
* Persistence: MongoDB Atlas / Compass (Records DB)
* Shared Contract: mga-all-models-jars

---

## ⚙️ Build & Deployment Instructions
# Standard JVM Run
./gradlew bootRun

# GraalVM Native Image Compilation
./gradlew nativeCompile

# Paketo OCI Native Container Image
./gradlew bootBuildImage
