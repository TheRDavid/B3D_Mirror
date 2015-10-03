package other;

import b3dElements.animations.B3D_Animation;
import b3dElements.animations.B3D_AnimationCommand;
import b3dElements.animations.animationTypes.B3D_Anim_Call;
import b3dElements.animations.animationTypes.B3D_Anim_FireParticles;
import b3dElements.animations.animationTypes.B3D_Anim_Move;
import b3dElements.animations.animationTypes.B3D_Anim_PlayMotion;
import b3dElements.animations.animationTypes.B3D_Anim_Rotate;
import b3dElements.animations.animationTypes.B3D_Anim_Scale;
import com.jme3.light.PointLight;
import com.jme3.light.SpotLight;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.UUID;
import javax.swing.JOptionPane;

/**
 *
 * @author David
 */
public class AnimationTranslator
{

    public static String dec(B3D_AnimationCommand anim)
    {
        String dec = "";
        String type = null;
        if (anim.getClass().equals(B3D_Anim_Move.class))
        {
            type = "move";
        } else if (anim.getClass().equals(B3D_Anim_Rotate.class))
        {
            type = "rotate";
        } else if (anim.getClass().equals(B3D_Anim_Scale.class))
        {
            type = "scale";
        } else if (anim.getClass().equals(B3D_Anim_Call.class))
        {
            type = "call";
        } else if (anim.getClass().equals(B3D_Anim_FireParticles.class))
        {
            type = "fireParticles";
        } else if (anim.getClass().equals(B3D_Anim_PlayMotion.class))
        {
            type = "playMotion";
        }
        dec = type + " : ";
        String value = null;

        if (anim.getValue().getClass().equals(Vector3f.class))
        {
            Vector3f vector = (Vector3f) anim.getValue();
            value = vector.getX() + "," + vector.getY() + "," + vector.getZ();
        } else if (anim.getValue().getClass().equals(Float.class)
                || anim.getValue().getClass().equals(Integer.class)
                || anim.getValue().getClass().equals(Boolean.class)
                || anim.getValue().getClass().equals(String.class))
        {
            value = anim.getValue().toString();
        }
        if (anim.getClass().equals(B3D_Anim_Call.class))
        {
            B3D_Anim_Call callAnim = (B3D_Anim_Call) anim;
            value = callAnim.getCalledObject().toString() + " / " + callAnim.getAnimationName();
        }
        //Some types do not have a duration, they happen & are over in an instant
        if (!type.equals("call") && !type.equals("fireParticles") && !type.equals("playMotion"))
        {
            dec += value + " : " + anim.getDuration() * 1000 + " : " + anim.getStartTime() * 1000 + ":";
        } else
            dec += value + " : " + anim.getStartTime() * 1000 + ":";
        dec += anim.isExact() + ";";
        return dec;
    }

    private static B3D_AnimationCommand enc(String command, UUID object)
    {
        B3D_AnimationCommand enc = null;
        int start = 0;
        int lastDelim = command.indexOf(":");
        String type = command.substring(start, lastDelim);
        System.out.println("\t\t" + start + "->" + lastDelim + " Type: " + type);

        start = lastDelim;
        lastDelim = command.indexOf(":", start + 1);
        String value = command.substring(start + 1, lastDelim);
        System.out.println("\t\t" + start + "->" + lastDelim + " Values: " + value);
        String duration = null;
        String exact = "";

        //Some types do not have a duration, they happen & are over in an instant
        if (!type.equals("call") && !type.equals("fireParticles") && !type.equals("playMotion"))
        {
            start = lastDelim;
            lastDelim = command.indexOf(":", start + 1);
            duration = command.substring(start + 1, lastDelim);
            System.out.println("\t\t" + start + "->" + lastDelim + " Duration: " + duration);
        }

        start = lastDelim;
        lastDelim = command.indexOf(":", start + 1);
        String startTime = command.substring(start + 1, lastDelim);
        System.out.println("\t\t" + start + "->End StartTime: " + startTime);

        //exact?
        start = lastDelim;
        exact = command.substring(start + 1);

        Object actualObject = Wizard.getObjects().getOriginalObject(Wizard.getObjectReferences().getID(object));
        Object startValue = null;
        //animations.txt
        StringTokenizer valueTokenizer = new StringTokenizer(value, ",");
        //ms
        float durationMs = 1;
        if (duration != null)
            durationMs = Float.parseFloat(duration) / 1000;
        float startTimeMs = Float.parseFloat(startTime) / 1000;
        if (type.equals("move"))
        {
            float valX = Float.parseFloat(valueTokenizer.nextToken());
            float valY = Float.parseFloat(valueTokenizer.nextToken());
            float valZ = Float.parseFloat(valueTokenizer.nextToken());
            enc = new B3D_Anim_Move(object, new Vector3f(valX, valY, valZ), durationMs, startTimeMs, exact.toLowerCase().equals("true"));
        } else if (type.equals("rotate"))
        {
            float valX = Float.parseFloat(valueTokenizer.nextToken());
            float valY = Float.parseFloat(valueTokenizer.nextToken());
            float valZ = Float.parseFloat(valueTokenizer.nextToken());
            //too stupid for this
            enc = new B3D_Anim_Rotate(object, new Vector3f(valX, valY, valZ), durationMs, startTimeMs, exact.toLowerCase().equals("true"));
        } else if (type.equals("scale"))
        {
            if (value.contains(","))
            {
                float valX = Float.parseFloat(valueTokenizer.nextToken());
                float valY = Float.parseFloat(valueTokenizer.nextToken());
                float valZ = Float.parseFloat(valueTokenizer.nextToken());
                enc = new B3D_Anim_Scale(object, new Vector3f(valX, valY, valZ), durationMs, startTimeMs, exact.toLowerCase().equals("true"));
            } else
                enc = new B3D_Anim_Scale(object, Float.parseFloat(value), durationMs, startTimeMs, exact.toLowerCase().equals("true"));
        } else if (type.equals("call"))
        {
            String uuidString = value.substring(0, value.indexOf("/"));
            String animationName = value.substring(uuidString.length() + 1);
            enc = new B3D_Anim_Call(object, UUID.fromString(uuidString), animationName, startTimeMs);
        } else if (type.equals("fireParticles"))
            enc = new B3D_Anim_FireParticles(object, Boolean.parseBoolean(value), startTimeMs);
        else if (type.equals("playMotion"))
            enc = new B3D_Anim_PlayMotion(object, value, startTimeMs);
        return enc;
    }

    public static ArrayList<B3D_Animation> parseToLocalAnimations(String script, UUID object)
    {
        System.out.println("Before: \n" + script);
        ArrayList<B3D_Animation> animations = new ArrayList<B3D_Animation>();
        script = script.replaceAll(" ", "");
        script = script.replaceAll("\n", "");
        script = script.replaceAll("\t", "");
        System.out.println("After cleaning: " + script);
        StringTokenizer tokenizer = new StringTokenizer(script);
        while (tokenizer.hasMoreTokens())
        {
            String aniString = tokenizer.nextToken("#");

            StringTokenizer nTokenizer = new StringTokenizer(aniString, "{");
            String name = nTokenizer.nextToken("()");
            System.out.println("Name: " + name);
            B3D_Animation animation = new B3D_Animation(object, name);
            //Parameters
            String parameters = nTokenizer.nextToken("(");
            System.out.println("Params: " + parameters);
            if (!parameters.startsWith(")"))
            {
                StringTokenizer pTokenizer = new StringTokenizer(parameters, ",");
                String iterations = pTokenizer.nextToken();
                iterations = iterations.substring(0, iterations.indexOf(")"));
                System.out.println("iterations : " + iterations);
                if (iterations.equals("loop"))
                    animation.setIterations(B3D_Animation.LOOP);
                else
                    animation.setIterations(Integer.parseInt(iterations));
            }
            //Commands
            try
            {
                StringTokenizer cTokenizer = new StringTokenizer(aniString.substring(aniString.indexOf("{") + 1), ";");
                System.out.println("Commands: " + aniString.substring(aniString.indexOf("{")));
                while (cTokenizer.hasMoreTokens())
                {
                    //Skip the {
                    String command = cTokenizer.nextToken().substring(0);
                    if (!command.contains("}") && command.length() > 0)
                        try
                        {
                            System.out.println("\tReading command: " + command);
                            animation.getCommands().add(enc(command, object));
                        } catch (Exception e)
                        {
                            e.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Nope\n" + e, "Ehm...", JOptionPane.ERROR_MESSAGE);
                        }
                }
                animations.add(animation);
            } catch (NoSuchElementException nsee)
            {
                System.out.println("No commands in Animation!");
            }
        }
        return animations;
    }

    public static String translate(B3D_Animation animation)
    {
        String translation = "#" + animation.getName() + "(";
        if (animation.getIterations() != B3D_Animation.DEFAULT)
        {
            if (animation.getIterations() == B3D_Animation.LOOP)
                translation += "loop";
            else
                translation += animation.getIterations();
        }
        translation += ")";
        translation += "\n{";
        for (B3D_AnimationCommand command : animation.getCommands())
            translation += "\n\t" + dec(command);
        translation += "\n}\n";
        return translation;
    }
}
