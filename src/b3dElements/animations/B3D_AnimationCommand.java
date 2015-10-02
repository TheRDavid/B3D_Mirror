package b3dElements.animations;

import java.io.Serializable;
import java.util.UUID;

/**
 *
 * @author David
 */
public abstract class B3D_AnimationCommand implements Serializable, Cloneable
{

    protected UUID objectID;
    protected Object value, startValue;
    protected float duration, startTime, remaining;
    protected boolean playing = false, done = false, exact = false;

    public B3D_AnimationCommand(UUID obj, Object val, Object startVal, float dur, float start)
    {
        startValue = startVal;
        objectID = obj;
        value = val;
        duration = dur;
        remaining = duration;
        startTime = start;
    }

    public B3D_AnimationCommand(UUID obj, Object val, Object startVal, float dur, float start, boolean ex)
    {
        exact = ex;
        startValue = startVal;
        objectID = obj;
        value = val;
        duration = dur;
        remaining = duration;
        startTime = start;
    }

    public boolean isPlaying()
    {
        return playing;
    }

    public float getStartTime()
    {
        return startTime;
    }

    public boolean isDone()
    {
        return done;
    }

    public void setDone(boolean done)
    {
        this.done = done;
    }

    public void play()
    {
        playing = true;
    }

    public float getDuration()
    {
        return duration;
    }

    public void setDuration(float duration)
    {
        this.duration = duration;
    }

    public void setObjectID(UUID objectID)
    {
        this.objectID = objectID;
    }

    public void setRemaining(float remaining)
    {
        this.remaining = remaining;
    }

    public Object getValue()
    {
        return value;
    }

    public final void update(float tpf, Object actualObject)
    {
        if (remaining > 0)
        {
            step(tpf, actualObject);
            remaining -= tpf;
        } else
        {
            if (exact)
                stepFinal(actualObject);
            remaining = duration;
            playing = false;
            done = true;
        }
    }

    protected abstract void step(float tpf, Object actualObject);

    protected abstract void stepFinal(Object actualObject);

    @Override
    protected Object clone() throws CloneNotSupportedException
    {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }
}
