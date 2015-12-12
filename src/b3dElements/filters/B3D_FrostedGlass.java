package b3dElements.filters;

import b3dElements.B3D_Element;
import java.io.Serializable;
import other.Wizard;

public class B3D_FrostedGlass extends B3D_Filter implements Serializable
{

    private float randomFactor, randomScale;

    /**
     * Only use when set() is called afterwards
     */
    public B3D_FrostedGlass()
    {
    }

    public B3D_FrostedGlass(String _name, int _index, float _randomFactor, float _randomScale)
    {
        setName(_name);
        changeFilterIndex(_index);
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

    @Override
    public void set(B3D_Element e)
    {
        super.set(e);
        B3D_FrostedGlass a = (B3D_FrostedGlass) e;
        Wizard.copyValues(a, this, getClass());
    }
}
