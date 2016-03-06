package monkeyStuff.keyframeAnimation;

import java.io.Serializable;
import java.util.ArrayList;
import b3dElements.animations.keyframeAnimations.AnimationType;

/**
 *
 * @author David
 */
public abstract class LiveKeyframeUpdater<E>
{

    private ArrayList<LiveKeyframeProperty> keyframeProperties = new ArrayList<LiveKeyframeProperty>();
    protected E object;
    private int maxFrames;

    public LiveKeyframeUpdater(E object)
    {
        this.object = object;
    }

    public ArrayList<LiveKeyframeProperty> getKeyframeProperties()
    {
        return keyframeProperties;
    }

    protected void update(int frame)
    {
        if (frame <= maxFrames)
            for (LiveKeyframeProperty kfp : keyframeProperties)
                if (!kfp.isDone())
                    setValue(kfp.type, kfp.step(frame));
    }

    protected void goTo(int frame)
    {
        if (frame > maxFrames)
            frame = maxFrames - 1;
        for (LiveKeyframeProperty kfp : keyframeProperties)
        {
            kfp.setDone(kfp.getValues().length <= frame);
            if (!kfp.isDone())
                setValue(kfp.type, kfp.step(frame));
        }
    }

    public void reset()
    {
        for (LiveKeyframeProperty kfp : keyframeProperties)
            kfp.setDone(false);
    }

    public int calcMaxFrames()
    {
        maxFrames = 0;
        for (LiveKeyframeProperty kfp : keyframeProperties)
            maxFrames = kfp.getValues().length > maxFrames ? kfp.getValues().length : maxFrames;
        return maxFrames;
    }

    protected abstract void setValue(AnimationType type, Object value);

    public abstract Serializable getLiveValue(AnimationType type) throws Exception;

    void calcValues()
    {
        for (LiveKeyframeProperty kfp : keyframeProperties)
            kfp.calcValues();
    }

    public E getObject()
    {
        return object;
    }

    public abstract LiveKeyframeUpdater createNew();

    void uncalcValues()
    {
        for (LiveKeyframeProperty lkp : keyframeProperties)
            lkp.uncalcValues();
    }
}
