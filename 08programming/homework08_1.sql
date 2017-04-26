DROP TABLE RankLog;

CREATE TABLE RankLog (
	userid VARCHAR(40),
	changeTime DATE,
	movieid integer,
	origRank number(10,2),
	newRank number(10,2)
	);
	
CREATE OR REPLACE TRIGGER Update_rank
	BEFORE UPDATE OF rank ON Movie
	FOR EACH ROW
BEGIN
	INSERT INTO RankLog(userid, changeTime, movieid, origRank, newRank)
	VALUES (USER, SYSDATE, :NEW.id, :OLD.rank, :NEW.rank);
END;
/