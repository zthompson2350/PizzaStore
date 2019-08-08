create table customer(
customerID number primary key,
username varchar2(20) unique,
pass varchar2(20)
);

create table pizza(
pizzaID number primary key,
customerID number, --foreign key
pizzatypeID number, -- foreign key
pizzasizeID number, -- foreign key
statusID number default 0 -- foreign key
);

create table pizzatype(
pizzatypeID number primary key,
typedescription varchar2(30)
);

create table pizzasize(
sizeID number primary key,
sizedescription varchar(20)
);

create table pizzastatus(
statusID number primary key,
statusDescription varchar(30)
);

alter table pizza add constraint fk_pizzatype foreign key(pizzatypeID) references pizzatype(pizzatypeID);
alter table pizza add constraint fk_pizzasize foreign key(pizzasizeID) references pizzasize(sizeID);
alter table pizza add constraint fk_customerID foreign key(customerID) references customer(customerID);
alter table pizza add constraint fk_status foreign key(statusID) references pizzastatus(statusID);

insert into pizzastatus values(0, 'ordered');
insert into pizzastatus values(1, 'prepping');
insert into pizzastatus values(2, 'on its way');
insert into pizzastatus values(3, 'delivered');
insert into pizzastatus values(10, 'canceled/lost');

insert into pizzasize values(0, 'small');
insert into pizzasize values(1, 'medium');
insert into pizzasize values(2, 'large');
insert into pizzasize values(3, 'extra large');

insert into pizzatype values(0, 'cheese');
insert into pizzatype values(1, 'pepperoni');
insert into pizzatype values(2, 'hawaiian');
insert into pizzatype values(3, 'cowboy');

create sequence pizzaseq
increment by 5;

create or replace procedure orderPizza
(customerId in number, pizzatype in number, pizzasize in number)
as
begin 
insert into pizza values(pizzaseq.nextval, customerID, pizzatype, pizzasize, 0);
commit;
end;
/

select customer.username, typedescription type, sizedescription, statusdescription
    from pizza
    join customer on pizza.customerID = customer.customerID 
    join pizzasize on pizza.pizzasizeID = pizzasize.sizeID
    join pizzatype on pizza.pizzatypeId = pizzatype.pizzatypeID
    join pizzastatus on pizza.statusID = pizzastatus.statusID;

