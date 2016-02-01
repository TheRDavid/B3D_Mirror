/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package b3dElements.animations.keyframeAnimations;

import java.io.Serializable;

/**
 *
 * @author User
 */
public enum AnimationType implements Serializable
{

    Translation, Rotation, Scale, // Spatial
    Frozen, Particles_Per_Second;                                                //Particle Emitter

    public static AnimationType valueOfString(String s)
    {
        return AnimationType.valueOf(s.replaceAll(" ", "_"));
    }

    @Override
    public String toString()
    {
        return super.toString().replaceAll("_", " ");
    }
}
