package b3dElements.other;

import b3dElements.B3D_Element;
import b3dElements.physics.collisionShapes.B3D_BoxShape;
import com.jme3.cinematic.events.MotionEvent;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import java.io.Serializable;
import java.util.Vector;
import other.Wizard;

public class B3D_MotionPath extends B3D_Element implements Serializable
{

    private Vector<Vector3f> wayPoints = new Vector<Vector3f>();
    private float curveTension;
    private float speed;
    private ColorRGBA color;
    private boolean cycle;
    private Quaternion rotation;
    private DirectionType directionType;
    private Object lookAtObject = null;
    private LoopMode loopMode;

    public B3D_MotionPath(Object _lookAtObject, float _curveTension, float _speed, ColorRGBA _color, boolean _cycle, Quaternion _rotation, MotionEvent.Direction _directionType, com.jme3.animation.LoopMode _loopMode)
    {
        lookAtObject = _lookAtObject;
        this.curveTension = _curveTension;
        this.speed = _speed;
        this.color = _color;
        this.cycle = _cycle;
        this.rotation = _rotation;
        if (_directionType.equals(MotionEvent.Direction.LookAt))
        {
            this.directionType = DirectionType.LookAt;
        } else if (_directionType.equals(MotionEvent.Direction.None))
        {
            this.directionType = DirectionType.None;
        } else if (_directionType.equals(MotionEvent.Direction.Path))
        {
            this.directionType = DirectionType.Path;
        } else if (_directionType.equals(MotionEvent.Direction.PathAndRotation))
        {
            this.directionType = DirectionType.PathAndRotation;
        } else if (_directionType.equals(MotionEvent.Direction.Rotation))
        {
            this.directionType = DirectionType.Rotation;
        }
        if (_loopMode.equals(com.jme3.animation.LoopMode.Cycle))
        {
            this.loopMode = LoopMode.Cycle;
        } else if (_loopMode.equals(com.jme3.animation.LoopMode.DontLoop))
        {
            this.loopMode = LoopMode.DontLoop;
        } else if (_loopMode.equals(com.jme3.animation.LoopMode.Loop))
        {
            this.loopMode = LoopMode.Loop;
        }
    }

    public enum DirectionType
    {

        Path, Rotation, PathAndRotation, LookAt, None
    }

    public enum LoopMode
    {

        Loop, Cycle, DontLoop
    }

    public Vector<Vector3f> getWayPoints()
    {
        return wayPoints;
    }

    public void setWayPoints(Vector<Vector3f> wayPoints)
    {
        this.wayPoints = wayPoints;
    }

    public float getCurveTension()
    {
        return curveTension;
    }

    public void setCurveTension(float curveTension)
    {
        this.curveTension = curveTension;
    }

    public float getSpeed()
    {
        return speed;
    }

    public void setSpeed(float speed)
    {
        this.speed = speed;
    }

    public ColorRGBA getColor()
    {
        return color;
    }

    public void setColor(ColorRGBA color)
    {
        this.color = color;
    }

    public boolean isCycled()
    {
        return cycle;
    }

    public void setCycle(boolean cycle)
    {
        this.cycle = cycle;
    }

    public Quaternion getRotation()
    {
        return rotation;
    }

    public void setRotation(Quaternion rotation)
    {
        this.rotation = rotation;
    }

    public DirectionType getDirectionType()
    {
        return directionType;
    }

    public void setDirectionType(DirectionType directionType)
    {
        this.directionType = directionType;
    }

    public Object getLookAtObject()
    {
        return lookAtObject;
    }

    /**
     * Should be a UUID in most cases (not if camera)
     * @param lookAtObjectID 
     */
    public void setLookAtObject(Object lookAtObjectID)
    {
        this.lookAtObject = lookAtObjectID;
    }

    public LoopMode getLoopMode()
    {
        return loopMode;
    }

    public void setLoopMode(LoopMode loopMode)
    {
        this.loopMode = loopMode;
    }

    @Override
    public void set(B3D_Element e)
    {
        super.set(e);
        B3D_MotionPath b = (B3D_MotionPath) e;
        Wizard.copyValues(b, this, getClass());
    }
}
