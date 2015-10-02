package b3dElements.spatials.geometries;

import java.io.Serializable;

public class B3D_SingleTextureSkyBox extends B3D_Geometry implements Serializable
{

    String northTextureName;

    public B3D_SingleTextureSkyBox(String _name, String _northTextureName)
    {
        setName(_name);
        northTextureName = _northTextureName;
    }

    public String getNorthTextureName()
    {
        return northTextureName;
    }

    public void setNorthTextureName(String northTextureName)
    {
        this.northTextureName = northTextureName;
    }
}
