package b3dElements.filters;

import b3dElements.B3D_Element;
import com.jme3.math.ColorRGBA;
import java.io.Serializable;
import other.Wizard;

public class B3D_Cartoon extends B3D_Filter implements Serializable
{

    private ColorRGBA color;
    private float depthSensivity, depthTreshold, edgeIntensity, edgeWidth, normalSensivity, normalTreshold;

    /**
     * Only use when set() is called afterwards
     */
    public B3D_Cartoon()
    {
    }

    public B3D_Cartoon(String _name,
            int _index,
            ColorRGBA _color,
            float _depthSensivity,
            float _depthTreshold,
            float _edgeIntensity,
            float _edgeWidth,
            float _normalSensivity,
            float _normalTreshold)
    {
        setName(_name);
        setFilterIndex(_index);
        color = _color;
        depthSensivity = _depthSensivity;
        depthTreshold = _depthTreshold;
        edgeIntensity = _edgeIntensity;
        edgeWidth = _edgeWidth;
        normalSensivity = _normalSensivity;
        normalTreshold = _normalTreshold;
    }

    public ColorRGBA getColor()
    {
        return color;
    }

    public void setColor(ColorRGBA color)
    {
        this.color = color;
    }

    public float getDepthSensivity()
    {
        return depthSensivity;
    }

    public void setDepthSensivity(float depthSensivity)
    {
        this.depthSensivity = depthSensivity;
    }

    public float getDepthTreshold()
    {
        return depthTreshold;
    }

    public void setDepthTreshold(float depthTreshold)
    {
        this.depthTreshold = depthTreshold;
    }

    public float getEdgeIntensity()
    {
        return edgeIntensity;
    }

    public void setEdgeIntensity(float edgeIntensity)
    {
        this.edgeIntensity = edgeIntensity;
    }

    public float getEdgeWidth()
    {
        return edgeWidth;
    }

    public void setEdgeWidth(float edgeWidth)
    {
        this.edgeWidth = edgeWidth;
    }

    public float getNormalSensivity()
    {
        return normalSensivity;
    }

    public void setNormalSensivity(float normalSensivity)
    {
        this.normalSensivity = normalSensivity;
    }

    public float getNormalTreshold()
    {
        return normalTreshold;
    }

    public void setNormalTreshold(float normalTreshold)
    {
        this.normalTreshold = normalTreshold;
    }

    @Override
    public void set(B3D_Element e)
    {
        super.set(e);
        B3D_Cartoon a = (B3D_Cartoon) e;
        Wizard.copyValues(a, this, getClass());
    }
}
