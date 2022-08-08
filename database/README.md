# Script for Database application

### Table Authors

```sql
CREATE TABLE Authors (
	AuthorId int Primary Key not null auto_increment, 
	AuthorName varchar(80)
);
```

### Table Tipos
```sql
CREATE TABLE Tipos (
	TypeId int Primary Key not null auto_increment,
	TypeName varchar(80)
);
```

### Table Categories
```sql
CREATE TABLE Categories (
	CategoryId int Primary Key not null auto_increment,
	CategoryName varchar(80)
);
```

### Table Entries
```sql
CREATE TABLE Entries (
	EntryId int Primary Key not null auto_increment,
	EntryName varchar(80),
	EntryDate timestamp not null,
	EntryValue float not null,
	AuthorId int,
	TypeId int,
	CategoryId int,
	FOREIGN KEY (AuthorId) REFERENCES Authors(AuthorId),
	FOREIGN KEY (TypeId) REFERENCES Tipos(TypeId),
	FOREIGN KEY (CategoryId) REFERENCES Categories(CategoryId)
);
```

### Table Debits
```sql
CREATE TABLE Debits (
	DebitId int Primary Key not null auto_increment,
	DebitName varchar(80),
	DebitDate timestamp not null,
	DebitValue float not null,
	AuthorId int,
	TypeId int,
	CategoryId int,
	FOREIGN KEY (AuthorId) REFERENCES Authors(AuthorId),
	FOREIGN KEY (TypeId) REFERENCES Tipos(TypeId),
	FOREIGN KEY (CategoryId) REFERENCES Categories(CategoryId)
);
```