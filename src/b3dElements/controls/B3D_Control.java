package b3dElements.controls;

import b3dElements.B3D_Element;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.UUID;
import other.Wizard;

/**
 *
 * @author David
 */
public abstract class B3D_Control extends B3D_Element implements Serializable
{

    private UUID spatialUUID;
    private boolean enabled = true;

    public UUID getSpatialUUID()
    {
        return spatialUUID;
    }

    public void setSpatialUUID(UUID spatialUUID)
    {
        this.spatialUUID = spatialUUID;
    }

    public boolean isEnabled()
    {
        return enabled;
    }

    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }

    @Override
    public void set(B3D_Element e)
    {
        super.set(e);
        B3D_Control a = (B3D_Control) e;
        Wizard.copyValues(a, this, getClass());
    }
}
