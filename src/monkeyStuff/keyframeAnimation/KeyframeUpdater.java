package monkeyStuff.keyframeAnimation;

import com.jme3.scene.Spatial;
import java.io.Serializable;
import java.util.ArrayList;
import monkeyStuff.keyframeAnimation.Updaters.AnimationType;
import monkeyStuff.keyframeAnimation.Updaters.SpatialUpdater;

/**
 *
 * @author David
 */
public abstract class KeyframeUpdater<E>
{

    private ArrayList<KeyframeProperty> keyframeProperties = new ArrayList<>();
    protected E object;
    private int maxFrames;

    public KeyframeUpdater(E object)
    {
        this.object = object;
    }

    public ArrayList<KeyframeProperty> getKeyframeProperties()
    {
        return keyframeProperties;
    }

    protected void update(int frame)
    {
        if (frame <= maxFrames)
            for (KeyframeProperty kfp : keyframeProperties)
                if (!kfp.isDone())
                    setValue(kfp.type, kfp.step(frame));
    }

    public void reset()
    {
        for (KeyframeProperty kfp : keyframeProperties)
            kfp.setDone(false);
    }

    public int calcMaxFrames()
    {
        maxFrames = 0;
        for (KeyframeProperty kfp : keyframeProperties)
            maxFrames = kfp.getValues().length > maxFrames ? kfp.getValues().length : maxFrames;

        System.out.println("Returning " + maxFrames);
        return maxFrames;
    }

    protected abstract void setValue(AnimationType type, Object value);

    public abstract Serializable getLiveValue(AnimationType type) throws Exception;

    void calcValues()
    {
        for (KeyframeProperty kfp : keyframeProperties)
            kfp.calcValues();
    }

    public E getObject()
    {
        return object;
    }

    public abstract KeyframeUpdater createNew();
}
