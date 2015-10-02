/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monkeyStuff;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.post.Filter;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;

/**
 *
 * @author The guyz from shaderblow
 */
public class ColorScaleFilterWithGetters extends Filter
{

    private static final ColorRGBA DEAFULT_COLOR = ColorRGBA.Red.clone();
    private static final float DEFAULT_DENSITY = 0.7f;
    private ColorRGBA filterColor = null;
    private float colorDensity = 0f;
    private boolean overlay = false;
    private boolean multiply = false;
    private boolean init = false;

    public ColorScaleFilterWithGetters()
    {
        this(ColorScaleFilterWithGetters.DEAFULT_COLOR, ColorScaleFilterWithGetters.DEFAULT_DENSITY);
    }

    public ColorScaleFilterWithGetters(final ColorRGBA filterColor, final float colorDensity)
    {
        super("ColorScaleFilter");
        this.filterColor = filterColor;
        this.colorDensity = colorDensity;
    }

    @Override
    protected Material getMaterial()
    {
        return this.material;
    }

    public Material getTheMaterial()
    {
        return this.material;
    }

    public void setOverlay(boolean _overlay)
    {
        if(material!=null)
        this.material.setBoolean("Overlay", overlay);
        overlay = _overlay;
    }

    public void setMultiply(boolean _multiply)
    {
        if(material!=null)
        this.material.setBoolean("Multiply", _multiply);
        multiply = _multiply;
    }

    @Override
    protected void initFilter(final AssetManager manager, final RenderManager renderManager, final ViewPort vp,
            final int w, final int h)
    {
        this.material = new Material(manager, "ShaderBlow/MatDefs/Filters/ColorScale/ColorScale.j3md");
        init = true;
        this.material.setColor("FilterColor", this.filterColor);
        this.material.setFloat("ColorDensity", this.colorDensity);
        this.material.setBoolean("Overlay", this.overlay);
        this.material.setBoolean("Multiply", this.multiply);
    }

    public void setColorDensity(final float colorDensity)
    {
        if (this.material != null)
        {
            this.material.setFloat("ColorDensity", this.colorDensity);
            this.colorDensity = colorDensity;
        }
    }

    public float getColorDensity()
    {
        return this.colorDensity;
    }

    public void setFilterColor(final ColorRGBA filterColor)
    {
        if (this.material != null)
        {
            this.material.setColor("FilterColor", this.filterColor);
            this.filterColor = filterColor;
        }
    }

    public ColorRGBA getFilterColor()
    {
        return this.filterColor;
    }

    public boolean isOverlay()
    {
        return overlay;
    }

    public boolean isMultiply()
    {
        return multiply;
    }
}
