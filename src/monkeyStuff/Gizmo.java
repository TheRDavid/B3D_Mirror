package monkeyStuff;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import java.util.Vector;
import other.Wizard;

/**
 *
 * @author David
 */
public class Gizmo extends Node
{

    private Node xArrow;
    private Node yArrow;
    private Node zArrow;
    private Material xArrowMaterial;
    private Material yArrowMaterial;
    private Material zArrowMaterial;
    private Node selectedArrow = null;
    private float incFactor = 1.5f;

    public enum Arrow
    {

        X, Y, Z
    }

    public Gizmo(AssetManager asm)
    {
        setName("Arrows");
        xArrow = (Node) asm.loadModel("Models/arrowNew.j3o");
        xArrow.setName("xArrow");
        xArrow.rotate(0, FastMath.DEG_TO_RAD * 180, FastMath.DEG_TO_RAD * 90);
        xArrow.setQueueBucket(RenderQueue.Bucket.Transparent);
        yArrow = (Node) asm.loadModel("Models/arrowNew.j3o");
        yArrow.setName("yArrow");
        yArrow.setQueueBucket(RenderQueue.Bucket.Transparent);
        zArrow = (Node) asm.loadModel("Models/arrowNew.j3o");
        zArrow.setName("zArrow");
        zArrow.rotate(FastMath.DEG_TO_RAD * 90, 0, 0);
        zArrow.setQueueBucket(RenderQueue.Bucket.Transparent);
        xArrowMaterial = new Material(asm, "Common/MatDefs/Misc/Unshaded.j3md");
        xArrowMaterial.getAdditionalRenderState().setBlendMode(RenderState.BlendMode.Alpha);
        xArrowMaterial.getAdditionalRenderState().setDepthTest(true);
        xArrowMaterial.setColor("Color", new ColorRGBA(1, 0, 0, .3f));
        yArrowMaterial = new Material(asm, "Common/MatDefs/Misc/Unshaded.j3md");
        yArrowMaterial.getAdditionalRenderState().setBlendMode(RenderState.BlendMode.Alpha);
        yArrowMaterial.getAdditionalRenderState().setDepthTest(true);
        yArrowMaterial.setColor("Color", new ColorRGBA(0, 0, 1, .3f));
        zArrowMaterial = new Material(asm, "Common/MatDefs/Misc/Unshaded.j3md");
        zArrowMaterial.getAdditionalRenderState().setBlendMode(RenderState.BlendMode.Alpha);
        zArrowMaterial.getAdditionalRenderState().setDepthTest(true);
        zArrowMaterial.setColor("Color", new ColorRGBA(0, 1, 0, .3f));
        xArrow.setMaterial(xArrowMaterial);
        yArrow.setMaterial(yArrowMaterial);
        zArrow.setMaterial(zArrowMaterial);
        xArrow.setShadowMode(RenderQueue.ShadowMode.Off);
        yArrow.setShadowMode(RenderQueue.ShadowMode.Off);
        zArrow.setShadowMode(RenderQueue.ShadowMode.Off);
        attachChild(xArrow);
        attachChild(yArrow);
        attachChild(zArrow);
    }

    public Node getxArrow()
    {
        return xArrow;
    }

    public Node getyArrow()
    {
        return yArrow;
    }

    public Node getzArrow()
    {
        return zArrow;
    }

    public Node getSelectedArrow()
    {
        return selectedArrow;
    }

    public Material getyArrowMaterial()
    {
        return yArrowMaterial;
    }

    public float getIncFactor()
    {
        return incFactor;
    }

    public void setIncFactor(float incFactor)
    {
        this.incFactor = incFactor;
    }

    public void select(Arrow a)
    {
        selectedArrow = null;
        xArrow.setLocalScale(1);
        yArrow.setLocalScale(1);
        zArrow.setLocalScale(1);
        if (a != null)
            switch (a)
            {
                case X:
                    selectedArrow = xArrow;
                    xArrow.setLocalScale(incFactor);
                    break;
                case Y:
                    selectedArrow = yArrow;
                    yArrow.setLocalScale(incFactor);
                    break;
                case Z:
                    selectedArrow = zArrow;
                    zArrow.setLocalScale(incFactor);
                    break;
            }
    }
}
