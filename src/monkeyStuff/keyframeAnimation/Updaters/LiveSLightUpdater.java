/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monkeyStuff.keyframeAnimation.Updaters;

import b3dElements.animations.keyframeAnimations.AnimationType;
import com.jme3.light.Light;
import com.jme3.light.SpotLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import java.io.Serializable;
import monkeyStuff.keyframeAnimation.LiveKeyframeProperty;
import monkeyStuff.keyframeAnimation.LiveKeyframeUpdater;

/**
 *
 * @author User
 */
public class LiveSLightUpdater extends LiveKeyframeUpdater<SpotLight>
{

    public LiveSLightUpdater(SpotLight object)
    {
        super(object);
    }

    @Override
    protected void setValue(AnimationType type, Object value)
    {
        //System.out.println("Light Setting to " + value);
        if (type.equals(AnimationType.Light_Color_Blend))
            object.setColor((ColorRGBA) value);
        else if (type.equals(AnimationType.Direction))
            object.setDirection((Vector3f) value);
        else if (type.equals(AnimationType.Position))
            object.setPosition((Vector3f) value);
    }

    @Override
    public Serializable getLiveValue(AnimationType type) throws Exception
    {
        if (type.equals(AnimationType.Light_Color_Blend))
            return new ColorRGBA(object.getColor());
        else if (type.equals(AnimationType.Direction))
            return new Vector3f(object.getDirection());
        else if (type.equals(AnimationType.Position))
            return new Vector3f(object.getPosition());
        throw new Exception("No such live value!\n" + type.toString());
    }

    @Override
    public LiveKeyframeUpdater createNew()
    {
        LiveSLightUpdater updater = new LiveSLightUpdater(object);
        for (LiveKeyframeProperty kfp : getKeyframeProperties())
            updater.getKeyframeProperties().add(kfp.createNew(updater));
        return updater;
    }
}
