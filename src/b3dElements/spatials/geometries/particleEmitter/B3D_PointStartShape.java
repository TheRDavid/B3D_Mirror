package b3dElements.spatials.geometries.particleEmitter;

import b3dElements.B3D_Element;
import com.jme3.math.Vector3f;
import java.io.Serializable;
import other.Wizard;

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

    /**
     * Only use when set() is called afterwards
     */
    public B3D_PointStartShape()
    {
    }

    public B3D_PointStartShape(Vector3f location)
    {
        point = location;
    }

    @Override
    public void set(B3D_Element e)
    {
        super.set(e);
        B3D_PointStartShape b = (B3D_PointStartShape) e;
        Wizard.copyValues(b, this, getClass());
    }
}
