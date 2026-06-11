# Arquitectura

## Capas

1. **Vista:** React. No calcula TMO ni aplica reglas críticas.
2. **Controlador:** REST. Valida DTO y delega.
3. **Servicio/Modelo:** reglas de negocio, cálculo TMO, transacciones y auditoría.
4. **DAO:** contratos de persistencia. Los servicios dependen de interfaces.
5. **Persistencia:** PostgreSQL con índices y restricciones.
6. **Transversal:** seguridad, manejo global de errores, Logback, Guava, POI y Commons.

## Regla principal

No hay SQL, cálculos de TMO ni validaciones de negocio en controladores o pantallas. El cierre del ticket y el registro TMO se guardan en una sola transacción.
