package b3dElements.lights;

import com.jme3.math.ColorRGBA;
import java.io.Serializable;

public class B3D_AmbientLight extends B3D_Light implements Serializable
{

    public B3D_AmbientLight(String _name, ColorRGBA _color)
    {
        setName(_name);
        setColor(_color);
    }
}
