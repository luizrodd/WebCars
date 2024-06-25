create table Car(
    id int auto_increment primary key,
    description varchar(255) not null,
    brand_id int not null,
    model_id int not null,
    version_id int not null,
    foreign key (brand_id) references Brand(id),
    foreign key (model_id) references Model(id),
    foreign key (version_id) references Version(id)

)