/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monkeyStuff.keyframeAnimation;

import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;

/**
 *
 * @author User
 */
public class ConstraintControl extends AbstractControl
{

    protected Spatial influencer, dependant;

    public ConstraintControl(Spatial influencer, Spatial dependant)
    {
        this.influencer = influencer;
        this.dependant = dependant;
    }

    @Override
    protected void controlUpdate(float tpf)
    {
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp)
    {
    }
}
