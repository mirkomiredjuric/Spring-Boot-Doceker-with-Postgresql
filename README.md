###Podici u docker-u postgres sledecom komandom

docker run --name postgres-spring -e POSTGRES_PASSWORD=password -d -p 5432:5432 postgres:alpine
***
###Provera da li kontejner radi

docker ps
***
###Provera na kom portu postgres radi u dockeru
docker port postgres-spring 
***
###Pristup postgresu unutar dockera
docker exec -it containerID bin/bash
***
###Logovanje kao postgres user
psql -U postgres
***
###Kreiranje baze
CREATE DATABASE springbootdb;
***
###KORISNE POSTGRES KOMANDE
\l - proverimo da li je kreirana baza

\c springbootdb - konekcija ka bazi springbootdb

\d - pregled relacija za sve

\dt - pregled relacija za tabele
***
###Provera da li je instalirano uuid_generate_v4()
SELECT uuid_generate_v4(); 
 
ako baci gresku onda treba instalirati extenziju sledecom komandom

CREATE EXTENSION "uuid-ossp";
***
###Insertovanje podataka u tabelu

INSERT INTO person (uuid, name) VALUES (uuid_generate_v4(), 'Ivo Andric');
INSERT INTO person (uuid, name) VALUES (uuid_generate_v4(), 'Mesa Selimovic');
INSERT INTO person (uuid, name) VALUES (uuid_generate_v4(), 'Miroslav Antic');
***
###Provera da li su podaci insertovani
SELECT * FROM person;
***
#Pokretanje aplikacije

Otvoriti pom.xml sa IntelliJ, Eclipse ili nekim drugim IDE-om

Pokrenuti SpringBoot.java

Otvoriti u browseru ili Postman-u: http://localhost:8080

###Vracanje svih osoba iz baze
GET localhost:8080/api/v1/person

###Unos osoba u in-memory
POST localhost:8080/api/v1/person 

    //u body ide JSON
    //{
    //   "name": "Mesa Selimovic"
    //}
###Vracanje osobe sa odgovarajucim uuid
GET localhost:8080/api/v1/person/uuid_osobe_koja_vec_postoji
***
###Brisanje osobe sa odgovarajucim uuid
DELETE localhost:8080/api/v1/person/uuid_osobe_koja_vec_postoji
***
###Update osobe sa odgovarajucim uuid
PUT localhost:8080/api/v1/person/uuid_osobe_koja_vec_postoji