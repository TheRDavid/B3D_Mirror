package b3dElements.spatials;

import b3dElements.B3D_Element;
import b3dElements.controls.B3D_Control;
import b3dElements.physics.B3D_Physics;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;
import java.util.Vector;
import other.Wizard;

public class B3D_Spatial extends B3D_Element implements Serializable
{

    private B3D_Physics physics;
    private Vector3f translation;
    private Quaternion rotation;
    private Vector3f scale;
    private String shadowMode = "Cast&Recieve";
    private UUID parentUUID = Wizard.NULL_SELECTION;
    private Vector3f angles;
    private Vector<B3D_Control> controls = new Vector<B3D_Control>();
    private HashMap<String, String> userData = new HashMap<String, String>();

    public B3D_Physics getPhysics()
    {
        return physics;
    }

    public void setPhysics(B3D_Physics physics)
    {
        this.physics = physics;
    }

    public Vector3f getTranslation()
    {
        return translation;
    }

    public void setTranslation(Vector3f translation)
    {
        this.translation = translation;
    }

    public Quaternion getRotation()
    {
        return rotation;
    }

    public void setRotation(Quaternion rotation)
    {
        this.rotation = rotation;
    }

    public Vector3f getScale()
    {
        return scale;
    }

    public void setScale(Vector3f scale)
    {
        this.scale = scale;
    }

    public String getShadowMode()
    {
        return shadowMode;
    }

    public void setShadowMode(String shadowMode)
    {
        this.shadowMode = shadowMode;
    }

    public UUID getParentUUID()
    {
        return parentUUID;
    }

    public void setParentUUID(UUID _parentUUID)
    {
        if (_parentUUID == null)
        {
            _parentUUID = Wizard.NULL_SELECTION;
        }
        parentUUID = _parentUUID;
    }

    public Vector3f getAngles()
    {
        return angles;
    }

    public void setAngles(Vector3f angles)
    {
        this.angles = angles;
    }

    public Vector<B3D_Control> getControls()
    {
        return controls;
    }

    public HashMap<String, String> getUserData()
    {
        return userData;
    }
}
