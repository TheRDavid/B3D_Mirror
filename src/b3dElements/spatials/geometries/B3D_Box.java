package b3dElements.spatials.geometries;

import b3dElements.other.B3D_Material;
import java.io.Serializable;

public class B3D_Box extends B3D_Geometry implements Serializable
{

    private int xSlices, ySlices, zSlices;

    public B3D_Box(String _name, B3D_Material _material, int _xSlices, int _ySlices, int _zSlices, String _shadowMode)
    {
        xSlices = _xSlices;
        ySlices = _ySlices;
        zSlices = _zSlices;
        setName(_name);
        setMaterial(_material);
        setShadowMode(_shadowMode);
    }

    public int getxSlices()
    {
        return xSlices;
    }

    public void setxSlices(int xSlices)
    {
        this.xSlices = xSlices;
    }

    public int getySlices()
    {
        return ySlices;
    }

    public void setySlices(int ySlices)
    {
        this.ySlices = ySlices;
    }

    public int getzSlices()
    {
        return zSlices;
    }

    public void setzSlices(int zSlices)
    {
        this.zSlices = zSlices;
    }
}
