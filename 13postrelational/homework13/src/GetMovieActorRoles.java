import oracle.kv.*;

import java.util.Arrays;
import java.util.Map;

public class GetMovieActorRoles {

    public void gmar(String actorID, String movieID) {
        KVStore store = KVStoreFactory.getStore(new KVStoreConfig("kvstore", "localhost:5000"));

        Key roleMajorKeyPathOnly = Key.createKey(Arrays.asList("role", actorID, movieID));
        Map<Key, ValueVersion> fields = store.multiGet(roleMajorKeyPathOnly, null, null);
        for (Map.Entry<Key, ValueVersion> field : fields.entrySet()) {
            String fieldValue = new String(field.getValue().getValue().getValue());
            System.out.println(fieldValue);
        }
        store.close();
    }
}
