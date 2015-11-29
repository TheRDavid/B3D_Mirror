package b3dElements.other;

import b3dElements.B3D_Element;
import java.io.Serializable;
import java.util.HashMap;
import other.Wizard;

public class B3D_MaterialPropertyList extends B3D_Element implements Serializable
{

    private HashMap<String, Property> properties = new HashMap<String, Property>();

    public B3D_MaterialPropertyList()
    {
    }

    public void add(String propertyName, String propertyType, String value)
    {
        properties.put(propertyName, new Property(propertyType, value));
    }

    public void change(String propertyName, String value)
    {
        properties.get(propertyName).setPropertyValue(value);
    }

    public void remove(String propertyName)
    {
        properties.remove(propertyName);
    }

    public boolean has(String propertyName)
    {
        return properties.containsKey(propertyName);
    }

    public Property getProperty(String propertyName)
    {
        return properties.get(propertyName);
    }

    public HashMap<String, Property> getProperties()
    {
        return properties;
    }

    public class Property implements Serializable
    {

        private String propertyType;
        private String propertyValue;

        public Property(String type, String value)
        {
            propertyType = type;
            propertyValue = value;
        }

        public String getPropertyType()
        {
            return propertyType;
        }

        public void setPropertyType(String propertyType)
        {
            this.propertyType = propertyType;
        }

        public String getPropertyValue()
        {
            return propertyValue;
        }

        public void setPropertyValue(String propertyValue)
        {
            this.propertyValue = propertyValue;
        }
    }

    @Override
    public void set(B3D_Element e)
    {
        super.set(e);
        B3D_MaterialPropertyList t = (B3D_MaterialPropertyList) e;
        Wizard.copyValues(t, this, getClass());
    }
}
