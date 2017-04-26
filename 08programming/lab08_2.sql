-- Insert your results into this table.
CREATE TABLE SequelsTemp (
  id INTEGER,
  name varchar2(100),
  PRIMARY KEY (id)
 );
 
CREATE OR REPLACE PROCEDURE getSequels (movieIdIn IN Movie.id%type) AS
	-- Fill this in based on:
	--     the cursor example in class exercise 8.2.b.
	--     the recursive procedure example in class exercise 8.3.b.
	CURSOR cur IS
		SELECT id, name, sequelid FROM Movie ORDER BY id ASC;
	nextID INTEGER;
BEGIN
	SELECT sequelid INTO nextID
	FROM Movie
	WHERE id = movieIdIn;
	FOR c IN cur LOOP
		IF nextID IS NOT NULL AND c.id = nextID THEN
--			dbms_output.put_line(nextID);
			nextID := c.sequelid;
			INSERT INTO SequelsTemp(id, name)
			VALUES (c.id, c.name);
		END IF;
	END LOOP;
END;
/

-- Get the sequels for Ocean's Fourteen, i.e., none.
BEGIN  getSequels(238075);  END;
/
SELECT * FROM SequelsTemp;

-- Get the sequels for Ocean's 11, i.e., 4 of them.
BEGIN  getSequels(238071);  END;
/
SELECT * FROM SequelsTemp;

-- Clean up.
DROP TABLE SequelsTemp;