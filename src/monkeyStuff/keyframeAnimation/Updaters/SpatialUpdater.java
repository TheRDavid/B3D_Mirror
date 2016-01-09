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

    public static final String TRANSLATION_TYPE = "Translation";

    public SpatialUpdater(Spatial object)
    {
        super(object);
    }

    @Override
    protected void setValue(String type, Object value)
    {
        System.out.println("Setting to "+value);
        if (type.equals(TRANSLATION_TYPE))
            object.setLocalTranslation((Vector3f) value);
    }
}
