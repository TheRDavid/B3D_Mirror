package b3dElements.spatials.geometries.particleEmitter;

import com.jme3.math.Vector3f;
import java.io.Serializable;

public class B3D_PointStartShape extends B3D_StartShape implements Serializable
{

    private Vector3f point;

    public Vector3f getPoint()
    {
        return point;
    }

    public void setPoint(Vector3f point)
    {
        this.point = point;
    }

    public B3D_PointStartShape(Vector3f location)
    {
        point = location;
    }
}
