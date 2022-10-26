# Script for Database application

### Running Docker Image and create container MySQL
```
docker run --name mysql -e MYSQL_ROOT_PASSWORD=my-secret-pw -p 3306:3306 -d mysql:latest
```
### Table Authors

```sql
CREATE TABLE Authors (
	AuthorId int Primary Key not null auto_increment, 
	AuthorName varchar(80)
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
	EntryMonth int,
	EntryYear int,
	AuthorId int,
	CategoryId int,
	FOREIGN KEY (AuthorId) REFERENCES Authors(AuthorId),
	FOREIGN KEY (CategoryId) REFERENCES Categories(CategoryId)
);
```

### Table Debits
```sql
CREATE TABLE Debits (
	id int Primary Key not null auto_increment,
	name varchar(80),
	date timestamp not null,
	value float not null,
	month int,
	year int,
	AuthorId int,
	CategoryId int,
	FOREIGN KEY (AuthorId) REFERENCES Authors(AuthorId),
	FOREIGN KEY (CategoryId) REFERENCES Categories(CategoryId)
);
```