package b3dElements.animations;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
public class B3D_Animation implements Serializable, Cloneable
{

    private ArrayList<B3D_AnimationCommand> commands = new ArrayList<B3D_AnimationCommand>();
    private String name;
    private UUID objectID;
    private boolean playing = false, allDone = false;
    private float time = 0;
    public static final int LOOP = Integer.MAX_VALUE, DEFAULT = 1;
    private int iterations = DEFAULT, iterationsDone = 0;

    public B3D_Animation(UUID obj, String name)
    {
        this.name = name;
        objectID = obj;
    }

    public void play()
    {
        playing = true;
    }

    public void stop()
    {
        playing = false;
        allDone = false;
        iterationsDone = 0;
        time = 0;

        for (B3D_AnimationCommand ac : commands)
        {
            ac.setDone(false);
            ac.setRemaining(ac.getDuration());
            ac.setFirstStep(true);
            ac.setPlaying(false);
        }
    }

    public void update(float tpf, Object actualObject)
    {
        if (isPlaying())
        {
            time += tpf;
            allDone = true;
            for (B3D_AnimationCommand ac : commands)
            {
                if (!ac.isDone())
                {
                    allDone = false;
                    if (ac.playing)
                    {
                        ac.update(tpf, actualObject);
                    } else if (ac.getStartTime() <= time)
                    {
                        ac.play();
                        ac.update(tpf, actualObject);
                        ac.setDone(false);
                    }
                }
            }
            if (allDone)
            {
                time = 0;
                if (++iterationsDone == iterations)
                {
                    playing = false;
                    allDone = false;
                    iterationsDone = 0;
                }
                for (B3D_AnimationCommand ac : commands)
                {
                    ac.setDone(false);
                    ac.setRemaining(ac.getDuration());
                }
            }
        }
    }

    public ArrayList<B3D_AnimationCommand> getCommands()
    {
        return commands;
    }

    public void setCommands(ArrayList<B3D_AnimationCommand> commands)
    {
        this.commands = commands;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public UUID getObject()
    {
        return objectID;
    }

    public void setObject(UUID object)
    {
        this.objectID = object;
        for (B3D_AnimationCommand ac : commands)
            ac.setObjectID(object);
    }

    public boolean isPlaying()
    {
        return playing;
    }

    public float getTime()
    {
        return time;
    }

    public float getDuration()
    {
        float duration = 0;
        for (B3D_AnimationCommand command : commands)
            if (duration < command.getStartTime() + command.getDuration())
                duration = command.getStartTime() + command.getDuration();
        return duration;
    }

    public B3D_Animation copy()
    {
        B3D_Animation animation = new B3D_Animation(objectID, name);
        animation.setIterations(iterations);
        for (B3D_AnimationCommand command : commands)
        {
            try
            {
                animation.getCommands().add((B3D_AnimationCommand) command.clone());
            } catch (CloneNotSupportedException ex)
            {
                ex.printStackTrace();
            }
        }
        return animation;
    }

    public int getIterations()
    {
        return iterations;
    }

    public void setIterations(int iterations)
    {
        this.iterations = iterations;
    }
}
