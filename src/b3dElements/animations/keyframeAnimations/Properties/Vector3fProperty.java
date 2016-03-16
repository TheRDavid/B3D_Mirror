/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package b3dElements.animations.keyframeAnimations.Properties;

import b3dElements.animations.keyframeAnimations.AnimationType;
import b3dElements.animations.keyframeAnimations.InterpolationType;
import com.jme3.math.Vector3f;
import java.util.logging.Level;
import java.util.logging.Logger;
import monkeyStuff.keyframeAnimation.LiveKeyframeProperty;
import monkeyStuff.keyframeAnimation.LiveKeyframeUpdater;
import java.io.Serializable;

/**
 *
 * @author User
 */
public class Vector3fProperty extends LiveKeyframeProperty<Vector3f> implements Serializable
{

    public Vector3fProperty(AnimationType type, int frames, Vector3f firstValue, Vector3f lastValue, LiveKeyframeUpdater kfu) throws Exception
    {
        super(type, frames, firstValue, lastValue, kfu);
    }

    public Vector3fProperty(AnimationType type, int frames, Vector3f value, LiveKeyframeUpdater kfu) throws Exception
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
        {
            for (int i = cStart + 1; i < values.length; i++)
            {
                if (values[i] != null)
                {
                    int inBetween = i - cStart;
                    Vector3f startVector = values[cStart];
                    Vector3f endVector = values[i];
                    Vector3f diffVector = endVector.subtract(startVector).divide(inBetween);
                    if (getInterpolationType(cStart).equals(InterpolationType.Linear))
                        for (int j = cStart + 1; j < i; j++)
                            values[j] = values[j - 1].add(diffVector);
                    else if (getInterpolationType(cStart).equals(InterpolationType.Ease_In))
                    {
                        double k = 0;
                        for (int j = cStart + 1; j < i; j++, k += 2 / (double) inBetween)
                            values[j] = values[j - 1].add(diffVector.mult((float) k));
                    } else if (getInterpolationType(cStart).equals(InterpolationType.Ease_Out))
                    {
                        double k = 2;
                        for (int j = cStart + 1; j < i; j++, k -= 2 / (double) inBetween)
                            values[j] = values[j - 1].add(diffVector.mult((float) k));
                    }
                    cStart = i;
                    cDone = cStart == values.length - 1;
                }
            }
        }
    }

    @Override
    public LiveKeyframeProperty createNew(LiveKeyframeUpdater kfu)
    {
        cutValues();
        try
        {
            LiveKeyframeProperty property = new Vector3fProperty(type, values.length, new Vector3f(values[0]), new Vector3f(values[values.length - 1]), kfu);
            for (int i = 0; i < values.length - 1; i++)
            {
                InterpolationType iT = getInterpolationTypes()[i];
                property.setValue(i, values[i], iT);
            }
            return property;
        } catch (Exception ex)
        {
            Logger.getLogger(Vector3fProperty.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
