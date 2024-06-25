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