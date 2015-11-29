package b3dElements.spatials.geometries.particleEmitter;

import b3dElements.B3D_Element;
import com.jme3.math.Vector3f;
import java.io.Serializable;
import other.Wizard;

public class B3D_BoxStartShape extends B3D_StartShape implements Serializable
{

    private Vector3f mini, maxi;

    /**
     * Only use when set() is called afterwards
     */
    public B3D_BoxStartShape()
    {
    }

    public B3D_BoxStartShape(Vector3f min, Vector3f max)
    {
        mini = min;
        maxi = max;
    }

    public Vector3f getMini()
    {
        return mini;
    }

    public void setMini(Vector3f mini)
    {
        this.mini = mini;
    }

    public Vector3f getMaxi()
    {
        return maxi;
    }

    public void setMaxi(Vector3f maxi)
    {
        this.maxi = maxi;
    }

    @Override
    public void set(B3D_Element e)
    {
        super.set(e);
        B3D_BoxStartShape b = (B3D_BoxStartShape) e;
        Wizard.copyValues(b, this, getClass());
    }
}
