import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import oracle.kv.*;


public class test {
    public static void main(String[] args) throws SQLException {
        GetTableValues gtv = new GetTableValues();
        GetMovieActorRoles gmar = new GetMovieActorRoles();
        GetMovieActors gma = new GetMovieActors();
        GetSortedMovies gsm = new GetSortedMovies();
        gtv.gtv("92616");
        //Gives the rank twice, it does this for no other movie that I've tested and I can't figure out why
        gtv.gtv("130128");
        //This works fine for example
        gmar.gmar("92616","429719");
        gma.gma("92616");
        gsm.gsm();
    }
}
