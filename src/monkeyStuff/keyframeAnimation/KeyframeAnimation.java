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

    public void addUpdater(KeyframeUpdater kfu)
    {
        updaters.add(kfu);
        calcMaxFrames();
    }

    private void calcMaxFrames()
    {
        maxFrames = 0;
        for (KeyframeUpdater u : updaters)
            maxFrames = u.calcMaxFrames() > maxFrames ? u.calcMaxFrames() : maxFrames;
    }

    public void update()
    {
        if (playing)
            if (++currentFrame <= maxFrames)
                for (KeyframeUpdater u : updaters)
                    u.update(currentFrame);
            else
                playing = false;
    }

    private void play()
    {
        playing = true;
    }

    private void pause()
    {
        playing = false;
    }

    private void stop()
    {
        playing = false;
        currentFrame = 0;
    }

    private void jump(int frame)
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
}
