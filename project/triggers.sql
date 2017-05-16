CREATE OR REPLACE TRIGGER Teams_per_Game
	AFTER INSERT OR UPDATE OF team ON GameStats
	FOR EACH ROW
DECLARE
	team_num INTEGER;
	too_many_teams EXCEPTION;
	PRAGMA EXCEPTION_INIT(too_many_teams, -20001)
BEGIN
	SELECT COUNT(DISTINCT team) INTO team_num WHERE gameid = NEW.gameid;
	IF team_num > 2 THEN
		RAISE too_many_teams;
	END IF;
END;
/