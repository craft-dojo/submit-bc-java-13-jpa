# BC Java - Assignment 13 - JPA

* [BC Java - Assignment 13 - JPA](#bc-java---assignment-13---jpa)
    * [Prerequisites](#prerequisites)
        * [Getting Start](#getting-start)
        * [Project Structure](#project-structure)
    * [Questions](#questions)
        * [Bài 1: Quản lý Sinh viên (CRUD cơ bản)](#bài-1-quản-lý-sinh-viên-crud-cơ-bản)
        * [Bài 2: Quản lý Sản phẩm + Tìm kiếm + Phân trang](#bài-2-quản-lý-sản-phẩm--tìm-kiếm--phân-trang)
        * [Bài 3: One-to-Many: Danh mục & Sản phẩm](#bài-3-one-to-many-danh-mục--sản-phẩm)
    * [Author](#author)

## Prerequisites

- IDE: IntelliJ
- Java 21
- Docker
- MySQL

### Getting Start

- Bước 1: Chạy [docker-compose.yaml](docker-compose.yaml) để cài đặt môi trường cho MySQL
- Bước 2: Connect database với DBBearer/DataGrip và chạy [init.sql](init.sql) để tạo schema và sample data
    ```txt
    port: 3307
    user: root
    password: Root@1234
    ```
- Bước 3: Start project
- Bước 4: Import [Postman Collection](BC%20Java%20-%20Assignment%2013%20-%20JPA.postman_collection.json) vào Postman để
  test APIs.

### Project Structure

```txt
./
│   .gitattributes
│   .gitignore
│   BC Java - Assignment 13 - JPA.postman_collection.json
│   docker-compose.yaml
│   init.sql
│   mvnw
│   mvnw.cmd
│   pom.xml
│   README.md
│
├───.mvn
│   └───wrapper
│           maven-wrapper.properties
│
└───src
    └───main
        ├───java
        │   └───com
        │       └───cybersoft
        │           └───assign_13_jpa
        │               │   Assign13JpaApplication.java
        │               │
        │               ├───config
        │               ├───controller
        │               ├───dto
        │               ├───entity
        │               ├───repository
        │               ├───request
        │               ├───response
        │               └───services
        │                   └───imp
        └───resources
                application.yaml

```

## Questions

### Bài 1: Quản lý Sinh viên (CRUD cơ bản)

- **Mục tiêu**:
    - Xây dựng API cho quản lý sinh viên, bao gồm các chức năng:
        - Lấy danh sách sinh viên
        - Tìm sinh viên theo ID
        - Thêm sinh viên mới
        - Cập nhật sinh viên
        - Xoá sinh viên

### Bài 2: Quản lý Sản phẩm + Tìm kiếm + Phân trang

- **Mục tiêu**:
    - Xây dựng API CRUD cho `Product`, hỗ trợ phân trang và tìm kiếm theo tên.

### Bài 3: One-to-Many: Danh mục & Sản phẩm

- **Mục tiêu**:
    - Mỗi danh mục (`Category`) chứa nhiều sản phẩm (`Product`).
    - Xây dựng API CRUD và lấy sản phẩm theo danh mục

## Author

Khang Nguyen
