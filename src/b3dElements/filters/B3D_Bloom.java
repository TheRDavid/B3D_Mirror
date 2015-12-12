package b3dElements.filters;

import b3dElements.B3D_Element;
import java.io.Serializable;
import other.Wizard;

public class B3D_Bloom extends B3D_Filter implements Serializable
{

    private float intensity, blurScale, exposureCutOff, exposurePower, downSamplingFactor;

    /**
     * Only use when set() is called afterwards
     */
    public B3D_Bloom()
    {
    }

    public B3D_Bloom(String _name,
            int _index,
            float _intensity,
            float _blurScale,
            float _exposureCutOff,
            float _exposurePower,
            float _downSamplingFactor)
    {
        setName(_name);
        changeFilterIndex(_index);
        intensity = _intensity;
        blurScale = _blurScale;
        exposureCutOff = _exposureCutOff;
        exposurePower = _exposurePower;
        downSamplingFactor = _downSamplingFactor;
    }

    public float getIntensity()
    {
        return intensity;
    }

    public void setIntensity(float intensity)
    {
        this.intensity = intensity;
    }

    public float getBlurScale()
    {
        return blurScale;
    }

    public void setBlurScale(float blurScale)
    {
        this.blurScale = blurScale;
    }

    public float getExposureCutOff()
    {
        return exposureCutOff;
    }

    public void setExposureCutOff(float exposureCutOff)
    {
        this.exposureCutOff = exposureCutOff;
    }

    public float getExposurePower()
    {
        return exposurePower;
    }

    public void setExposurePower(float exposurePower)
    {
        this.exposurePower = exposurePower;
    }

    public float getDownSamplingFactor()
    {
        return downSamplingFactor;
    }

    public void setDownSamplingFactor(float downSamplingFactor)
    {
        this.downSamplingFactor = downSamplingFactor;
    }

    @Override
    public void set(B3D_Element e)
    {
        super.set(e);
        B3D_Bloom a = (B3D_Bloom) e;
        Wizard.copyValues(a, this, getClass());
    }
}
