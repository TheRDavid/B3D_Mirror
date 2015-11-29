package b3dElements.controls;

import b3dElements.B3D_Element;
import java.io.Serializable;
import java.util.UUID;
import other.Wizard;

public class B3D_LightControl extends B3D_Control implements Serializable
{

    private UUID lightUUID;

    /**
     * Only use when set() is called afterwards
     */
    public B3D_LightControl()
    {
    }

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

    @Override
    public void set(B3D_Element e)
    {
        super.set(e);
        B3D_LightControl a = (B3D_LightControl) e;
        Wizard.copyValues(a, this, getClass());
    }
}