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
import java.io.Serializable;

/**
 *
 * @author User
 */
public class BoolProperty extends LiveKeyframeProperty<Boolean> implements Serializable
{

    public BoolProperty(AnimationType type, int frames, Boolean firstValue, Boolean lastValue, LiveKeyframeUpdater kfu) throws Exception
    {
        super(type, frames, firstValue, lastValue, kfu);
    }

    public BoolProperty(AnimationType type, int frames, Boolean value, LiveKeyframeUpdater kfu) throws Exception
    {
        super(type, frames, value, kfu);
    }

    @Override
    public void calcValues()
    {
        storeIndexes();
        boolean cDone = false;
        int cStart = 0;
        while (!cDone)
            for (int i = cStart + 1; i < values.length; i++)
                if (values[i] != null)
                {
                    for (int j = cStart + 1; j < i; j++)
                        values[j] = !values[i];
                    cStart = i;
                    cDone = cStart == values.length - 1;
                }
    }

    @Override
    public LiveKeyframeProperty createNew(LiveKeyframeUpdater kfu)
    {
        cutValues();
        try
        {
            LiveKeyframeProperty property = new BoolProperty(type, values.length, values[0], values[values.length - 1], kfu);
            for (int i = 1; i < values.length - 1; i++)
                property.setValue(i, values[i]);
            return property;
        } catch (Exception ex)
        {
            Logger.getLogger(BoolProperty.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
