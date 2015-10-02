package b3dElements.filters;

import com.jme3.math.ColorRGBA;

public class B3D_ColorScale extends B3D_Filter
{

    private float colorDensity;
    private boolean multiply, overlay;
    private ColorRGBA color;

    public B3D_ColorScale(String _name, int _index, float _colorDensity, boolean _multiply, boolean _overlay, ColorRGBA _color)
    {
        setName(_name);
        setFilterIndex(_index);
        color = _color;
        multiply = _multiply;
        overlay = _overlay;
        colorDensity = _colorDensity;
    }

    public float getColorDensity()
    {
        return colorDensity;
    }

    public void setColorDensity(float colorDensity)
    {
        this.colorDensity = colorDensity;
    }

    public boolean isMultiply()
    {
        return multiply;
    }

    public void setMultiply(boolean multiply)
    {
        this.multiply = multiply;
    }

    public boolean isOverlay()
    {
        return overlay;
    }

    public void setOverlay(boolean overlay)
    {
        this.overlay = overlay;
    }

    public ColorRGBA getColor()
    {
        return color;
    }

    public void setColor(ColorRGBA color)
    {
        this.color = color;
    }
}
