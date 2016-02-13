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
    // Spatial

    Translation, //Vector3f
    Rotation, //Quaternion
    Scale, // Vector3f
    //Particle Emitter
    Particles_Frozen, //boolean
    Particles_Per_Second, //int 
    Particles_Emit_All, // boolean 
    Particles_Start_Color, // ColorRGBA 
    Particles_End_Color; // ColorRGBA 

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
