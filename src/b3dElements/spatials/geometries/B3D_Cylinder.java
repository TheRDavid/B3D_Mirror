package b3dElements.spatials.geometries;

import b3dElements.other.B3D_Material;
import java.io.Serializable;

public class B3D_Cylinder extends B3D_Geometry implements Serializable
{

    private int axisSamples, radialSamples;
    private float height, radius, radius2;
    private boolean isClosed, isInverted;

    public B3D_Cylinder(
            String _name,
            B3D_Material _material,
            int _aSamples,
            int _rSamples,
            float _radius,
            float _radius2,
            float _height,
            boolean closed,
            boolean inverted,
            String _shadowMode)
    {
        setName(_name);
        setMaterial(_material);
        axisSamples = _aSamples;
        radius = _radius;
        radius2 = _radius2;
        height = _height;
        radialSamples = _rSamples;
        isClosed = closed;
        isInverted = inverted;
        setShadowMode(_shadowMode);
    }

    public int getAxisSamples()
    {
        return axisSamples;
    }

    public void setAxisSamples(int axisSamples)
    {
        this.axisSamples = axisSamples;
    }

    public int getRadialSamples()
    {
        return radialSamples;
    }

    public void setRadialSamples(int radialSamples)
    {
        this.radialSamples = radialSamples;
    }

    public float getHeight()
    {
        return height;
    }

    public void setHeight(float height)
    {
        this.height = height;
    }

    public float getRadius()
    {
        return radius;
    }

    public void setRadius(float radius)
    {
        this.radius = radius;
    }

    public boolean isIsClosed()
    {
        return isClosed;
    }

    public void setIsClosed(boolean isClosed)
    {
        this.isClosed = isClosed;
    }

    public boolean isIsInverted()
    {
        return isInverted;
    }

    public void setIsInverted(boolean isInverted)
    {
        this.isInverted = isInverted;
    }

    public float getRadius2()
    {
        return radius2;
    }

    public void setRadius2(float radius2)
    {
        this.radius2 = radius2;
    }
}
