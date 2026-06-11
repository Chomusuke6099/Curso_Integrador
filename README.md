# Sistema TMO - Monitoreo y Control del Tiempo Medio de Operación

Proyecto Java profesional para el componente **Desarrollo del Código en Java** del APF3. Implementa una arquitectura web monolítica modular con Spring Boot, React/TypeScript, PostgreSQL, MVC, DAO, SOLID, TDD, librerías Java obligatorias y controles de seguridad.

## Estructura

- `backend/`: API REST Java 17 + Spring Boot.
- `frontend/`: vista React + TypeScript.
- `docs/`: arquitectura, matriz de cumplimiento y checklist de seguridad.
- `docker-compose.yml`: entorno local.
- `.env.example`: variables necesarias.

## Ejecución rápida

```bash
cp .env.example .env
docker compose up --build
```

Vista: `http://localhost:5173`  
API: `http://localhost:8080/actuator/health`

## Usuarios de demostración

Solo se crean si `TMO_DEMO_DATA_ENABLED=true`.

| Rol | Correo |
|---|---|
| Analista | analista@tmo.local |
| Supervisor | supervisor@tmo.local |
| Gerente | gerente@tmo.local |
| Administrador | admin@tmo.local |

La contraseña se toma de `TMO_DEMO_PASSWORD`.

## Cumplimiento técnico

- MVC: React como vista, controladores REST, servicios/modelo y DAO separados.
- DAO: interfaces en `dao` e implementaciones JPA en `infrastructure.persistence`.
- SOLID: inyección por constructor, clases con una sola responsabilidad, servicios sobre contratos.
- TDD: pruebas JUnit 5 + Mockito para reglas críticas.
- Librerías: Guava, Apache POI, Apache Commons y Logback con uso funcional.
- Seguridad: JWT, BCrypt, roles, CORS restringido, Bean Validation, transacciones, auditoría y errores controlados.
