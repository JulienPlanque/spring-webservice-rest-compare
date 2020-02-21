drop table product if exists;
drop sequence if exists hibernate_sequence;
create sequence hibernate_sequence start with 1 increment by 1;
create table product (
	id integer not null, 
	nom varchar(20) not null, 
	prix integer not null check (prix>=1), 
	prixAchat integer, 
	primary key (id));

INSERT INTO product(id,nom,prix,prixAchat) VALUES(hibernate_sequence.nextval,'Ordinateur portable' , 350, 120);
INSERT INTO product(id,nom,prix,prixAchat) VALUES(hibernate_sequence.nextval,'Aspirateur Robot' , 500, 200);
INSERT INTO product(id,nom,prix,prixAchat) VALUES(hibernate_sequence.nextval,'Table de Ping Pong' , 750, 400);