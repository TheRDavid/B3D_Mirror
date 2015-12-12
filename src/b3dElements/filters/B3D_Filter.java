package b3dElements.filters;

import b3dElements.B3D_Element;
import java.io.Serializable;
import other.Wizard;

public abstract class B3D_Filter extends b3dElements.B3D_Element implements Serializable
{

    private int filterIndex;
    private boolean enabled = true;

    public int getFilterIndex()
    {
        return filterIndex;
    }

    public void changeFilterIndex(int filterIndex)
    {
        this.filterIndex = filterIndex;
    }

    public void indexUp()
    {
        //ikr
        filterIndex--;
    }

    public void indexDown()
    {
        filterIndex++;
    }

    public boolean isEnabled()
    {
        return enabled;
    }

    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }

    @Override
    public void set(B3D_Element e)
    {
        super.set(e);
        B3D_Filter a = (B3D_Filter) e;
        Wizard.copyValues(a, this, B3D_Filter.class);
    }
}
