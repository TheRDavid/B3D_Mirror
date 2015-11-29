package b3dElements.lights;

import b3dElements.B3D_Element;
import com.jme3.math.ColorRGBA;
import java.io.Serializable;
import other.Wizard;

public class B3D_AmbientLight extends B3D_Light implements Serializable
{

    /**
     * Only use when set() is called afterwards
     */
    public B3D_AmbientLight()
    {
    }

    public B3D_AmbientLight(String _name, ColorRGBA _color)
    {
        setName(_name);
        setColor(_color);
    }

    @Override
    public void set(B3D_Element e)
    {
        super.set(e);
        B3D_AmbientLight a = (B3D_AmbientLight) e;
        Wizard.copyValues(a, this, getClass());
    }
}
