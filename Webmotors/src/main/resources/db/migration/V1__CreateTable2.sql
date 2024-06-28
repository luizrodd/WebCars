Create table Brand(
    id int auto_increment primary key,
    name varchar(255) not null
);
create table Model(
    id int auto_increment primary key,
    name varchar(255) not null,
    brand_id int not null,
    foreign key (brand_id) references Brand(id)
);
create table Version(
    id int auto_increment primary key,
    name varchar(255) not null,
    model_id int not null,
    foreign key (model_id) references Model(id)
);


insert into brand(id,name) values(1,'Toyota');
insert into brand(id,name) values(2,'Honda');
insert into brand(id,name) values(3,'Ford');

insert into model(id,name,brand_id) values(1,'Corolla',1);
insert into model(id,name,brand_id) values(2,'Civic',2);
insert into model(id,name,brand_id) values(3,'Accord',2);

insert into version(id,name,model_id) values(2,'XLI',1);
insert into version(id,name,model_id) values(1,'GLI',1);
insert into version(id,name,model_id) values(3,'VTi',2);
