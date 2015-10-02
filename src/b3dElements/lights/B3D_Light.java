package b3dElements.lights;

import com.jme3.math.ColorRGBA;
import java.io.Serializable;

public class B3D_Light extends b3dElements.B3D_Element implements Serializable
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
}
