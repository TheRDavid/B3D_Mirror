/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monkeyStuff;

import com.jme3.asset.AssetManager;
import com.jme3.shadow.PointLightShadowFilter;
import java.util.UUID;

/**
 *
 * @author David
 */
public class PointLightShadowFilterWithGetters extends PointLightShadowFilter
{

    private int shadowMapSize;

    public PointLightShadowFilterWithGetters(AssetManager assetManager, int mapSize)
    {
        super(assetManager, mapSize);
        setFlushQueues(false);
        shadowMapSize = mapSize;
    }

    public int getShadowMapSize()
    {
        return shadowMapSize;
    }
}
