package b3dElements.spatials.geometries.particleEmitter;

import b3dElements.B3D_Element;
import com.jme3.math.Vector3f;
import java.io.Serializable;
import other.Wizard;

public class B3D_SphereStartShape extends B3D_StartShape implements Serializable
{

    private Vector3f center;
    private float radius;

    /**
     * Only use when set() is called afterwards
     */
    public B3D_SphereStartShape()
    {
    }

    public B3D_SphereStartShape(Vector3f cent, float rad)
    {
        center = cent;
        radius = rad;
    }

    public Vector3f getCenter()
    {
        return center;
    }

    public void setCenter(Vector3f center)
    {
        this.center = center;
    }

    public float getRadius()
    {
        return radius;
    }

    public void setRadius(float radius)
    {
        this.radius = radius;
    }

    @Override
    public void set(B3D_Element e)
    {
        super.set(e);
        B3D_SphereStartShape b = (B3D_SphereStartShape) e;
        Wizard.copyValues(b, this, getClass());
    }
}
