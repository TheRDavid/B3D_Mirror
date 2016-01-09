package monkeyStuff.keyframeAnimation;

import java.io.Serializable;
import java.lang.reflect.Array;

/**
 *
 * @author David
 */
public abstract class KeyframeProperty<E extends Serializable>
{

    protected boolean done = false;
    protected E[] values;
    public String type;

    public KeyframeProperty(String type, int frames, E firstValue, E lastValue) throws Exception
    {
        if (frames < 2)
            throw new Exception("At least 2 franmes requiered!");
        //values = (E[]) new Object[frames];
        values = (E[]) Array.newInstance(firstValue.getClass(), frames);
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
    
    public abstract void calcValues();
}
