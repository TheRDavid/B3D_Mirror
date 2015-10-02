package b3dElements.animations.animationTypes;

import b3dElements.animations.B3D_AnimationCommand;
import com.jme3.light.DirectionalLight;
import com.jme3.light.Light;
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
public class B3D_Anim_Rotate extends B3D_AnimationCommand implements Serializable, Cloneable
{

    private Vector3f anglesDone = new Vector3f();

    public B3D_Anim_Rotate(UUID obj, Vector3f val, float t, float start, boolean exact)
    {
        super(obj, val, null, t, start, exact);
    }

    @Override
    protected void step(float tpf, Object actualObject)
    {
        if (!(actualObject instanceof Spatial) && !(actualObject instanceof Light))
        {
            ObserverDialog.getObserverDialog().printMessage("The Object must either be a Light Source or a 3D-Object!");
            return;
        }
        Vector3f angles = ((Vector3f) value).mult(tpf).divide(duration);
        anglesDone.add(angles);
        if (actualObject instanceof Spatial)
            ((Spatial) actualObject).rotate(angles.getX(), angles.getY(), angles.getZ());
        else if (actualObject instanceof SpotLight)
            ((SpotLight) actualObject).setDirection(((SpotLight) actualObject).getDirection().add(angles));
        else if (actualObject instanceof DirectionalLight)
            ((DirectionalLight) actualObject).setDirection(((DirectionalLight) actualObject).getDirection().add(angles));
    }

    @Override
    protected Object clone() throws CloneNotSupportedException
    {
        return new B3D_Anim_Rotate(objectID, (Vector3f) value, duration, startTime, exact);
    }

    @Override
    protected void stepFinal(Object actualObject)
    {
        if (!(actualObject instanceof Spatial) && !(actualObject instanceof Light))
        {
            ObserverDialog.getObserverDialog().printMessage("The Object must either be a Light Source or a 3D-Object!");
            return;
        }
        Vector3f fail = anglesDone.subtract((Vector3f) value).add((Vector3f) startValue);
        if (actualObject instanceof Spatial)
            ((Spatial) actualObject).rotate(fail.getX(), fail.getY(), fail.getZ());
        else if (actualObject instanceof SpotLight)
            ((SpotLight) actualObject).setDirection(((SpotLight) actualObject).getDirection().add(fail));
        else if (actualObject instanceof DirectionalLight)
            ((DirectionalLight) actualObject).setDirection(((DirectionalLight) actualObject).getDirection().add(fail));
    }
}