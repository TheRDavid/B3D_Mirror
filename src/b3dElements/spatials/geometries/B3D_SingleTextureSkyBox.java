package b3dElements.spatials.geometries;

import b3dElements.B3D_Element;
import java.io.Serializable;
import other.Wizard;

public class B3D_SingleTextureSkyBox extends B3D_Geometry implements Serializable
{

    String northTextureName;

    /**
     * Only use when set() is called afterwards
     */
    public B3D_SingleTextureSkyBox()
    {
    }

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

    @Override
    public void set(B3D_Element e)
    {
        super.set(e);
        B3D_SingleTextureSkyBox t = (B3D_SingleTextureSkyBox) e;
        Wizard.copyValues(t, this, getClass());
    }
}
