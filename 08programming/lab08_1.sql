CREATE OR REPLACE PROCEDURE add_role(
	aID INTEGER, movID INTEGER, role VARCHAR)
IS 
	number_of_actors INTEGER;
	existent_actor INTEGER;
BEGIN 
   SELECT COUNT(*) INTO number_of_actors 
   FROM Role
   WHERE movieID = movid;
   SELECT COUNT(*) INTO existent_actor
   FROM Role
   WHERE movieID = movID AND actorID = aID;
   IF existent_actor > 0 THEN
		dbms_output.put_line('Actor already in movie');
	ELSIF number_of_actors >= 230 THEN
		dbms_output.put_line('Too many actors already in movie');
	ELSE
		INSERT INTO Role VALUES(aID, movID, role);
		dbms_output.put_line('Actor added to movie');

	END IF;
END; 
/

BEGIN
	add_role(89558, 238072, 'Danny Ocean');
	add_role(89558, 238073, 'Danny Ocean');
	add_role(89558, 167324, 'Danny Ocean');
END;
/