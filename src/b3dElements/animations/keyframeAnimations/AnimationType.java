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
    //Light

    Light_Color_Blend, //ColorRGBA
    // Spatial
    Translation, //Vector3f
    Rotation, //Quaternion
    Scale, // Vector3f
    //Particle Emitter
    Frozen, //boolean
    Particles_Per_Second, //int 
    Emit_All, // boolean 
    Start_Color_Blend, // ColorRGBA 
    End_Color_Blend; // ColorRGBA 

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
