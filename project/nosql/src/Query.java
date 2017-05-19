import oracle.kv.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

//This prints off a chronological list of games played this season with the scores
//Also cal
public class Query {

    //This gets a list of players and the team they are on
    public void getPlayerTeam() {
        KVStore store = KVStoreFactory.getStore(new KVStoreConfig("kvstore", "localhost:5000"));

        ArrayList<String> newKey = new ArrayList<String>();
        String holder = "";
        newKey.add("player");
        Key searchKey = Key.createKey(newKey);
        Iterator<KeyValueVersion> search = store.storeIterator(Direction.UNORDERED, 0, searchKey, null, null);
        Integer i = 0;
        while (search.hasNext()) {
            Key temp = search.next().getKey();
            String result = new String(store.get(temp).getValue().getValue());

            if(i < 2){
                holder += result + ' ';
                i++;
            }
            else {
                ArrayList<String> newKey2 = new ArrayList<String>();
                newKey2.add("team");
                if (!result.equals("null")) {
                    newKey2.add(result);
                    Key locKey = Key.createKey(newKey2, Arrays.asList("location"));
                    Key nameKey = Key.createKey(newKey2, Arrays.asList("name"));
                    String loc = new String(store.get(locKey).getValue().getValue());
                    String name = new String(store.get(nameKey).getValue().getValue());
                    holder += '\t' + loc + ' ' + name;
                    System.out.println(holder);
                    holder = "";
                    i = 0;
                }
                else {
                    holder += '\t' + "None";
                    System.out.println(holder);
                    holder = "";
                    i = 0;
                }
            }
        }

    }

    //This prints off an ordered list of games played this season with the scores

    public void getGameList() {
        KVStore store = KVStoreFactory.getStore(new KVStoreConfig("kvstore", "localhost:5000"));

        ArrayList<String> newKey = new ArrayList<String>();
        ArrayList<String> games = new ArrayList<String>();
        newKey.add("game");
        Key searchKey = Key.createKey(newKey);
        Iterator<KeyValueVersion> search = store.storeIterator(Direction.UNORDERED, 0, searchKey, null, null);
        Integer j = 0;
        String holder = "";
        while (search.hasNext()) {
            Key temp = search.next().getKey();
//            String result = new String(store.get(temp).getValue().getValue());
//            System.out.println(temp.toString() + " : " + result);
            if(j < 4){
                j++;
            }
            else {
                ArrayList<String> newKey2 = new ArrayList<String>();
                newKey2.add("game");
                newKey2.add(temp.getMajorPath().get(1));

                Key dateK = Key.createKey(newKey2, Arrays.asList("date"));
                Key homeK = Key.createKey(newKey2, Arrays.asList("home"));
                Key homescK = Key.createKey(newKey2, Arrays.asList("homescore"));
                Key awayK = Key.createKey(newKey2, Arrays.asList("away"));
                Key awayscK = Key.createKey(newKey2, Arrays.asList("awayscore"));

                String date = new String(store.get(dateK).getValue().getValue());
                String home = new String(store.get(homeK).getValue().getValue());
                String homesc = new String(store.get(homescK).getValue().getValue());
                String away = new String(store.get(awayK).getValue().getValue());
                String awaysc = new String(store.get(awayscK).getValue().getValue());

                games.add(date + '\t' + away + '\t' + awaysc + '\t' + home + '\t' + homesc);
                j = 0;
            }
         }
        Collections.sort(games);
        for (Integer i = 0;i < games.size();i++)
        {
            System.out.println(games.get(i));
        }
        store.close();
    }


    public static void main(String[] args) throws SQLException {
        Query q = new Query();
        q.getGameList();
        q.getPlayerTeam();
    }
}