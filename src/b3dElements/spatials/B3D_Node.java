package b3dElements.spatials;

import java.io.Serializable;
import java.util.Vector;

public class B3D_Node extends B3D_Spatial implements Serializable
{

    private Vector<B3D_Spatial> children = new Vector<B3D_Spatial>();

    public B3D_Node(String _name, Vector<B3D_Spatial> _children, String _shadowMode, boolean _batched)
    {
        setName(_name);
        if (_children != null)
        {
            children = _children;
        } else
        {
            children = new Vector();
        }
        setShadowMode(_shadowMode);
    }

    public Vector<B3D_Spatial> getChildren()
    {
        return children;
    }
}
