package b3dElements.animations.timedAnimations.animationTypes;

import b3dElements.B3D_Element;
import b3dElements.animations.timedAnimations.B3D_AnimationCommand;
import com.jme3.cinematic.events.MotionEvent;
import dialogs.ObserverDialog;
import java.util.UUID;
import other.Wizard;

/**
 *
 * @author David
 */
public class B3D_Anim_PlayMotion extends B3D_AnimationCommand
{

    /**
     * Only use when set() is called afterwards
     */
    public B3D_Anim_PlayMotion()
    {
    }

    public B3D_Anim_PlayMotion(UUID obj, Object val, float start)
    {
        super(obj, val, 1, start);
    }

    @Override
    protected void step(float tpf, Object actualObject)
    {
        if (!(actualObject instanceof MotionEvent))
        {
            ObserverDialog.getObserverDialog().printMessage("The Object must be a Motion Event!");
            return;
        }
        MotionEvent mEvent = (MotionEvent) actualObject;
        if (value.equals("play"))
            mEvent.play();
        else if (value.equals("pause"))
            mEvent.pause();
        else if (value.equals("stop"))
            mEvent.stop();
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
        B3D_Anim_PlayMotion a = (B3D_Anim_PlayMotion) e;
        Wizard.copyValues(a, this, getClass());
    }
}
