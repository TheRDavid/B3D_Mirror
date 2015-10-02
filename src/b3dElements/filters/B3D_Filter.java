package b3dElements.filters;

import java.io.Serializable;

public class B3D_Filter extends b3dElements.B3D_Element implements Serializable
{

    private int filterIndex;
    private boolean enabled = true;

    public int getFilterIndex()
    {
        return filterIndex;
    }

    public void setFilterIndex(int filterIndex)
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
}
