package monkeyStuff.keyframeAnimation.Updaters;

import b3dElements.animations.keyframeAnimations.AnimationType;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import java.io.Serializable;
import monkeyStuff.CustomParticleEmitter;
import monkeyStuff.keyframeAnimation.LiveKeyframeProperty;
import monkeyStuff.keyframeAnimation.LiveKeyframeUpdater;

/**
 *
 * @author David
 */
public class LiveParticleEmitterUpdater extends LiveKeyframeUpdater<CustomParticleEmitter>
{

    public LiveParticleEmitterUpdater(CustomParticleEmitter object)
    {
        super(object);
    }

    @Override
    protected void setValue(AnimationType type, Object value)
    {
        System.out.println("Setting to " + value);
        if (type.equals(AnimationType.Translation))
            object.setLocalTranslation((Vector3f) value);
        else if (type.equals(AnimationType.Rotation))
            object.setLocalRotation((Quaternion) value);
        else if (type.equals(AnimationType.Scale))
            object.setLocalScale((Vector3f) value);
        else if (type.equals(AnimationType.Frozen))
            object.setEnabled(!(boolean) value);
        else if (type.equals(AnimationType.Particles_Per_Second))
            object.setParticlesPerSec(Float.valueOf(value + "")); // k
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
        else if (type.equals(AnimationType.Frozen))
            return !object.isEnabled();
        else if (type.equals(AnimationType.Particles_Per_Second))
            return object.getParticlesPerSec();
        throw new Exception("No such live value!\n" + type.toString());
    }

    @Override
    public LiveKeyframeUpdater createNew()
    {
        LiveParticleEmitterUpdater updater = new LiveParticleEmitterUpdater(object);
        for (LiveKeyframeProperty kfp : getKeyframeProperties())
            updater.getKeyframeProperties().add(kfp.createNew(updater));
        return updater;
    }
}
