package b3dElements.filters;

import b3dElements.B3D_Element;
import com.jme3.math.Vector3f;
import java.io.Serializable;
import other.Wizard;

public class B3D_LightScattering extends B3D_Filter implements Serializable
{

    private int samples;
    private float blurStart, blurWidth, density;
    private Vector3f position;

    /**
     * Only use when set() is called afterwards
     */
    public B3D_LightScattering()
    {
    }

    public B3D_LightScattering(String _name, int _index, float _blurStart, float _blurWidth, float _density, int _samples, Vector3f _position)
    {
        setName(_name);
        setFilterIndex(_index);
        blurStart = _blurStart;
        density = _density;
        blurWidth = _blurWidth;
        samples = _samples;
        position = _position;
    }

    public float getBlurStart()
    {
        return blurStart;
    }

    public void setBlurStart(float blurStart)
    {
        this.blurStart = blurStart;
    }

    public float getBlurWidth()
    {
        return blurWidth;
    }

    public void setBlurWidth(float blurWidth)
    {
        this.blurWidth = blurWidth;
    }

    public float getDensity()
    {
        return density;
    }

    public void setDensity(float density)
    {
        this.density = density;
    }

    public int getSamples()
    {
        return samples;
    }

    public void setSamples(int samples)
    {
        this.samples = samples;
    }

    public Vector3f getPosition()
    {
        return position;
    }

    public void setPosition(Vector3f position)
    {
        this.position = position;
    }

    @Override
    public void set(B3D_Element e)
    {
        super.set(e);
        B3D_LightScattering a = (B3D_LightScattering) e;
        Wizard.copyValues(a, this, getClass());
    }
}
