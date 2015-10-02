package b3dElements.physics.collisionShapes;

import java.io.Serializable;

public class B3D_CapsuleShape extends B3D_CShape implements Serializable
{

    private float radius, height;

    public B3D_CapsuleShape(float _radius, float _height)
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
}
