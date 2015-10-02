/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monkeyStuff;

import com.jme3.asset.AssetManager;
import com.jme3.shadow.DirectionalLightShadowFilter;

/**
 *
 * @author David
 */
public class DirectionalLightShadowFilterWithGetters extends DirectionalLightShadowFilter
{

    private int shadowMapSize;

    public DirectionalLightShadowFilterWithGetters(AssetManager assetManager, int mapSize, int nbSplits)
    {
        super(assetManager, mapSize, nbSplits);
        setFlushQueues(false);
        shadowMapSize = mapSize;
    }

    public int getShadowMapSize()
    {
        return shadowMapSize;
    }
}
