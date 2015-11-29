package other;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
import com.jme3.cinematic.MotionPath;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.post.Filter;
import com.jme3.renderer.Camera;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.Control;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Wizard
{

    private static Objects objects = new Objects();
    private static References objectReferences = new References();
    private static AssetManager assetManager;
    private static Camera camera;
    private static CameraNode cameraNode;
    private static Node sceneNode;
    private static ArrayList<String> reservedUserData = new ArrayList<String>(Arrays.asList(
            "ID", "angles", "correctedTranslation", "scale", "cShape", "modelName", "assetName", "autoSyncPhysicsToTransform", "ORIGINAL_PATH", "ORIGINAL_NAME",
            "xSlices", "ySlices", "zSlices", "north", "south", "west", "east", "top", "bottom", "motionEventName", "motionEventLookAtName", "modelChild", "heightmapLink"));
    public static UUID NULL_SELECTION = UUID.fromString("46b16a0d-a605-448b-862c-25ed3957df94");
    public final static ColorRGBA defaultViewportBackground = new ColorRGBA(0.11f, 0.112f, 0.11f, 1);
    //Custom Animations
    private static ArrayList<LiveAnimation> activeAnimations = new ArrayList<LiveAnimation>();
    private static SimpleApplication app;

    public synchronized static void updateCustomAnimations(float tpf)
    {
        ArrayList<LiveAnimation> noLongerActiveAnimations = new ArrayList<LiveAnimation>();
        for (LiveAnimation activeAnimation : activeAnimations)
        {
            if (activeAnimation.getAnimation().isPlaying())
            {
                activeAnimation.update(tpf);
            } else
            {
                noLongerActiveAnimations.add(activeAnimation);
            }
        }
        for (LiveAnimation toRemove : noLongerActiveAnimations)
        {
            activeAnimations.remove(toRemove);
        }
        noLongerActiveAnimations.clear();
    }

    public static ArrayList<LiveAnimation> getActiveAnimations()
    {
        return activeAnimations;
    }

    public static void cleanup()
    {
        objects = new Objects();
        objectReferences = new References();
        camera = null;
        sceneNode = null;
    }

    public static Object loadFile(String absolutePath)
    {
        File f = new File(absolutePath);
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try
        {
            fis = new FileInputStream(f);
        } catch (FileNotFoundException ex)
        {
            JOptionPane.showMessageDialog(null, "Loading the file\n" + absolutePath + "\nlead to a terrible crash :)\n" + ex, "Whops", JOptionPane.ERROR_MESSAGE);
        }
        try
        {
            ois = new ObjectInputStream(fis);
        } catch (IOException ex)
        {
            JOptionPane.showMessageDialog(null, "Streaming object in File\n" + absolutePath + "\nlead to a terrible crash :)\n" + ex, "Whops", JOptionPane.ERROR_MESSAGE);
        }
        try
        {
            Object o = ois.readObject();
            try
            {
                fis.close();
                ois.close();
                return o;
            } catch (IOException ex)
            {
                JOptionPane.showMessageDialog(null, "Now that is not nice\n" + ex, "Whops", JOptionPane.ERROR_MESSAGE);
            }
            return ois.readObject();
        } catch (IOException ex)
        {
            JOptionPane.showMessageDialog(null, "Reading the object in File \n" + absolutePath + "\nlead to a terrible crash :)\n" + ex, "Whops", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex)
        {
            JOptionPane.showMessageDialog(null, "Reading the object in File \n" + absolutePath + "\nlead to a terrible crash :)\n" + ex, "Whops", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public static void saveFile(String filePath, Object object)
    {
        File f = new File(filePath);
        System.out.println("SAVING AT " + filePath);
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try
        {
            fos = new FileOutputStream(f);
        } catch (FileNotFoundException ex)
        {
            JOptionPane.showMessageDialog(null, "Writing the object in File \n" + filePath + "\nlead to a terrible crash :)\n" + ex, "Whops", JOptionPane.ERROR_MESSAGE);
        }
        try
        {
            oos = new ObjectOutputStream(fos);
            oos.writeObject(object);
            fos.close();
            oos.close();
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    public static void completelyCopyValues(Object copyFrom, Object insertIn)
    {
        completelyCopyValues(copyFrom, insertIn, copyFrom.getClass());
    }

    public static void copyValues(Object copyFrom, Object insertIn)
    {
        copyValues(copyFrom, insertIn, copyFrom.getClass());
    }

    public static void completelyCopyValues(Object copyFrom, Object insertIn, Class c)
    {
        if (c.equals(Object.class))
            return;
        System.out.println("Copy comp from " + c);
        if (!copyFrom.getClass().equals(insertIn.getClass()))
        {
            System.err.println("SET FAILED: " + copyFrom + " -> " + insertIn);
            return;
        }
        if (c.equals(Spatial.class))
            copySpatialValues((Spatial) copyFrom, (Spatial) insertIn);
        else
            copyValues(copyFrom, insertIn, c);
        completelyCopyValues(copyFrom, insertIn, c.getSuperclass());
    }

    public static void copySpatialValues(final Spatial copyFrom, final Spatial insertIn)
    {
        app.enqueue(new Callable<Void>()
        {
            @Override
            public Void call() throws Exception
            {
                for (String key : copyFrom.getUserDataKeys())
                    insertIn.setUserData(key, copyFrom.getUserData(key));
                insertIn.setLocalScale(new Vector3f(copyFrom.getLocalScale()));
                insertIn.setName(copyFrom.getName());
                insertIn.setLocalTranslation(new Vector3f(copyFrom.getLocalTranslation()));
                insertIn.setLocalRotation(new Quaternion(copyFrom.getLocalRotation()));
                if (copyFrom.getShadowMode().equals(RenderQueue.ShadowMode.Cast))
                    insertIn.setShadowMode(RenderQueue.ShadowMode.Cast);
                else if (copyFrom.getShadowMode().equals(RenderQueue.ShadowMode.CastAndReceive))
                    insertIn.setShadowMode(RenderQueue.ShadowMode.CastAndReceive);
                else if (copyFrom.getShadowMode().equals(RenderQueue.ShadowMode.Inherit))
                    insertIn.setShadowMode(RenderQueue.ShadowMode.Inherit);
                else if (copyFrom.getShadowMode().equals(RenderQueue.ShadowMode.Off))
                    insertIn.setShadowMode(RenderQueue.ShadowMode.Off);
                else
                    insertIn.setShadowMode(RenderQueue.ShadowMode.Receive);
                for (int i = 0; i < copyFrom.getNumControls(); i++)
                {
                    try
                    {
                        Control ac = copyFrom.getControl(i).getClass().newInstance();
                        copyValues(ac, copyFrom.getControl(i));
                        insertIn.addControl(ac);
                    } catch (InstantiationException ex)
                    {
                        ex.printStackTrace();
                    } catch (IllegalAccessException ex)
                    {
                        ex.printStackTrace();
                    }

                }
                return null;
            }
        });
    }
    private static Object ii, cf;

    public static void copyValues(Object copyFrom, Object insertIn, final Class c)
    {
        System.out.println("Copy from " + c);
        if (!copyFrom.getClass().equals(insertIn.getClass()))
        {
            System.err.println("SET FAILED: " + copyFrom + " -> " + insertIn);
            return;
        }
        ii = insertIn;
        cf = copyFrom;
        System.out.println("ii: " + ii + "\ncf: " + cf + "\nc: " + c.getName());
        /*app.enqueue(new Callable<Void>()
         {
         @Override
         public Void call() throws Exception
         {*/
        for (Field f : c.getDeclaredFields())
            try
            {
                f.setAccessible(true);
                if (!(ii instanceof Filter))
                {
                    System.out.println("Setting " + f.getName() + " from " + f.get(ii) + " to " + f.get(cf));
                    f.set(ii, f.get(cf));
                }
                if (ii instanceof Filter)
                {
                    Filter fii = (Filter) ii;
                    String method = "set" + Character.toUpperCase(f.getName().charAt(0)) + f.getName().substring(1);
                    try
                    {
                        if (f.get(cf) != null)
                            c.getMethod(method, f.getType()).invoke(ii, f.get(cf));
                    } catch (NoSuchMethodException ex)
                    {
                        System.out.println("Could not invoke " + method + " (nsme)");
                    } catch (SecurityException ex)
                    {
                        System.out.println("Could not invoke " + method + " (se)");
                    } catch (InvocationTargetException ex)
                    {
                        System.out.println("Could not invoke " + method + " (ite)");
                    }
                }
            } catch (SecurityException ex)
            {
                ex.printStackTrace();
            } catch (IllegalArgumentException ex)
            {
                ex.printStackTrace();
            } catch (IllegalAccessException ex)
            {
                System.out.println("Can not access " + f.getName());
            }
        /* return null;
         }
         });*/
    }

    public static HashMap readMat(File f)
    {
        String zeile;
        BufferedReader in;
        HashMap</*Value, Type*/String, String> hashMap = new HashMap<String, String>();
        try
        {
            in = new BufferedReader(new FileReader(f));
            zeile = null;
            boolean kE = false;
            while ((zeile = in.readLine()) != null)
                if (!zeile.contains("{") && !zeile.contains("//") && !kE && !(zeile.trim().length() == 0))
                {
                    if (zeile.contains("}"))
                        kE = true;
                    else
                    {
                        zeile = zeile.trim();
                        StringTokenizer st = new StringTokenizer(zeile, " ");
                        String fToken = st.nextToken();
                        String sToken = st.nextToken();
                        hashMap.put(sToken, fToken);
                    }
                }
        } catch (FileNotFoundException ex)
        {
            Logger.getLogger(Wizard.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex)
        {
            Logger.getLogger(Wizard.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return hashMap;
    }

    /**
     * @param colorRGBA
     * @return a Swing-Color
     */
    public static Color makeColor(ColorRGBA colorRGBA)
    {
        try
        {
            int r = (int) (colorRGBA.r * 255);
            int g = (int) (colorRGBA.g * 255);
            int b = (int) (colorRGBA.b * 255);
            int a = (int) (colorRGBA.a * 255);
            return new Color(r, g, b, a);
        } catch (java.lang.IllegalArgumentException iae)
        {
            System.out.println("Color fucked up!");
            return Color.BLACK;
        }
    }

    /**
     * @param color
     * @return a Monkey-Color
     */
    public static ColorRGBA makeColorRGBA(Color color)
    {
        float red = color.getRed();
        float green = color.getGreen();
        float blue = color.getBlue();
        float alpha = color.getAlpha();
        return new ColorRGBA(red / 255f, green / 255f, blue / 255f, alpha / 255f);
    }

    public static ImageIcon resizeImage(Image originalImage, int w, int h, boolean keepRelation)
    {
        int width = w;
        int height = h;
        if (keepRelation)
            if (w < h)
                height = width;
            else
                width = height;
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();
        ImageIcon icon = new ImageIcon(resizedImage);
        return icon;
    }

    public static ImageIcon resizeImage(Image originalImage, int w, int h, boolean keepRelation, int imgType)
    {
        int width = w;
        int height = h;
        if (keepRelation)
            if (w < h)
                height = width;
            else
                width = height;
        BufferedImage resizedImage = new BufferedImage(width, height, imgType);
        g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();
        ImageIcon icon = new ImageIcon(resizedImage);
        return icon;
    }
    private static Graphics2D g;
    private static BufferedImage resizedImage;

    public static Image resizeImageI(Image originalImage, int w, int h)
    {
        resizedImage = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);
        g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, w, h, null);
        g.dispose();
        return resizedImage;
    }

    public static BufferedImage resizeImageI(BufferedImage originalImage, int w, int h, int type)
    {
        resizedImage = new BufferedImage(w, h, type);
        g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, w, h, null);
        g.dispose();
        return resizedImage;
    }

    public static void setWireframe(Spatial spatial, boolean wireframeEnabled)
    {
        if (!spatial.getName().equals("Light Scattering Node")
                && !spatial.getName().equals("Light Node"))
        {
            if (spatial instanceof Node)
            {
                Node node = (Node) spatial;
                for (int i = 0; i < node.getQuantity(); i++)
                {
                    Spatial child = node.getChild(i);
                    setWireframe(child, wireframeEnabled);
                }
            } else if (spatial instanceof Geometry)
            {
                Geometry geo = (Geometry) spatial;
                geo.getMaterial().getAdditionalRenderState().setWireframe(wireframeEnabled);
            }
        }
    }

    public static void insertAllGeometrys(Node node, Vector<Geometry> geometrys)
    {
        for (Spatial child : node.getChildren())
            if (child.getUserData("modelChild") == null)
                if (child instanceof Node)
                    insertAllGeometrys((Node) child, geometrys);
                else if (child instanceof Geometry)
                    geometrys.add((Geometry) child);
    }

    public static void insertAllSpatials(Node node, Vector<Spatial> spatials)
    {
        for (Spatial child : node.getChildren())
            if (child.getUserData("modelChild") == null)
            {
                spatials.add(child);
                if (child instanceof Node)
                    insertAllSpatials((Node) child, spatials);
            }
    }

    /**
     * Set the AssetManager that is supposed to be used
     */
    public static void setAssetManager(AssetManager _assetManager)
    {
        assetManager = _assetManager;
    }

    /**
     * Set the Camera that is supposed to be used (for MotionEvents for
     * instance)
     */
    public static void setCamera(Camera _camera)
    {
        camera = _camera;
    }

    public static AssetManager getAssetManager()
    {
        return assetManager;
    }

    public static Camera getCamera()
    {
        return camera;
    }

    public static CameraNode getCameraNode()
    {
        return cameraNode;
    }

    public static SimpleApplication getApp()
    {
        return app;
    }

    public static void setApp(SimpleApplication app)
    {
        Wizard.app = app;
    }

    public static void setCameraNode(CameraNode cameraNode)
    {
        Wizard.cameraNode = cameraNode;
    }

    public static float calculateDistance(MotionPath motionPath, int waypointsDone)
    {
        float distance = 0;
        if (waypointsDone == motionPath.getNbWayPoints())
        {
            return distance;
        } else
        {
            for (int i = waypointsDone; i < motionPath.getNbWayPoints() - 1; i++)
            {
                distance += motionPath.getWayPoint(i).distance(motionPath.getWayPoint(i + 1));
            }
            return distance;
        }
    }

    public static Node getSceneNode()
    {
        return sceneNode;
    }

    public static void setSceneNode(Node sceneNode)
    {
        Wizard.sceneNode = sceneNode;
    }

    public static ArrayList<String> getReservedUserData()
    {
        return reservedUserData;
    }

    public static Objects getObjects()
    {
        return objects;
    }

    public static References getObjectReferences()
    {
        return objectReferences;
    }
}