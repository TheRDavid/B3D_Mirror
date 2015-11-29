package b3dElements.filters;

import b3dElements.B3D_Element;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import java.io.Serializable;
import other.Wizard;

public class B3D_Water extends B3D_Filter implements Serializable
{

    private float causistsIntensity, foamHardness, foamIntensity, maxAmplitude, normalScale, reflectionDisplace, refractionStrength, shininess,
            shoreHardness, speed, sunScale, underWaterFogDistance, height, transparency, waveScale;
    private int resolution;
    private Vector2f windDirection;
    private Vector3f colorExtinction, foamExistence, lightDirection;
    private ColorRGBA deepWaterColor, lightColor, color;
    private boolean useCausists, useFoam, useHQShoreLine, useRefraction, useRipples, useSpecular;
    private String causistsTexture, foamTexture, heightTexture, normalTexture;

    /**
     * Only use when set() is called afterwards
     */
    public B3D_Water()
    {
    }

    public B3D_Water(
            String _name,
            int _index,
            float _causistsIntensity,
            float _foamHardness,
            float _foamIntensity,
            float _maxAmplitude,
            float _normalScale,
            float _reflectionDisplace,
            int _resolution,
            float _refractionStrength,
            float _shininess,
            float _shoreHardness,
            float _speed,
            float _sunScale,
            float _underWaterFogDistance,
            float _height,
            float _transparency,
            float _waveScale,
            Vector2f _windDirection,
            Vector3f _colorExtinction,
            Vector3f _foamExistence,
            Vector3f _lightDirection,
            ColorRGBA _color,
            ColorRGBA _deepWaterColor,
            ColorRGBA _lightColor,
            boolean _useCausists,
            boolean _useFoam,
            boolean _useHQShoreLine,
            boolean _useRefraction,
            boolean _useRipples,
            boolean _useSpecular,
            String _causistsTexture,
            String _foamTexture,
            String _heightTexture,
            String _normalTexture)
    {
        setName(_name);
        setFilterIndex(_index);
        causistsTexture = _causistsTexture;
        foamTexture = _foamTexture;
        heightTexture = _heightTexture;
        normalTexture = _normalTexture;
        causistsIntensity = _causistsIntensity;
        foamHardness = _foamHardness;
        foamIntensity = _foamIntensity;
        maxAmplitude = _maxAmplitude;
        normalScale = _normalScale;
        reflectionDisplace = _reflectionDisplace;
        resolution = _resolution;
        refractionStrength = _refractionStrength;
        shininess = _shininess;
        shoreHardness = _shoreHardness;
        speed = _speed;
        sunScale = _sunScale;
        underWaterFogDistance = _underWaterFogDistance;
        height = _height;
        transparency = _transparency;
        waveScale = _waveScale;
        windDirection = _windDirection;
        colorExtinction = _colorExtinction;
        foamExistence = _foamExistence;
        lightDirection = _lightDirection;
        color = _color;
        deepWaterColor = _deepWaterColor;
        lightColor = _lightColor;
        useCausists = _useCausists;
        useFoam = _useFoam;
        useHQShoreLine = _useHQShoreLine;
        useRefraction = _useRefraction;
        useRipples = _useRipples;
        useSpecular = _useSpecular;
    }

    public float getCausistsIntensity()
    {
        return causistsIntensity;
    }

    public void setCausistsIntensity(float causistsIntensity)
    {
        this.causistsIntensity = causistsIntensity;
    }

    public float getFoamHardness()
    {
        return foamHardness;
    }

    public void setFoamHardness(float foamHardness)
    {
        this.foamHardness = foamHardness;
    }

    public float getFoamIntensity()
    {
        return foamIntensity;
    }

    public void setFoamIntensity(float foamIntensity)
    {
        this.foamIntensity = foamIntensity;
    }

    public float getMaxAmplitude()
    {
        return maxAmplitude;
    }

    public void setMaxAmplitude(float maxAmplitude)
    {
        this.maxAmplitude = maxAmplitude;
    }

    public float getNormalScale()
    {
        return normalScale;
    }

    public void setNormalScale(float normalScale)
    {
        this.normalScale = normalScale;
    }

    public float getReflectionDisplace()
    {
        return reflectionDisplace;
    }

    public void setReflectionDisplace(float reflectionDisplace)
    {
        this.reflectionDisplace = reflectionDisplace;
    }

    public int getResolution()
    {
        return resolution;
    }

    public void setResolution(int resolution)
    {
        this.resolution = resolution;
    }

    public float getRefractionStrength()
    {
        return refractionStrength;
    }

    public void setRefractionStrength(float refractionStrength)
    {
        this.refractionStrength = refractionStrength;
    }

    public float getShininess()
    {
        return shininess;
    }

    public void setShininess(float shininess)
    {
        this.shininess = shininess;
    }

    public float getShoreHardness()
    {
        return shoreHardness;
    }

    public void setShoreHardness(float shoreHardness)
    {
        this.shoreHardness = shoreHardness;
    }

    public float getSpeed()
    {
        return speed;
    }

    public void setSpeed(float speed)
    {
        this.speed = speed;
    }

    public float getSunScale()
    {
        return sunScale;
    }

    public void setSunScale(float sunScale)
    {
        this.sunScale = sunScale;
    }

    public float getUnderWaterFogDistance()
    {
        return underWaterFogDistance;
    }

    public void setUnderWaterFogDistance(float underWaterFogDistance)
    {
        this.underWaterFogDistance = underWaterFogDistance;
    }

    public float getHeight()
    {
        return height;
    }

    public void setHeight(float height)
    {
        this.height = height;
    }

    public float getTransparency()
    {
        return transparency;
    }

    public void setTransparency(float transparency)
    {
        this.transparency = transparency;
    }

    public float getWaveScale()
    {
        return waveScale;
    }

    public void setWaveScale(float waveScale)
    {
        this.waveScale = waveScale;
    }

    public Vector2f getWindDirection()
    {
        return windDirection;
    }

    public void setWindDirection(Vector2f windDirection)
    {
        this.windDirection = windDirection;
    }

    public Vector3f getColorExtinction()
    {
        return colorExtinction;
    }

    public void setColorExtinction(Vector3f colorExtinction)
    {
        this.colorExtinction = colorExtinction;
    }

    public Vector3f getFoamExistence()
    {
        return foamExistence;
    }

    public void setFoamExistence(Vector3f foamExistence)
    {
        this.foamExistence = foamExistence;
    }

    public Vector3f getLightDirection()
    {
        return lightDirection;
    }

    public void setLightDirection(Vector3f lightDirection)
    {
        this.lightDirection = lightDirection;
    }

    public ColorRGBA getDeepWaterColor()
    {
        return deepWaterColor;
    }

    public void setDeepWaterColor(ColorRGBA deepWaterColor)
    {
        this.deepWaterColor = deepWaterColor;
    }

    public ColorRGBA getLightColor()
    {
        return lightColor;
    }

    public void setLightColor(ColorRGBA lightColor)
    {
        this.lightColor = lightColor;
    }

    public ColorRGBA getColor()
    {
        return color;
    }

    public void setColor(ColorRGBA color)
    {
        this.color = color;
    }

    public boolean isUseCausists()
    {
        return useCausists;
    }

    public void setUseCausists(boolean useCausists)
    {
        this.useCausists = useCausists;
    }

    public boolean isUseFoam()
    {
        return useFoam;
    }

    public void setUseFoam(boolean useFoam)
    {
        this.useFoam = useFoam;
    }

    public boolean isUseHQShoreLine()
    {
        return useHQShoreLine;
    }

    public void setUseHQShoreLine(boolean useHQShoreLine)
    {
        this.useHQShoreLine = useHQShoreLine;
    }

    public boolean isUseRefraction()
    {
        return useRefraction;
    }

    public void setUseRefraction(boolean useRefraction)
    {
        this.useRefraction = useRefraction;
    }

    public boolean isUseRipples()
    {
        return useRipples;
    }

    public void setUseRipples(boolean useRipples)
    {
        this.useRipples = useRipples;
    }

    public boolean isUseSpecular()
    {
        return useSpecular;
    }

    public void setUseSpecular(boolean useSpecular)
    {
        this.useSpecular = useSpecular;
    }

    public String getCausistsTexture()
    {
        return causistsTexture;
    }

    public void setCausistsTexture(String causistsTexture)
    {
        this.causistsTexture = causistsTexture;
    }

    public String getFoamTexture()
    {
        return foamTexture;
    }

    public void setFoamTexture(String foamTexture)
    {
        this.foamTexture = foamTexture;
    }

    public String getHeightTexture()
    {
        return heightTexture;
    }

    public void setHeightTexture(String heightTexture)
    {
        this.heightTexture = heightTexture;
    }

    public String getNormalTexture()
    {
        return normalTexture;
    }

    public void setNormalTexture(String normalTexture)
    {
        this.normalTexture = normalTexture;
    }

    @Override
    public void set(B3D_Element e)
    {
        super.set(e);
        B3D_Water a = (B3D_Water) e;
        Wizard.copyValues(a, this, getClass());
    }
}
