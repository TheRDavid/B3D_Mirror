package b3dElements.spatials.geometries.particleEmitter;

import b3dElements.B3D_Element;
import b3dElements.spatials.geometries.B3D_Geometry;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import java.io.Serializable;
import other.Wizard;

public class B3D_ParticleEffect extends B3D_Geometry implements Serializable
{

    public enum Type
    {

        Point, Triangle
    };
    private B3D_StartShape startShape;
    private Vector3f gravity, directionVelocity, faceNormal;
    private float startSize, endSize, directionVariation, highLife, lowLife, rotateSpeed, particlesPerSecond;
    private int imgX, imgY, maxParticles;
    private ColorRGBA startColor, endColor;
    private boolean depthWrite, faceVelocity, firing, frozen;
    private Type type;
    private String textureName;

    /**
     * Only use when set() is called afterwards
     */
    public B3D_ParticleEffect()
    {
    }

    public B3D_ParticleEffect(String _name,
            String _textureName,
            B3D_StartShape _startShape,
            Vector3f _gravity,
            Vector3f _directionVelocity,
            float _startSize,
            float _endSize,
            ColorRGBA _startColor,
            ColorRGBA _endColor,
            float _directionVariation,
            float _highLife,
            float _lowLife,
            float _rotateSpeed,
            float _particlesPerSecond,
            int _imgX,
            int _imgY,
            int _maxParticles,
            boolean _depthWrite,
            boolean _faceVelocity,
            Vector3f _faceNormal,
            boolean _firing,
            boolean _frozen,
            Type _type,
            String _shadowMode)
    {
        firing = _firing;
        setName(_name);
        startShape = _startShape;
        textureName = _textureName;
        faceNormal = _faceNormal;
        faceVelocity = _faceVelocity;
        depthWrite = _depthWrite;
        gravity = _gravity;
        directionVelocity = _directionVelocity;
        startSize = _startSize;
        endSize = _endSize;
        startColor = _startColor;
        endColor = _endColor;
        directionVariation = _directionVariation;
        highLife = _highLife;
        lowLife = _lowLife;
        rotateSpeed = _rotateSpeed;
        imgX = _imgX;
        imgY = _imgY;
        maxParticles = _maxParticles;
        particlesPerSecond = _particlesPerSecond;
        type = _type;
        frozen = _frozen;
        setShadowMode(_shadowMode);
    }

    public boolean isFrozen()
    {
        return frozen;
    }

    public void setFrozen(boolean frozen)
    {
        this.frozen = frozen;
    }

    public String getTextureName()
    {
        return textureName;
    }

    public void setTextureName(String textureName)
    {
        this.textureName = textureName;
    }

    public Vector3f getGravity()
    {
        return gravity;
    }

    public void setGravity(Vector3f gravity)
    {
        this.gravity = gravity;
    }

    public Vector3f getDirectionVelocity()
    {
        return directionVelocity;
    }

    public void setDirectionVelocity(Vector3f directionVelocity)
    {
        this.directionVelocity = directionVelocity;
    }

    public float getStartSize()
    {
        return startSize;
    }

    public void setStartSize(float startSize)
    {
        this.startSize = startSize;
    }

    public float getEndSize()
    {
        return endSize;
    }

    public void setEndSize(float endSize)
    {
        this.endSize = endSize;
    }

    public float getDirectionVariation()
    {
        return directionVariation;
    }

    public void setDirectionVariation(float directionVariation)
    {
        this.directionVariation = directionVariation;
    }

    public float getHighLife()
    {
        return highLife;
    }

    public void setHighLife(float highLife)
    {
        this.highLife = highLife;
    }

    public float getLowLife()
    {
        return lowLife;
    }

    public void setLowLife(float lowLife)
    {
        this.lowLife = lowLife;
    }

    public float getRotateSpeed()
    {
        return rotateSpeed;
    }

    public void setRotateSpeed(float spinning)
    {
        this.rotateSpeed = spinning;
    }

    public int getImgX()
    {
        return imgX;
    }

    public void setImgX(int imgX)
    {
        this.imgX = imgX;
    }

    public int getImgY()
    {
        return imgY;
    }

    public void setImgY(int imgY)
    {
        this.imgY = imgY;
    }

    public int getMaxParticles()
    {
        return maxParticles;
    }

    public void setMaxParticles(int maxParticles)
    {
        this.maxParticles = maxParticles;
    }

    public float getParticlesPerSecond()
    {
        return particlesPerSecond;
    }

    public void setParticlesPerSecond(float particlesPerSecond)
    {
        this.particlesPerSecond = particlesPerSecond;
    }

    public Type getType()
    {
        return type;
    }

    public void setType(Type type)
    {
        this.type = type;
    }

    public ColorRGBA getStartColor()
    {
        return startColor;
    }

    public void setStartColor(ColorRGBA startColor)
    {
        this.startColor = startColor;
    }

    public ColorRGBA getEndColor()
    {
        return endColor;
    }

    public void setEndColor(ColorRGBA endColor)
    {
        this.endColor = endColor;
    }

    public boolean isDepthWrite()
    {
        return depthWrite;
    }

    public void setDepthWrite(boolean depthWrite)
    {
        this.depthWrite = depthWrite;
    }

    public Vector3f getFaceNormal()
    {
        return faceNormal;
    }

    public void setFaceNormal(Vector3f faceNormal)
    {
        this.faceNormal = faceNormal;
    }

    public boolean isFaceVelocity()
    {
        return faceVelocity;
    }

    public void setFaceVelocity(boolean faceVelocity)
    {
        this.faceVelocity = faceVelocity;
    }

    public B3D_StartShape getStartShape()
    {
        return startShape;
    }

    public void setStartShape(B3D_StartShape startShape)
    {
        this.startShape = startShape;
    }

    public boolean isFiring()
    {
        return firing;
    }

    public void setFiring(boolean firing)
    {
        this.firing = firing;
    }

    @Override
    public void set(B3D_Element e)
    {
        super.set(e);
        B3D_ParticleEffect b = (B3D_ParticleEffect) e;
        Wizard.copyValues(b, this, getClass());
    }
}
