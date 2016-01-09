/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monkeyStuff.keyframeAnimation.Properties;

import com.jme3.math.Vector3f;
import monkeyStuff.keyframeAnimation.KeyframeProperty;

/**
 *
 * @author User
 */
public class Vector3fProperty extends KeyframeProperty<Vector3f>
{

    public Vector3fProperty(String type, int frames, Vector3f firstValue, Vector3f lastValue) throws Exception
    {
        super(type, frames, firstValue, lastValue);
    }

    @Override
    public void calcValues()
    {
        boolean done = false;
        int currentStart = 0;
        int currentEnd = values.length - 1;
        while (!done)
        {
            for (int i = currentStart + 1; i < values.length; i++)
            {
               // System.out.println("Looping through value at " + i);
                if (values[i] != null)
                {
                    currentEnd = i;
                    int inBetween = currentEnd - currentStart;
                    Vector3f startVector = values[currentStart];
                    Vector3f endVector = values[currentEnd];
                    Vector3f diffVector = endVector.subtract(startVector).divide(inBetween);
                    //linear
                    for (int j = currentStart + 1; j < currentEnd; j++)
                    {
                        values[j] = values[j - 1].add(diffVector);
                       // System.out.println("Set " + j + " to " + values[j]);
                    }
                    done = currentEnd == values.length - 1;
                }
            }
        }
       // System.out.println("Values of " + type + ":");
        //for (Vector3f v3f : values)
        //    System.out.println(v3f);
    }
}
