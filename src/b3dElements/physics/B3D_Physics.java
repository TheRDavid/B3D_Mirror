package b3dElements.physics;

import b3dElements.physics.collisionShapes.B3D_CShape;
import b3dElements.B3D_Element;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import java.io.Serializable;

public class B3D_Physics extends B3D_Element implements Serializable
{

    private float mass;
    private float friction;
    private float resitution;
    private float motionThreshold;
    private B3D_CShape cShape;
    private boolean kinematic;
    private Vector3f physicsLocation;
    private Quaternion physicsRotation;

    public B3D_Physics(
            Vector3f _physicsLocation,
            Quaternion _physicsRotation,
            float _mass,
            float _friction,
            float _restitution,
            B3D_CShape _cShape,
            boolean _kinematic,
            float _motionTreshold)
    {
        setName("Physics");
        physicsLocation = _physicsLocation;
        physicsRotation = _physicsRotation;
        kinematic = _kinematic;
        mass = _mass;
        resitution = _restitution;
        cShape = _cShape;
        friction = _friction;
        motionThreshold = _motionTreshold;
    }

    public boolean isKinematic()
    {
        return kinematic;
    }

    public void setKinematic(boolean kinematic)
    {
        this.kinematic = kinematic;
    }

    public float getMass()
    {
        return mass;
    }

    public void setMass(float mass)
    {
        this.mass = mass;
    }

    public float getResitution()
    {
        return resitution;
    }

    public void setResitution(float resitution)
    {
        this.resitution = resitution;
    }

    public B3D_CShape getcShape()
    {
        return cShape;
    }

    public void setcShape(B3D_CShape cShape)
    {
        this.cShape = cShape;
    }

    public Vector3f getPhysicsLocation()
    {
        return physicsLocation;
    }

    public void setPhysicsLocation(Vector3f physicsLocation)
    {
        this.physicsLocation = physicsLocation;
    }

    public Quaternion getPhysicsRotation()
    {
        return physicsRotation;
    }

    public void setPhysicsRotation(Quaternion physicsRotation)
    {
        this.physicsRotation = physicsRotation;
    }

    public float getFriction()
    {
        return friction;
    }

    public void setFriction(float friction)
    {
        this.friction = friction;
    }

    public float getMotionThreshold()
    {
        return motionThreshold;
    }

    public void setMotionThreshold(float motionThreshold)
    {
        this.motionThreshold = motionThreshold;
    }
}
