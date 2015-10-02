package b3dElements.filters;

import java.io.Serializable;

public class B3D_DepthOfField extends B3D_Filter implements Serializable
{

    private float blurScale, focusDistance, focusRange;

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
}
