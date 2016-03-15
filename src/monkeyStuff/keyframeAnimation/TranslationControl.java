/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monkeyStuff.keyframeAnimation;

import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;

/**
 *
 * @author User
 */
public class TranslationControl extends ConstraintControl
{

    private Vector3f lastTranslation;

    public TranslationControl(Spatial influencer, Spatial dependant)
    {
        super(influencer, dependant);
        lastTranslation = influencer.getWorldTranslation();
    }

    @Override
    protected void controlUpdate(float tpf)
    {
        System.out.println("MOVE " + dependant.getName() + " BY: " + influencer.getWorldTranslation() + " - " + lastTranslation + " = " + influencer.getLocalTranslation().subtract(lastTranslation));
        dependant.move(influencer.getWorldTranslation().subtract(lastTranslation));
        lastTranslation = new Vector3f(influencer.getWorldTranslation());
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp)
    {
//whatever
    }
}
