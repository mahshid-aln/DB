create table address(
	address_code int primary key,
	city varchar(10),
	street_name varchar(20)
);


create table agency(
	agency_id int primary key,
	agency_name varchar(20),
	address_code int,
	phone_no varchar(12),
	foreign key (address_code) references address(address_code) on delete cascade on update cascade
);



create table taxi(
	number_plate varchar(10) primary key,
	agency_id int,
	model varchar(15),
	date_of_membership date,
	end_of_membership date,
	foreign key (agency_id) references agency(agency_id) on DELETE cascade on update cascade
);

create table driver(
	driver_id int primary key,
	number_plate varchar(10),
	name varchar(20),
	phone_no varchar(12),
	foreign key (number_plate) references taxi(number_plate) on delete cascade on update cascade
);	

create table customer(
	customer_id int primary key,
	name varchar(20),
	address_code int,
	phone_no varchar(12),
	order_count int not null default 0,
	foreign key (address_code) references address(address_code) on delete cascade on update cascade

);

create table price (
	destination_address_code int,
	source_address_code int,
	agency_id int,
	price_amount int,
	primary key (destination_address_code, source_address_code, agency_id),
	foreign key (agency_id) references agency(agency_id) on delete cascade on update cascade,	
	foreign key (destination_address_code) references address(address_code) on delete cascade on update cascade,
	foreign key (source_address_code) references address(address_code) on delete cascade on update cascade
);
drop table price;
alter table price add price_amount int;

create table orders(
	order_id int primary key,
	agency_id int not null, 
	source_address_code int not null, 
	destination_address_code int not null,
	date_of_order date, 
	time_of_order time,
	order_status varchar(10),
	customer_id int,
	foreign key (agency_id) references agency(agency_id) on delete cascade on update cascade,
	foreign key (source_address_code) references address(address_code) on delete cascade on update cascade,
	foreign key (destination_address_code) references address(address_code) on delete cascade on update cascade,
	foreign key (customer_id) references customer(customer_id) on delete cascade on update cascade,
	constraint const1 check( order_status in ('done', 'undone'))
);


show tables
