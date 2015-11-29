package b3dElements.physics.collisionShapes;

import b3dElements.B3D_Element;
import java.io.Serializable;
import other.Wizard;

public class B3D_ConeShape extends B3D_CShape implements Serializable
{

    private float radius, height;

    /**
     * Only use when set() is called afterwards
     */
    public B3D_ConeShape()
    {
    }

    public B3D_ConeShape(float _radius, float _height)
    {
        setName("CShape");
        radius = _radius;
        height = _height;
    }

    public float getRadius()
    {
        return radius;
    }

    public void setRadius(float radius)
    {
        this.radius = radius;
    }

    public float getHeight()
    {
        return height;
    }

    public void setHeight(float height)
    {
        this.height = height;
    }

    @Override
    public void set(B3D_Element e)
    {
        super.set(e);
        B3D_ConeShape b = (B3D_ConeShape) e;
        Wizard.copyValues(b, this, getClass());
    }
}
