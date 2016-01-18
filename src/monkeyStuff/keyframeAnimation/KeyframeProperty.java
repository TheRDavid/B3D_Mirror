package monkeyStuff.keyframeAnimation;

import java.io.Serializable;
import java.lang.reflect.Array;
import monkeyStuff.keyframeAnimation.Updaters.AnimationType;

/**
 *
 * @author David
 */
public abstract class KeyframeProperty<E extends Serializable>
{

    protected boolean done = false;
    protected E[] values;
    public AnimationType type;
    protected KeyframeUpdater updater;

    public KeyframeProperty(AnimationType type, int frames, E firstValue, E lastValue, KeyframeUpdater kfu) throws Exception
    {
        if (frames < 2)
            throw new Exception("At least 2 franmes requiered!");
        updater = kfu;
        //values = (E[]) new Object[frames];
        values = (E[]) Array.newInstance(firstValue.getClass(), frames);
        values[0] = firstValue;
        values[frames - 1] = lastValue;
        this.type = type;
    }

    public KeyframeProperty(AnimationType type, int frames, E firstValue, KeyframeUpdater kfu) throws Exception
    {
        if (frames < 2)
            throw new Exception("At least 2 franmes requiered!");
        //values = (E[]) new Object[frames];
        updater = kfu;
        values = (E[]) Array.newInstance(firstValue.getClass(), frames);
        values[0] = firstValue;
        values[frames - 1] = firstValue;
        this.type = type;
    }

    public KeyframeUpdater getUpdater()
    {
        return updater;
    }

    public void setValue(int frame, E value)
    {
        if (frame >= values.length)
        {
            E[] valuesCopy = (E[]) Array.newInstance(values[0].getClass(), values.length);
            for (int i = 0; i < values.length; i++)
                valuesCopy[i] = values[i];
            values = (E[]) Array.newInstance(values[0].getClass(), frame + 1);
            for (int i = 0; i < valuesCopy.length; i++)
                values[i] = valuesCopy[i];
        }
        values[frame] = value;
    }

    public E step(int frame)
    {
        done = frame == values.length - 1;
        return values[frame];
    }

    public abstract KeyframeProperty createNew(KeyframeUpdater kfu);

    protected final void cutValues()
    {
        boolean done = false;
        int index = values.length - 1;
        while (!done)
        {
            if (values[index] != null)
            {
                E[] valuesCopy = (E[]) Array.newInstance(values[0].getClass(), index + 1);
                for (int i = 0; i <= index; i++)
                    valuesCopy[i] = values[i];
                values = valuesCopy;
                done = true;
            }
            index--;
        }
    }

    public E[] getValues()
    {
        return values;
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
