--Wasiq Sohail@queries
--CS 342

--Schema for a basketball database containing the 2015-16 NBA season

CREATE TABLE Team (
	id VARCHAR(3),
	--The official 3 letter acronym is used here rather than a surrogate key
	location VARCHAR(30),
	--City or region team is from
	name VARCHAR(20),
	division VARCHAR(20),
	conference VARCHAR(7),
	PRIMARY KEY (id)
	);

CREATE TABLE Player (
	id VARCHAR(9),
	teamID VARCHAR(3),
	firstName VARCHAR(30),
	lastName VARCHAR(30),
	DOB DATE,
	height INTEGER,
	--In inches
	experience INTEGER,
	--Just need the first season they played, not their actual debut date
	college VARCHAR(80),
	PRIMARY KEY (id),
	FOREIGN KEY (teamID) REFERENCES Team(id)
	);
	
CREATE TABLE Game (
	id VARCHAR(12),
	dayPlayed DATE,
	awayTeam VARCHAR(3),
	awayScore INTEGER,
	homeTeam VARCHAR(3),
	homeScore INTEGER,
	winnerID VARCHAR(3),
	--The team who wins has their id here
	loserID VARCHAR(3),
	overtimes VARCHAR(3),
	PRIMARY KEY (id),
	FOREIGN KEY (homeTeam) REFERENCES Team(id),
	FOREIGN KEY (awayTeam) REFERENCES Team(id),
	FOREIGN KEY (winnerID) REFERENCES Team(id),
	FOREIGN KEY (loserID) REFERENCES Team(id)
	);
	
CREATE TABLE GameStats (
	gameID VARCHAR(12),
	teamID VARCHAR(3),
	playerID VARCHAR(9),
	timePlayed INTEGER,
	--In seconds
	fg INTEGER,
	fga INTEGER,
	fg_perc NUMBER,
	tp INTEGER,
	tpa INTEGER,
	tp_perc NUMBER,
	ft INTEGER,
	fta INTEGER,
	ft_perc NUMBER,
	orb INTEGER,
	drb INTEGER,
	trb INTEGER,
	ast INTEGER,
	stl INTEGER,
	blk INTEGER,
	tov INTEGER,
	foul INTEGER,
	pts INTEGER,
	plusminus NUMBER,
	FOREIGN KEY (gameID) REFERENCES Game(id),
	FOREIGN KEY (playerID) REFERENCES Player(id),
	FOREIGN KEY (teamID) REFERENCES Team(id)
	);

CREATE TABLE Position (
	playerID VARCHAR(9),
	postion VARCHAR(2),
	FOREIGN KEY (playerID) REFERENCES Player(id)
	);