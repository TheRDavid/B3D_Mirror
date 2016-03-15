package monkeyStuff.keyframeAnimation.Updaters;

import b3dElements.animations.keyframeAnimations.AnimationType;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import java.io.Serializable;
import java.util.UUID;
import monkeyStuff.keyframeAnimation.LiveKeyframeProperty;
import monkeyStuff.keyframeAnimation.LiveKeyframeUpdater;
import monkeyStuff.keyframeAnimation.TranslationControl;
import other.Wizard;

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
        else if (type.equals(AnimationType.Translation_Constraint) && value != null)
        {
            System.out.println("CONSTRAINT CHANGED");
            object.removeControl(TranslationControl.class);
            if (!Wizard.NULL_SELECTION.equals(value))
            {
                System.out.println("NEW CONTRAINT");
                int hashCode = Wizard.getObjectReferences().getID((UUID) value);
                Spatial spat = (Spatial) Wizard.getObjects().getOriginalObject(hashCode);
                object.addControl(new TranslationControl(spat, object));
            }
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
        else if (type.equals(AnimationType.Translation_Constraint))
            return Wizard.NULL_SELECTION;
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
