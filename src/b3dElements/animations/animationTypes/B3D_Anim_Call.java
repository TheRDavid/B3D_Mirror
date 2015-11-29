package b3dElements.animations.animationTypes;

import b3dElements.B3D_Element;
import b3dElements.animations.B3D_AnimationCommand;
import java.util.UUID;
import other.Wizard;

/**
 *
 * @author David
 */
public class B3D_Anim_Call extends B3D_AnimationCommand
{

    private UUID calledObject;
    private String animationName;

    /**
     * Only use when set() is called afterwards
     */
    public B3D_Anim_Call()
    {
    }

    public B3D_Anim_Call(UUID obj, UUID calledObj, String animName, float start)
    {
        super(obj, calledObj.toString() + animName, 1, start);
        animationName = animName;
        calledObject = calledObj;
        duration = 1;
        remaining = 1;
    }

    @Override
    protected void step(float tpf, Object actualObject)
    {
        if (remaining == 1)
        {
            remaining = 0;
            b3dElements.B3D_Element element = Wizard.getObjects().getB3D_Element(calledObject);
            System.out.println("Calling " + calledObject.toString() + " to " + animationName);
            element.call(animationName);
        }
    }

    public UUID getCalledObject()
    {
        return calledObject;
    }

    public String getAnimationName()
    {
        return animationName;
    }

    @Override
    protected void stepFinal(Object actualObject)
    {
        //Nothing to do here...
    }

    @Override
    protected void saveStartValue(Object actualObject)
    {
        // Nothing to do here
    }

    @Override
    public void set(B3D_Element e)
    {
        super.set(e);
        B3D_Anim_Call a = (B3D_Anim_Call) e;
        Wizard.copyValues(a, this, getClass());
    }
}
