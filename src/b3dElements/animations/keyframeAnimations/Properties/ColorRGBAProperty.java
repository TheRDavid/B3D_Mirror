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
import com.jme3.math.ColorRGBA;
import java.io.Serializable;
import javax.vecmath.Color3f;

/**
 *
 * @author User
 */
public class ColorRGBAProperty extends LiveKeyframeProperty<ColorRGBA> implements Serializable
{

    public ColorRGBAProperty(AnimationType type, int frames, ColorRGBA firstValue, ColorRGBA lastValue, LiveKeyframeUpdater kfu) throws Exception
    {
        super(type, frames, firstValue, lastValue, kfu);
    }

    public ColorRGBAProperty(AnimationType type, int frames, ColorRGBA value, LiveKeyframeUpdater kfu) throws Exception
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
                    float inBetween = i - cStart;
                    ColorRGBA start = values[cStart];
                    ColorRGBA end = values[i];
                    ColorRGBA diff = new ColorRGBA(
                            (end.r - start.r) / inBetween,
                            (end.g - start.g) / inBetween,
                            (end.b - start.b) / inBetween,
                            (end.a - start.a) / inBetween);
                    //linear
                    for (int j = cStart + 1; j < i; j++)
                        values[j] = values[j - 1].add(diff);
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
            LiveKeyframeProperty property = new ColorRGBAProperty(type, values.length, values[0], values[values.length - 1], kfu);
            for (int i = 1; i < values.length - 1; i++)
                property.setValue(i, values[i]);
            return property;
        } catch (Exception ex)
        {
            Logger.getLogger(ColorRGBAProperty.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
