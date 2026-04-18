# 🛒 Carrito de Compras - Taller Individual
### Sistemas Distribuidos | CORHUILA

Sistema de gestión de carrito de compras desarrollado con arquitectura de microservicios, implementando buenas prácticas de desarrollo con múltiples ambientes (DEV, QA, PROD).

---

## 📋 Descripción

Aplicación full-stack que permite gestionar el flujo completo de un carrito de compras:

**Usuario → Producto → Factura → Detalle de Factura**

---

## 🏗️ Tecnologías

| Capa | Tecnología |
|------|-----------|
| Backend | Spring Boot 3.5.1 + Java 17 |
| Base de datos | PostgreSQL 15 |
| Migraciones | Liquibase |
| Frontend | Vue 3 + Vue Router |
| Contenedores | Docker + Docker Compose |
| CI/CD | GitHub Actions |

---

## 📁 Estructura del Proyecto

```
carrito-compras/
├── src/
│   └── main/
│       ├── java/carrito/
│       │   ├── controller/        # Endpoints REST
│       │   ├── model/             # Entidades JPA
│       │   ├── repository/        # Repositorios Spring Data
│       │   ├── service/           # Lógica de negocio
│       │   ├── CarritoApplication.java
│       │   └── CorsConfig.java
│       └── resources/
│           ├── db/changelog/      # Migraciones Liquibase
│           ├── application.properties
│           ├── application-dev.properties
│           └── application-qa.properties
├── carrito-frontend/              # Aplicación Vue 3
│   └── src/
│       ├── views/                 # Vistas (Usuarios, Productos, Facturas, Detalles)
│       ├── router/                # Configuración de rutas
│       ├── App.vue
│       └── main.js
├── .github/workflows/             # Pipeline CI/CD
│   └── ci.yml
├── docker-compose.dev.yml
├── docker-compose.qa.yml
├── docker-compose.prod.yml
└── Dockerfile
```

---

## 🌿 Estrategia de Ramas

```
main      → Ambiente PROD (producción)
qa        → Ambiente QA (pruebas)
develop   → Ambiente DEV (desarrollo)
```

Cada push a cualquiera de las 3 ramas activa automáticamente el pipeline de GitHub Actions.

---

## 🔧 Ambientes

| Ambiente | Backend | Frontend | Base de Datos |
|----------|---------|----------|---------------|
| DEV | :8080 | :5173 | :5432 |
| QA | :8081 | :5174 | :5433 |
| PROD | :8082 | :5175 | :5434 |

---

## 🚀 Cómo correr el proyecto

### Prerequisitos
- Docker Desktop instalado y corriendo
- Git

### 1. Clonar el repositorio

```bash
git clone https://github.com/NicolasAlvarez25/carrito-compras.git
cd carrito-compras
```

### 2. Levantar los ambientes

**DEV:**
```bash
docker compose -f docker-compose.dev.yml up -d
```

**QA:**
```bash
docker compose -f docker-compose.qa.yml up -d
```

**PROD:**
```bash
docker compose -f docker-compose.prod.yml up -d
```

### 3. Levantar todos los ambientes a la vez

```bash
docker compose -f docker-compose.dev.yml up -d; docker compose -f docker-compose.qa.yml up -d; docker compose -f docker-compose.prod.yml up -d
```

### 4. Verificar contenedores corriendo

```bash
docker ps
```

Deberían aparecer 9 contenedores activos (3 por ambiente).

### 5. Detener los ambientes

```bash
docker compose -f docker-compose.dev.yml stop; docker compose -f docker-compose.qa.yml stop; docker compose -f docker-compose.prod.yml stop
```

---

## 🌐 URLs de acceso

| Ambiente | Frontend | Backend API |
|----------|----------|-------------|
| DEV | http://localhost:5173 | http://localhost:8080 |
| QA | http://localhost:5174 | http://localhost:8081 |
| PROD | http://localhost:5175 | http://localhost:8082 |

---

## 📡 Endpoints REST

### Usuarios `/api/usuarios`
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | /api/usuarios | Listar todos |
| GET | /api/usuarios/{id} | Buscar por ID |
| POST | /api/usuarios | Crear usuario |
| PUT | /api/usuarios/{id} | Actualizar usuario |
| DELETE | /api/usuarios/{id} | Eliminar usuario |

### Productos `/api/productos`
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | /api/productos | Listar todos |
| GET | /api/productos/{id} | Buscar por ID |
| POST | /api/productos | Crear producto |
| PUT | /api/productos/{id} | Actualizar producto |
| DELETE | /api/productos/{id} | Eliminar producto |

### Facturas `/api/facturas`
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | /api/facturas | Listar todas |
| GET | /api/facturas/{id} | Buscar por ID |
| POST | /api/facturas | Crear factura |
| PUT | /api/facturas/{id} | Actualizar factura |
| DELETE | /api/facturas/{id} | Eliminar factura |

### Detalle Factura `/api/detalles`
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | /api/detalles | Listar todos |
| GET | /api/detalles/{id} | Buscar por ID |
| POST | /api/detalles | Crear detalle |
| PUT | /api/detalles/{id} | Actualizar detalle |
| DELETE | /api/detalles/{id} | Eliminar detalle |

---

## 📦 Ejemplos de uso (JSON)

### Crear Usuario
```json
POST /api/usuarios
{
    "nombre": "Nicolas",
    "email": "nicolas@gmail.com",
    "password": "123456"
}
```

### Crear Producto
```json
POST /api/productos
{
    "nombre": "Laptop",
    "descripcion": "Laptop gaming 16GB RAM",
    "precio": 2500000,
    "stock": 10
}
```

### Crear Factura
```json
POST /api/facturas
{
    "usuario": { "id": 1 },
    "fecha": "2026-04-18T10:00:00",
    "total": 2500000
}
```

### Crear Detalle de Factura
```json
POST /api/detalles
{
    "factura": { "id": 1 },
    "producto": { "id": 1 },
    "cantidad": 1,
    "subtotal": 2500000
}
```

---

## 🗄️ Migraciones con Liquibase

Las migraciones se encuentran en `src/main/resources/db/changelog/changes/`.

Liquibase aplica los changesets automáticamente al iniciar la aplicación. No es necesario crear las tablas manualmente.

---

## ⚙️ Variables de entorno Docker

| Variable | Descripción |
|----------|-------------|
| SPRING_PROFILES_ACTIVE | Perfil activo (dev/qa/prod) |
| SPRING_DATASOURCE_URL | URL de conexión a PostgreSQL |
| SPRING_DATASOURCE_USERNAME | Usuario de la BD |
| SPRING_DATASOURCE_PASSWORD | Contraseña de la BD |

---

## 🔄 CI/CD con GitHub Actions

El pipeline se activa automáticamente con cada push a las ramas `main`, `qa` y `develop`.

Pasos del pipeline:
1. Checkout del código
2. Configurar Java 17
3. Dar permisos a mvnw
4. Compilar y ejecutar tests

---

## 👤 Autor

**Nicolás Álvarez**  
Ingeniería de Sistemas - CORHUILA  
Sistemas Distribuidos
