import oracle.kv.*;

import java.sql.*;
import java.util.Arrays;


public class LoadDB {
    public static void main(String[] args) throws SQLException {
        KVStore store = KVStoreFactory.getStore(new KVStoreConfig("kvstore", "localhost:5000"));
        Connection jdbcConnection = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe", "bbdb", "bbdb");
        Statement jdbcStatement = jdbcConnection.createStatement();

        ResultSet teamResults = jdbcStatement.executeQuery("SELECT id, location, name, division, conference FROM Team");
        while(teamResults.next()) {
            Key Key1 = Key.createKey(Arrays.asList("team", teamResults.getString(1)),Arrays.asList("location"));
            Value Val1 = Value.createValue(teamResults.getString(2).getBytes());
            store.put(Key1, Val1);

            Key Key2 = Key.createKey(Arrays.asList("team", teamResults.getString(1)),Arrays.asList("name"));
            Value Val2 = Value.createValue(teamResults.getString(3).getBytes());
            store.put(Key2, Val2);

            Key Key3 = Key.createKey(Arrays.asList("team", teamResults.getString(1)),Arrays.asList("division"));
            Value Val3 = Value.createValue(teamResults.getString(4).getBytes());
            store.put(Key3, Val3);

            Key Key4 = Key.createKey(Arrays.asList("team", teamResults.getString(1)),Arrays.asList("conference"));
            Value Val4 = Value.createValue(teamResults.getString(5).getBytes());
            store.put(Key4, Val4);
        }

        ResultSet playerResults = jdbcStatement.executeQuery("SELECT id, teamid, firstName, lastName FROM Player");
        while(playerResults.next()) {
            Key Key1 = Key.createKey(Arrays.asList("player", playerResults.getString(1)),Arrays.asList("teamid"));
            Value Val1;
            if (playerResults.getString(2)==null){
                Val1 = Value.createValue("null".getBytes());
            }
            else {
                Val1 = Value.createValue(playerResults.getString(2).getBytes());
            }
            store.put(Key1, Val1);

            Key Key2 = Key.createKey(Arrays.asList("player", playerResults.getString(1)),Arrays.asList("firstname"));
            Value Val2 = Value.createValue(playerResults.getString(3).getBytes());
            store.put(Key2, Val2);

            Key Key3 = Key.createKey(Arrays.asList("player", playerResults.getString(1)),Arrays.asList("lastname"));
            Value Val3 = Value.createValue(playerResults.getString(4).getBytes());
            store.put(Key3, Val3);
        }

        ResultSet gameResults = jdbcStatement.executeQuery("SELECT id, dayPlayed, homeTeam, homeScore, awayTeam, awayScore FROM Game");
        while(gameResults.next()) {
            Key Key1 = Key.createKey(Arrays.asList("game", gameResults.getString(1)),Arrays.asList("date"));
            Value Val1 = Value.createValue(gameResults.getString(2).substring(0,10).getBytes());
            store.put(Key1, Val1);

            Key Key2 = Key.createKey(Arrays.asList("game",/* gameResults.getString(2).substring(0,10),*/ gameResults.getString(1)),Arrays.asList("home"));
            Value Val2 = Value.createValue(gameResults.getString(3).getBytes());
            store.put(Key2, Val2);

            Key Key3 = Key.createKey(Arrays.asList("game", /*gameResults.getString(2).substring(0,10), */gameResults.getString(1)),Arrays.asList("homescore"));
            Value Val3 = Value.createValue(gameResults.getString(4).getBytes());
            store.put(Key3, Val3);

            Key Key4 = Key.createKey(Arrays.asList("game", /*gameResults.getString(2).substring(0,10), */gameResults.getString(1)),Arrays.asList("away"));
            Value Val4 = Value.createValue(gameResults.getString(5).getBytes());
            store.put(Key4, Val4);

            Key Key5 = Key.createKey(Arrays.asList("game", /*gameResults.getString(2).substring(0,10), */gameResults.getString(1)),Arrays.asList("awayscore"));
            Value Val5 = Value.createValue(gameResults.getString(6).getBytes());
            store.put(Key5, Val5);
        }
        ResultSet gsResults = jdbcStatement.executeQuery("SELECT gameID, teamID, playerID, timePlayed, pts, trb, ast, blk, stl, foul FROM GameStats");
        while(gsResults.next()) {
            Key gameteamKey = Key.createKey(Arrays.asList("gamestat", gsResults.getString(1), gsResults.getString(2)),Arrays.asList(gsResults.getString(3)));
            Value gameteamVal = Value.createValue(gsResults.getString(3).getBytes());
            store.put(gameteamKey, gameteamVal);

            Key Key1 = Key.createKey(Arrays.asList("gamestat", gsResults.getString(1), gsResults.getString(3)),Arrays.asList("teamid"));
            Value Val1 = Value.createValue(gsResults.getString(2).getBytes());
            store.put(Key1, Val1);

            Key Key2 = Key.createKey(Arrays.asList("gamestat", gsResults.getString(1), gsResults.getString(3)),Arrays.asList("playtime"));
            Value Val2;
            if (gsResults.getString(4)==null){
                Val2 = Value.createValue("null".getBytes());
            }
            else {
                Val2 = Value.createValue(gsResults.getString(4).getBytes());
            }
            store.put(Key2, Val2);

            Key Key3 = Key.createKey(Arrays.asList("gamestat", gsResults.getString(1), gsResults.getString(3)),Arrays.asList("pts"));
            Value Val3;
            if (gsResults.getString(5)==null){
                Val3 = Value.createValue("null".getBytes());
            }
            else {
                Val3 = Value.createValue(gsResults.getString(5).getBytes());
            }
            store.put(Key3, Val3);

            Key Key4 = Key.createKey(Arrays.asList("gamestat", gsResults.getString(1), gsResults.getString(3)),Arrays.asList("trb"));
            Value Val4;
            if (gsResults.getString(6)==null){
                Val4 = Value.createValue("null".getBytes());
            }
            else {
                Val4 = Value.createValue(gsResults.getString(6).getBytes());
            }
            store.put(Key4, Val4);

            Key Key5 = Key.createKey(Arrays.asList("gamestat", gsResults.getString(1), gsResults.getString(3)),Arrays.asList("ast"));
            Value Val5;
            if (gsResults.getString(7)==null){
                Val5 = Value.createValue("null".getBytes());
            }
            else {
                Val5 = Value.createValue(gsResults.getString(7).getBytes());
            }
            store.put(Key5, Val5);

            Key Key6 = Key.createKey(Arrays.asList("gamestat", gsResults.getString(1), gsResults.getString(3)),Arrays.asList("blk"));
            Value Val6;
            if (gsResults.getString(8)==null){
                Val6 = Value.createValue("null".getBytes());
            }
            else {
                Val6 = Value.createValue(gsResults.getString(8).getBytes());
            }
            store.put(Key6, Val6);

            Key Key7 = Key.createKey(Arrays.asList("gamestat", gsResults.getString(1), gsResults.getString(3)),Arrays.asList("stl"));
            Value Val7;
            if (gsResults.getString(9)==null){
                Val7 = Value.createValue("null".getBytes());
            }
            else {
                Val7 = Value.createValue(gsResults.getString(9).getBytes());
            }
            store.put(Key7, Val7);

            Key Key8 = Key.createKey(Arrays.asList("gamestat", gsResults.getString(1), gsResults.getString(3)),Arrays.asList("foul"));
            Value Val8;
            if (gsResults.getString(10)==null){
                Val8 = Value.createValue("null".getBytes());
            }
            else {
                Val8 = Value.createValue(gsResults.getString(10).getBytes());
            }
            store.put(Key8, Val8);
        }

        teamResults.close();
        playerResults.close();
        gameResults.close();
        gsResults.close();
        jdbcStatement.close();
        jdbcConnection.close();
    }
}
