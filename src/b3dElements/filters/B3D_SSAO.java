package b3dElements.filters;

import b3dElements.B3D_Element;
import java.io.Serializable;
import other.Wizard;

public class B3D_SSAO extends B3D_Filter implements Serializable
{

    private float sampleRadius, bias, intensity, scale;

    /**
     * Only use when set() is called afterwards
     */
    public B3D_SSAO()
    {
    }

    public B3D_SSAO(String _name, int _index, float _sampleRadius, float _bias, float _intensity, float _scale)
    {
        setName(_name);
        changeFilterIndex(_index);
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

    @Override
    public void set(B3D_Element e)
    {
        super.set(e);
        B3D_SSAO a = (B3D_SSAO) e;
        Wizard.copyValues(a, this, getClass());
    }
}
