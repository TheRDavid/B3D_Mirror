/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monkeyStuff.keyframeAnimation.Properties;

import com.jme3.math.Quaternion;
import java.util.logging.Level;
import java.util.logging.Logger;
import monkeyStuff.keyframeAnimation.KeyframeProperty;
import monkeyStuff.keyframeAnimation.KeyframeUpdater;
import monkeyStuff.keyframeAnimation.Updaters.AnimationType;

/**
 *
 * @author User
 */
public class QuaternionProperty extends KeyframeProperty<Quaternion>
{

    public QuaternionProperty(AnimationType type, int frames, Quaternion firstValue, Quaternion lastValue, KeyframeUpdater kfu) throws Exception
    {
        super(type, frames, firstValue, lastValue, kfu);
    }

    public QuaternionProperty(AnimationType type, int frames, Quaternion firstValue, KeyframeUpdater kfu) throws Exception
    {
        super(type, frames, firstValue, kfu);
    }

    @Override
    public void calcValues()
    {
        boolean cDone = false;
        int currentStart = 0;
        int currentEnd;
        while (!cDone)
        {
            for (int i = currentStart + 1; i < values.length; i++)
            {
                // System.out.println("Looping through value at " + i);
                if (values[i] != null)
                {
                    currentEnd = i;
                    int inBetween = currentEnd - currentStart;
                    Quaternion startQuaternion = values[currentStart];
                    Quaternion endQuaternion = values[currentEnd];
                    Quaternion diffQuaternion = endQuaternion.subtract(startQuaternion).mult(1 / inBetween);
                    //linear
                    for (int j = currentStart + 1; j < currentEnd; j++)
                    {
                        values[j] = values[j - 1].add(diffQuaternion);
                        System.out.println("Set " + j + " to " + values[j]);
                    }
                    cDone = currentEnd == values.length - 1;
                }
            }
        }
        //System.out.println("Values of " + type + ":");
        //for (Vector3f v3f : values)
        //    System.out.println(v3f);
    }

    @Override
    public KeyframeProperty createNew()
    {
        try
        {
            KeyframeProperty property = new QuaternionProperty(type, values.length, values[0], values[values.length - 1], updater);
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
