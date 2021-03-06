package b3dElements.physics.collisionShapes;

import b3dElements.B3D_Element;
import com.jme3.math.Vector3f;
import java.io.Serializable;
import other.Wizard;

public class B3D_BoxShape extends B3D_CShape implements Serializable
{

    private Vector3f halfExtends;

    /**
     * Only use when set() is called afterwards
     */
    public B3D_BoxShape()
    {
    }

    public B3D_BoxShape(Vector3f _halfExtends)
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

    @Override
    public void set(B3D_Element e)
    {
        super.set(e);
        B3D_BoxShape b = (B3D_BoxShape) e;
        Wizard.copyValues(b, this, getClass());
    }
}
