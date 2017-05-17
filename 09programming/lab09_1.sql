SET AUTOTRACE ON; 
SET SERVEROUTPUT ON;
SET TIMING ON;
SET AUTOTRACE TRACEONLY;

--a.
/*
DECLARE
	num INTEGER;
BEGIN
	FOR i in 1..1000 LOOP
		SELECT COUNT(1) INTO num FROM Actor;
	END LOOP;
END;
/

--Elapsed: 00:00:12.99

DECLARE
	num INTEGER;
BEGIN
	FOR i in 1..1000 LOOP
		SELECT COUNT(*) INTO num FROM Actor;
	END LOOP;
END;
/

--Elapsed: 00:00:12.99

DECLARE
	num INTEGER;
BEGIN
	FOR i in 1..1000 LOOP
		SELECT SUM(1) INTO num FROM Actor;
	END LOOP;
END;
/

--Elapsed: 00:00:25.66

--b.

DECLARE
	num INTEGER;
BEGIN
	FOR i in 1..1000 LOOP
		SELECT COUNT(*)
		INTO num
		FROM Director 
		JOIN MovieDirector ON Director.id = MovieDirector.directorid
		JOIN Movie ON MovieDirector.movieid = Movie.id;
	END LOOP;
END;
/

--00:00:05.86

DECLARE
	num INTEGER;
BEGIN
	FOR i in 1..1000 LOOP
		SELECT COUNT(*) 
		INTO num
		FROM Movie
		JOIN MovieDirector ON Movie.id = MovieDirector.movieid
		JOIN Director ON MovieDirector.directorid = Director.id;
	END LOOP;
END;
/


--Elapsed: 00:00:05.82



--c.

DECLARE
	num INTEGER;
BEGIN
	FOR i in 1..1000 LOOP
		SELECT COUNT(*) 
		INTO num
		FROM Movie
		JOIN MovieDirector ON Movie.id+0 = MovieDirector.movieid+0
		JOIN Director ON MovieDirector.directorid+0 = Director.id+0;
	END LOOP;
END;
/

--Elapsed: 00:05:03.07

--d.

DECLARE
	num INTEGER;
BEGIN
	FOR i in 1..1000 LOOP
		SELECT COUNT(1) INTO num FROM ActorOrdinal;
	END LOOP;
END;
/

--Elapsed: 00:00:12.33

DECLARE
	num INTEGER;
BEGIN
	FOR i in 1..1000 LOOP
		SELECT COUNT(1) INTO num FROM ActorOrdinal;
	END LOOP;
END;
/

--Elapsed: 00:00:12.36

DECLARE
	num INTEGER;
BEGIN
	FOR i in 1..1000 LOOP
		SELECT COUNT(1) INTO num FROM ActorOrdinal;
	END LOOP;
END;
/

--Elapsed: 00:00:12.40
*/


DECLARE
	num INTEGER;
BEGIN
	FOR i in 1..1000 LOOP
		SELECT COUNT(*) 
		INTO num
		FROM Movie
		JOIN MovieDirector ON Movie.id = MovieDirector.movieid;
	END LOOP;
END;
/

--Elapsed: 00:00:05.40

CREATE INDEX MovieDirectorIndex ON MovieDirector(movieid);

DECLARE
	num INTEGER;
BEGIN
	FOR i in 1..1000 LOOP
		SELECT COUNT(*) 
		INTO num
		FROM Movie
		JOIN MovieDirector ON Movie.id = MovieDirector.movieid;
	END LOOP;
END;
/

--Elapsed: 00:00:05.42

--The index provided no improvement whatsoever