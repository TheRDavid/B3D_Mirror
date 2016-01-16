package monkeyStuff.keyframeAnimation;

import java.util.ArrayList;

/**
 *
 * @author David
 */
public class KeyframeAnimation
{

    private boolean playing = false;
    private int currentFrame = 0;
    private int maxFrames = 0;
    private ArrayList<KeyframeUpdater> updaters = new ArrayList<>();
    boolean debug = false;
    private String name;

    public KeyframeAnimation(String name)
    {
        this.name = name;
    }

    public int getMaxFrames()
    {
        return maxFrames;
    }

    public void addUpdater(KeyframeUpdater kfu)
    {
        System.out.println("Added Updater: " + kfu);
        updaters.add(kfu);
        calcMaxFrames();
    }

    public void calcMaxFrames()
    {
        maxFrames = 0;
        for (KeyframeUpdater u : updaters)
            maxFrames = u.calcMaxFrames() > maxFrames ? u.calcMaxFrames() : maxFrames;
        System.out.println("Calc Frames: " + maxFrames);
    }

    public void update()
    {
        if (playing)
        {
            System.out.println("Playing " + currentFrame + " -> " + maxFrames);
            if (++currentFrame <= maxFrames)
                for (KeyframeUpdater u : updaters)
                    u.update(currentFrame);
            else
            {
                playing = false;
                for (KeyframeUpdater u : updaters)
                {
                    currentFrame = 0;
                    u.reset();
                }
            }
        }
    }

    public void play()
    {
        System.out.println("Gonna play");
        for (KeyframeUpdater updater : updaters)
            updater.calcValues();
        calcMaxFrames();
        playing = true;
    }

    public void pause()
    {
        playing = false;
    }

    public void stop()
    {
        playing = false;
        currentFrame = 0;
    }

    public void jump(int frame)
    {
        currentFrame = frame;
        if (debug)
            for (KeyframeUpdater u : updaters)
                u.update(currentFrame);
    }

    public String getName()
    {
        return name;
    }

    public void removeAllUpdaters()
    {
        updaters.clear();
    }
}
