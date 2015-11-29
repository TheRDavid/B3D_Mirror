package b3dElements.spatials.geometries;

import b3dElements.B3D_Element;
import b3dElements.other.B3D_Material;
import java.io.Serializable;
import other.Wizard;

public class B3D_Torus extends B3D_Geometry implements Serializable
{

    private int circleSamples, radialSamples;
    private float innerRadius, outerRadius;

    /**
     * Only use when set() is called afterwards
     */
    public B3D_Torus()
    {
    }

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

    @Override
    public void set(B3D_Element e)
    {
        super.set(e);
        B3D_Torus t = (B3D_Torus) e;
        Wizard.copyValues(t, this, getClass());
    }
}
