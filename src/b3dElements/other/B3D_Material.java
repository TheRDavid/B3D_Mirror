package b3dElements.other;

import b3dElements.B3D_Element;
import java.io.Serializable;
import other.Wizard;

public class B3D_Material extends B3D_Element implements Serializable
{

    private B3D_MaterialPropertyList propertyList;
    private String defName;
    public static B3D_Material FILE_MATERIAL = new B3D_Material(null, null);

    public B3D_Material(String _defName, B3D_MaterialPropertyList _properties)
    {
        setName("Material");
        defName = _defName;
        if (_properties != null)
        {
            propertyList = _properties;
        } else
        {
            propertyList = new B3D_MaterialPropertyList();
        }
    }

    public B3D_MaterialPropertyList getPropertyList()
    {
        return propertyList;
    }

    public void setPropertyList(B3D_MaterialPropertyList _propertyList)
    {
        propertyList = _propertyList;
    }

    public String getDefName()
    {
        return defName;
    }

    public void setDefName(String dName)
    {
        defName = dName;
    }

    @Override
    public void set(B3D_Element e)
    {
        super.set(e);
        B3D_Material t = (B3D_Material) e;
        Wizard.copyValues(t, this, getClass());
    }
}
