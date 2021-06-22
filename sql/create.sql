create table user_details(
	id serial primary key,
	name varchar(40) not null,
	age int,
	gender char(1),
	mobile_no bigint not null,
	email varchar(40) not null,
	username varchar(20) not null,
	password varchar(20) not null,
	role char(1),
	unique(mobile_no, email, username)
);

create table veg_details(
	id serial primary key,
	name varchar(20) not null,
	price int not null,
	quantity int not null,
	unique(name)
);


create table order_details(
	order_id serial primary key,
	user_id int not null,
	status varchar(30) not null,
	active boolean,
	created_date timestamp not null,
	delivery_date timestamp not null,
	payment_method varchar(30) not null,
	address varchar(200) not null
);

create table order_items(
	id serial primary key,
	order_id bigint not null,
	veg_id int not null,
	price int not null,
	quantity int not null,
	bill int not null
);