package b3dElements.animations.keyframeAnimations;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public class B3D_KeyframeProperty<E extends Serializable> implements Serializable
{

    protected E[] values;
    public AnimationType type;
    private ArrayList<Integer> indices = new ArrayList<>();
    protected InterpolationType[] interpolationTypes;

    public B3D_KeyframeProperty(AnimationType type, E[] vals, InterpolationType[] iTypes) throws Exception
    {
        if (vals.length < 2)
            throw new Exception("At least 2 franmes requiered!");
        values = vals;
        interpolationTypes = iTypes;
        this.type = type;
    }

    public E[] getValues()
    {
        return values;
    }

    public ArrayList<Integer> getIndices()
    {
        return indices;
    }

    public void setIndices(ArrayList<Integer> indices)
    {
        this.indices = indices;
    }

    public InterpolationType[] getInterpolationTypes()
    {
        return interpolationTypes;
    }

    public void setInterpolationTypes(InterpolationType[] interpolationTypes)
    {
        this.interpolationTypes = interpolationTypes;
    }
}
