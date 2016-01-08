package b3dElements.controls;

import b3dElements.B3D_Element;
import b3dElements.animations.timedAnimations.animationTypes.B3D_Anim_Call;
import java.io.Serializable;
import java.util.UUID;
import other.Wizard;

/**
 *
 * @author David
 */
public class B3D_LightScatteringMotionControl extends B3D_Control implements Serializable
{

    private UUID filterID;

    /**
     * Only use when set() is called afterwards
     */
    public B3D_LightScatteringMotionControl()
    {
    }

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

    @Override
    public void set(B3D_Element e)
    {
        super.set(e);
        B3D_LightScatteringMotionControl a = (B3D_LightScatteringMotionControl) e;
        Wizard.copyValues(a, this, getClass());
    }
}
