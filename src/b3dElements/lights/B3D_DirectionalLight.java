package b3dElements.lights;

import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import java.io.Serializable;

public class B3D_DirectionalLight extends B3D_Light implements Serializable
{

    private Vector3f direction;

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
}