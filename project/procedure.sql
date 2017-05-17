--To avoid any incomplete records we want to put an entire game's stats in the database at once so a temporary table
--is built in order to avoid incorrect summaries or lost updates. This table should only ever hold a single game
--to keep files in the Game table tidy.
DROP TABLE TempGameStats;
CREATE TABLE TempGameStats (
	gameID VARCHAR(12),
	teamID VARCHAR(3),
	playerID VARCHAR(9),
	timePlayed INTEGER,
	CHECK (timePlayed >= 0),
	--In seconds
	fg INTEGER,
	CHECK (fg >= 0),
	fga INTEGER,
	CHECK (fga >= 0),
	fg_perc NUMBER,
	CHECK (fg_perc >= 0.00),
	tp INTEGER,
	CHECK (tp >= 0),
	tpa INTEGER,
	CHECK (tpa >= 0),
	tp_perc NUMBER,
	CHECK (tp_perc >= 0.00),
	ft INTEGER,
	CHECK (ft >= 0),
	fta INTEGER,
	CHECK (fta >= 0),
	ft_perc NUMBER,
	CHECK (ft_perc >= 0.00),
	orb INTEGER,
	CHECK (orb >= 0),
	drb INTEGER,
	CHECK (drb >= 0),
	trb INTEGER,
	CHECK (trb >= 0),
	ast INTEGER,
	CHECK (ast >= 0),
	stl INTEGER,
	CHECK (stl >= 0),
	blk INTEGER,
	CHECK (blk >= 0),
	tov INTEGER,
	CHECK (tov >= 0),
	foul INTEGER,
	CHECK (foul >= 0),
	pts INTEGER,
	CHECK (pts >= 0),
	plusminus NUMBER
	);

--Because overtime and date played cannot be derived from the player stats, they need to be passed to the procedure
CREATE OR REPLACE PROCEDURE NewGame(newGameID IN Game.id%type, dateplayed IN Game.dayPlayed%type, awayT IN Team.id%type,
							homeT IN Team.id%type, OT IN Game.overtimes%type) AS
	game_exist BOOLEAN;
	awayScore INTEGER;
	homeScore INTEGER;
	winner Team.id%type;
	loser Team.id%type;
	CURSOR cur IS SELECT * FROM TempGameStats FOR UPDATE;
BEGIN 
	LOCK TABLE Game IN EXCLUSIVE MODE;
	SELECT SUM(pts) INTO awayScore FROM TempGameStats WHERE teamID = awayT;
	SELECT SUM(pts) INTO homeScore FROM TempGameStats WHERE teamID = homeT;
	IF awayScore > homeScore THEN
		winner := awayT;
		loser := homeT;
	ELSE
		winner := homeT;
		loser := awayT;
	END IF;
	INSERT INTO Game VALUES(newGameID, dateplayed, awayT, awayScore, homeT, homeScore, winner, loser, OT);	
	COMMIT;
	FOR c in cur LOOP
		INSERT INTO GameStats VALUES(c.gameID, c.teamID, c.playerID, c.timePlayed, c.fg, c.fga, c.fg_perc, c.tp, 
		c.tpa, c.tp_perc, c.ft, c.fta, c.ft_perc, c.orb, c.drb, c.trb, c.ast, c.stl, c.blk, c.tov, c.foul, 
		c.pts, c.plusminus);
		COMMIT;
	END LOOP;
END;
/