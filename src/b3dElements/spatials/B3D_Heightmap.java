package b3dElements.spatials;

import b3dElements.other.B3D_Material;
import java.io.Serializable;

/**
 *
 * @author David
 */
public class B3D_Heightmap extends B3D_Terrain implements Serializable
{

    private float[] height;
    private int totalSize, patchSize;

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
}
