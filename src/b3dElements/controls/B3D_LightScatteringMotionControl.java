package b3dElements.controls;

import java.io.Serializable;
import java.util.UUID;

/**
 *
 * @author David
 */
public class B3D_LightScatteringMotionControl extends B3D_Control implements Serializable
{

    private UUID filterID;

    public B3D_LightScatteringMotionControl(UUID filterID, UUID spatialUUID, boolean enabled)
    {
        setEnabled(enabled);
        this.filterID = filterID;
        setSpatialUUID(spatialUUID);
    }

    public UUID getFilterID()
    {
        return filterID;
    }

    public void setFilterID(UUID filterID)
    {
        this.filterID = filterID;
    }
}
