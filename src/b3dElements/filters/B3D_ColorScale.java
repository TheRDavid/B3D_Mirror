package b3dElements.filters;

import b3dElements.B3D_Element;
import com.jme3.math.ColorRGBA;
import other.Wizard;

public class B3D_ColorScale extends B3D_Filter
{

    private float colorDensity;
    private boolean multiply, overlay;
    private ColorRGBA color;

    /**
     * Only use when set() is called afterwards
     */
    public B3D_ColorScale()
    {
    }

    public B3D_ColorScale(String _name, int _index, float _colorDensity, boolean _multiply, boolean _overlay, ColorRGBA _color)
    {
        setName(_name);
        changeFilterIndex(_index);
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

    @Override
    public void set(B3D_Element e)
    {
        super.set(e);
        B3D_ColorScale a = (B3D_ColorScale) e;
        Wizard.copyValues(a, this, getClass());
    }
}
