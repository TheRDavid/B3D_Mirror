/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monkeyStuff.keyframeAnimation.Properties;

import com.jme3.math.Vector3f;
import java.util.logging.Level;
import java.util.logging.Logger;
import monkeyStuff.keyframeAnimation.KeyframeProperty;
import monkeyStuff.keyframeAnimation.KeyframeUpdater;
import monkeyStuff.keyframeAnimation.Updaters.AnimationType;

/**
 *
 * @author User
 */
public class Vector3fProperty extends KeyframeProperty<Vector3f>
{

    public Vector3fProperty(AnimationType type, int frames, Vector3f firstValue, Vector3f lastValue, KeyframeUpdater kfu) throws Exception
    {
        super(type, frames, firstValue, lastValue, kfu);
    }

    public Vector3fProperty(AnimationType type, int frames, Vector3f value, KeyframeUpdater kfu) throws Exception
    {
        super(type, frames, value, kfu);
    }

    @Override
    public void calcValues()
    {
        boolean cDone = false;
        int cStart = 0;
        int cEnd;
        System.out.println("Before:");
        for (Vector3f v3f : values)
            System.out.println(v3f);
        while (!cDone)
        {
            for (int i = cStart + 1; i < values.length; i++)
            {
                System.out.println("Looping through value at " + i);
                if (values[i] != null)
                {
                    cEnd = i;
                    int inBetween = cEnd - cStart;
                    Vector3f startVector = values[cStart];
                    Vector3f endVector = values[cEnd];
                    Vector3f diffVector = endVector.subtract(startVector).divide(inBetween);
                    //linear
                    for (int j = cStart + 1; j < cEnd; j++)
                    {
                        values[j] = values[j - 1].add(diffVector);
                        System.out.println("Set " + j + " to " + values[j]);
                    }
                    cStart = cEnd;
                    cDone = cStart == values.length - 1;
                }
            }
        }
        System.out.println("After:");
        for (Vector3f v3f : values)
            System.out.println(v3f);
    }

    @Override
    public KeyframeProperty createNew(KeyframeUpdater kfu)
    {
        try
        {
            KeyframeProperty property = new Vector3fProperty(type, values.length, new Vector3f(values[0]), new Vector3f(values[values.length - 1]), kfu);
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
