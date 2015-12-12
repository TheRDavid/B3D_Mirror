package b3dElements.filters;

import b3dElements.B3D_Element;
import static com.jme3.shadow.EdgeFilteringMode.Bilinear;
import static com.jme3.shadow.EdgeFilteringMode.Dither;
import static com.jme3.shadow.EdgeFilteringMode.Nearest;
import static com.jme3.shadow.EdgeFilteringMode.PCF8;
import java.io.Serializable;
import java.util.UUID;
import other.Wizard;

public class B3D_Shadow extends B3D_Filter implements Serializable
{

    private float intensity;
    private int shadowMapSize, edgeThickness;
    private boolean flushQueues;
    private EdgeFilteringMode edgeFilteringMode;
    private UUID lightUUID;
    private LightType lightType;

    /**
     * Only use when set() is called afterwards
     */
    public B3D_Shadow()
    {
    }

    public B3D_Shadow(
            String _name,
            int _filterIndex,
            LightType _lightType,
            float _intensity,
            UUID _lightUUID,
            boolean _flushQueues,
            com.jme3.shadow.EdgeFilteringMode _edgeFilteringMode,
            int _edgeThickness,
            int _shadowMapSize)
    {
        setName(_name);
        changeFilterIndex(_filterIndex);
        lightUUID = _lightUUID;
        lightType = _lightType;
        intensity = _intensity;
        flushQueues = _flushQueues;
        switch (_edgeFilteringMode)
        {
            case Bilinear:
                edgeFilteringMode = EdgeFilteringMode.Bilinear;
                break;
            case Dither:
                edgeFilteringMode = EdgeFilteringMode.Dither;
                break;
            case Nearest:
                edgeFilteringMode = EdgeFilteringMode.Nearest;
                break;
            case PCF4:
                edgeFilteringMode = EdgeFilteringMode.PCF4;
                break;
            case PCF8:
                edgeFilteringMode = EdgeFilteringMode.PCF8;
                break;
            case PCFPOISSON:
                edgeFilteringMode = EdgeFilteringMode.PCFPOISSON;
                break;
        }
        edgeThickness = _edgeThickness;
        shadowMapSize = _shadowMapSize;
    }

    public enum EdgeFilteringMode
    {

        Nearest,
        Bilinear,
        Dither,
        PCF4,
        PCFPOISSON,
        PCF8
    }

    public enum LightType
    {

        Direction,
        Point,
        Spot
    }

    public int getShadowMapSize()
    {
        return shadowMapSize;
    }

    public void setShadowMapSize(int shadowMapSize)
    {
        this.shadowMapSize = shadowMapSize;
    }

    public int getEdgeThickness()
    {
        return edgeThickness;
    }

    public void setEdgeThickness(int edgeThickness)
    {
        this.edgeThickness = edgeThickness;
    }

    public boolean isFlushQueues()
    {
        return flushQueues;
    }

    public void setFlushQueues(boolean flushQueues)
    {
        this.flushQueues = flushQueues;
    }

    public EdgeFilteringMode getEdgeFilteringMode()
    {
        return edgeFilteringMode;
    }

    public void setEdgeFilteringMode(EdgeFilteringMode edgeFilteringMode)
    {
        this.edgeFilteringMode = edgeFilteringMode;
    }

    public float getIntensity()
    {
        return intensity;
    }

    public void setIntensity(float intensity)
    {
        this.intensity = intensity;
    }

    public UUID getLightUUID()
    {
        return lightUUID;
    }

    public void setLightUUID(UUID lightIndex)
    {
        this.lightUUID = lightIndex;
    }

    public LightType getLightType()
    {
        return lightType;
    }

    public void setLightType(LightType lightType)
    {
        this.lightType = lightType;
    }

    @Override
    public void set(B3D_Element e)
    {
        super.set(e);
        B3D_Shadow a = (B3D_Shadow) e;
        Wizard.copyValues(a, this, getClass());
    }
}
