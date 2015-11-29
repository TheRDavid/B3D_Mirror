package b3dElements.lights;

import b3dElements.B3D_Element;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import java.io.Serializable;
import other.Wizard;

public class B3D_DirectionalLight extends B3D_Light implements Serializable
{

    private Vector3f direction;

    /**
     * Only use when set() is called afterwards
     */
    public B3D_DirectionalLight()
    {
    }

    public B3D_DirectionalLight(String _name, ColorRGBA _color, Vector3f _direction)
    {
        setName(_name);
        setColor(_color);
        direction = _direction;
    }

    public Vector3f getDirection()
    {
        return direction;
    }

    public void setDirection(Vector3f direction)
    {
        this.direction = direction;
    }

    @Override
    public void set(B3D_Element e)
    {
        super.set(e);
        B3D_DirectionalLight a = (B3D_DirectionalLight) e;
        Wizard.copyValues(a, this, getClass());
    }
}