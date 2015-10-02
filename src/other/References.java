package other;

import java.util.HashMap;
import java.util.UUID;

/**
 *
 * @author David
 */
public class References
{

    private HashMap<Integer, UUID> originalToB3DMap = new HashMap<Integer, UUID>();
    private HashMap<UUID, Integer> b3dToOriginalMap = new HashMap<UUID, Integer>();

    public void addReference(UUID uuid, int id)
    {
        originalToB3DMap.put(id, uuid);
        b3dToOriginalMap.put(uuid, id);
    }

    public void removeReference(UUID uuid, int id)
    {
        originalToB3DMap.remove(id);
        b3dToOriginalMap.remove(uuid);
    }

    public UUID getUUID(int id)
    {
        return originalToB3DMap.get(id);
    }

    public Integer getID(UUID uuid)
    {
        //System.out.println("Return id of " + uuid);
        return b3dToOriginalMap.get(uuid);
    }

    void clear()
    {
        originalToB3DMap.clear();
        b3dToOriginalMap.clear();
    }
}
