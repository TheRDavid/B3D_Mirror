package b3dElements.animations.animationTypes;

import b3dElements.B3D_Element;
import b3dElements.animations.B3D_Animation;
import b3dElements.animations.B3D_AnimationCommand;
import com.jme3.math.Vector3f;
import dialogs.ObserverDialog;
import java.util.UUID;
import monkeyStuff.CustomParticleEmitter;
import other.Wizard;

/**
 *
 * @author David
 */
public class B3D_Anim_FireParticles extends B3D_AnimationCommand
{

    /**
     * Only use when set() is called afterwards
     */
    public B3D_Anim_FireParticles()
    {
    }

    public B3D_Anim_FireParticles(UUID obj, Object val, float start)
    {
        super(obj, val, 1, start);
    }

    @Override
    protected void step(float tpf, Object actualObject)
    {
        if (!(actualObject instanceof CustomParticleEmitter))
        {
            ObserverDialog.getObserverDialog().printMessage("The Object must be a Particle Emitter!");
            return;
        }
        if (!(Boolean) value)
            ((CustomParticleEmitter) actualObject).setFiring(false);
        else
            ((CustomParticleEmitter) actualObject).setFiring(true);
    }

    @Override
    public B3D_Anim_FireParticles clone()
    {
        return new B3D_Anim_FireParticles(objectID, (Vector3f) value, startTime);
    }

    @Override
    protected void stepFinal(Object actualObject)
    {
        //Nothing to do here
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
        B3D_Anim_FireParticles a = (B3D_Anim_FireParticles) e;
        Wizard.copyValues(a, this, getClass());
    }
}
