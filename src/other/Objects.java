package other;

import b3dElements.B3D_Element;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

/**
 *
 * @author David
 */
public class Objects
{

    private static HashMap<Integer, Object> originalObjects = new HashMap<Integer, Object>();
    private static HashMap<UUID, B3D_Element> b3d_Elements = new HashMap<UUID, B3D_Element>();

    public void add(Object originalObject, B3D_Element element)
    {
        originalObjects.put(originalObject.hashCode(), originalObject);
        b3d_Elements.put(element.getUUID(), element);
        Wizard.getObjectReferences().addReference(element.getUUID(), originalObject.hashCode());
        //System.out.println("New Connection, " + element.getClass().getSimpleName() + ": " + element.getUUID() + " -> " + originalObject.getClass().getSimpleName() + ": " + originalObject.hashCode());
    }

    public void remove(int id, UUID uuid)
    {
        originalObjects.remove(id);
        b3d_Elements.remove(uuid);
        Wizard.getObjectReferences().removeReference(uuid, id);
    }

    public void remove(int id)
    {
        UUID uuid = Wizard.getObjectReferences().getUUID(id);
        remove(id, uuid);
    }

    public void remove(UUID uuid)
    {
        int id = Wizard.getObjectReferences().getID(uuid);
        remove(id, uuid);
    }

    public Object getOriginalObject(int id)
    {
        return originalObjects.get(id);
    }

    public B3D_Element getB3D_Element(UUID uuid)
    {
        return b3d_Elements.get(uuid);
    }

    /**
     * Only use as Iterator, don't directly add shit!
     *
     * @return all Original Objects
     */
    public Collection getOriginalObjectsIterator()
    {
        return originalObjects.values();
    }

    /**
     * Only use as Iterator, don't directly add shit!
     *
     * @return all Original Objects
     */
    public Collection<B3D_Element> getB3D_ElementsIterator()
    {
        return b3d_Elements.values();
    }

    /**
     * Also clears all References!
     */
    public void clear()
    {
        originalObjects.clear();
        b3d_Elements.clear();
        Wizard.getObjectReferences().clear();
    }
}
