package b3dElements.controls;

import java.io.Serializable;
import java.util.UUID;

public class B3D_LightControl extends B3D_Control implements Serializable
{

    private UUID lightUUID;

    public B3D_LightControl(UUID _light, UUID _spatial, boolean enabled)
    {
        setSpatialUUID(_spatial);
        lightUUID = _light;
        setEnabled(enabled);
    }

    public UUID getLightUUID()
    {
        return lightUUID;
    }

    public void setLightUUID(UUID uuid)
    {
        this.lightUUID = uuid;
    }
}