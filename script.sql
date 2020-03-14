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
    constraint route_ibfk_1
        foreign key (StartPoint) references city (Id),
    constraint route_ibfk_2
        foreign key (EndPoint) references city (Id)
);

create index EndPoint
    on route (EndPoint);

create index StartPoint
    on route (StartPoint);

create table typeairlines
(
    Id   int auto_increment
        primary key,
    Type varchar(20) null
);

create table airlines
(
    Id           int auto_increment
        primary key,
    Route        int  null,
    TypeAirlines int  null,
    Time         int  null,
    Date         date null,
    constraint airlines_ibfk_1
        foreign key (Route) references route (Id),
    constraint airlines_ibfk_2
        foreign key (TypeAirlines) references typeairlines (Id)
);

create index Route
    on airlines (Route);

create index TypeAirlines
    on airlines (TypeAirlines);

create table plane
(
    Id        int auto_increment
        primary key,
    NamePlane varchar(20) null,
    IdType    int         null,
    constraint plane_typeairlines_Id_fk
        foreign key (IdType) references typeairlines (Id)
);


