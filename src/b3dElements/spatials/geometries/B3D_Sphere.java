package b3dElements.spatials.geometries;

import b3dElements.B3D_Element;
import b3dElements.other.B3D_Material;
import java.io.Serializable;
import other.Wizard;

public class B3D_Sphere extends B3D_Geometry implements Serializable
{

    private int radialSamples, zSamples;
    private float radius;

    /**
     * Only use when set() is called afterwards
     */
    public B3D_Sphere()
    {
    }

    public B3D_Sphere(String _name, B3D_Material mat, int r_Samples, int z_Samples, float rad, String _shadowMode)
    {
        setName(_name);
        setMaterial(mat);
        radius = rad;
        radialSamples = r_Samples;
        zSamples = z_Samples;
        setShadowMode(_shadowMode);
    }

    public int getRadialSamples()
    {
        return radialSamples;
    }

    public void setRadialSamples(int radialSamples)
    {
        this.radialSamples = radialSamples;
    }

    public int getzSamples()
    {
        return zSamples;
    }

    public void setzSamples(int zSamples)
    {
        this.zSamples = zSamples;
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
        B3D_Sphere t = (B3D_Sphere) e;
        Wizard.copyValues(t, this, getClass());
    }
}
