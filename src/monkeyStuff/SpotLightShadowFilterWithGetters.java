/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monkeyStuff;

import com.jme3.asset.AssetManager;
import com.jme3.shadow.SpotLightShadowFilter;
import java.util.UUID;

/**
 *
 * @author David
 */
public class SpotLightShadowFilterWithGetters extends SpotLightShadowFilter
{

    private int shadowMapSize;

    public SpotLightShadowFilterWithGetters(AssetManager assetManager, int mapSize)
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
