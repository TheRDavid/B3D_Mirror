package b3dElements.physics.collisionShapes;

import b3dElements.B3D_Element;
import java.io.Serializable;
import java.util.UUID;
import other.Wizard;

public class B3D_DynamicMeshShape extends B3D_CShape implements Serializable
{

    private UUID spatialUUID;

    /**
     * Only use when set() is called afterwards
     */
    public B3D_DynamicMeshShape()
    {
    }

    public B3D_DynamicMeshShape(UUID _spatialUUID)
    {
        setName("CShape");
        spatialUUID = _spatialUUID;
    }

    public UUID getSpatialUUID()
    {
        return spatialUUID;
    }

    public void setSpatialIndex(UUID _spatialUUID)
    {
        spatialUUID = _spatialUUID;
    }

    @Override
    public void set(B3D_Element e)
    {
        super.set(e);
        B3D_DynamicMeshShape b = (B3D_DynamicMeshShape) e;
        Wizard.copyValues(b, this, getClass());
    }
}
