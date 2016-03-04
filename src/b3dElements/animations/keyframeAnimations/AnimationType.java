/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package b3dElements.animations.keyframeAnimations;

import b3dElements.animations.keyframeAnimations.Properties.BoolProperty;
import b3dElements.animations.keyframeAnimations.Properties.ColorRGBAProperty;
import b3dElements.animations.keyframeAnimations.Properties.IntProperty;
import b3dElements.animations.keyframeAnimations.Properties.QuaternionProperty;
import b3dElements.animations.keyframeAnimations.Properties.Vector3fProperty;
import java.io.Serializable;

/**
 *
 * @author User
 */
public enum AnimationType implements Serializable
{
    //Light

    Position, // Vector3f
    Direction, // Vector3f
    Radius, // Float - not yet implemented!
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

    public boolean legit(Class c)
    {
        if (c.equals(BoolProperty.class))
            return this == Frozen 
                    || this == Emit_All;
        if (c.equals(ColorRGBAProperty.class))
            return this == Light_Color_Blend 
                    || this == Start_Color_Blend 
                    || this == End_Color_Blend;
        if (c.equals(IntProperty.class))
            return this == Particles_Per_Second;
        if (c.equals(QuaternionProperty.class))
            return this == Rotation;
        if (c.equals(Vector3fProperty.class))
            return this == Position
                    || this == Direction
                    || this == Translation
                    || this == Scale;
        return false;
    }
}
