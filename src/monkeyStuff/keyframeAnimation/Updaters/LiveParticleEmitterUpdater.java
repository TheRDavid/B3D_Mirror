package monkeyStuff.keyframeAnimation.Updaters;

import monkeyStuff.keyframeAnimation.TranslationControl;
import b3dElements.animations.keyframeAnimations.AnimationType;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import java.io.Serializable;
import java.util.UUID;
import monkeyStuff.CustomParticleEmitter;
import monkeyStuff.keyframeAnimation.LiveKeyframeProperty;
import monkeyStuff.keyframeAnimation.LiveKeyframeUpdater;
import other.Wizard;

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
        //System.out.println("Emitter Setting to " + value);
        if (type.equals(AnimationType.Translation))
            object.setLocalTranslation((Vector3f) value);
        else if (type.equals(AnimationType.Rotation))
            object.setLocalRotation((Quaternion) value);
        else if (type.equals(AnimationType.Scale))
            object.setLocalScale((Vector3f) value);
        else if (type.equals(AnimationType.Start_Color_Blend))
            object.setStartColor((ColorRGBA) value);
        else if (type.equals(AnimationType.End_Color_Blend))
            object.setEndColor((ColorRGBA) value);
        else if (type.equals(AnimationType.Frozen))
            object.setEnabled(!(boolean) value);
        else if (type.equals(AnimationType.Particles_Per_Second))
            object.setParticlesPerSec(Float.valueOf(value + "")); // k
        else if (type.equals(AnimationType.Emit_All))
        {
            if ((boolean) value)
                object.emitAllParticles();
        } else if (type.equals(AnimationType.Translation_Constraint) && value != null)
        {
            UUID id = (UUID) value;
            int hashCode = Wizard.getObjectReferences().getID(id);
            Spatial spat = (Spatial) Wizard.getObjects().getOriginalObject(hashCode);
            spat.removeControl(TranslationControl.class);
            spat.addControl(new TranslationControl(spat, object));
        }
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
        else if (type.equals(AnimationType.Emit_All))
            return false;
        else if (type.equals(AnimationType.End_Color_Blend))
            return object.getEndColor();
        else if (type.equals(AnimationType.Start_Color_Blend))
            return object.getStartColor();
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
