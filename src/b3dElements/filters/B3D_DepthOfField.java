package b3dElements.filters;

import b3dElements.B3D_Element;
import java.io.Serializable;
import other.Wizard;

public class B3D_DepthOfField extends B3D_Filter implements Serializable
{

    private float blurScale, focusDistance, focusRange;

    /**
     * Only use when set() is called afterwards
     */
    public B3D_DepthOfField()
    {
    }

    public B3D_DepthOfField(String _name, int _index, float _blurScale, float _focusDistance, float _focusRange)
    {
        setName(_name);
        setFilterIndex(_index);
        blurScale = _blurScale;
        focusDistance = _focusDistance;
        focusRange = _focusRange;
    }

    public float getBlurScale()
    {
        return blurScale;
    }

    public void setBlurScale(float blurScale)
    {
        this.blurScale = blurScale;
    }

    public float getFocusDistance()
    {
        return focusDistance;
    }

    public void setFocusDistance(float focusDistance)
    {
        this.focusDistance = focusDistance;
    }

    public float getFocusRange()
    {
        return focusRange;
    }

    public void setFocusRange(float focusRange)
    {
        this.focusRange = focusRange;
    }

    @Override
    public void set(B3D_Element e)
    {
        super.set(e);
        B3D_DepthOfField a = (B3D_DepthOfField) e;
        Wizard.copyValues(a, this, getClass());
    }
}
