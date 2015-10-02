package b3dElements.animations.animationTypes;

import b3dElements.animations.B3D_AnimationCommand;
import com.jme3.light.Light;
import com.jme3.light.PointLight;
import com.jme3.light.SpotLight;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import dialogs.ObserverDialog;
import java.io.Serializable;
import java.util.UUID;

/**
 *
 * @author David
 */
public class B3D_Anim_Move extends B3D_AnimationCommand implements Serializable, Cloneable
{

    public B3D_Anim_Move(UUID obj, Vector3f val, Vector3f startVal, float t, float start, boolean exact)
    {
        super(obj, val, startVal, t, start, exact);
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
            ((Spatial) actualObj).move(((Vector3f) value).mult(tpf).divide(duration));
        else if (actualObj instanceof SpotLight)
            ((SpotLight) actualObj).setPosition(((SpotLight) actualObj).getPosition().add(((Vector3f) value).mult(tpf).divide(duration)));
        else if (actualObj instanceof PointLight)
            ((PointLight) actualObj).setPosition(((PointLight) actualObj).getPosition().add(((Vector3f) value).mult(tpf).divide(duration)));
    }

    @Override
    protected Object clone() throws CloneNotSupportedException
    {
        return new B3D_Anim_Move(objectID, (Vector3f) value, (Vector3f) startValue, duration, startTime, exact);
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
            ((Spatial) actualObj).setLocalTranslation(((Vector3f) startValue).add((Vector3f) value));
        else if (actualObj instanceof SpotLight)
            ((SpotLight) actualObj).setPosition(((Vector3f) startValue).add((Vector3f) value));
        else if (actualObj instanceof PointLight)
            ((PointLight) actualObj).setPosition(((Vector3f) startValue).add((Vector3f) value));
    }
}
