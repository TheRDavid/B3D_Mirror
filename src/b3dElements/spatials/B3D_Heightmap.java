package b3dElements.spatials;

import b3dElements.B3D_Element;
import b3dElements.other.B3D_Material;
import java.io.Serializable;
import other.Wizard;

/**
 *
 * @author David
 */
public class B3D_Heightmap extends B3D_Terrain implements Serializable
{

    private float[] height;
    private int totalSize, patchSize;

    /**
     * Only use when set() is called afterwards
     */
    public B3D_Heightmap()
    {
    }

    public B3D_Heightmap(String name, float[] height, int totalSize, int patchSize, B3D_Material _material, String _shadowMode)
    {
        setMaterial(_material);
        setName(name);
        this.height = height;
        this.totalSize = totalSize;
        this.patchSize = patchSize;
        setShadowMode(_shadowMode);
    }

    public float[] getHeight()
    {
        return height;
    }

    public void setHeight(float[] height)
    {
        this.height = height;
    }

    public int getTotalSize()
    {
        return totalSize;
    }

    public void setTotalSize(int totalSize)
    {
        this.totalSize = totalSize;
    }

    public int getPatchSize()
    {
        return patchSize;
    }

    public void setPatchSize(int patchSize)
    {
        this.patchSize = patchSize;
    }

    @Override
    public void set(B3D_Element e)
    {
        super.set(e);
        B3D_Heightmap t = (B3D_Heightmap) e;
        Wizard.copyValues(t, this, getClass());
    }
}
