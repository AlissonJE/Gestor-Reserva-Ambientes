INSERT INTO ambientes (nombre, tipo, capacidad, activo) VALUES
('Ambiente 1', 'SALA', 20, true),
('Ambiente 2', 'LABORATORIO', 15, true),
('Ambiente 3', 'AUDITORIO', 80, true),
('Ambiente 4', 'SALA', 10, false);

INSERT INTO reservas (ambiente_id, instructor, inicio, fin, aprendices, estado) VALUES
(1, 'Rigoberto Diaz', '2026-06-25 08:00:00', '2026-06-25 10:00:00', 15, 'ACTIVA'),
(2, 'Mariana Torres', '2026-06-25 09:00:00', '2026-06-25 11:00:00', 10, 'ACTIVA'),
(1, 'Carlos Gomez', '2026-06-20 14:00:00', '2026-06-20 16:00:00', 18, 'FINALIZADA');