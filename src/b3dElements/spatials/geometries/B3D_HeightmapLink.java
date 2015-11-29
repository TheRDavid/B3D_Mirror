/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package b3dElements.spatials.geometries;

import b3dElements.B3D_Element;
import b3dElements.other.B3D_Material;
import b3dElements.spatials.B3D_Terrain;
import other.Wizard;

/**
 *
 * @author User
 */
public class B3D_HeightmapLink extends B3D_Terrain
{

    private String path;

    /**
     * Only use when set() is called afterwards
     */
    public B3D_HeightmapLink()
    {
    }

    public B3D_HeightmapLink(String _name, String _path, B3D_Material _material, String _shadowMode)
    {
        setName(_name);
        setMaterial(_material);
        path = _path;
        setShadowMode(_shadowMode);
    }

    public String getPath()
    {
        return path;
    }

    public void setPath(String path)
    {
        this.path = path;
    }

    @Override
    public void set(B3D_Element e)
    {
        super.set(e);
        B3D_HeightmapLink t = (B3D_HeightmapLink) e;
        Wizard.copyValues(t, this, getClass());
    }
}
