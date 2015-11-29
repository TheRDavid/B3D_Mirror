package b3dElements.lights;

import b3dElements.B3D_Element;
import b3dElements.filters.B3D_BasicSSAO;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import java.io.Serializable;
import other.Wizard;

public class B3D_SpotLight extends B3D_Light implements Serializable
{

    private Vector3f position, direction;
    private float innerAngle, outerAngle, range;

    /**
     * Only use when set() is called afterwards
     */
    public B3D_SpotLight()
    {
    }

    public B3D_SpotLight(String _name,
            ColorRGBA _color,
            Vector3f _position,
            Vector3f _direction,
            float _innerAngle,
            float _outerAngle,
            float _range)
    {
        setName(_name);
        setColor(_color);
        position = _position;
        direction = _direction;
        innerAngle = _innerAngle;
        outerAngle = _outerAngle;
        range = _range;
    }

    public Vector3f getPosition()
    {
        return position;
    }

    public void setPosition(Vector3f position)
    {
        this.position = position;
    }

    public Vector3f getDirection()
    {
        return direction;
    }

    public void setDirection(Vector3f direction)
    {
        this.direction = direction;
    }

    public float getInnerAngle()
    {
        return innerAngle;
    }

    public void setInnerAngle(float innerAngle)
    {
        this.innerAngle = innerAngle;
    }

    public float getOuterAngle()
    {
        return outerAngle;
    }

    public void setOuterAngle(float outerAngle)
    {
        this.outerAngle = outerAngle;
    }

    public float getRange()
    {
        return range;
    }

    public void setRange(float range)
    {
        this.range = range;
    }

    @Override
    public void set(B3D_Element e)
    {
        super.set(e);
        B3D_SpotLight a = (B3D_SpotLight) e;
        Wizard.copyValues(a, this, getClass());
    }
}
