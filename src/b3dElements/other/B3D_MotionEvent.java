package b3dElements.other;

import b3dElements.B3D_Element;
import b3dElements.spatials.B3D_Heightmap;
import java.io.Serializable;
import other.Wizard;

public class B3D_MotionEvent extends B3D_Element implements Serializable
{

    private Object objectProbablyUUID;
    private B3D_MotionPath motionPath;

    public enum Cam
    {

        CAM_ID
    };

    public B3D_MotionEvent(String _name, Object movingObject, B3D_MotionPath path)
    {
        setName(_name);
        objectProbablyUUID = movingObject;
        motionPath = path;
    }

    public Object getObjectProbablyUUID()
    {
        return objectProbablyUUID;
    }

    public void setObjectProbablyUUID(Object _objectProbablyUUID)
    {
        objectProbablyUUID = _objectProbablyUUID;
    }

    public B3D_MotionPath getMotionPath()
    {
        return motionPath;
    }

    public void setMotionPath(B3D_MotionPath motionPath)
    {
        this.motionPath = motionPath;
    }

    @Override
    public void set(B3D_Element e)
    {
        super.set(e);
        B3D_MotionEvent t = (B3D_MotionEvent) e;
        Wizard.copyValues(t, this, getClass());
    }
}
