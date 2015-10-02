package monkeyStuff;

import com.jme3.post.filters.LightScatteringFilter;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.control.AbstractControl;
import java.util.UUID;
import other.Wizard;

/**
 *
 * @author David
 */
public class LightScatteringMotionControl extends AbstractControl
{

    private UUID lightScatteringUUID;
    private LightScatteringFilter lightScatteringFilter;

    public LightScatteringMotionControl(UUID lsfUUID, boolean enabled)
    {
        setEnabled(enabled);
        lightScatteringUUID = lsfUUID;
    }

    @Override
    protected void controlUpdate(float tpf)
    {
        if (enabled)
        {
            if (lightScatteringFilter == null)
            {
                Integer id = Wizard.getObjectReferences().getID(
                        lightScatteringUUID);
                if (id != null)
                    lightScatteringFilter = (LightScatteringFilter) Wizard.getObjects().getOriginalObject(
                            id);
                //Filter was deleted
                else
                {
                    spatial.removeControl(this);
                    return;
                }
            }
            lightScatteringFilter.setLightPosition(spatial.getWorldTranslation().clone());
        }
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp)
    {
        //nah...
    }

    public UUID getFilterUUID()
    {
        return lightScatteringUUID;
    }
}
