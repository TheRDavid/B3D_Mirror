package other;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
public class Preferences implements Serializable
{

    private HashMap<Object, Object> preferences = new HashMap<>();
    private String path = "preferences";

    public void set(Object preferenceName, Object value)
    {
        preferences.put(preferenceName, value);
    }

    public Object get(Object preferenceName)
    {
        return preferences.get(preferenceName);
    }

    public void setFilePath(String path)
    {
        this.path = path;
    }

    public void save()
    {
        try
        {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(path)));
            oos.writeObject(this);
            oos.close();
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
}
