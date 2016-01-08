package monkeyStuff.keyframeAnimation;

import java.io.Serializable;

/**
 *
 * @author David
 */
public abstract class KeyframeProperty<E extends Serializable>
{

    private boolean done = false;
    private E[] values;
    public int type;

    public KeyframeProperty(int type, int frames, E firstValue, E lastValue) throws Exception
    {
        if (frames < 2)
            throw new Exception("At least 2 franmes requiered!");
        values = (E[]) new Object[frames];
        values[0] = firstValue;
        values[frames - 1] = lastValue;
        this.type = type;
    }

    public void setValue(int frame, E value)
    {
        values[frame] = value;
    }

    public E step(int frame)
    {
        done = frame == values.length - 1;
        return values[frame];
    }

    public int getLastFrame()
    {
        return values.length;
    }

    public boolean isDone()
    {
        return done;
    }

    public void setDone(boolean done)
    {
        this.done = done;
    }
}
