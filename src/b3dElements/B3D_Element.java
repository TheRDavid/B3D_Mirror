package b3dElements;

import b3dElements.animations.B3D_Animation;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.Callable;
import other.LiveAnimation;
import other.Wizard;

public class B3D_Element implements Serializable, Cloneable
{

    private UUID uuid = UUID.randomUUID();
    private ArrayList<B3D_Animation> animations = new ArrayList<B3D_Animation>();
    private String name = "Element";

    public void call(String animation)
    {
        for (final B3D_Animation anim : animations)
            if (anim.getName().equals(animation))
            {
                Wizard.getApp().enqueue(new Callable<Void>()
                {
                    @Override
                    public Void call() throws Exception
                    {
                        Wizard.getActiveAnimations().add(new LiveAnimation(anim));
                        anim.play();
                        return null;
                    }
                });
                break;
            }
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public ArrayList<B3D_Animation> getAnimations()
    {
        return animations;
    }

    public void setAnimations(ArrayList<B3D_Animation> animations)
    {
        this.animations = animations;
    }

    public UUID getUUID()
    {
        return uuid;
    }

    /**
     * Be careful with that one
     *
     * @param uuid
     */
    public void setUuid(UUID _uuid)
    {
        this.uuid = _uuid;
    }

    @Override
    public B3D_Element clone()
    {
        try
        {
            return (B3D_Element) super.clone();
        } catch (CloneNotSupportedException e)
        {
            throw new InternalError();
        }
    }
}
