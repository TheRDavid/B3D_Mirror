package b3dElements.filters;

import b3dElements.B3D_Element;
import java.io.Serializable;
import other.Wizard;

public class B3D_Posterization extends B3D_Filter implements Serializable
{

    private float gamma, strength;
    private int numColors;

    /**
     * Only use when set() is called afterwards
     */
    public B3D_Posterization()
    {
    }

    public B3D_Posterization(String _name, int _index, float _gamma, float _strength, int _numColors)
    {
        setName(_name);
        setFilterIndex(_index);
        gamma = _gamma;
        strength = _strength;
        numColors = _numColors;
    }

    public float getGamma()
    {
        return gamma;
    }

    public void setGamma(float gamma)
    {
        this.gamma = gamma;
    }

    public float getStrength()
    {
        return strength;
    }

    public void setStrength(float strength)
    {
        this.strength = strength;
    }

    public int getNumColors()
    {
        return numColors;
    }

    public void setNumColors(int numColors)
    {
        this.numColors = numColors;
    }

    @Override
    public void set(B3D_Element e)
    {
        super.set(e);
        B3D_Posterization a = (B3D_Posterization) e;
        Wizard.copyValues(a, this, getClass());
    }
}
