package b3dElements.physics.collisionShapes;

import b3dElements.B3D_Element;
import java.io.Serializable;
import java.util.UUID;
import other.Wizard;

public class B3D_StaticMeshShape extends B3D_CShape implements Serializable
{

    private UUID spatialUUID;

    /**
     * Only use when set() is called afterwards
     */
    public B3D_StaticMeshShape()
    {
    }

    public B3D_StaticMeshShape(UUID _spatialIndex)
    {
        setName("CShape");
        spatialUUID = _spatialIndex;
    }

    public UUID getSpatialUUID()
    {
        return spatialUUID;
    }

    public void setSpatialIndex(UUID _spatialUUID)
    {
        this.spatialUUID = _spatialUUID;
    }

    @Override
    public void set(B3D_Element e)
    {
        super.set(e);
        B3D_StaticMeshShape b = (B3D_StaticMeshShape) e;
        Wizard.copyValues(b, this, getClass());
    }
}
