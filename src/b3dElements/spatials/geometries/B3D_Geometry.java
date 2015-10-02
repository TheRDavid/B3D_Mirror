package b3dElements.spatials.geometries;

import b3dElements.other.B3D_Material;
import b3dElements.spatials.B3D_Spatial;
import java.io.Serializable;

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
}
