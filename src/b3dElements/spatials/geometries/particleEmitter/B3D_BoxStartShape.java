package b3dElements.spatials.geometries.particleEmitter;

import com.jme3.math.Vector3f;
import java.io.Serializable;

public class B3D_BoxStartShape extends B3D_StartShape implements Serializable
{

    private Vector3f mini, maxi;

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
}
