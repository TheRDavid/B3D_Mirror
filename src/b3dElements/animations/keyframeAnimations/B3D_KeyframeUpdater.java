package b3dElements.animations.keyframeAnimations;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author David
 */
public class B3D_KeyframeUpdater<E> implements Serializable
{

    private ArrayList<B3D_KeyframeProperty> keyframeProperties = new ArrayList<>();
    protected UUID objectID;

    public B3D_KeyframeUpdater(UUID objectID)
    {
        this.objectID = objectID;
    }

    public ArrayList<B3D_KeyframeProperty> getKeyframeProperties()
    {
        return keyframeProperties;
    }

    public UUID getObjectID()
    {
        return objectID;
    }
}
