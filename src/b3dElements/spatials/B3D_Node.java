package b3dElements.spatials;

import b3dElements.B3D_Element;
import java.io.Serializable;
import java.util.Vector;
import other.Wizard;

public class B3D_Node extends B3D_Spatial implements Serializable
{

    private Vector<B3D_Spatial> children = new Vector<B3D_Spatial>();

    /**
     * Only use when set() is called afterwards
     */
    public B3D_Node()
    {
    }

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

    @Override
    public void set(B3D_Element e)
    {
        super.set(e);
        B3D_Node t = (B3D_Node) e;
        Wizard.copyValues(t, this, getClass());
    }
}
