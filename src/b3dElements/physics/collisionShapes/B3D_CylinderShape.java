package b3dElements.physics.collisionShapes;

import com.jme3.math.Vector3f;
import java.io.Serializable;

public class B3D_CylinderShape extends B3D_CShape implements Serializable
{

    private Vector3f halfExtends;

    public B3D_CylinderShape(Vector3f _halfExtends)
    {
        setName("CShape");
        halfExtends = _halfExtends;
    }

    public Vector3f getHalfExtends()
    {
        return halfExtends;
    }

    public void setHalfExtends(Vector3f halfExtends)
    {
        this.halfExtends = halfExtends;
    }
}
