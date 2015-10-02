package other;

import com.jme3.math.ColorRGBA;
import java.io.Serializable;
import java.util.Vector;

public class B3D_Scene implements Serializable
{

    private ColorRGBA viewPortColor = Wizard.defaultViewportBackground;
    private float physicsSpeed = 1f;
    private Vector<b3dElements.B3D_Element> elements = new Vector<b3dElements.B3D_Element>();
    private String name;

    public B3D_Scene(String _name)
    {
        name = _name;
    }

    public ColorRGBA getViewPortColor()
    {
        return viewPortColor;
    }

    public void setViewPortColor(ColorRGBA viewPortColor)
    {
        this.viewPortColor = viewPortColor;
    }

    public float getPhysicsSpeed()
    {
        return physicsSpeed;
    }

    public void setPhysicsSpeed(float physicsSpeed)
    {
        this.physicsSpeed = physicsSpeed;
    }

    public Vector<b3dElements.B3D_Element> getElements()
    {
        return elements;
    }

    public void setElements(Vector<b3dElements.B3D_Element> elements)
    {
        this.elements = elements;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
