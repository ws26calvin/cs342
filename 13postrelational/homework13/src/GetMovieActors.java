import oracle.kv.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

public class GetMovieActors {
    public void gma(String movieID) {
        KVStore store = KVStoreFactory.getStore(new KVStoreConfig("kvstore", "localhost:5000"));
        System.out.println("Movie ID: " + movieID);
        ArrayList<String> newKey = new ArrayList<String>();
        newKey.add("role");
        newKey.add(movieID);
        Key searchKey = Key.createKey(newKey);
        Iterator<KeyValueVersion> search = store.storeIterator(Direction.UNORDERED, 0, searchKey, null, null);
        while(search.hasNext()){
            Key temp = search.next().getKey();
            ArrayList<String> newKey2 = new ArrayList<String>();
            ArrayList<String> newKey3 = new ArrayList<String>();
            newKey2.add("actor");
            newKey2.add(temp.getMajorPath().get(2));
            newKey3.add("actor");
            newKey3.add(temp.getMajorPath().get(2));

            Key fnameKey = Key.createKey(newKey2,Arrays.asList("firstname"));
            Key lnameKey = Key.createKey(newKey3,Arrays.asList("lastname"));
            String fname = new String(store.get(fnameKey).getValue().getValue());
            String lname = new String(store.get(lnameKey).getValue().getValue());
            String result = new String(store.get(temp).getValue().getValue());
            System.out.println(temp.getMajorPath().get(2) + '\t' + fname + ' ' + lname + "" + "\t" + result);
        }
    }
}
