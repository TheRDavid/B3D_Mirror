package b3dElements.animations.animationTypes;

import b3dElements.animations.B3D_AnimationCommand;
import com.jme3.math.Vector3f;
import dialogs.ObserverDialog;
import java.util.UUID;
import monkeyStuff.CustomParticleEmitter;

/**
 *
 * @author David
 */
public class B3D_Anim_FireParticles extends B3D_AnimationCommand
{

    public B3D_Anim_FireParticles(UUID obj, Object val, float start)
    {
        super(obj, val,null, 1, start);
    }

    @Override
    protected void step(float tpf, Object actualObject)
    {
        if (!(actualObject instanceof CustomParticleEmitter))
        {
            ObserverDialog.getObserverDialog().printMessage("The Object must be a Particle Emitter!");
            return;
        }
        System.out.println("Firing: " + value);
        if (!(Boolean) value)
            ((CustomParticleEmitter) actualObject).setFiring(false);
        else
            ((CustomParticleEmitter) actualObject).setFiring(true);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException
    {
        return new B3D_Anim_FireParticles(objectID, (Vector3f) value, startTime);
    }

    @Override
    protected void stepFinal(Object actualObject)
    {
        //Nothing to do here
    }
}
