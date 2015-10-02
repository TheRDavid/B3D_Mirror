package b3dElements.spatials;

import java.io.Serializable;

/**
 *
 * @author David
 */
public class B3D_Terrain extends B3D_Spatial implements Serializable
{

    private float[] height;
    private int totalSize, patchSize;

    public B3D_Terrain(String name,float[] height, int totalSize, int patchSize, String _shadowMode)
    {
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
