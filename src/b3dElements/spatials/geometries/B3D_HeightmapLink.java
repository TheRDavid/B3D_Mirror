/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package b3dElements.spatials.geometries;

import b3dElements.other.B3D_Material;
import b3dElements.spatials.B3D_Terrain;

/**
 *
 * @author User
 */
public class B3D_HeightmapLink extends B3D_Terrain
{
    
    private String path;

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
}
