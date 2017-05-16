CREATE OR REPLACE PROCEDURE incrementRank
	(movieIdIn IN Movie.id%type, 
	 deltaIn IN integer
    ) AS
	x Movie.rank%type;
BEGIN
	FOR i IN 1..50000 LOOP
		SELECT rank INTO x FROM Movie WHERE id=movieIdIn;
		UPDATE Movie SET rank=x+deltaIn WHERE id=movieIdIn;
		COMMIT;
	END LOOP;
END;
/

--When run simultaneously in two different sessions, it gives an incorrect value because due to lost updates reading
--values before they are committed

CREATE OR REPLACE PROCEDURE lockIncrementRank
	(movieIdIn IN Movie.id%type, 
	 deltaIn IN integer
    ) AS
	x Movie.rank%type;
BEGIN
	FOR i IN 1..50000 LOOP
		LOCK TABLE Movie IN EXCLUSIVE MODE;
		SELECT rank INTO x FROM Movie WHERE id=movieIdIn;
		UPDATE Movie SET rank=x+deltaIn WHERE id=movieIdIn;
		COMMIT;
	END LOOP;
END;
/

--I just added a lock to the procedure thatopens up with the commit so there won't be any overrides while a value
--is being read