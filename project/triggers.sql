--This trigger ensures that for every game, there are only 2 teams playing
CREATE OR REPLACE TRIGGER Teams_per_Game
	AFTER INSERT OR UPDATE OF TeamID ON GameStats
	FOR EACH ROW
DECLARE
	team_num INTEGER;
	too_many_teams EXCEPTION;
	PRAGMA EXCEPTION_INIT(too_many_teams, -20001);
BEGIN
	SELECT COUNT(DISTINCT teamID) INTO team_num FROM GameStats WHERE gameid = :NEW.gameid;
	IF team_num > 2 THEN
		RAISE too_many_teams;
	END IF;
EXCEPTION
	WHEN too_many_teams THEN
		ROLLBACK;
END;
/

--This trigger detects if a player is playing for a different team than the one they have on record,
--which indicates a change in teams and updates the player record accordingly
CREATE OR REPLACE TRIGGER New_Team
	BEFORE UPDATE OF teamID ON GameStats
	FOR EACH ROW
DECLARE
	cur_team VARCHAR(3);
BEGIN
	SELECT teamID INTO cur_team FROM player WHERE id = :NEW.playerid;
	IF cur_team <> :NEW.teamID THEN
		UPDATE Player SET teamID = :NEW.teamID WHERE id = :NEW.playerid;
	END IF;
END;
/
