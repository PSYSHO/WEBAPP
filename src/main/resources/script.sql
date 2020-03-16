create table city
(
    Id       int auto_increment
        primary key,
    NameCity varchar(20) null
);

create table route
(
    Id         int auto_increment
        primary key,
    StartPoint int null,
    EndPoint   int null,
    Time       int not null,
    constraint route_ibfk_1
        foreign key (StartPoint) references city (Id),
    constraint route_ibfk_2
        foreign key (EndPoint) references city (Id)
);

create table typeairlines
(
    Id   int auto_increment
        primary key,
    Type varchar(20) null
);

create table plane
(
    Id        int auto_increment
        primary key,
    NamePlane varchar(20) null,
    IdType    int         null,
    constraint plane_typeairlines_Id_fk
        foreign key (IdType) references typeairlines (Id)
);

create table airlines
(
    Id    int auto_increment
        primary key,
    Route int  null,
    Plane int  null,
    Date  date null,
    constraint airlines_ibfk_1
        foreign key (Route) references route (Id),
    constraint airlines_ibfk_2
        foreign key (Plane) references plane (Id)
);


