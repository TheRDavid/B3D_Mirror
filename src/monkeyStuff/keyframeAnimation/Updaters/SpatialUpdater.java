package monkeyStuff.keyframeAnimation.Updaters;

import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import monkeyStuff.keyframeAnimation.KeyframeUpdater;

/**
 *
 * @author David
 */
public class SpatialUpdater extends KeyframeUpdater<Spatial>
{

    public static final int TRANSLATION_TYPE = 0;

    public SpatialUpdater(Spatial object)
    {
        super(object);
    }

    @Override
    protected void setValue(int type, Object value)
    {
        if (type == TRANSLATION_TYPE)
            object.setLocalTranslation((Vector3f) value);
    }
}
