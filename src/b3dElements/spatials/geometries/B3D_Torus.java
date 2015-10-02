package b3dElements.spatials.geometries;

import b3dElements.other.B3D_Material;
import java.io.Serializable;

public class B3D_Torus extends B3D_Geometry implements Serializable
{

    private int circleSamples, radialSamples;
    private float innerRadius, outerRadius;

    public B3D_Torus(String _name, B3D_Material mat, int cSamples, int rSamples, float iRadius, float oRadius, String _shadowMode)
    {
        setName(_name);
        setMaterial(mat);
        circleSamples = cSamples;
        radialSamples = rSamples;
        innerRadius = iRadius;
        outerRadius = oRadius;
        setShadowMode(_shadowMode);
    }

    public int getCircleSamples()
    {
        return circleSamples;
    }

    public void setCircleSamples(int circleSamples)
    {
        this.circleSamples = circleSamples;
    }

    public int getRadialSamples()
    {
        return radialSamples;
    }

    public void setRadialSamples(int radialSamples)
    {
        this.radialSamples = radialSamples;
    }

    public float getInnerRadius()
    {
        return innerRadius;
    }

    public void setInnerRadius(float innerRadius)
    {
        this.innerRadius = innerRadius;
    }

    public float getOuterRadius()
    {
        return outerRadius;
    }

    public void setOuterRadius(float outerRadius)
    {
        this.outerRadius = outerRadius;
    }
}
