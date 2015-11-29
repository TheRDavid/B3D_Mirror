package b3dElements.spatials.geometries;

import b3dElements.B3D_Element;
import b3dElements.other.B3D_Material;
import b3dElements.spatials.B3D_Spatial;
import java.io.Serializable;
import other.Wizard;

public class B3D_Geometry extends B3D_Spatial implements Serializable
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
        B3D_Geometry t = (B3D_Geometry) e;
        Wizard.copyValues(t, this, getClass());
    }
}
