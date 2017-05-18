import oracle.kv.*;

import java.util.Arrays;
import java.util.Map;

public class GetTableValues {
    public void gtv(String id) {
        KVStore store = KVStoreFactory.getStore(new KVStoreConfig("kvstore", "localhost:5000"));
        Key majorKeyPathOnly = Key.createKey(Arrays.asList("movie", id));
        System.out.println("Table: movie");
        System.out.println("ID: " + id);
        Map<Key, ValueVersion> fields = store.multiGet(majorKeyPathOnly, null, null);
        for (Map.Entry<Key, ValueVersion> field : fields.entrySet()) {
            String fieldValue = new String(field.getValue().getValue().getValue());
            System.out.println(fieldValue);
        }
        System.out.println("\n");
        store.close();
    }
}
