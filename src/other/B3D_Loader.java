package other;

import b3dElements.filters.B3D_Filter;
import b3dElements.lights.B3D_Light;
import b3dElements.spatials.B3D_Spatial;
import com.jme3.asset.AssetManager;
import com.jme3.asset.plugins.FileLocator;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.cinematic.events.MotionEvent;
import com.jme3.light.Light;
import com.jme3.post.Filter;
import com.jme3.post.FilterPostProcessor;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import java.io.File;
import java.util.Vector;

/**
 *
 * @author David
 */
public class B3D_Loader
{

    public static Vector<b3dElements.B3D_Element> loadAllElemets(B3D_Scene scene)
    {
        Wizard.cleanup();
        Vector<b3dElements.B3D_Element> all = new Vector<b3dElements.B3D_Element>();
        Vector spatials = new Vector();
        Vector others = new Vector();
        for (b3dElements.B3D_Element element : scene.getElements())
        {
            if (element instanceof B3D_Light)
            {
                all.add(element);
            } else if (element instanceof B3D_Spatial)
            {
                spatials.add(element);
            } else
            {
                others.add(element);
            }
        }
        all.addAll(spatials);
        all.addAll(others);
        return all;
    }

    public static Vector loadAllObjects(B3D_Scene scene)
    {
        Wizard.cleanup();
        Vector all = new Vector();
        Vector spatials = new Vector();
        Vector others = new Vector();
        for (b3dElements.B3D_Element element : scene.getElements())
        {
            if (element instanceof B3D_Light)
            {
                Object o = ElementToObjectConverter.convertToObject(element);
                Wizard.getObjects().add(o, element);
                all.add(o);
            } else if (element instanceof B3D_Spatial)
            {
                Object o = ElementToObjectConverter.convertToObject(element);
                Wizard.getObjects().add(o, element);
                all.add(o);
            } else
            {
                Object o = ElementToObjectConverter.convertToObject(element);
                Wizard.getObjects().add(o, element);
                all.add(o);
            }
        }
        all.addAll(spatials);
        all.addAll(others);
        return all;
    }

    /**
     * Builds the complete Level (highly magic)
     *
     * @param scene The loaded level
     * @param rootNode A Node that shall contain all the loaded Spatials of the
     * level
     * @param mainCamera The Camera the MotionsEvents (if there are any) shall
     * control
     * @param camNode The CameraNode the MotionEvents (if there are any) shall
     * control
     * @param bulletAppState The BulletAppState dealing with the physics
     * @param filterPostProcessor The Processor all the Filters (if there are
     * any) shall be added to
     * @param viewPort The Viewport (only interesting because of the
     * ViewPortColor)
     * @param assetManager The AssetManager the Textures, Models, etc shall be
     * loaded with
     * @param motionEvents A Vector that will contain all the MotionEvents (if
     * there are any)
     */
    public static void buildIntoApp(B3D_Scene scene, Node rootNode, Camera mainCamera, CameraNode camNode, BulletAppState bulletAppState,
            FilterPostProcessor filterPostProcessor, ViewPort viewPort, AssetManager assetManager, Vector<Light> lights, Vector<MotionEvent> motionEvents)
    {
        Vector<Spatial> allSpatials = new Vector<Spatial>();
        Wizard.cleanup();
        Wizard.setAssetManager(assetManager);
        Wizard.setCamera(mainCamera);
        Wizard.setCameraNode(camNode);
        Wizard.setSceneNode(rootNode);
        allocateAll(assetManager, new File("assets"));
        viewPort.setBackgroundColor(scene.getViewPortColor());
        bulletAppState.setSpeed(scene.getPhysicsSpeed());
        Vector<Filter> filters = new Vector<Filter>();
        for (b3dElements.B3D_Element element : scene.getElements())
        {
            if (element instanceof B3D_Light)
            {
                Object o = ElementToObjectConverter.convertToObject(element);
                Wizard.getObjects().add(o, element);
                System.out.println("Adding " + o);
                lights.add((Light) o);
                rootNode.addLight((Light) o);
            } else if (element instanceof B3D_Spatial && ((B3D_Spatial) element).getParentUUID().equals(Wizard.NULL_SELECTION))
            {
                Object o = ElementToObjectConverter.convertToObject(element);
                Wizard.getObjects().add(o, element);
                rootNode.attachChild((Spatial) o);
                if (((Spatial) o).getControl(RigidBodyControl.class) != null)
                    bulletAppState.getPhysicsSpace().add(o);
                if (o instanceof Node)
                {
                    Wizard.insertAllSpatials((Node) o, allSpatials);
                    for (Spatial s : allSpatials)
                        if (s.getControl(RigidBodyControl.class) != null)
                            bulletAppState.getPhysicsSpace().add(s);
                }
            } else
            {
                Object o = ElementToObjectConverter.convertToObject(element);
                Wizard.getObjects().add(o, element);
                if (o instanceof Filter)
                    filters.add(((B3D_Filter) element).getFilterIndex(), (Filter) o);
                else
                    motionEvents.add((MotionEvent) o);
            }
        }
        for (Filter f : filters)
        {
            filterPostProcessor.addFilter(f);
        }
        //Correct fuckups
        allSpatials.clear();
        Wizard.insertAllSpatials(rootNode, allSpatials);
        for (Spatial s : allSpatials)
        {
            if (s.getUserData("adjust") != null)
            {
                B3D_Spatial b3D_Spatial = (B3D_Spatial) Wizard.getObjects().getB3D_Element(Wizard.getObjectReferences().getUUID(s.hashCode()));
                s.setLocalTranslation(b3D_Spatial.getTranslation());
                s.getControl(RigidBodyControl.class).setPhysicsLocation(b3D_Spatial.getPhysics().getPhysicsLocation());
                s.getUserDataKeys().remove("adjust");
            }
        }
    }

    private static void allocateAll(AssetManager assetManager, File file)
    {
        assetManager.registerLocator(file.getAbsolutePath(), FileLocator.class);
        for (File f : file.listFiles())
        {
            if (f.isDirectory())
            {
                allocateAll(assetManager, f);
            }
        }
    }
}
