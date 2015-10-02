package b3dElements.physics.collisionShapes;

import java.io.Serializable;
import java.util.UUID;

public class B3D_DynamicMeshShape extends B3D_CShape implements Serializable
{

    private UUID spatialUUID;

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
}
