/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package b3dElements.animations.keyframeAnimations;

/**
 *
 * @author User
 */
public enum InterpolationType
{

    Ease_In, Ease_Out, Linear, Keep;

    @Override
    public String toString()
    {
        return super.toString().replace('_', ' ');
    }

    public static InterpolationType valueOfName(String value)
    {
        return Enum.valueOf(InterpolationType.class, value.replace(' ', '_'));
    }
}
