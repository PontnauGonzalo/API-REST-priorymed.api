ALTER TABLE medicos ADD COLUMN activo TINYINT;
UPDATE medicos SET activo = 1