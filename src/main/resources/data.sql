-- Usuario admin por defecto
INSERT INTO users (email, password, role) VALUES ('admin@demo.com', '$2a$10$zQ7s9cC5C6i2nQJk5qjR4u7S1q8u8mWb5rW2v8nDkO1S6H5GJH3Dy', 'ADMIN');
-- password encriptado corresponde a 'admin123' (BCrypt con cost 10)
