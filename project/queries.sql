--Joins 4 tables and creates a view:

--This view gives the complete season's worth of stats for every game for each 
--player along with their information for easy accessibility

--Anybody interested in looking up and comparing the stats for players would use 
--this view, and it also allows filtering by position and team for easier use

--A nonmaterialized view is used because if more game information was added to the table,
--the view would give the updated information 

DROP VIEW player_stats;

CREATE VIEW player_stats AS
SELECT *
FROM Player
JOIN Position ON Player.id = Position.playerid
JOIN GameStats ON Player.id = GameStats.playerid
JOIN Game ON GameStats.gameid = Game.id;

--Null comparison, outer joins

--Returns the player ids of players who have changed/left teams during the season

--Fans are always interested in seeing who moves between teams and may wonder what
--happened to a player that used to be on their team,so this query would give this answer

SELECT DISTINCT id, GameStats.teamid, Player.teamid current_team
FROM Player
LEFT JOIN GameStats ON Player.id = GameStats.playerid
--Left join preserves the players who have NULL for their team value
--(They are not on a team at the end of the season, but still played during the season
WHERE Player.teamid <> GameStats.teamid OR Player.teamid IS NULL;

--Self JOIN
--Shows every in division game played during the season

--In division games are disproportionately important for qualifying in the playoffs
--And there are rivalries within each division, so this is a list of more notable games
--that people would bemore interested in.

SELECT t1.division, Game.id, dayPlayed, awayTeam, awayScore, homeTeam, homeScore, winnerId, loserID, overtimes
FROM Game
JOIN Team t1 ON Game.homeTeam = t1.id
JOIN Team t2 ON Game.awayTeam = t2.id 
WHERE t1.division = t2.division;

--Outer/Inner join, nested select, aggregates
--Returns the average stats this season for every player

--Anybody who wants to see the average stats for every single player, and of course the
--the query can be easily modified for just a team or player

SELECT Player.teamid, Player.id, Player.firstname, Player.lastname, games_played, 
avg_seconds_played, avg_fg, avg_fga, avg_fg_perc, avg_tp, avg_tpa, avg_tp_perc, 
avg_ft, avg_fta, avg_ft_perc, avg_orb, avg_drb, avg_trb, avg_ast, avg_stl, 
avg_blk, avg_tov, avg_foul, avg_pts, avg_plusminus
FROM Player
LEFT JOIN Team ON Player.teamid = Team.id
JOIN (SELECT playerid, COUNT(GameStats.timePlayed) AS games_played,
AVG(GameStats.timePlayed) AS avg_seconds_played, AVG(GameStats.fg) AS avg_fg,
AVG(GameStats.fga) AS avg_fga, AVG(GameStats.fg_perc) AS avg_fg_perc,
AVG(GameStats.tp) AS avg_tp, AVG(GameStats.tpa) AS avg_tpa, 
AVG(GameStats.tp_perc) AS avg_tp_perc, AVG(GameStats.ft) AS avg_ft,
AVG(GameStats.fta) AS avg_fta, AVG(GameStats.ft_perc) AS avg_ft_perc,
AVG(GameStats.orb) AS avg_orb, AVG(GameStats.drb) AS avg_drb, 
AVG(GameStats.trb) AS avg_trb, AVG(GameStats.ast) AS avg_ast,
AVG(GameStats.stl) AS avg_stl, AVG(GameStats.blk) AS avg_blk,
AVG(GameStats.tov) AS avg_tov, AVG(GameStats.foul) AS avg_foul,
AVG(GameStats.pts) AS avg_pts, AVG(GameStats.plusminus) AS avg_plusminus
FROM GameStats
GROUP BY GameStats.playerid) gs ON Player.id = gs.playerid
ORDER BY Team.conference, Team.division, Team.location, gs.avg_seconds_played DESC;
