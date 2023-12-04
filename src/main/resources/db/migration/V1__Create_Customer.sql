CREATE TABLE Customer(
    id int primary key ,
    first_name varchar(60) not null ,
    middle_name varchar(60) not null ,
    last_name varchar(60) not null ,
    email varchar(60) not null ,
    dob date not null ,
    country varchar(30) not null ,
    theme varchar(30) default 'light',
    register_date date,
    profile_picture_url varchar(255),
    profile_picture_secret varchar(255),
    role varchar(30) default 'General'
)