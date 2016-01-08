package monkeyStuff.keyframeAnimation;

import java.util.ArrayList;

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

    public int calcMaxFrames()
    {
        maxFrames = 0;
        for (KeyframeProperty kfp : keyframeProperties)
            maxFrames = kfp.getLastFrame() > maxFrames ? kfp.getLastFrame() : maxFrames;
        return maxFrames;
    }

    protected abstract void setValue(int type, Object value);
}
