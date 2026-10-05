# REST API - Gestión de Franquicias

API RESTful desarrollada en Java 21 con Spring Boot 3 para la gestión centralizada de franquicias, sucursales y productos. Permite registrar entidades, modificar datos de stock y nombres, eliminar productos y consultar el producto con mayor stock por cada sucursal de una franquicia.

---

## 🚀 Tecnologías Utilizadas

* **Lenguaje:** Java 21
* **Framework:** Spring Boot 3.4+
* **Base de Datos:** MariaDB 10.6
* **Persistencia:** Spring Data JPA / Hibernate
* **Documentación:** SpringDoc OpenAPI 2.8.5 (Swagger UI)
* **Contenerización:** Docker & Docker Compose
* **Gestor de Dependencias:** Maven

---

## 🏗️ Arquitectura y Entidades

El modelo relacional está compuesto por tres entidades principales:

1. **Franquicia (`Franquicia`):** Representa la entidad de negocio principal. Tiene una relación uno a muchos ($1:N$) con las sucursales.
2. **Sucursal (`Sucursal`):** Pertenece a una franquicia y cuenta con múltiples productos ($1:N$).
3. **Producto (`Producto`):** Pertenece a una sucursal y contiene los datos de `nombre` y `stock`.

---

## 📦 Ejecución con Docker Compose

Para levantar el entorno completo (aplicación Spring Boot y base de datos MariaDB) de forma aislada, asegúrate de tener instalado **Docker** y **Docker Compose**.

1. **Clonar el repositorio:**
   ```bash
   git clone <https://github.com/Abraham7905/Gestion-de-Franquicias>
   cd franquicias
   ```

2. **Compilar el proyecto y levantar los contenedores:**
   ```bash
   ./mvnw clean package -DskipTests
   docker compose up --build
   ```

3. **Verificar estado de los contenedores:**
   ```bash
   docker compose ps
   ```

La aplicación estará disponible en `http://localhost:8088`.

---

## 📖 Documentación de la API (Swagger UI)

Una vez iniciados los contenedores, puedes interactuar directamente con todos los endpoints desde la interfaz visual de Swagger en la siguiente dirección:

👉 **[http://localhost:8088/swagger-ui/index.html](http://localhost:8088/swagger-ui/index.html)**

---

## 🛣️ Endpoints Disponibles

### Franquicias
* `POST /api/franquicias` — Registrar una nueva franquicia.
* `PATCH /api/franquicias/{id}/nombre` — Modificar el nombre de una franquicia.
* `GET /api/franquicias/{franquiciaId}/producto-mayor-stock` — Obtener el producto con mayor stock por cada sucursal de la franquicia.

### Sucursales
* `POST /api/franquicias/{franquiciaId}/sucursales` — Agregar una nueva sucursal a una franquicia.
* `PATCH /api/franquicias/sucursales/{id}/nombre` — Modificar el nombre de una sucursal.

### Productos
* `POST /api/franquicias/sucursales/{sucursalId}/productos` — Agregar un nuevo producto a una sucursal.
* `PATCH /api/franquicias/productos/{productoId}/stock` — Modificar el stock disponible de un producto.
* `PATCH /api/franquicias/productos/{id}/nombre` — Modificar el nombre de un producto.
* `DELETE /api/franquicias/productos/{productoId}` — Eliminar un producto.

---

## 📄 Licencia

Este proyecto fue desarrollado como parte de un proceso de evaluación técnica.