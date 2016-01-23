/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package b3dElements.animations.keyframeAnimations.Properties;

import com.jme3.math.Quaternion;
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
public class QuaternionProperty extends LiveKeyframeProperty<Quaternion> implements Serializable
{

    public QuaternionProperty(AnimationType type, int frames, Quaternion firstValue, Quaternion lastValue, LiveKeyframeUpdater kfu) throws Exception
    {
        super(type, frames, firstValue, lastValue, kfu);
    }

    public QuaternionProperty(AnimationType type, int frames, Quaternion firstValue, LiveKeyframeUpdater kfu) throws Exception
    {
        super(type, frames, firstValue, kfu);
    }

    @Override
    public void calcValues()
    {
        storeIndexes();
        boolean cDone = false;
        int currentStart = 0;
        float currentStartF = 0;
        while (!cDone)
        {
            for (int i = currentStart + 1; i < values.length; i++)
            {
                if (values[i] != null)
                {
                    float inBetween = 1/(i - currentStartF);
                    Quaternion startQuaternion = values[currentStart];
                    Quaternion endQuaternion = values[i];
                   // System.out.println("Interpolation");
                    //linear
                    for (int j = currentStart + 1; j <= i; j++)
                    {
                        float factor = (j - currentStart) * inBetween;
                        Quaternion diffQuaternion = new Quaternion();
                        diffQuaternion.slerp(new Quaternion(startQuaternion), new Quaternion(endQuaternion), factor);
                        //System.out.println(diffQuaternion + "\tat " + factor);

                        values[j] = new Quaternion(diffQuaternion);
                    }
                    currentStart = i;
                    currentStartF = i;
                    cDone = i == values.length - 1;
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
            LiveKeyframeProperty property = new QuaternionProperty(type, values.length,
                    new Quaternion(values[0]), new Quaternion(values[values.length - 1]), kfu);
            for (int i = 1; i < values.length - 1; i++)
                property.setValue(i, values[i]);
            return property;
        } catch (Exception ex)
        {
            Logger.getLogger(Vector3fProperty.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
