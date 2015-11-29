package b3dElements.lights;

import b3dElements.B3D_Element;
import com.jme3.math.ColorRGBA;
import java.io.Serializable;
import other.Wizard;

public abstract class B3D_Light extends b3dElements.B3D_Element implements Serializable
{

    private ColorRGBA color;

    public ColorRGBA getColor()
    {
        return color;
    }

    public void setColor(ColorRGBA color)
    {
        this.color = color;
    }

    @Override
    public void set(B3D_Element e)
    {
        super.set(e);
        B3D_Light a = (B3D_Light) e;
        Wizard.copyValues(a, this, B3D_Light.class);
    }
}
