import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import oracle.kv.*;


/**
 * Created by ws26 on 5/5/2017.
 */
public class HelloRecords {
    public static void main(String[] args) {

        KVStore store = KVStoreFactory.getStore(new KVStoreConfig("kvstore", "localhost:5000"));

        // C(reate)
        // This initializes an arbitrary key-value pair and stores it in KVLite.
        // The key-value structure is /helloKey : $value
        /*
        String keyString = "helloKey", valueString = "Hello, Oracle NoSQL!";
        Key key = Key.createKey(Arrays.asList(keyString));
        Value value = Value.createValue(valueString.getBytes());
        store.put(key, value);
*/
        Key key = Key.createKey(Arrays.asList("movie","92616"),Arrays.asList("name"));
        Value value = Value.createValue("Dr. Strangelove".getBytes());
        store.put(key, value);

//        String result = new String(store.get(key).getValue().getValue());
//        System.out.println(key.toString() + " : " + result);

        key = Key.createKey(Arrays.asList("movie","92616"),Arrays.asList("year"));
        value = Value.createValue("1964".getBytes());
        store.put(key, value);

//        result = new String(store.get(key).getValue().getValue());
//        System.out.println(key.toString() + " : " + result);

        key = Key.createKey(Arrays.asList("movie","92616"),Arrays.asList("rating"));
        value = Value.createValue("8.7".getBytes());
        store.put(key, value);

        Key majorKeyPathOnly = Key.createKey(Arrays.asList("movie", "92616"));
        Map<Key, ValueVersion> fields = store.multiGet(majorKeyPathOnly, null, null);
        for (Map.Entry<Key, ValueVersion> field : fields.entrySet()) {
            String fieldName = field.getKey().getMinorPath().get(0);
            String fieldValue = new String(field.getValue().getValue().getValue());
            System.out.println("\t" + majorKeyPathOnly + "/-/" + fieldName + "\t: " + fieldValue);
        }

        store.close();

    }
}