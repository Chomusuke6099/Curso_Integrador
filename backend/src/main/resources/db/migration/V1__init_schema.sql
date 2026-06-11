CREATE TABLE usuarios (
  id BIGSERIAL PRIMARY KEY,
  nombres VARCHAR(120) NOT NULL,
  correo VARCHAR(180) NOT NULL UNIQUE,
  password_hash VARCHAR(120) NOT NULL,
  rol VARCHAR(30) NOT NULL,
  area VARCHAR(80) NOT NULL,
  activo BOOLEAN NOT NULL DEFAULT TRUE,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE tipificaciones (
  id BIGSERIAL PRIMARY KEY,
  nombre VARCHAR(120) NOT NULL,
  activo BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE parametros_sla (
  id BIGSERIAL PRIMARY KEY,
  tipo_caso VARCHAR(40) NOT NULL,
  minutos_objetivo INTEGER NOT NULL,
  umbral_alerta INTEGER NOT NULL,
  activo BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE casos (
  id BIGSERIAL PRIMARY KEY,
  codigo VARCHAR(40) NOT NULL UNIQUE,
  analista_id BIGINT NOT NULL REFERENCES usuarios(id),
  tipo_caso VARCHAR(40) NOT NULL,
  estado VARCHAR(30) NOT NULL,
  tipificacion_id BIGINT REFERENCES tipificaciones(id),
  referencia VARCHAR(160) NOT NULL,
  observacion VARCHAR(1000),
  fecha_inicio TIMESTAMP NOT NULL,
  fecha_fin TIMESTAMP,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE registros_tmo (
  id BIGSERIAL PRIMARY KEY,
  caso_id BIGINT NOT NULL UNIQUE REFERENCES casos(id),
  inicio TIMESTAMP NOT NULL,
  fin TIMESTAMP NOT NULL,
  tmo_segundos BIGINT NOT NULL CHECK (tmo_segundos >= 0)
);

CREATE TABLE auditoria_eventos (
  id BIGSERIAL PRIMARY KEY,
  usuario_id BIGINT REFERENCES usuarios(id),
  caso_id BIGINT REFERENCES casos(id),
  accion VARCHAR(80) NOT NULL,
  fecha TIMESTAMP NOT NULL,
  detalle_seguro VARCHAR(500) NOT NULL,
  resultado VARCHAR(40) NOT NULL
);

CREATE INDEX idx_usuarios_correo ON usuarios(correo);
CREATE INDEX idx_casos_estado ON casos(estado);
CREATE INDEX idx_casos_analista ON casos(analista_id);
CREATE INDEX idx_casos_tipo ON casos(tipo_caso);
CREATE INDEX idx_casos_fecha_inicio ON casos(fecha_inicio);
CREATE INDEX idx_registros_tmo_fin ON registros_tmo(fin);
