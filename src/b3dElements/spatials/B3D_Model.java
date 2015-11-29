package b3dElements.spatials;

import b3dElements.B3D_Element;
import other.Wizard;

public class B3D_Model extends B3D_Spatial
{

    private String path;

    /**
     * Only use when set() is called afterwards
     */
    public B3D_Model()
    {
    }

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

    @Override
    public void set(B3D_Element e)
    {
        super.set(e);
        B3D_Model t = (B3D_Model) e;
        Wizard.copyValues(t, this, getClass());
    }
}
