package b3dElements.animations.timedAnimations;

import b3dElements.B3D_Element;
import java.io.Serializable;
import java.util.UUID;
import other.Wizard;

/**
 *
 * @author David
 */
public abstract class B3D_AnimationCommand extends B3D_Element implements Serializable, Cloneable
{

    protected UUID objectID;
    protected Object value, startValue;
    protected float duration, startTime, remaining;
    protected boolean playing = false, done = false, firstStep = true;

    /**
     * Only use when set() is called afterwards
     */
    public B3D_AnimationCommand()
    {
    }

    public B3D_AnimationCommand(UUID obj, Object val, float dur, float start)
    {
        objectID = obj;
        value = val;
        duration = dur;
        remaining = duration;
        startTime = start;
    }

    public B3D_AnimationCommand(UUID obj, Object val, float dur, float start, boolean ex)
    {
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
        if (firstStep)
        {
            firstStep = false;
            saveStartValue(actualObject);
        }
        if (remaining > 0)
        {
            step(tpf, actualObject);
            remaining -= tpf;
            if (remaining <= tpf * 1.1f)
            {
                stepFinal(actualObject);
                remaining = duration;
                playing = false;
                firstStep = true;
                done = true;
            }
        } else
        {
            remaining = duration;
            playing = false;
            firstStep = true;
            done = true;
        }
    }

    protected abstract void step(float tpf, Object actualObject);

    protected abstract void stepFinal(Object actualObject);

    protected abstract void saveStartValue(Object actualObject);

    @Override
    public B3D_AnimationCommand clone()
    {
        return (B3D_AnimationCommand) super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

    public void setPlaying(boolean playing)
    {
        this.playing = playing;
    }

    public void setFirstStep(boolean firstStep)
    {
        this.firstStep = firstStep;
    }

    @Override
    public void set(B3D_Element e)
    {
        super.set(e);
        B3D_AnimationCommand a = (B3D_AnimationCommand) e;
        Wizard.copyValues(a, this, getClass());
    }
}
