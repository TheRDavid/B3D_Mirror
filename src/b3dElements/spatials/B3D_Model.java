package b3dElements.spatials;

public class B3D_Model extends B3D_Spatial
{

    private String path;

    public B3D_Model(String _name, String _path, String _shadowMode)
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
