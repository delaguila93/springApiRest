INSERT INTO regiones( nombre) VALUES( 'Sudamerica');
INSERT INTO regiones( nombre) VALUES( 'CentroAmerica');
INSERT INTO regiones( nombre) VALUES( 'NorteAmerica');
INSERT INTO regiones( nombre) VALUES( 'Europa');
INSERT INTO regiones( nombre) VALUES( 'Asia');
INSERT INTO regiones( nombre) VALUES( 'Oceania');
INSERT INTO regiones( nombre) VALUES( 'Antartida');

INSERT INTO clientes (nombre,apellidos,email,telefono,created_at,id_region) VALUES('Jose','Perez','jp@hotmail.com',65433220,'2021-10-01',1);
INSERT INTO clientes (nombre,apellidos,email,telefono,created_at,id_region) VALUES('Carlos','Lopez','cl@hotmail.com',65433221,'2021-01-01',3);
INSERT INTO clientes (nombre,apellidos,email,telefono,created_at,id_region) VALUES('Maria','Orillana','mo@hotmail.com',65433222,'2021-02-01',4);
INSERT INTO clientes (nombre,apellidos,email,telefono,created_at,id_region) VALUES('Dina','Ramirez','dr@hotmail.com',65433223,'2021-03-01',2);
INSERT INTO clientes (nombre,apellidos,email,telefono,created_at,id_region) VALUES('Mirna','Ramos','mr@hotmail.com',65433224,'2021-04-01',6);
INSERT INTO clientes (nombre,apellidos,email,telefono,created_at,id_region) VALUES('Pepe','Mojica','pm@hotmail.com',65433225,'2021-05-01',7);
INSERT INTO clientes (nombre,apellidos,email,telefono,created_at,id_region) VALUES('Juan','Chavez','jc@hotmail.com',65433226,'2021-06-01',4);
INSERT INTO clientes (nombre,apellidos,email,telefono,created_at,id_region) VALUES('Enrrique','Iglesias','ei@hotmail.com',65433227,'2021-07-01',2);
INSERT INTO clientes (nombre,apellidos,email,telefono,created_at,id_region) VALUES('Pedro','Diaz','pd@hotmail.com',65433228,'2021-08-01',1);
INSERT INTO clientes (nombre,apellidos,email,telefono,created_at,id_region) VALUES('Ramon','Gonzalez','rgonzalez@gmail.com',65433229,'2021-09-01',6);

INSERT INTO roles (nombre) VALUES ("pepe");
INSERT INTO roles (nombre) VALUES ("ibai");
INSERT INTO roles (nombre) VALUES ("papsito");
INSERT INTO roles (nombre) VALUES ("lksada");
INSERT INTO roles (nombre) VALUES ("qdaawde");

INSERT INTO usuarios (enabled,password,username) VALUES(1,"asdfuasf","papasito");
INSERT INTO usuarios (enabled,password,username) VALUES(0,"sfsyañldk","ibai");
INSERT INTO usuarios (enabled,password,username) VALUES(0,"asdf","pepe");
INSERT INTO usuarios (enabled,password,username) VALUES(1,"asdqwe","lksada");
INSERT INTO usuarios (enabled,password,username) VALUES(1,"qwecqqq","qdaawde");

INSERT INTO usuarios_roles(usuarios_id,roles_id) VALUES(1,2);
INSERT INTO usuarios_roles(usuarios_id,roles_id) VALUES(2,5);
INSERT INTO usuarios_roles(usuarios_id,roles_id) VALUES(3,4);
INSERT INTO usuarios_roles(usuarios_id,roles_id) VALUES(4,1);
INSERT INTO usuarios_roles(usuarios_id,roles_id) VALUES(5,3);
INSERT INTO usuarios_roles(usuarios_id,roles_id) VALUES(5,5);
INSERT INTO usuarios_roles(usuarios_id,roles_id) VALUES(4,3);
INSERT INTO usuarios_roles(usuarios_id,roles_id) VALUES(1,5);
INSERT INTO usuarios_roles(usuarios_id,roles_id) VALUES(2,1);
INSERT INTO usuarios_roles(usuarios_id,roles_id) VALUES(4,1);