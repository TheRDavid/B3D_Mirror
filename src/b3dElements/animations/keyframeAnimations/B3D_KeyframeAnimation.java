package b3dElements.animations.keyframeAnimations;

import b3dElements.B3D_Element;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public class B3D_KeyframeAnimation extends B3D_Element implements Serializable
{

    private ArrayList<B3D_KeyframeUpdater> updaters = new ArrayList<>();
    private String name;

    public B3D_KeyframeAnimation(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public ArrayList<B3D_KeyframeUpdater> getUpdaters()
    {
        return updaters;
    }
}
