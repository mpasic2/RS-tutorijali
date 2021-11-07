BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "Driver" (
	"DriverID"	INTEGER PRIMARY KEY AUTOINCREMENT,
	"name"	TEXT,
	"surname"	TEXT,
	"jmb"	TEXT,
	"Birthday"	date,
	"EmployedDate"	date
);
CREATE TABLE IF NOT EXISTS "bus" (
	"BusID"	INTEGER PRIMARY KEY AUTOINCREMENT,
	"Maker"	TEXT,
	"Series"	TEXT,
	"SeatNumber"	INTEGER,
	"DriverOne"	TEXT,
	"DriverTwo"	TEXT,
	FOREIGN KEY("DriverOne") REFERENCES "Driver"("jmb"),
	FOREIGN KEY("DriverTwo") REFERENCES "Driver"("jmb")
);
DELETE FROM bus;
DELETE FROM Driver;
COMMIT;
