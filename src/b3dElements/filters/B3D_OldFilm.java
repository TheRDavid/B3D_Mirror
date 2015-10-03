package b3dElements.filters;

import com.jme3.math.ColorRGBA;

public class B3D_OldFilm extends B3D_Filter
{

    private ColorRGBA color;
    private float colorDensity, noiseDensity, scratchDensity, vignetteValue;

    public B3D_OldFilm(String _name, int _index, ColorRGBA _color, float _colorDensity, float _noiseDensity, float _scratchDensity, float _vignetteValue)
    {
        setName(_name);
        setFilterIndex(_index);
        color = _color;
        colorDensity = _colorDensity;
        noiseDensity = _noiseDensity;
        scratchDensity = _scratchDensity;
        vignetteValue = _vignetteValue;
    }

    public ColorRGBA getColor()
    {
        return color;
    }

    public void setColor(ColorRGBA color)
    {
        this.color = color;
    }

    public float getColorDensity()
    {
        return colorDensity;
    }

    public void setColorDensity(float colorDensity)
    {
        this.colorDensity = colorDensity;
    }

    public float getNoiseDensity()
    {
        return noiseDensity;
    }

    public void setNoiseDensity(float noiseDensity)
    {
        this.noiseDensity = noiseDensity;
    }

    public float getScratchDensity()
    {
        return scratchDensity;
    }

    public void setScratchDensity(float scratchDensity)
    {
        this.scratchDensity = scratchDensity;
    }

    public float getVignetteValue()
    {
        return vignetteValue;
    }

    public void setVignetteValue(float vignetteValue)
    {
        this.vignetteValue = vignetteValue;
    }
}