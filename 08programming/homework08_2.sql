DROP TABLE BaconTable;
DROP PROCEDURE getBaconNumber;

alter system set open_cursors = 1000

CREATE TABLE BaconTable (
	id INTEGER,
	degree INTEGER,
	PRIMARY KEY (id)
	);

CREATE PROCEDURE getBaconNumber(inID IN INTEGER, inDegree IN INTEGER) IS
	idExtant INTEGER;
	temp_deg INTEGER;
	movie_count INTEGER;
	CURSOR cur IS
		SELECT DISTINCT actorid FROM Role WHERE movieid IN 
		(SELECT movieid FROM Role WHERE inID = actorid);
BEGIN
	INSERT INTO BaconTable(id, degree)
	VALUES (inID, inDegree);
	SELECT COUNT(*) INTO movie_count FROM Movie;
	FOR c IN cur LOOP
		SELECT COUNT(*) INTO idExtant FROM BaconTable WHERE id = c.actorid;
		IF idExtant = 0 and inDegree < movie_count THEN
		--Limiting the amount of times the procedure is run in order to limit
		--number of open_cursors
			getBaconNumber(c.actorid, inDegree + 1);
		ELSIF idExtant = 1 THEN
			SELECT degree INTO temp_deg FROM BaconTable WHERE id = c.actorid;
			IF temp_deg > inDegree + 1 THEN
				UPDATE BaconTable 
				SET degree = (inDegree + 1)
				WHERE id = c.actorid;
			END IF;
		END IF;
	END LOOP;
END;
/
	
--Kevin Bacon: 22591
BEGIN  getBaconNumber(22591,0);  END;
/