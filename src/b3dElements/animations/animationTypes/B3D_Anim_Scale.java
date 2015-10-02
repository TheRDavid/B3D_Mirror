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
import other.Wizard;

/**
 *
 * @author David
 */
public class B3D_Anim_Scale extends B3D_AnimationCommand implements Serializable, Cloneable
{

    private Vector3f originalVector;

    public B3D_Anim_Scale(UUID obj, Vector3f val, Vector3f startVal, float t, float start, boolean exact)
    {
        super(obj, val, startVal, t, start);
        originalVector = ((Spatial) Wizard.getObjects().getOriginalObject(Wizard.getObjectReferences().getID(obj))).getLocalScale().clone();
    }

    public B3D_Anim_Scale(UUID obj, float val, float startVal, float t, float start, boolean exact)
    {
        super(obj, val, startVal, t, start);
        Object object = (Wizard.getObjects().getOriginalObject(Wizard.getObjectReferences().getID(obj)));
        if (object instanceof Spatial)
            originalVector = ((Spatial) Wizard.getObjects().getOriginalObject(Wizard.getObjectReferences().getID(obj))).getLocalScale().clone();
        else if (object instanceof PointLight)
        {
            PointLight pLight = (PointLight) object;
            originalVector = new Vector3f(pLight.getRadius(), pLight.getRadius(), pLight.getRadius());
        } else
        {
            SpotLight sLight = (SpotLight) object;
            originalVector = new Vector3f(sLight.getSpotRange(), sLight.getSpotRange(), sLight.getSpotRange());
        }
    }

    @Override
    protected void step(float tpf, Object actualObject)
    {
        if (!(actualObject instanceof Spatial) && !(actualObject instanceof Light))
        {
            ObserverDialog.getObserverDialog().printMessage("The Object must either be a Light Source or a 3D-Object!");
            return;
        }
        if (actualObject instanceof Spatial)
            if (value instanceof Vector3f)
            {
                Vector3f currentScaling = new Vector3f((Vector3f) value);
                currentScaling.multLocal(tpf / duration);
                System.out.println("Current Scaling: " + currentScaling);
                ((Spatial) actualObject).setLocalScale(((Spatial) actualObject).getLocalScale().add(currentScaling.x, currentScaling.y, currentScaling.z));
            } else
                ((Spatial) actualObject).setLocalScale(((Spatial) actualObject).getLocalScale().add(new Vector3f(1, 1, 1).mult((Float) value * tpf / duration)));
        else if (actualObject instanceof PointLight)
        {
            PointLight pl = (PointLight) actualObject;
            float radius = pl.getRadius() + originalVector.getX() * (Float) value * tpf / duration;
            if (radius < 0)
                radius = 0;
            pl.setRadius(radius);
        } else if (actualObject instanceof SpotLight)
        {
            SpotLight sl = (SpotLight) actualObject;
            float range = sl.getSpotRange() + originalVector.getX() * (Float) value * tpf / duration;
            if (range < 0)
                range = 0;
            sl.setSpotRange(range);
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException
    {
        if (value instanceof Vector3f)
            return new B3D_Anim_Scale(objectID, (Vector3f) value, (Vector3f) startValue, duration, startTime, exact);
        return new B3D_Anim_Scale(objectID, (Float) value, (Float) startValue, duration, startTime, exact);
    }

    @Override
    protected void stepFinal(Object actualObject)
    {
        if (!(actualObject instanceof Spatial) && !(actualObject instanceof Light))
        {
            ObserverDialog.getObserverDialog().printMessage("The Object must either be a Light Source or a 3D-Object!");
            return;
        }
        if (actualObject instanceof Spatial)
            if (value instanceof Vector3f)
            {
                ((Spatial) actualObject).setLocalScale(((Vector3f) startValue).add((Vector3f) value));
            } else
                ((Spatial) actualObject).setLocalScale(((Float) startValue) + ((Float) value));
        else if (actualObject instanceof PointLight)
        {
            PointLight pl = (PointLight) actualObject;
            float radius = (Float) startValue + (Float) value;
            if (radius < 0)
                radius = 0;
            pl.setRadius(radius);
        } else if (actualObject instanceof SpotLight)
        {
            SpotLight sl = (SpotLight) actualObject;
            float range = (Float) startValue + (Float) value;
            if (range < 0)
                range = 0;
            sl.setSpotRange(range);
        }
    }
}
