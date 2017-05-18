import oracle.kv.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

public class GetSortedMovies {
    public void gsm() {
        KVStore store = KVStoreFactory.getStore(new KVStoreConfig("kvstore", "localhost:5000"));
        ArrayList<String> newKey = new ArrayList<String>();
        ArrayList<String> movie = new ArrayList<String>();
        newKey.add("movie");
        Key searchKey = Key.createKey(newKey);
        Iterator<KeyValueVersion> search = store.storeIterator(Direction.UNORDERED, 0, searchKey, null, null);
        while (search.hasNext()) {
            Key temp = search.next().getKey();
            ArrayList<String> newKey2 = new ArrayList<String>();
            ArrayList<String> newKey3 = new ArrayList<String>();
            newKey2.add("movie");
            newKey2.add(temp.getMajorPath().get(1));
            newKey3.add("movie");
            newKey3.add(temp.getMajorPath().get(1));
            Key yeark = Key.createKey(newKey2, Arrays.asList("year"));
            Key namek = Key.createKey(newKey3,Arrays.asList("name"));
            String year = new String(store.get(yeark).getValue().getValue());
            String name = new String(store.get(namek).getValue().getValue());

//            System.out.println(year + ' ' + temp.getMajorPath().get(1) + ' ' + name);
            movie.add(year + '\t' + temp.getMajorPath().get(1) + '\t' + name);

        }
        Collections.sort(movie);
        for (Integer i = 0;i < movie.size();i++)
        {
            System.out.println(movie.get(i));
        }
    }
}
