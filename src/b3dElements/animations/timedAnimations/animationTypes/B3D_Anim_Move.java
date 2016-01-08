package b3dElements.animations.timedAnimations.animationTypes;

import b3dElements.B3D_Element;
import b3dElements.animations.timedAnimations.B3D_AnimationCommand;
import com.jme3.light.Light;
import com.jme3.light.PointLight;
import com.jme3.light.SpotLight;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import dialogs.ObserverDialog;
import java.io.Serializable;
import java.util.UUID;
import other.Wizard;

/**
 *
 * @author David
 */
public class B3D_Anim_Move extends B3D_AnimationCommand implements Serializable, Cloneable
{

    /**
     * Only use when set() is called afterwards
     */
    public B3D_Anim_Move()
    {
    }

    public B3D_Anim_Move(UUID obj, Vector3f val, float t, float start)
    {
        super(obj, val, t, start);
    }

    @Override
    protected void step(float tpf, Object actualObj)
    {
        if (!(actualObj instanceof Spatial) && !(actualObj instanceof Light))
        {
            ObserverDialog.getObserverDialog().printMessage("The Object must either be a Light Source or a 3D-Object!");
            return;
        }
        if (actualObj instanceof Spatial)
            ((Spatial) actualObj).move(((Vector3f) value).clone().mult(tpf).divide(duration));
        else if (actualObj instanceof SpotLight)
            ((SpotLight) actualObj).setPosition(((SpotLight) actualObj).getPosition().add(((Vector3f) value).mult(tpf).divide(duration)));
        else if (actualObj instanceof PointLight)
            ((PointLight) actualObj).setPosition(((PointLight) actualObj).getPosition().add(((Vector3f) value).mult(tpf).divide(duration)));
    }

    @Override
    public B3D_Anim_Move clone()
    {
        return new B3D_Anim_Move(objectID, (Vector3f) value, duration, startTime);
    }

    @Override
    protected void stepFinal(Object actualObj)
    {
        if (!(actualObj instanceof Spatial) && !(actualObj instanceof Light))
        {
            ObserverDialog.getObserverDialog().printMessage("The Object must either be a Light Source or a 3D-Object!");
            return;
        }
        if (actualObj instanceof Spatial)
        {
            ((Spatial) actualObj).setLocalTranslation(((Vector3f) startValue).clone().add((Vector3f) value));
        } else if (actualObj instanceof SpotLight)
            ((SpotLight) actualObj).setPosition(((Vector3f) startValue).clone().add((Vector3f) value));
        else if (actualObj instanceof PointLight)
            ((PointLight) actualObj).setPosition(((Vector3f) startValue).clone().add((Vector3f) value));
    }

    @Override
    protected void saveStartValue(Object actualObject)
    {
        if (actualObject instanceof Spatial)
        {
            startValue = ((Spatial) actualObject).getLocalTranslation().clone();
        } else if (actualObject instanceof SpotLight)
            startValue = ((SpotLight) actualObject).getPosition().clone();
        else if (actualObject instanceof PointLight)
            startValue = ((PointLight) actualObject).getPosition().clone();
    }

    @Override
    public void set(B3D_Element e)
    {
        super.set(e);
        B3D_Anim_Move a = (B3D_Anim_Move) e;
        Wizard.copyValues(a, this, getClass());
    }
}
