package b3dElements.lights;

import b3dElements.B3D_Element;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import java.io.Serializable;
import other.Wizard;

public class B3D_PointLight extends B3D_Light implements Serializable
{

    private Vector3f position;
    private float radius;

    /**
     * Only use when set() is called afterwards
     */
    public B3D_PointLight()
    {
    }

    public B3D_PointLight(String _name, ColorRGBA _color, Vector3f _position, float _radius)
    {
        setName(_name);
        setColor(_color);
        position = _position;
        radius = _radius;
    }

    public Vector3f getPosition()
    {
        return position;
    }

    public void setPosition(Vector3f position)
    {
        this.position = position;
    }

    public float getRadius()
    {
        return radius;
    }

    public void setRadius(float radius)
    {
        this.radius = radius;
    }

    @Override
    public void set(B3D_Element e)
    {
        super.set(e);
        B3D_PointLight a = (B3D_PointLight) e;
        Wizard.copyValues(a, this, getClass());
    }
}
