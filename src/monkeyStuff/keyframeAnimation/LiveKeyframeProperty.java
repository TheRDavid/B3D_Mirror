package monkeyStuff.keyframeAnimation;

import java.io.Serializable;
import java.lang.reflect.Array;
import b3dElements.animations.keyframeAnimations.AnimationType;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public abstract class LiveKeyframeProperty<E extends Serializable>
{

    protected boolean done = false;
    protected E[] values;
    public AnimationType type;
    protected LiveKeyframeUpdater updater;
    private ArrayList<Integer> indices = new ArrayList<>();

    public LiveKeyframeProperty(AnimationType type, int frames, E firstValue, E lastValue, LiveKeyframeUpdater kfu) throws Exception
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

    public LiveKeyframeProperty(AnimationType type, int frames, E firstValue, LiveKeyframeUpdater kfu) throws Exception
    {
        if (frames < 2)
            throw new Exception("At least 2 franmes requiered!");
        //values = (E[]) new Object[frames];
        updater = kfu;
        values = (E[]) Array.newInstance(firstValue.getClass(), frames);
       // System.out.println("setze [0] auf "+firstValue);
        values[0] = firstValue;
        values[frames - 1] = firstValue;
        this.type = type;
    }

    public LiveKeyframeUpdater getUpdater()
    {
        return updater;
    }

    public final void storeIndexes()
    {
        indices.clear();
        for (int i = 0; i < values.length; i++)
            if (values[i] != null)
                indices.add(i);
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

    public abstract LiveKeyframeProperty createNew(LiveKeyframeUpdater kfu);

    public final void cutValues()
    {
        boolean done = false;
        int index = values.length - 1;
        while (!done)
        {
            if (values[index] != null)
            {
               /* System.out.println("E: "+values.getClass().getName());
                System.out.println("values[index]: "+values[index]);
                System.out.println("values[0]: "+values[0]);
                System.out.println("values[0].getClass: "+values[0].getClass());
                System.out.println("index: "+index);*/
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

    public int numKeyframes()
    {
        int numKeyframes = 0;
        for (int i = 0; i < values.length; i++)
            if (values[i] != null)
                numKeyframes++;
        return numKeyframes;
    }

    public ArrayList<Integer> getIndices()
    {
        return indices;
    }

    public void setIndices(ArrayList<Integer> indices)
    {
        this.indices = indices;
    }

    public final void uncalcValues()
    {
        for (int i = 1; i < values.length-1; i++)
            if (!indices.contains(i)) // should be optimized... but probably won't, since it's not realtime
                values[i] = null;
    }
}
