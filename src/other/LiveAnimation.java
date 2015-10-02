package other;

import b3dElements.animations.B3D_Animation;

/**
 *
 * @author David
 */
public class LiveAnimation
{

    private B3D_Animation animation;
    private Object object;

    public LiveAnimation(B3D_Animation anim)
    {
        animation = anim;
        object = Wizard.getObjects().getOriginalObject(Wizard.getObjectReferences().getID(anim.getObject()));
    }

    public void update(float tpf)
    {
        animation.update(tpf, object);
    }

    public B3D_Animation getAnimation()
    {
        return animation;
    }
}
