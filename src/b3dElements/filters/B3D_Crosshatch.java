package b3dElements.filters;

import b3dElements.B3D_Element;
import com.jme3.math.ColorRGBA;
import java.io.Serializable;
import other.Wizard;

public class B3D_Crosshatch extends B3D_Filter implements Serializable
{

    private ColorRGBA lineColor, paperColor;
    private float colorInfluenceLine, colorInfluencePaper, fillValue, lineThickness, lineDistance;
    private Float[] luminanceLevels = new Float[5];

    /**
     * Only use when set() is called afterwards
     */
    public B3D_Crosshatch()
    {
    }

    public B3D_Crosshatch(String _name,
            int _index,
            ColorRGBA _lineColor,
            ColorRGBA _paperColor,
            float _colorInfluenceLine,
            float _colorInfluencePaper,
            float _fillValue,
            float _lineThickness,
            float _lineDistance,
            Float[] _luminanceLevels)
    {
        setName(_name);
        setFilterIndex(_index);
        lineColor = _lineColor;
        paperColor = _paperColor;
        colorInfluenceLine = _colorInfluenceLine;
        colorInfluencePaper = _colorInfluencePaper;
        fillValue = _fillValue;
        lineThickness = _lineThickness;
        lineDistance = _lineDistance;
        luminanceLevels = _luminanceLevels;
    }

    public ColorRGBA getLineColor()
    {
        return lineColor;
    }

    public void setLineColor(ColorRGBA lineColor)
    {
        this.lineColor = lineColor;
    }

    public ColorRGBA getPaperColor()
    {
        return paperColor;
    }

    public void setPaperColor(ColorRGBA paperColor)
    {
        this.paperColor = paperColor;
    }

    public float getColorInfluenceLine()
    {
        return colorInfluenceLine;
    }

    public void setColorInfluenceLine(float colorInfluenceLine)
    {
        this.colorInfluenceLine = colorInfluenceLine;
    }

    public float getColorInfluencePaper()
    {
        return colorInfluencePaper;
    }

    public void setColorInfluencePaper(float colorInfluencePaper)
    {
        this.colorInfluencePaper = colorInfluencePaper;
    }

    public float getFillValue()
    {
        return fillValue;
    }

    public void setFillValue(float fillValue)
    {
        this.fillValue = fillValue;
    }

    public float getLineThickness()
    {
        return lineThickness;
    }

    public void setLineThickness(float lineThickness)
    {
        this.lineThickness = lineThickness;
    }

    public float getLineDistance()
    {
        return lineDistance;
    }

    public void setLineDistance(float lineDistance)
    {
        this.lineDistance = lineDistance;
    }

    public Float[] getLuminanceLevels()
    {
        return luminanceLevels;
    }

    public void setLuminanceLevels(Float[] luminanceLevels)
    {
        this.luminanceLevels = luminanceLevels;
    }

    @Override
    public void set(B3D_Element e)
    {
        super.set(e);
        B3D_Crosshatch a = (B3D_Crosshatch) e;
        Wizard.copyValues(a, this, getClass());
    }
}
