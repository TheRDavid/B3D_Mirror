package monkeyStuff.keyframeAnimation.Updaters;

import b3dElements.animations.keyframeAnimations.AnimationType;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import java.io.Serializable;
import monkeyStuff.keyframeAnimation.LiveKeyframeProperty;
import monkeyStuff.keyframeAnimation.LiveKeyframeUpdater;

/**
 *
 * @author David
 */
public class LiveSpatialUpdater extends LiveKeyframeUpdater<Spatial>
{

    public LiveSpatialUpdater(Spatial object)
    {
        super(object);
    }

    @Override
    protected void setValue(AnimationType type, Object value)
    {
        System.out.println("Spatial Setting to " + value);
        if (type.equals(AnimationType.Translation))
            object.setLocalTranslation((Vector3f) value);
        else if (type.equals(AnimationType.Rotation))
        {
           // System.out.println("Setting to " + value);
            object.setLocalRotation((Quaternion) value);
        } else if (type.equals(AnimationType.Scale))
            object.setLocalScale((Vector3f) value);
    }

    @Override
    public Serializable getLiveValue(AnimationType type) throws Exception
    {
        if (type.equals(AnimationType.Translation))
            return new Vector3f(object.getLocalTranslation());
        else if (type.equals(AnimationType.Rotation))
            return new Quaternion(object.getLocalRotation());
        else if (type.equals(AnimationType.Scale))
            return new Vector3f(object.getLocalScale());
        throw new Exception("No such live value!\n" + type.toString());
    }

    @Override
    public LiveKeyframeUpdater createNew()
    {
        LiveSpatialUpdater updater = new LiveSpatialUpdater(object);
        for (LiveKeyframeProperty kfp : getKeyframeProperties())
            updater.getKeyframeProperties().add(kfp.createNew(updater));
        return updater;
    }
}
