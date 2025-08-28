# 📦 Product Service

[![Java](https://img.shields.io/badge/Java-17-orange?logo=java)](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.3-brightgreen?logo=spring)](https://spring.io/projects/spring-boot)
[![H2 Database](https://img.shields.io/badge/H2-Test%20DB-yellowgreen)](https://www.h2database.com/)
[![SQLite](https://img.shields.io/badge/SQLite-Local%20DB-lightblue)](https://www.sqlite.org/)
[![JSON:API](https://img.shields.io/badge/JSON%3AAPI-Compliant-blueviolet)](https://jsonapi.org/)

## 📋 Descripción

Microservicio encargado de la gestión de productos. Permite crear, obtener y listar productos, exponiendo una API RESTful siguiendo el estándar JSON:API. Este servicio es fundamental para proporcionar la información base de los productos que luego son gestionados por el servicio de inventario.

## 🛠️ Tecnologías y Dependencias Clave

- **Spring Boot 3.5.3**
- **Spring Data JPA**: Para la persistencia de datos.
- **Spring Web**: Para la creación de endpoints REST.
- **Spring Validation**: Para validación de datos de entrada.
- **H2 Database**: Base de datos en memoria para pruebas.
- **SQLite JDBC Driver**: Driver para la base de datos SQLite en entorno local.
- **SpringDoc OpenAPI (Swagger UI)**: Documentación interactiva de la API.
- **Spring Boot Actuator**: Endpoints de salud y monitoreo.
- **Lombok**: Reducción de código boilerplate.
- **JUnit & Spring Boot Test**: Framework de pruebas.

## ⚙️ Configuración

- **Puerto**: `8081`
- **Context Path**: `/api`
- **API Key**: Configurada en `application.yml` bajo `security.api-key`. Valor por defecto: `PRODUCT_SERVICE_KEY`.
- **Base de Datos**:
    - **H2 (Test)**: `jdbc:h2:mem:productdb`
    - **SQLite (Local)**: Se crea un archivo `product.db` en el directorio de ejecución.

## 🚀 Instrucciones de Ejecución

### 🐳 Con Docker

Este servicio se ejecuta como parte del `docker-compose.yml` principal. Para ejecutarlo de forma aislada:

```bash
# Desde el directorio raíz del proyecto
docker-compose up --build product-service
```
### 🧪 Local con Maven
Requiere Java 17 y Maven instalados.

```bash
./mvnw spring-boot:run
```
El servicio estará disponible en http://localhost:8081/api.

Documentación de la API (Swagger UI): http://localhost:8081/api/swagger-ui.html

## 📡 Endpoints
Todos los endpoints requieren la cabecera X-API-KEY con el valor de la API Key configurada.

GET /api/products
Obtiene una lista de todos los productos.

Respuesta (JSON:API):

```bash
{
    "data": {
        "attributes": [
            {
                "id": 1,
                "name": "Teclado mecánico",
                "price": 150.75,
                "description": "Teclado retroiluminado RGB"
            },
            {
                "id": 2,
                "name": "Mouse Inalambrico",
                "price": 230.75,
                "description": "Mouse retroiluminado RGB"
            }
        ]
    }
}
```
GET /api/products/{id}
Obtiene un producto específico por su ID.

Respuesta (JSON:API - Éxito):

```bash
{
    "data": {
        "attributes": {
            "id": 1,
            "name": "Teclado mecánico",
            "price": 150.75,
            "description": "Teclado retroiluminado RGB"
        }
    }
}
```
Respuesta (Error - Producto no encontrado):


```bash
{
    "errors": [
        {
            "detail": "Product not found: 80",
            "title": "Product Not Found",
            "status": "404"
        }
    ]
}
```
POST /api/products
Crea un nuevo producto.

Cuerpo de la Solicitud:
```bash
{
    "name": "Escritorio RGb, Ajustable",
    "price": 200.69,
    "description": "Escritorio RGb, Altura ajustable de 1 mt a 1.50 mts"
}
```
Respuesta (JSON:API - Éxito):
```bash
{
    "data": {
        "attributes": {
            "id": 29,
            "name": "Escritorio RGb, Ajustable",
            "price": 200.69,
            "description": "Escritorio RGb, Altura ajustable de 1 mt a 1.50 mts"
        }
    }
}
```
Respuesta (Error - Validación):
```bash
{
    "timestamp": "2025-08-28T20:24:00.646+00:00",
    "status": 400,
    "error": "Bad Request",
    "path": "/products"
}
```
## 🧪 Pruebas
Las pruebas unitarias y de integración se encuentran en src/test. Se utiliza H2 para las pruebas.

Para ejecutar las pruebas:
```bash
./mvnw test
```
## 📄 Licencia
Este proyecto está licenciado bajo la Licencia "".
