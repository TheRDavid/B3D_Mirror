package b3dElements.filters;

import java.io.Serializable;

public class B3D_Posterization extends B3D_Filter implements Serializable
{

    private float gamma, strength;
    private int numColors;

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
}
