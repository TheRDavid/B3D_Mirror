package b3dElements.physics.collisionShapes;

import java.io.Serializable;
import java.util.UUID;

public class B3D_StaticMeshShape extends B3D_CShape implements Serializable
{

    private UUID spatialUUID;

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
}
