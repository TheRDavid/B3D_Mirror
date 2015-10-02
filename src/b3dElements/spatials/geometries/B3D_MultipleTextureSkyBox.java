package b3dElements.spatials.geometries;

import java.io.Serializable;

public class B3D_MultipleTextureSkyBox extends B3D_Geometry implements Serializable
{

    String northTextureName, soutTextureName, westTextureName, eastTextureName, topTextureName, bottomTextureName;

    public B3D_MultipleTextureSkyBox(String _name, String _northTextureName,
            String _soutTextureName,
            String _westTextureName,
            String _eastTextureName,
            String _topTextureName,
            String _bottomTextureName)
    {
        setName(_name);
        northTextureName = _northTextureName;
        soutTextureName = _soutTextureName;
        westTextureName = _westTextureName;
        eastTextureName = _eastTextureName;
        topTextureName = _topTextureName;
        bottomTextureName = _bottomTextureName;
    }

    public String getNorthTextureName()
    {
        return northTextureName;
    }

    public void setNorthTextureName(String northTextureName)
    {
        this.northTextureName = northTextureName;
    }

    public String getSoutTextureName()
    {
        return soutTextureName;
    }

    public void setSoutTextureName(String soutTextureName)
    {
        this.soutTextureName = soutTextureName;
    }

    public String getWestTextureName()
    {
        return westTextureName;
    }

    public void setWestTextureName(String westTextureName)
    {
        this.westTextureName = westTextureName;
    }

    public String getEastTextureName()
    {
        return eastTextureName;
    }

    public void setEastTextureName(String eastTextureName)
    {
        this.eastTextureName = eastTextureName;
    }

    public String getTopTextureName()
    {
        return topTextureName;
    }

    public void setTopTextureName(String topTextureName)
    {
        this.topTextureName = topTextureName;
    }

    public String getBottomTextureName()
    {
        return bottomTextureName;
    }

    public void setBottomTextureName(String bottomTextureName)
    {
        this.bottomTextureName = bottomTextureName;
    }
}
