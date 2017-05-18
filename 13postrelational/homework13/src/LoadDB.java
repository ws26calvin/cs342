import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import oracle.kv.*;


public class LoadDB {
    public static void main(String[] args) throws SQLException {
        KVStore store = KVStoreFactory.getStore(new KVStoreConfig("kvstore", "localhost:5000"));
        Connection jdbcConnection = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe", "imdb", "bjarne");
        Statement jdbcStatement = jdbcConnection.createStatement();

        ResultSet movieResults = jdbcStatement.executeQuery("SELECT id, name, year, rank FROM Movie");
        while (movieResults.next()){
            Key kname = Key.createKey(Arrays.asList("movie", movieResults.getString(1)), Arrays.asList("name"));
            Value vname = Value.createValue(movieResults.getString(2).getBytes());
            store.put(kname,vname);

            Key kyear = Key.createKey(Arrays.asList("movie", movieResults.getString(1)), Arrays.asList("year"));
            Value vyear = Value.createValue(movieResults.getString(3).getBytes());
            store.put(kyear, vyear);

            Key krank = Key.createKey(Arrays.asList("movie", movieResults.getString(1)), Arrays.asList("rank"));
            Value vrank;
            if(movieResults.getString(4) == null){
                vrank = Value.createValue("null".getBytes());
            }
            else {
                vrank = Value.createValue(movieResults.getString(4).getBytes());
            }
            store.put(krank,vrank);
        }

        ResultSet actorResults = jdbcStatement.executeQuery("SELECT id, firstname, lastname FROM Actor");
        while (actorResults.next()){
            Key kfname = Key.createKey(Arrays.asList("actor", actorResults.getString(1)), Arrays.asList("firstname"));
            Value vfname = Value.createValue(actorResults.getString(2).getBytes());
            store.put(kfname, vfname);

            Key klname = Key.createKey(Arrays.asList("actor", actorResults.getString(1)), Arrays.asList("lastname"));
            Value vlname = Value.createValue(actorResults.getString(3).getBytes());
            store.put(klname, vlname);
        }

        ResultSet roleResults = jdbcStatement.executeQuery("SELECT movieid, actorid, role FROM Role");
        while (roleResults.next()){
            //Need to distinguish same actor with different roles in same movie and this is the easiest way to do it. It's minor rather than major
            //so the role name is not necessary for searching
            Key krole = Key.createKey(Arrays.asList("role", roleResults.getString(1),roleResults.getString(2)), Arrays.asList(roleResults.getString(3)));

            Value vrole = Value.createValue(roleResults.getString(3).getBytes());
            store.put(krole,vrole);

        }

        movieResults.close();
        actorResults.close();
        roleResults.close();
        jdbcStatement.close();
        jdbcConnection.close();
    }
}
