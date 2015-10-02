package b3dElements.filters;

import com.jme3.math.ColorRGBA;
import java.io.Serializable;

public class B3D_Fog extends B3D_Filter implements Serializable
{

    private ColorRGBA color;
    private float density, distance;

    public B3D_Fog(String _name, int _index, ColorRGBA _color, float _density, float _distance)
    {
        setName(_name);
        setFilterIndex(_index);
        color = _color;
        density = _density;
        distance = _distance;
    }

    public ColorRGBA getColor()
    {
        return color;
    }

    public void setColor(ColorRGBA color)
    {
        this.color = color;
    }

    public float getDensity()
    {
        return density;
    }

    public void setDensity(float density)
    {
        this.density = density;
    }

    public float getDistance()
    {
        return distance;
    }

    public void setDistance(float distance)
    {
        this.distance = distance;
    }
}
