CREATE TABLE Person(
Pnr integer Not Null,
Navn varchar(20),
constraint Person_Pk primary key (Pnr));

Create table Hund(
Regnr integer not Null,
Navn varchar(30),
Faar integer,
Rase varchar(30),
Eierpnr integer not null,
constraint Hund_Pk primary key (Regnr),
constraint Hund_Fk foreign key (EierPnr) references Person(Pnr) on update cascade on delete no action,
constraint Alderssjekk check (Faar>1980));

create table Bittav(
Pnr integer not null,
Regnr integer not null,
Antall integer default 0,
constraint BittAv_Pk primary key (Pnr, Regnr),
constraint BittAv_Fk1 foreign key (Pnr) references Person(Pnr) on update cascade on delete cascade,
constraint BittAv_Fk2 foreign key (Regnr) references Hund(Regnr) on update cascade on delete cascade,
constraint AntallSjekk check (Antall >= 0));



