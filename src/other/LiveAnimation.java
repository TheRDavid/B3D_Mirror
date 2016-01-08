package other;

import b3dElements.animations.timedAnimations.B3D_TimedAnimation;

/**
 *
 * @author David
 */
public class LiveAnimation
{

    private B3D_TimedAnimation animation;
    private Object object;

    public LiveAnimation(B3D_TimedAnimation anim)
    {
        animation = anim;
        object = Wizard.getObjects().getOriginalObject(Wizard.getObjectReferences().getID(anim.getObject()));
    }

    public void update(float tpf)
    {
        animation.update(tpf, object);
    }

    public B3D_TimedAnimation getAnimation()
    {
        return animation;
    }
}
