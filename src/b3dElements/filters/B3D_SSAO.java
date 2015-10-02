package b3dElements.filters;

import java.io.Serializable;

public class B3D_SSAO extends B3D_Filter implements Serializable
{

    private float sampleRadius, bias, intensity, scale;

    public B3D_SSAO(String _name, int _index, float _sampleRadius, float _bias, float _intensity, float _scale)
    {
        setName(_name);
        setFilterIndex(_index);
        sampleRadius = _sampleRadius;
        bias = _bias;
        intensity = _intensity;
        scale = _scale;
    }

    public float getSampleRadius()
    {
        return sampleRadius;
    }

    public void setSampleRadius(float sampleRadius)
    {
        this.sampleRadius = sampleRadius;
    }

    public float getBias()
    {
        return bias;
    }

    public void setBias(float bias)
    {
        this.bias = bias;
    }

    public float getIntensity()
    {
        return intensity;
    }

    public void setIntensity(float intensity)
    {
        this.intensity = intensity;
    }

    public float getScale()
    {
        return scale;
    }

    public void setScale(float scale)
    {
        this.scale = scale;
    }
}
