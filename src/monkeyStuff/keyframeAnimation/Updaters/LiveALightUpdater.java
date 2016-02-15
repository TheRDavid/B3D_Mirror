/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monkeyStuff.keyframeAnimation.Updaters;

import b3dElements.animations.keyframeAnimations.AnimationType;
import com.jme3.light.AmbientLight;
import com.jme3.light.Light;
import com.jme3.math.ColorRGBA;
import java.io.Serializable;
import monkeyStuff.keyframeAnimation.LiveKeyframeProperty;
import monkeyStuff.keyframeAnimation.LiveKeyframeUpdater;

/**
 *
 * @author User
 */
public class LiveALightUpdater extends LiveKeyframeUpdater<AmbientLight>
{

    public LiveALightUpdater(AmbientLight object)
    {
        super(object);
    }

    @Override
    protected void setValue(AnimationType type, Object value)
    {
        //System.out.println("Light Setting to " + value);
        if (type.equals(AnimationType.Light_Color_Blend))
            object.setColor((ColorRGBA) value);
    }

    @Override
    public Serializable getLiveValue(AnimationType type) throws Exception
    {
        if (type.equals(AnimationType.Light_Color_Blend))
            return new ColorRGBA(object.getColor());
        throw new Exception("No such live value!\n" + type.toString());
    }

    @Override
    public LiveKeyframeUpdater createNew()
    {
        LiveALightUpdater updater = new LiveALightUpdater(object);
        for (LiveKeyframeProperty kfp : getKeyframeProperties())
            updater.getKeyframeProperties().add(kfp.createNew(updater));
        return updater;
    }
}
