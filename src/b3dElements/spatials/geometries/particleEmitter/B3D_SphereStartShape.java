package b3dElements.spatials.geometries.particleEmitter;

import com.jme3.math.Vector3f;
import java.io.Serializable;

public class B3D_SphereStartShape extends B3D_StartShape implements Serializable
{

    private Vector3f center;
    private float radius;

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
}
