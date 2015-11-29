package b3dElements.spatials;

import b3dElements.B3D_Element;
import b3dElements.other.B3D_Material;
import java.io.Serializable;
import other.Wizard;

/**
 *
 * @author David
 */
public class B3D_Terrain extends B3D_Spatial implements Serializable
{

    private B3D_Material material;

    public B3D_Material getMaterial()
    {
        return material;
    }

    public void setMaterial(B3D_Material material)
    {
        this.material = material;
    }

    @Override
    public void set(B3D_Element e)
    {
        super.set(e);
        B3D_Terrain t = (B3D_Terrain) e;
        Wizard.copyValues(t, this, getClass());
    }
}
