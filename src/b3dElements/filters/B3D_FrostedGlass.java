package b3dElements.filters;

import java.io.Serializable;

public class B3D_FrostedGlass extends B3D_Filter implements Serializable
{

    private float randomFactor, randomScale;

    public B3D_FrostedGlass(String _name, int _index, float _randomFactor, float _randomScale)
    {
        setName(_name);
        setFilterIndex(_index);
        randomFactor = _randomFactor;
        randomScale = _randomScale;
    }

    public float getRandomFactor()
    {
        return randomFactor;
    }

    public void setRandomFactor(float randomFactor)
    {
        this.randomFactor = randomFactor;
    }

    public float getRandomScale()
    {
        return randomScale;
    }

    public void setRandomScale(float randomScale)
    {
        this.randomScale = randomScale;
    }
}
