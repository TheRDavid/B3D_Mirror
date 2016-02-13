package monkeyStuff.keyframeAnimation;

import java.util.ArrayList;

/**
 *
 * @author David
 */
public class LiveKeyframeAnimation
{

    private boolean playing = false;
    private int currentFrame = 0;
    private int maxFrames = 0;
    private ArrayList<LiveKeyframeUpdater> updaters = new ArrayList<>();
    boolean debug = false;
    private String name;

    public LiveKeyframeAnimation(String name)
    {
        this.name = name;
    }

    public int getMaxFrames()
    {
        return maxFrames;
    }

    public void addUpdater(LiveKeyframeUpdater kfu)
    {
        updaters.add(kfu);
        calcMaxFrames();
    }

    public void calcMaxFrames()
    {
        maxFrames = 0;
        for (LiveKeyframeUpdater u : updaters)
            maxFrames = u.calcMaxFrames() > maxFrames ? u.calcMaxFrames() : maxFrames;
    }

    public void update()
    {
        if (playing)
        {
            if (currentFrame < maxFrames)
            {
                for (LiveKeyframeUpdater u : updaters)
                    u.update(currentFrame);
                currentFrame++;
            } else
            {
                playing = false;
                for (LiveKeyframeUpdater u : updaters)
                {
                    currentFrame = 0;
                    u.reset();
                }
            }
        }
    }

    public void play(boolean fromBeginning)
    {
        if (fromBeginning)
        {
            calcValues();
            playing = true;
            /* System.out.println("Playing with values:");
             for (LiveKeyframeUpdater kfu : updaters)
             for (LiveKeyframeProperty kfp : (ArrayList<LiveKeyframeProperty>) kfu.getKeyframeProperties())
             if (kfp.type.equals(AnimationType.Rotation))
             for (Serializable s : kfp.getValues())
             System.out.println(s);*/
        } else
            playing = true;
    }

    public void calcValues()
    {
        for (LiveKeyframeUpdater updater : updaters)
            updater.calcValues();
        calcMaxFrames();
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
            for (LiveKeyframeUpdater u : updaters)
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

    public boolean isPlaying()
    {
        return playing;
    }

    public int getCurrentFrame()
    {
        return currentFrame;
    }

    public void goTo(int currentFrame)
    {
        for (LiveKeyframeUpdater u : updaters)
            u.goTo(currentFrame);
    }

    public ArrayList<LiveKeyframeUpdater> getUpdaters()
    {
        return updaters;
    }

    public void uncalcValues()
    {
        for (LiveKeyframeUpdater lku : updaters)
            lku.uncalcValues();
    }
}
