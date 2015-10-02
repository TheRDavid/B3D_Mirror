package b3dElements.filters;

public class B3D_BasicSSAO extends B3D_Filter
{

    private float bias, detailBias, detailIntensity, detailSampleRadius, detailScale, falloffRate, falloffStartDistance, intensity, sampleRadius, scale;
    private boolean smoothMore, useAO, useOnlyAO, useDetailPass, useDistanceFalloff, useSmoothing;

    public B3D_BasicSSAO(String _name, int _index, float _bias, float _detailBias, float _detailIntensity, float _detailSampleRadius, float _detailScale,
            float _falloffRate, float _falloffStartDistance, float _intensity, float _sampleRadius, float _scale, boolean _smoothMore, boolean _useAO,
            boolean _useOnlyAO, boolean _useDetailPass, boolean _useDistanceFalloff, boolean _useSmoothing)
    {
        setName(_name);
        setFilterIndex(_index);
        bias = _bias;
        detailBias = _detailBias;
        detailIntensity = _detailIntensity;
        detailSampleRadius = _detailSampleRadius;
        detailScale = _detailScale;
        falloffRate = _falloffRate;
        falloffStartDistance = _falloffStartDistance;
        intensity = _intensity;
        sampleRadius = _sampleRadius;
        scale = _scale;
        smoothMore = _smoothMore;
        useAO = _useAO;
        useOnlyAO = _useOnlyAO;
        useDetailPass = _useDetailPass;
        useDistanceFalloff = _useDistanceFalloff;
        useSmoothing = _useSmoothing;
    }

    public float getBias()
    {
        return bias;
    }

    public void setBias(float bias)
    {
        this.bias = bias;
    }

    public float getDetailBias()
    {
        return detailBias;
    }

    public void setDetailBias(float detailBias)
    {
        this.detailBias = detailBias;
    }

    public float getDetailIntensity()
    {
        return detailIntensity;
    }

    public void setDetailIntensity(float detailIntensity)
    {
        this.detailIntensity = detailIntensity;
    }

    public float getDetailSampleRadius()
    {
        return detailSampleRadius;
    }

    public void setDetailSampleRadius(float detailSampleRadius)
    {
        this.detailSampleRadius = detailSampleRadius;
    }

    public float getDetailScale()
    {
        return detailScale;
    }

    public void setDetailScale(float detailScale)
    {
        this.detailScale = detailScale;
    }

    public float getFalloffRate()
    {
        return falloffRate;
    }

    public void setFalloffRate(float falloffRate)
    {
        this.falloffRate = falloffRate;
    }

    public float getFalloffStartDistance()
    {
        return falloffStartDistance;
    }

    public void setFalloffStartDistance(float falloffStartDistance)
    {
        this.falloffStartDistance = falloffStartDistance;
    }

    public float getIntensity()
    {
        return intensity;
    }

    public void setIntensity(float intensity)
    {
        this.intensity = intensity;
    }

    public float getSampleRadius()
    {
        return sampleRadius;
    }

    public void setSampleRadius(float sampleRadius)
    {
        this.sampleRadius = sampleRadius;
    }

    public float getScale()
    {
        return scale;
    }

    public void setScale(float scale)
    {
        this.scale = scale;
    }

    public boolean isSmoothMore()
    {
        return smoothMore;
    }

    public void setSmoothMore(boolean smoothMore)
    {
        this.smoothMore = smoothMore;
    }

    public boolean isUseAO()
    {
        return useAO;
    }

    public void setUseAO(boolean useAO)
    {
        this.useAO = useAO;
    }

    public boolean isUseOnlyAO()
    {
        return useOnlyAO;
    }

    public void setUseOnlyAO(boolean useOnlyAO)
    {
        this.useOnlyAO = useOnlyAO;
    }

    public boolean isUseDetailPass()
    {
        return useDetailPass;
    }

    public void setUseDetailPass(boolean useDetailPass)
    {
        this.useDetailPass = useDetailPass;
    }

    public boolean isUseDistanceFalloff()
    {
        return useDistanceFalloff;
    }

    public void setUseDistanceFalloff(boolean useDistanceFalloff)
    {
        this.useDistanceFalloff = useDistanceFalloff;
    }

    public boolean isUseSmoothing()
    {
        return useSmoothing;
    }

    public void setUseSmoothing(boolean useSmoothing)
    {
        this.useSmoothing = useSmoothing;
    }
}
