package b3dElements.spatials;

import java.io.Serializable;

/**
 *
 * @author David
 */
public class B3D_Heightmap extends B3D_Spatial implements Serializable
{

    private String path;

    public B3D_Heightmap(String _name, String _path, String _shadowMode)
    {
        setName(_name);
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
