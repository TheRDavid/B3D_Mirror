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
import java.io.Serializable;

/**
 *
 * @author User
 */
public class IntProperty extends LiveKeyframeProperty<Integer> implements Serializable
{

    public IntProperty(AnimationType type, int frames, Integer firstValue, Integer lastValue, LiveKeyframeUpdater kfu) throws Exception
    {
        super(type, frames, firstValue, lastValue, kfu);
    }

    public IntProperty(AnimationType type, int frames, Integer value, LiveKeyframeUpdater kfu) throws Exception
    {
        super(type, frames, value, kfu);
    }

    @Override
    public void calcValues()
    {
        storeIndexes();
        boolean cDone = false;
        int cStart = 0;
        double[] copyOf = new double[values.length];
        while (!cDone)
        {
            for (int i = cStart + 1; i < values.length; i++)
            {
                if (values[i] != null)
                {
                    double inBetween = i - cStart;
                    double start = values[cStart];
                    double end = values[i];
                    double diff = (end - start) / inBetween;
                    //linear
                    copyOf[cStart] = (double) values[cStart];
                    for (int j = cStart + 1; j < i; j++)
                    {
                        double d = copyOf[j - 1] + diff;
                        //          System.out.println(copyOf[j - 1] + " + " + diff + " = " + d);
                        copyOf[j] = d;
                    }
                    for (int j = cStart + 1; j < i; j++)
                    {
                        long l = Math.round(copyOf[j]);
                        values[j] = (int) l;
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
            LiveKeyframeProperty property = new IntProperty(type, values.length, values[0], values[values.length - 1], kfu);
            for (int i = 1; i < values.length - 1; i++)
            {
                InterpolationType iT = getInterpolationType(i);
                property.setValue(i, values[i], iT);
            }
            return property;
        } catch (Exception ex)
        {
            Logger.getLogger(IntProperty.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
