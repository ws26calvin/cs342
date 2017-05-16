CREATE OR REPLACE PROCEDURE transferRank(fromID IN Movie.id%type, toID IN Movie.id%type, delta IN FLOAT) AS
	fromRank Movie.rank%type;
	toRank Movie.rank%type;
	zero_rank EXCEPTION;
BEGIN
	LOCK TABLE Movie IN EXCLUSIVE MODE;
--	FOR UPDATE OF rank;
	SELECT rank INTO fromRank FROM Movie WHERE id = fromID;
	SELECT rank INTO toRank FROM Movie WHERE id = toID;
	IF (fromRank - delta) < 0 OR (toRank + delta) < 0 THEN
		RAISE zero_rank;
	END IF;
	UPDATE Movie SET rank = (rank - delta) WHERE id = fromID;
	COMMIT;
	UPDATE Movie SET rank = (rank + delta) WHERE id = toID;
	COMMIT;
EXCEPTION
	WHEN zero_rank THEN
		raise_application_error (-20001, 'Delta too big');
END;
/

BEGIN
	FOR i IN 1..10000 LOOP
		transferRank(176712, 176711, 0.1);
		COMMIT;
		transferRank(176711, 176712, 0.1);
		COMMIT;
	END LOOP;
END;
/

SELECT rank FROM Movie WHERE id = 176712;
SELECT rank FROM Movie WHERE id = 176711;
