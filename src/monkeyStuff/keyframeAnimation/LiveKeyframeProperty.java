package monkeyStuff.keyframeAnimation;

import b3dElements.animations.keyframeAnimations.AnimationType;
import b3dElements.animations.keyframeAnimations.InterpolationType;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public abstract class LiveKeyframeProperty<E extends Serializable>
{

    protected boolean done = false;
    protected E[] values;
    protected InterpolationType[] interpolationTypes;
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
        interpolationTypes = new InterpolationType[frames];
        values[0] = firstValue;
        values[frames - 1] = lastValue;
        interpolationTypes[0] = InterpolationType.Linear;
        interpolationTypes[frames - 1] = InterpolationType.Linear;
        this.type = type;
    }

    public LiveKeyframeProperty(AnimationType type, int frames, E firstValue, LiveKeyframeUpdater kfu) throws Exception
    {
        if (frames < 2)
            throw new Exception("At least 2 franmes requiered!");
        //values = (E[]) new Object[frames];
        updater = kfu;
        values = (E[]) Array.newInstance(firstValue.getClass(), frames);
        interpolationTypes = new InterpolationType[frames];
        // System.out.println("setze [0] auf "+firstValue);
        values[0] = firstValue;
        values[frames - 1] = firstValue;
        interpolationTypes[0] = InterpolationType.Linear;
        interpolationTypes[frames - 1] = InterpolationType.Linear;
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

    public void setInterpolationType(int frame, InterpolationType iType)
    {
        if (frame >= 0)
        {
            if (frame >= interpolationTypes.length)
            {
                InterpolationType[] iTypesCopy = new InterpolationType[interpolationTypes.length];
                for (int i = 0; i < interpolationTypes.length; i++)
                    iTypesCopy[i] = interpolationTypes[i];
                interpolationTypes = new InterpolationType[frame + 100];
                System.out.println("Length of interpolationTypes " + interpolationTypes.length);
                System.out.println("Length of iTypesCopy " + iTypesCopy.length);
                for (int i = 0; i < iTypesCopy.length&&i<interpolationTypes.length; i++)
                    interpolationTypes[i] = iTypesCopy[i];
            }
            interpolationTypes[frame] = iType;
        }
    }

    public InterpolationType[] getInterpolationTypes()
    {
        return interpolationTypes;
    }

    public void setInterpolationTypes(InterpolationType[] interpolationTypes)
    {
        this.interpolationTypes = interpolationTypes;
    }

    public void setValue(int frame, E value, InterpolationType iType)
    {
        System.out.println("Set value with iType = " + iType);
        if (frame >= 0)
        {
            if (frame >= values.length)
            {
                E[] valuesCopy = (E[]) Array.newInstance(values[0].getClass(), values.length);
                for (int i = 0; i < values.length; i++)
                    valuesCopy[i] = values[i];
                values = (E[]) Array.newInstance(values[0].getClass(), frame + 100);
                for (int i = 0; i < valuesCopy.length; i++)
                    values[i] = valuesCopy[i];
                InterpolationType[] iTypesCopy = (InterpolationType[]) Array.newInstance(interpolationTypes[0].getClass(), interpolationTypes.length);
                for (int i = 0; i < interpolationTypes.length; i++)
                    iTypesCopy[i] = interpolationTypes[i];
                interpolationTypes = (InterpolationType[]) Array.newInstance(interpolationTypes[0].getClass(), frame + 100);
                System.out.println("Length of interpolationTypes " + interpolationTypes.length);
                System.out.println("Length of iTypesCopy " + iTypesCopy.length);
                for (int i = 0; i < iTypesCopy.length&&i<interpolationTypes.length; i++)
                    interpolationTypes[i] = iTypesCopy[i];
            }
            values[frame] = value;
            if (iType != InterpolationType.Keep)
                interpolationTypes[frame] = iType;
        } else
            System.out.println("Trying to set value at negative index! (kfp)");
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
        for (int i = 1; i < values.length - 1; i++)
            if (!indices.contains(i)) // should be optimized... but probably won't, since it's not realtime
                values[i] = null;
    }

    public InterpolationType getInterpolationType(int frame)
    {

        InterpolationType iT = null;
        while (iT == null)
        {
            //  System.out.println("Searching for current interpolation type. Found " + interpolationTypes[frame] + " at " + frame + " in " + this);
            iT = interpolationTypes[frame--];
        }
        return iT;
    }
}
