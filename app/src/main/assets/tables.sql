CREATE TABLE Jugador (nombre TEXT, capitulo TEXT, comienzoCap TEXT, puntos	INTEGER, preguntas TEXT);
CREATE TABLE Dios (nombre TEXT, afinidad TEXT, info TEXT, rutaImg TEXT, mitologia TEXT);

CREATE TABLE Capitulo (nombre TEXT, rutaFic TEXT, hecho INTEGER, padre TEXT, siguiente TEXT);
CREATE TABLE MegaEleccion (decision TEXT,hecha INTEGER, rutaFic TEXT);