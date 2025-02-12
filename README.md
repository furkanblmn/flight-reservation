# ✈️ Flight Reservation System (Uçuş Rezervasyon Sistemi)

Bu proje, **Spring Boot (Java 11), Spring Data JPA ve MySQL** kullanarak geliştirilmiş **basit bir uçuş rezervasyon sistemidir**.

## 📌 Kullanılan Teknolojiler
- **Spring Boot 2.7.17**
- **Spring Data JPA**
- **MySQL**
- **Lombok**
- **Gradle**
- **Postman**

## 🚀 Projeyi Çalıştırma
### 1️⃣ MySQL Veritabanını Başlat
MySQL sunucun açık olmalı ve aşağıdaki bilgileri içeren bir veritabanı oluşturulmalı.

#### **application.properties**
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/flight_db
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

### 2️⃣ Gradle ile Build Al ve Uygulamayı Çalıştır
```sh
./gradlew clean build
./gradlew bootRun
```
📌 **Windows için:**
```sh
gradlew clean build
gradlew bootRun
```

## 🛠️ API Kullanımı
### 1️⃣ Uçuş İşlemleri
#### **Uçuş Ekleme**
```http
POST /api/flights
```
**Body (JSON)**
```json
{
  "name": "Istanbul - Berlin",
  "description": "Direct Flight",
  "price": 250.0
}
```

#### **Tüm Uçuşları Listeleme**
```http
GET /api/flights
```

#### **ID'ye Göre Uçuş Listeleme**
```http
GET /api/flights/{id}
```

#### **Uçuş Güncelleme**
```http
PUT /api/flights/{id}
```
**Body (JSON)**
```json
{
  "name": "Istanbul - Berlin (Updated)",
  "description": "Direct Flight - Updated",
  "price": 280.0
}
```

### 2️⃣ Koltuk İşlemleri
#### **Uçuşa Koltuk Ekleme**
```http
POST /api/seats
```
**Body (JSON)**
```json
{
  "seatNumber": "12A",
  "price": 50.0,
  "flightId": 1
}
```

#### **Uçuşun Koltuklarını Listeleme**
```http
GET /api/seats/{flightId}
```

#### **Koltuk Güncelleme**
```http
PUT /api/seats/{id}
```
**Body (JSON)**
```json
{
  "seatNumber": "12B",
  "price": 60.0,
  "available": false
}
```

### 3️⃣ Rezervasyon İşlemleri
#### **Koltuk Satın Alma (Rezervasyon Yapma)**
```http
POST /api/reservations
```
**Body (JSON)**
```json
{
  "flightId": 1,
  "seatId": 1,
  "passengerId": 1,
  "confirmed": true
}
```

#### **Yolcuya Ait Rezervasyonları Listeleme**
```http
GET /api/reservations/passenger/{passengerId}
```

---

**📌 Proje Başarıyla Çalıştığında**  
Spring Boot uygulaması **`http://localhost:8080`** adresinde çalışacaktır.
```sh
Application started on port 8080...
```
