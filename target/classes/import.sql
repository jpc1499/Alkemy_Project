INSERT INTO generos (id, nombre, imagen) VALUES (1, 'Diversión', 'imagenGenero.jpg');
INSERT INTO generos (id, nombre, imagen) VALUES (2, 'Historia infantil', 'imagenGenero2.jpg');
INSERT INTO generos (id, nombre, imagen) VALUES (3, 'Aventura', 'imagenGenero3.jpg');

INSERT INTO peliculas (id, titulo, fecha_creacion, calificacion) VALUES (1, 'Los tres mosqueteros', '2/2/22',5);
INSERT INTO peliculas (id, titulo, fecha_creacion, calificacion) VALUES (2, 'Las aventuras de Mickey', '2/2/22',5);

INSERT INTO peliculas_generos (pelicula_id, genero_id) VALUES (1, 2);
INSERT INTO peliculas_generos (pelicula_id, genero_id) VALUES (1, 3);
INSERT INTO peliculas_generos (pelicula_id, genero_id) VALUES (2, 1);
INSERT INTO peliculas_generos (pelicula_id, genero_id) VALUES (2, 3);

INSERT INTO personajes (id, nombre, edad, peso, historia) VALUES (1, 'Mickey', 22, 55, 'Es un ratón que nació hace 22 años');
INSERT INTO personajes (id, nombre, edad, peso, historia) VALUES (2, 'Donald', 20, 62, 'Es un pato que nació hace 20 años');

INSERT INTO personajes_peliculas (personaje_id, pelicula_id) VALUES (1 , 1);
INSERT INTO personajes_peliculas (personaje_id, pelicula_id) VALUES (1 , 2);
INSERT INTO personajes_peliculas (personaje_id, pelicula_id) VALUES (2 , 1);

INSERT INTO usuarios (id, username, password, enabled, nombre, apellido, email) VALUES (1, 'juan', '$2a$10$qw9xE51ARgu3UvP63s3zt.KUBmuJ9mmakDjhySMTLvMG70EDbf6K6', true, 'Meli', 'Maiolo', 'melimaiolo@gmail.com');
INSERT INTO usuarios (id, username, password, enabled, nombre, apellido, email) VALUES (2, 'admin', '$2a$10$S6nsZJkaaGPHVDIdlZ3Zgu/f224ot/v2SuQ4mKoX9LjdCig2Dbnpa', true, 'Juan', 'Cerezole', 'juancerezole@gmail.com');

INSERT INTO roles (id, nombre) VALUES (1, 'ROLE_USER');
INSERT INTO roles (id, nombre) VALUES (2, 'ROLE_ADMIN');

INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (1, 1);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2, 2);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2, 1);