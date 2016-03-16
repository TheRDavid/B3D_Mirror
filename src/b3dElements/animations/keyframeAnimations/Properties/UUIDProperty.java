/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package b3dElements.animations.keyframeAnimations.Properties;

import java.util.logging.Level;
import java.util.logging.Logger;
import monkeyStuff.keyframeAnimation.LiveKeyframeProperty;
import monkeyStuff.keyframeAnimation.LiveKeyframeUpdater;
import b3dElements.animations.keyframeAnimations.AnimationType;
import b3dElements.animations.keyframeAnimations.InterpolationType;
import com.jme3.scene.Spatial;
import java.io.Serializable;
import java.util.UUID;
import monkeyStuff.keyframeAnimation.ConstraintControl;

/**
 *
 * @author User
 */
public class UUIDProperty extends LiveKeyframeProperty<UUID> implements Serializable
{

    public UUIDProperty(AnimationType type, int frames, UUID firstValue, UUID lastValue, LiveKeyframeUpdater kfu) throws Exception
    {
        super(type, frames, firstValue, lastValue, kfu);
    }

    public UUIDProperty(AnimationType type, int frames, UUID value, LiveKeyframeUpdater kfu) throws Exception
    {
        super(type, frames, value, kfu);
    }

    @Override
    public void calcValues()
    {
        storeIndexes();
        /*should work without
         * int cStart = values.length - 2;
         for (int i = cStart; i > 0; i--)
         if (values[i] != null)
         {
         for (int j = cStart; j > i; j--)
         values[j] = values[i];
         cStart = i - 1;
         }*/
    }

    @Override
    public void setDone(boolean done)
    {
        super.setDone(done);
        if (!done)
        {
            Spatial spat = (Spatial) updater.getObject();
            while (spat.getControl(ConstraintControl.class) != null)
            {
                System.out.println("REMOVED CONSTRAINED FROM " + spat.getName());
                spat.removeControl(ConstraintControl.class);
            }
        }
    }

    @Override
    public LiveKeyframeProperty createNew(LiveKeyframeUpdater kfu)
    {
        cutValues();
        try
        {
            LiveKeyframeProperty property = new UUIDProperty(type, values.length, values[0], values[values.length - 1], kfu);
            for (int i = 1; i < values.length - 1; i++)
                property.setValue(i, values[i], InterpolationType.Linear);
            return property;
        } catch (Exception ex)
        {
            Logger.getLogger(UUIDProperty.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
