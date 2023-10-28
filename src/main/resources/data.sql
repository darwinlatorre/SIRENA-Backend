-- Insert roles
INSERT IGNORE INTO roles (rol_name) VALUES ('ADMIN'), ('COORDINADOR'), ('DOCENTE');

-- Insert permissions
INSERT IGNORE INTO permissions(per_name) VALUES 
('ADMIN_READ'), ('ADMIN_CREATE'), ('ADMIN_UPDATE'), ('ADMIN_DELETE'),
('DOCENTE_READ'), ('DOCENTE_CREATE'), ('DOCENTE_UPDATE'), ('DOCENTE_DELETE'),
('COORDINADOR_READ'), ('COORDINADOR_CREATE'), ('COORDINADOR_UPDATE'), ('COORDINADOR_DELETE');

-- Assign permissions to roles
INSERT IGNORE INTO roles_permissions (rol_int_id, per_int_id) VALUES 
(1, 1), (1, 2), (1, 3), (1, 4),
(2, 5), (2, 6), (2, 7), (2, 8),
(3, 9), (3, 10), (3, 11), (3, 12);  