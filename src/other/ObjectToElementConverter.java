package other;

import monkeyStuff.SpotLightShadowFilterWithGetters;
import monkeyStuff.DirectionalLightShadowFilterWithGetters;
import monkeyStuff.WaterFilterWithGetters;
import monkeyStuff.PointLightShadowFilterWithGetters;
import monkeyStuff.CustomParticleEmitter;
import monkeyStuff.ColorScaleFilterWithGetters;
import b3dElements.B3D_Element;
import b3dElements.animations.B3D_Animation;
import b3dElements.filters.B3D_BasicSSAO;
import b3dElements.filters.B3D_Bloom;
import b3dElements.filters.B3D_Cartoon;
import b3dElements.filters.B3D_ColorScale;
import b3dElements.filters.B3D_Crosshatch;
import b3dElements.filters.B3D_DepthOfField;
import b3dElements.filters.B3D_Filter;
import b3dElements.filters.B3D_Fog;
import b3dElements.filters.B3D_FrostedGlass;
import b3dElements.filters.B3D_LightScattering;
import b3dElements.filters.B3D_OldFilm;
import b3dElements.filters.B3D_Posterization;
import b3dElements.filters.B3D_SSAO;
import b3dElements.filters.B3D_Shadow;
import b3dElements.filters.B3D_Water;
import b3dElements.lights.B3D_AmbientLight;
import b3dElements.lights.B3D_DirectionalLight;
import b3dElements.lights.B3D_Light;
import b3dElements.lights.B3D_PointLight;
import b3dElements.lights.B3D_SpotLight;
import b3dElements.physics.collisionShapes.B3D_BoxShape;
import b3dElements.physics.collisionShapes.B3D_CShape;
import b3dElements.physics.collisionShapes.B3D_CapsuleShape;
import b3dElements.physics.collisionShapes.B3D_ConeShape;
import b3dElements.physics.collisionShapes.B3D_CylinderShape;
import b3dElements.physics.collisionShapes.B3D_DynamicMeshShape;
import b3dElements.physics.collisionShapes.B3D_StaticMeshShape;
import b3dElements.physics.B3D_Physics;
import b3dElements.spatials.geometries.B3D_Box;
import b3dElements.spatials.geometries.B3D_Cylinder;
import b3dElements.spatials.geometries.particleEmitter.B3D_ParticleEffect;
import b3dElements.spatials.geometries.B3D_MultipleTextureSkyBox;
import b3dElements.spatials.geometries.B3D_SingleTextureSkyBox;
import b3dElements.spatials.geometries.B3D_Sphere;
import b3dElements.spatials.geometries.B3D_Torus;
import b3dElements.spatials.geometries.particleEmitter.B3D_BoxStartShape;
import b3dElements.spatials.geometries.particleEmitter.B3D_PointStartShape;
import b3dElements.spatials.geometries.particleEmitter.B3D_SphereStartShape;
import b3dElements.spatials.geometries.particleEmitter.B3D_StartShape;
import b3dElements.spatials.B3D_Model;
import b3dElements.spatials.B3D_Node;
import b3dElements.spatials.B3D_Spatial;
import b3dElements.controls.B3D_LightControl;
import b3dElements.controls.B3D_LightScatteringMotionControl;
import b3dElements.other.B3D_Material;
import b3dElements.other.B3D_MaterialPropertyList;
import b3dElements.other.B3D_MotionEvent;
import b3dElements.other.B3D_MotionPath;
import b3dElements.spatials.B3D_Heightmap;
import b3dElements.spatials.geometries.B3D_HeightmapLink;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.collision.shapes.ConeCollisionShape;
import com.jme3.bullet.collision.shapes.CylinderCollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.cinematic.events.MotionEvent;
import com.jme3.effect.ParticleMesh;
import com.jme3.effect.shapes.EmitterBoxShape;
import com.jme3.effect.shapes.EmitterPointShape;
import com.jme3.effect.shapes.EmitterSphereShape;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.light.Light;
import com.jme3.light.PointLight;
import com.jme3.light.SpotLight;
import com.jme3.material.MatParam;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.math.Vector4f;
import com.jme3.post.Filter;
import com.jme3.post.filters.BloomFilter;
import com.jme3.post.filters.CartoonEdgeFilter;
import com.jme3.post.filters.CrossHatchFilter;
import com.jme3.post.filters.DepthOfFieldFilter;
import com.jme3.post.filters.FogFilter;
import com.jme3.post.filters.LightScatteringFilter;
import com.jme3.post.filters.PosterizationFilter;
import com.jme3.post.ssao.SSAOFilter;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.LightControl;
import com.jme3.scene.shape.Cylinder;
import com.jme3.scene.shape.Sphere;
import com.jme3.scene.shape.Torus;
import com.jme3.shader.VarType;
import com.jme3.shadow.SpotLightShadowFilter;
import com.jme3.terrain.geomipmap.TerrainQuad;
import com.shaderblow.filter.basicssao.BasicSSAO;
import com.shaderblow.filter.frostedglass.FrostedGlassFilter;
import com.shaderblow.filter.oldfilm.OldFilmFilter;
import com.simsilica.lemur.geom.MBox;
import java.util.ArrayList;
import java.util.UUID;
import java.util.Vector;
import monkeyStuff.LightScatteringMotionControl;

public class ObjectToElementConverter
{

    public static ConvertMode convertMode = ConvertMode.CREATING_TWIN;

    public enum ConvertMode
    {

        SAVING, CREATING_TWIN
    }

    public static B3D_Element convertToElement(Object object)
    {
        B3D_Element element = null;
        if (object instanceof Filter)
            //If there is no filterIndex as parameter, it is set to 0
            element = convertFilter((Filter) object, 0);
        else if (object instanceof Light)
            element = convertLight((Light) object);
        else if (object instanceof Spatial)
            element = convertSpatial((Spatial) object);
        else if (object instanceof MotionEvent)
            element = convertMotionEvent((MotionEvent) object);
        else if (object instanceof Material)
            element = convertMaterial((Material) object);
        return element;
    }

    public static B3D_Filter convertFilter(Filter filter, int filterIndex)
    {
        B3D_Filter b3D_Filter = null;
        if (filter instanceof BasicSSAO)
            b3D_Filter = convertBasicSSAO((BasicSSAO) filter, filterIndex);
        else if (filter instanceof BloomFilter)
            b3D_Filter = convertBloom((BloomFilter) filter, filterIndex);
        else if (filter instanceof CartoonEdgeFilter)
            b3D_Filter = convertCartoon((CartoonEdgeFilter) filter, filterIndex);
        else if (filter instanceof ColorScaleFilterWithGetters)
            b3D_Filter = convertColorScale((ColorScaleFilterWithGetters) filter, filterIndex);
        else if (filter instanceof CrossHatchFilter)
            b3D_Filter = convertCrosshatch((CrossHatchFilter) filter, filterIndex);
        else if (filter instanceof DepthOfFieldFilter)
            b3D_Filter = convertDOF((DepthOfFieldFilter) filter, filterIndex);
        else if (filter instanceof LightScatteringFilter)
            b3D_Filter = convertLightScattering((LightScatteringFilter) filter, filterIndex);
        else if (filter instanceof FogFilter)
            b3D_Filter = convertFog((FogFilter) filter, filterIndex);
        else if (filter instanceof FrostedGlassFilter)
            b3D_Filter = convertFrostedGlass((FrostedGlassFilter) filter, filterIndex);
        else if (filter instanceof OldFilmFilter)
            b3D_Filter = convertOldFilm((OldFilmFilter) filter, filterIndex);
        else if (filter instanceof PosterizationFilter)
            b3D_Filter = convertPosterization((PosterizationFilter) filter, filterIndex);
        else if (filter instanceof SSAOFilter)
            b3D_Filter = convertSSAO((SSAOFilter) filter, filterIndex);
        else if (filter instanceof WaterFilterWithGetters)
            b3D_Filter = convertWater((WaterFilterWithGetters) filter, filterIndex);
        else if (filter instanceof DirectionalLightShadowFilterWithGetters)
            b3D_Filter = convertDirectionalLightShadow((DirectionalLightShadowFilterWithGetters) filter, filterIndex);
        else if (filter instanceof PointLightShadowFilterWithGetters)
            b3D_Filter = convertPointLightShadow((PointLightShadowFilterWithGetters) filter, filterIndex);
        else if (filter instanceof SpotLightShadowFilterWithGetters)
            b3D_Filter = convertSpotLightShadow((SpotLightShadowFilterWithGetters) filter, filterIndex);
        else if (filter instanceof PointLightShadowFilterWithGetters)
            b3D_Filter = convertPointLightShadow((PointLightShadowFilterWithGetters) filter, filterIndex);
        else if (filter instanceof SpotLightShadowFilter)
            b3D_Filter = convertSpotLightShadow((SpotLightShadowFilterWithGetters) filter, filterIndex);
        b3D_Filter.setEnabled(filter.isEnabled());
        return b3D_Filter;
    }

    public static B3D_BasicSSAO convertBasicSSAO(BasicSSAO basicSSAO, int filterIndex)
    {
        return new B3D_BasicSSAO(
                basicSSAO.getName(),
                filterIndex,
                basicSSAO.getBias(),
                basicSSAO.getDetailBias(),
                basicSSAO.getDetailIntensity(),
                basicSSAO.getDetailSampleRadius(),
                basicSSAO.getDetailScale(),
                basicSSAO.getFalloffRate(),
                basicSSAO.getFalloffStartDistance(),
                basicSSAO.getIntensity(),
                basicSSAO.getSampleRadius(),
                basicSSAO.getScale(),
                basicSSAO.isSmoothMore(),
                basicSSAO.isUseAo(),
                basicSSAO.isUseOnlyAo(),
                basicSSAO.getUseDetailPass(),
                basicSSAO.getUseDistanceFalloff(),
                basicSSAO.isUseSmoothing());
    }

    public static B3D_Bloom convertBloom(BloomFilter bloomFilter, int filterIndex)
    {
        return new B3D_Bloom(
                bloomFilter.getName(),
                filterIndex,
                bloomFilter.getBloomIntensity(),
                bloomFilter.getBlurScale(),
                bloomFilter.getExposureCutOff(),
                bloomFilter.getExposurePower(),
                bloomFilter.getDownSamplingFactor());
    }

    public static B3D_Shadow convertDirectionalLightShadow(DirectionalLightShadowFilterWithGetters dlsf, int filterIndex)
    {
        UUID lightUUID = Wizard.getObjectReferences().getUUID(dlsf.getLight().hashCode());
        return new B3D_Shadow(
                dlsf.getName(),
                filterIndex,
                B3D_Shadow.LightType.Direction,
                dlsf.getShadowIntensity(),
                lightUUID,
                dlsf.isFlushQueues(),
                dlsf.getEdgeFilteringMode(),
                dlsf.getEdgesThickness(),
                dlsf.getShadowMapSize());
    }

    public static B3D_Shadow convertSpotLightShadow(SpotLightShadowFilterWithGetters slsf, int filterIndex)
    {
        UUID lightUUID = Wizard.getObjectReferences().getUUID(slsf.getLight().hashCode());
        return new B3D_Shadow(
                slsf.getName(),
                filterIndex,
                B3D_Shadow.LightType.Spot,
                slsf.getShadowIntensity(),
                lightUUID,
                slsf.isFlushQueues(),
                slsf.getEdgeFilteringMode(),
                slsf.getEdgesThickness(),
                slsf.getShadowMapSize());
    }

    public static B3D_Shadow convertPointLightShadow(PointLightShadowFilterWithGetters plsf, int filterIndex)
    {
        UUID lightUUID = Wizard.getObjectReferences().getUUID(plsf.getLight().hashCode());
        return new B3D_Shadow(
                plsf.getName(),
                filterIndex,
                B3D_Shadow.LightType.Point,
                plsf.getShadowIntensity(),
                lightUUID,
                plsf.isFlushQueues(),
                plsf.getEdgeFilteringMode(),
                plsf.getEdgesThickness(),
                plsf.getShadowMapSize());
    }

    public static B3D_Cartoon convertCartoon(CartoonEdgeFilter cartoonEdgeFilter, int filterIndex)
    {
        return new B3D_Cartoon(
                cartoonEdgeFilter.getName(),
                filterIndex,
                cartoonEdgeFilter.getEdgeColor(),
                cartoonEdgeFilter.getDepthSensitivity(),
                cartoonEdgeFilter.getDepthThreshold(),
                cartoonEdgeFilter.getEdgeIntensity(),
                cartoonEdgeFilter.getEdgeWidth(),
                cartoonEdgeFilter.getNormalSensitivity(),
                cartoonEdgeFilter.getNormalThreshold());
    }

    public static B3D_ColorScale convertColorScale(ColorScaleFilterWithGetters colorScaleFilter, int filterIndex)
    {
        return new B3D_ColorScale(
                colorScaleFilter.getName(),
                filterIndex,
                colorScaleFilter.getColorDensity(),
                colorScaleFilter.isMultiply(),
                colorScaleFilter.isOverlay(),
                new ColorRGBA(colorScaleFilter.getFilterColor()));
    }

    public static B3D_Crosshatch convertCrosshatch(CrossHatchFilter crossHatchFilter, int filterIndex)
    {
        return new B3D_Crosshatch(
                crossHatchFilter.getName(),
                filterIndex,
                crossHatchFilter.getLineColor(),
                crossHatchFilter.getPaperColor(),
                crossHatchFilter.getColorInfluenceLine(),
                crossHatchFilter.getColorInfluencePaper(),
                crossHatchFilter.getFillValue(),
                crossHatchFilter.getLineThickness(),
                crossHatchFilter.getLineDistance(),
                new Float[]
        {
            crossHatchFilter.getLuminance1(),
            crossHatchFilter.getLuminance2(),
            crossHatchFilter.getLuminance3(),
            crossHatchFilter.getLuminance4(),
            crossHatchFilter.getLuminance5()
        });
    }

    public static B3D_DepthOfField convertDOF(DepthOfFieldFilter depthOfFieldFilter, int filterIndex)
    {
        return new B3D_DepthOfField(
                depthOfFieldFilter.getName(),
                filterIndex,
                depthOfFieldFilter.getBlurScale(),
                depthOfFieldFilter.getFocusDistance(),
                depthOfFieldFilter.getFocusRange());
    }

    public static B3D_LightScattering convertLightScattering(LightScatteringFilter lightScatteringFilter, int filterIndex)
    {
        return new B3D_LightScattering(
                lightScatteringFilter.getName(),
                filterIndex,
                lightScatteringFilter.getBlurStart(),
                lightScatteringFilter.getBlurWidth(),
                lightScatteringFilter.getLightDensity(),
                lightScatteringFilter.getNbSamples(),
                new Vector3f(lightScatteringFilter.getLightPosition()));
    }

    public static B3D_Fog convertFog(FogFilter fogFilter, int filterIndex)
    {
        return new B3D_Fog(fogFilter.getName(), filterIndex, new ColorRGBA(fogFilter.getFogColor()), fogFilter.getFogDensity(), fogFilter.getFogDistance());
    }

    public static B3D_FrostedGlass convertFrostedGlass(FrostedGlassFilter frostedGlassFilter, int filterIndex)
    {
        return new B3D_FrostedGlass(frostedGlassFilter.getName(), filterIndex, frostedGlassFilter.getRandomFactor(), frostedGlassFilter.getRandomScale());
    }

    public static B3D_OldFilm convertOldFilm(OldFilmFilter oldFilmFilter, int filterIndex)
    {
        return new B3D_OldFilm(
                oldFilmFilter.getName(),
                filterIndex,
                new ColorRGBA(oldFilmFilter.getFilterColor()),
                oldFilmFilter.getColorDensity(),
                oldFilmFilter.getNoiseDensity(),
                oldFilmFilter.getScratchDensity(),
                oldFilmFilter.getVignettingValue());
    }

    public static B3D_Posterization convertPosterization(PosterizationFilter posterizationFilter, int filterIndex)
    {
        return new B3D_Posterization(
                posterizationFilter.getName(),
                filterIndex,
                posterizationFilter.getGamma(),
                posterizationFilter.getStrength(),
                posterizationFilter.getNumColors());
    }

    public static B3D_SSAO convertSSAO(SSAOFilter sSAOFilter, int filterIndex)
    {
        return new B3D_SSAO(
                sSAOFilter.getName(),
                filterIndex,
                sSAOFilter.getSampleRadius(),
                sSAOFilter.getBias(),
                sSAOFilter.getIntensity(),
                sSAOFilter.getScale());
    }

    public static B3D_Water convertWater(WaterFilterWithGetters waterFilter, int filterIndex)
    {
        String causticsTexture = null, foamTexture = null, heightTexture = null, normalTexture = null;
        if (waterFilter.getCausticsTexture() != null)
            causticsTexture = waterFilter.getCausticsTexture().getKey().getName();
        if (waterFilter.getFoamTexture() != null)
            foamTexture = waterFilter.getFoamTexture().getKey().getName();
        if (waterFilter.getHeightTexture() != null)
            heightTexture = waterFilter.getHeightTexture().getKey().getName();
        if (waterFilter.getNormalTexture() != null)
            normalTexture = waterFilter.getNormalTexture().getKey().getName();
        return new B3D_Water(
                waterFilter.getName(),
                filterIndex,
                waterFilter.getCausticsIntensity(),
                waterFilter.getFoamHardness(),
                waterFilter.getFoamIntensity(),
                waterFilter.getMaxAmplitude(),
                waterFilter.getNormalScale(),
                waterFilter.getReflectionDisplace(),
                waterFilter.getReflectionMapSize(),
                waterFilter.getRefractionStrength(),
                waterFilter.getShininess(),
                waterFilter.getShoreHardness(),
                waterFilter.getSpeed(),
                waterFilter.getSunScale(),
                waterFilter.getUnderWaterFogDistance(),
                waterFilter.getWaterHeight(),
                waterFilter.getWaterTransparency(),
                waterFilter.getWaveScale(),
                new Vector2f(waterFilter.getWindDirection()),
                new Vector3f(waterFilter.getColorExtinction()),
                new Vector3f(waterFilter.getFoamExistence()),
                new Vector3f(waterFilter.getLightDirection()),
                new ColorRGBA(waterFilter.getWaterColor()),
                new ColorRGBA(waterFilter.getDeepWaterColor()),
                new ColorRGBA(waterFilter.getLightColor()),
                waterFilter.isUseCaustics(),
                waterFilter.isUseFoam(),
                waterFilter.isUseHQShoreline(),
                waterFilter.isUseRefraction(),
                waterFilter.isUseRipples(),
                waterFilter.isUseSpecular(),
                causticsTexture,
                foamTexture,
                heightTexture,
                normalTexture);
    }

    public static B3D_Light convertLight(Light light)
    {
        if (light instanceof DirectionalLight)
            return convertDirectionalLight((DirectionalLight) light);
        else if (light instanceof AmbientLight)
            return convertAmbientLight((AmbientLight) light);
        else if (light instanceof PointLight)
            return convertPointLight((PointLight) light);
        else if (light instanceof SpotLight)
            return convertSpotLight((SpotLight) light);
        return null;
    }

    public static B3D_DirectionalLight convertDirectionalLight(DirectionalLight directionalLight)
    {
        return new B3D_DirectionalLight(directionalLight.getName(), new ColorRGBA(directionalLight.getColor()), new Vector3f(directionalLight.getDirection()));
    }

    public static B3D_AmbientLight convertAmbientLight(AmbientLight ambientLight)
    {
        return new B3D_AmbientLight(ambientLight.getName(), new ColorRGBA(ambientLight.getColor()));
    }

    public static B3D_PointLight convertPointLight(PointLight pointLight)
    {
        return new B3D_PointLight(pointLight.getName(), new ColorRGBA(pointLight.getColor()), new Vector3f(pointLight.getPosition()), pointLight.getRadius());
    }

    public static B3D_SpotLight convertSpotLight(SpotLight spotLight)
    {
        return new B3D_SpotLight(
                spotLight.getName(),
                new ColorRGBA(spotLight.getColor()),
                new Vector3f(spotLight.getPosition()),
                new Vector3f(spotLight.getDirection()),
                spotLight.getSpotInnerAngle(),
                spotLight.getSpotOuterAngle(),
                spotLight.getSpotRange());
    }

    public static B3D_Spatial convertSpatial(Spatial spatial)
    {
        System.out.println("Spatial: " + spatial);
        spatial.setUserData("autoSyncPhysicsToTransform", "y");
        B3D_Spatial b3D_Spatial = null;
        if (spatial.getUserData("modelName") != null)
            b3D_Spatial = convertModel(spatial);
        else if (spatial instanceof TerrainQuad)
            if (spatial.getUserData("heightmapLink") == null)
                b3D_Spatial = convertHeightmap((TerrainQuad) spatial);
            else
                b3D_Spatial = convertHeightmapLink((TerrainQuad) spatial);
        else if (spatial instanceof Node)
        {
            b3D_Spatial = convertNode((Node) spatial);
        } else if (spatial instanceof Geometry)
        {
            Geometry geometry = (Geometry) spatial;
            if (geometry.getMesh() instanceof MBox)
            {
                b3D_Spatial = convertBox(geometry);
            } else if (geometry.getMesh() instanceof Cylinder)
            {
                b3D_Spatial = convertCylinder(geometry);
            } else if (spatial.getUserData("north") != null)
            {
                if (spatial.getUserData("south") != null)
                    b3D_Spatial = convertMultipleTexturesSkyBox(geometry);
                else
                    b3D_Spatial = convertSingleTextureSkyBox(geometry);
            } else if (geometry.getMesh() instanceof Sphere)
                b3D_Spatial = convertSphere(geometry);
            else if (geometry.getMesh() instanceof Torus)
                b3D_Spatial = convertTorus(geometry);
            else if (geometry instanceof CustomParticleEmitter)
                b3D_Spatial = convertParticleEmitter((CustomParticleEmitter) geometry);
        }
        System.out.println("B3D_Spatial: " + b3D_Spatial);
        if (spatial.getParent() != null && Wizard.getObjectReferences().getUUID(spatial.getParent().hashCode()) != null)
            b3D_Spatial.setParentUUID((UUID) (Wizard.getObjectReferences().getUUID(spatial.getParent().hashCode())));
        for (String key : spatial.getUserDataKeys())
            if (!Wizard.getReservedUserData().contains(key))
                b3D_Spatial.getUserData().put(key, spatial.getUserData(key).toString());
        B3D_Spatial oldElement = (B3D_Spatial) Wizard.getObjects().getB3D_Element(Wizard.getObjectReferences().getUUID(spatial.hashCode()));
        if (oldElement != null && convertMode.equals(ConvertMode.SAVING))
        {
            b3D_Spatial.setUuid(oldElement.getUUID());
            b3D_Spatial.setAnimations((ArrayList<B3D_Animation>) oldElement.getAnimations().clone());
        }
        b3D_Spatial.setScale(new Vector3f(spatial.getLocalScale()));
        b3D_Spatial.setAngles(new Vector3f((Vector3f) spatial.getUserData("angles")));
        b3D_Spatial.setTranslation(new Vector3f(spatial.getLocalTranslation()));
        b3D_Spatial.setRotation(new Quaternion(spatial.getLocalRotation()));
        switch (spatial.getShadowMode())
        {
            case Cast:
                b3D_Spatial.setShadowMode("Cast");
                break;
            case Receive:
                b3D_Spatial.setShadowMode("Recieve");
                break;
            case CastAndReceive:
                b3D_Spatial.setShadowMode("Cast&Recieve");
                break;
            case Inherit:
                b3D_Spatial.setShadowMode("Inherit");
                break;
            default:
                b3D_Spatial.setShadowMode("Off");
                break;
        }
        for (int i = 0; i < spatial.getNumControls(); i++)
            if (spatial.getControl(i) instanceof RigidBodyControl)
                b3D_Spatial.setPhysics(convertPhysics(spatial, b3D_Spatial));
            else if (spatial.getControl(i) instanceof LightControl)
                b3D_Spatial.getControls().add(convertLightControl(b3D_Spatial, (LightControl) spatial.getControl(i)));
            else if (spatial.getControl(i) instanceof LightScatteringMotionControl)
                b3D_Spatial.getControls().add(convertLightScatteringMotionControl((LightScatteringMotionControl) spatial.getControl(i)));
        return b3D_Spatial;
    }

    public static B3D_LightScatteringMotionControl convertLightScatteringMotionControl(LightScatteringMotionControl control)
    {
        return new B3D_LightScatteringMotionControl(
                control.getFilterUUID(),
                Wizard.getObjects().getB3D_Element(
                Wizard.getObjectReferences().getUUID(
                control.getSpatial().hashCode())).getUUID(),
                control.isEnabled());
    }

    public static B3D_Node convertNode(Node node)
    {
        Vector<B3D_Spatial> b3D_Spatials = new Vector<B3D_Spatial>();
        for (Spatial spatial : node.getChildren())
            b3D_Spatials.add(convertSpatial(spatial));
        return new B3D_Node(node.getName(), b3D_Spatials, node.getShadowMode().toString(), node.getUserData("isBatched") != null);
    }

    private static B3D_Spatial convertHeightmapLink(TerrainQuad terrain)
    {
        B3D_Material b3D_Material = convertMaterial(terrain.getMaterial());
        return new B3D_HeightmapLink(terrain.getName(), (String) terrain.getUserData("heightmapLink"), b3D_Material, terrain.getShadowMode().toString());
    }

    private static B3D_Spatial convertHeightmap(TerrainQuad terrainQuad)
    {
        B3D_Material b3D_Material = convertMaterial(terrainQuad.getMaterial());
        return new B3D_Heightmap(terrainQuad.getName(), terrainQuad.getHeightMap(), terrainQuad.getTotalSize(), terrainQuad.getPatchSize(), b3D_Material, terrainQuad.getShadowMode().toString());
    }

    public static B3D_Box convertBox(Geometry geometry)
    {
        B3D_Material b3D_Material = convertMaterial(geometry.getMaterial());
        return new B3D_Box(
                geometry.getName(),
                b3D_Material,
                (Integer) geometry.getUserData("xSlices"),
                (Integer) geometry.getUserData("ySlices"),
                (Integer) geometry.getUserData("zSlices"),
                geometry.getShadowMode().toString());
    }

    public static B3D_Model convertModel(Spatial spatial)
    {
        if (spatial.getUserData("modelName") == null)
        {
            spatial.setUserData("modelName", "unknown");
        }
        return new B3D_Model(spatial.getName(), spatial.getUserData("modelName").toString(), spatial.getShadowMode().toString());
    }

    public static B3D_Cylinder convertCylinder(Geometry geometry)
    {
        return new B3D_Cylinder(
                geometry.getName(),
                convertMaterial(geometry.getMaterial()),
                ((Cylinder) geometry.getMesh()).getAxisSamples(),
                ((Cylinder) geometry.getMesh()).getRadialSamples(),
                ((Cylinder) geometry.getMesh()).getRadius(),
                ((Cylinder) geometry.getMesh()).getRadius2(),
                ((Cylinder) geometry.getMesh()).getHeight(),
                ((Cylinder) geometry.getMesh()).isClosed(),
                ((Cylinder) geometry.getMesh()).isInverted(),
                geometry.getShadowMode().toString());
    }

    public static B3D_ParticleEffect convertParticleEmitter(CustomParticleEmitter particleEmitter)
    {
        B3D_ParticleEffect.Type type;
        if (particleEmitter.getMeshType().equals(ParticleMesh.Type.Point))
            type = B3D_ParticleEffect.Type.Point;
        else
            type = B3D_ParticleEffect.Type.Triangle;
        B3D_StartShape startShape = null;
        if (particleEmitter.getShape() instanceof EmitterBoxShape)
            startShape = new B3D_BoxStartShape(
                    ((EmitterBoxShape) particleEmitter.getShape()).getMin(),
                    ((EmitterBoxShape) particleEmitter.getShape()).getLen().addLocal(((EmitterBoxShape) particleEmitter.getShape()).getMin()));
        else if (particleEmitter.getShape() instanceof EmitterSphereShape)
            startShape = new B3D_SphereStartShape(((EmitterSphereShape) particleEmitter.getShape()).getCenter(),
                    ((EmitterSphereShape) particleEmitter.getShape()).getRadius());
        else if (particleEmitter.getShape() instanceof EmitterPointShape)
            startShape = new B3D_PointStartShape(((EmitterPointShape) particleEmitter.getShape()).getPoint());
        Vector3f fn = null;
        if (particleEmitter.getFaceNormal() != null)
            fn = new Vector3f(particleEmitter.getFaceNormal());
        return new B3D_ParticleEffect(
                particleEmitter.getName(),
                particleEmitter.getMaterial().getParam("Texture").toString(),
                startShape,
                new Vector3f(particleEmitter.getGravity()),
                new Vector3f(particleEmitter.getParticleInfluencer().getInitialVelocity()),
                particleEmitter.getStartSize(),
                particleEmitter.getEndSize(),
                new ColorRGBA(particleEmitter.getStartColor()),
                new ColorRGBA(particleEmitter.getEndColor()),
                particleEmitter.getParticleInfluencer().getVelocityVariation(),
                particleEmitter.getHighLife(),
                particleEmitter.getLowLife(),
                particleEmitter.getRotateSpeed(),
                particleEmitter.getParticlesPerSec(),
                particleEmitter.getImagesX(),
                particleEmitter.getImagesY(),
                particleEmitter.getMaxNumParticles(),
                particleEmitter.getMaterial().getAdditionalRenderState().isDepthWrite(),
                particleEmitter.isFacingVelocity(), fn,
                particleEmitter.isFiring(),
                type,
                particleEmitter.getShadowMode().toString());
    }

    public static B3D_MultipleTextureSkyBox convertMultipleTexturesSkyBox(Spatial spatial)
    {
        return new B3D_MultipleTextureSkyBox(
                spatial.getName(),
                spatial.getUserData("north").toString(),
                spatial.getUserData("south").toString(),
                spatial.getUserData("west").toString(),
                spatial.getUserData("east").toString(),
                spatial.getUserData("top").toString(),
                spatial.getUserData("bottom").toString());
    }

    public static B3D_SingleTextureSkyBox convertSingleTextureSkyBox(Spatial spatial)
    {
        return new B3D_SingleTextureSkyBox(spatial.getName(), spatial.getUserData("north").toString());
    }

    public static B3D_Sphere convertSphere(Geometry geometry)
    {
        return new B3D_Sphere(
                geometry.getName(),
                convertMaterial(geometry.getMaterial()),
                ((Sphere) geometry.getMesh()).getRadialSamples(),
                ((Sphere) geometry.getMesh()).getZSamples(),
                ((Sphere) geometry.getMesh()).getRadius(),
                geometry.getShadowMode().toString());
    }

    public static B3D_Torus convertTorus(Geometry geometry)
    {
        return new B3D_Torus(
                geometry.getName(),
                convertMaterial(geometry.getMaterial()),
                ((Torus) geometry.getMesh()).getCircleSamples(),
                ((Torus) geometry.getMesh()).getRadialSamples(),
                ((Torus) geometry.getMesh()).getInnerRadius(),
                ((Torus) geometry.getMesh()).getOuterRadius(),
                geometry.getShadowMode().toString());
    }

    public static B3D_Material convertMaterial(Material material)
    {
        try
        {
            B3D_MaterialPropertyList b3D_MaterialPropertyList = new B3D_MaterialPropertyList();
            for (MatParam matParam : material.getParams())
            {
                if (!b3D_MaterialPropertyList.has(matParam.getName()))
                {
                    VarType varType = matParam.getVarType();
                    if (varType.equals(VarType.Boolean))
                    {
                        //System.out.println("PropertyList - Boolean: " + matParam.getName() + " -> " + matParam.getValueAsString());
                        b3D_MaterialPropertyList.add(matParam.getName(), "boolean", matParam.getValueAsString());
                    } else if (varType.equals(VarType.Vector4))
                        //ColorRGBA wird als Vector4 erkannt...
                        if (matParam.getValue() instanceof ColorRGBA)
                        {
                            ColorRGBA matColor = (ColorRGBA) matParam.getValue();
                            System.out.println("MAT COLOR: " + matColor);
                            b3D_MaterialPropertyList.add(matParam.getName(), "color", matColor.getRed() + ", " + matColor.getGreen() + ", " + matColor.getBlue() + ", " + matColor.getAlpha());
                        } else
                        {
                            Vector4f matVector = (Vector4f) matParam.getValue();
                            b3D_MaterialPropertyList.add(matParam.getName(), "vector4", "(" + matVector.getX() + ", " + matVector.getY() + ", " + matVector.getZ() + ", " + matVector.getW() + ")");
                        }
                    else if (varType.equals(VarType.Texture2D) || varType.equals(VarType.Texture3D))
                    {
                        //System.out.println("PropertyList - Texture: " + matParam.getName() + " -> " + matParam.getValueAsString());
                        String newValue = matParam.getValueAsString();
                        //System.out.println("PropertyList - Texture (second attempt): " + matParam.getName() + " -> " + newValue);
                        {
                            b3D_MaterialPropertyList.add(matParam.getName(), "texture", newValue);
                        }
                    } else if (varType.equals(VarType.Float))
                    {
                        //   System.out.println("PropertyList - Float: " + matParam.getName() + " -> " + matParam.getValueAsString());
                        b3D_MaterialPropertyList.add(matParam.getName(), "float", matParam.getValueAsString());
                    } else if (varType.equals(VarType.Int))
                    {
                        //   System.out.println("PropertyList - Int: " + matParam.getName() + " -> " + matParam.getValueAsString());
                        b3D_MaterialPropertyList.add(matParam.getName(), "int", matParam.getValueAsString());
                    } else if (varType.equals(VarType.Vector2))
                    {
                        //    System.out.println("PropertyList - Vector2: " + matParam.getName() + " -> " + matParam.getValueAsString());
                        Vector2f matVector = (Vector2f) matParam.getValue();
                        b3D_MaterialPropertyList.add(matParam.getName(), "vector2", "(" + matVector.getX() + ", " + matVector.getY() + ")");
                    } else if (varType.equals(VarType.Vector3))
                    {
                        Vector3f matVector = (Vector3f) matParam.getValue();
                        b3D_MaterialPropertyList.add(matParam.getName(), "vector3", "(" + matVector.getX() + ", " + matVector.getY() + ", " + matVector.getZ() + ")");
                    }
                }
            }
            return new B3D_Material(material.getMaterialDef().getAssetName(), b3D_MaterialPropertyList);
        } catch (UnsupportedOperationException uoe)
        {
            return B3D_Material.FILE_MATERIAL;
        }
    }

    public static B3D_Physics convertPhysics(Spatial spatial, B3D_Spatial b3D_Spatial)
    {
        B3D_CShape b3D_Shape = convertCollisionShape(spatial.getControl(RigidBodyControl.class).getCollisionShape(), spatial, b3D_Spatial);
        return new B3D_Physics(
                new Vector3f(spatial.getControl(RigidBodyControl.class).getPhysicsLocation()),
                new Quaternion(spatial.getControl(RigidBodyControl.class).getPhysicsRotation()),
                spatial.getControl(RigidBodyControl.class).getMass(),
                spatial.getControl(RigidBodyControl.class).getFriction(),
                spatial.getControl(RigidBodyControl.class).getRestitution(),
                b3D_Shape,
                spatial.getControl(RigidBodyControl.class).isKinematic(),
                spatial.getControl(RigidBodyControl.class).getCcdMotionThreshold());
    }

    public static B3D_CShape convertCollisionShape(CollisionShape collisionShape, Spatial spatial, B3D_Spatial b3D_Spatial)
    {
        if (collisionShape instanceof BoxCollisionShape)
            return new B3D_BoxShape(((BoxCollisionShape) collisionShape).getHalfExtents());
        else if (collisionShape instanceof CapsuleCollisionShape)
            return new B3D_CapsuleShape(((CapsuleCollisionShape) collisionShape).getRadius(), ((CapsuleCollisionShape) collisionShape).getHeight());
        else if (collisionShape instanceof ConeCollisionShape)
            return new B3D_ConeShape(((ConeCollisionShape) collisionShape).getRadius(), ((ConeCollisionShape) collisionShape).getHeight());
        else if (collisionShape instanceof CylinderCollisionShape)
            return new B3D_CylinderShape(((CylinderCollisionShape) collisionShape).getHalfExtents());
        else if (spatial.getUserData("cShape").equals("static"))
            return new B3D_StaticMeshShape(b3D_Spatial.getUUID());
        else if (spatial.getUserData("cShape").equals("dynamic"))
            return new B3D_DynamicMeshShape(b3D_Spatial.getUUID());
        return null;
    }

    public static B3D_Element convertMotionEvent(MotionEvent motionEvent)
    {
        B3D_MotionEvent b3D_MotionEvent;
        B3D_MotionPath b3D_MotionPath;
        Object movingObjectUUID;
        UUID b3d_UUID = Wizard.getObjectReferences().getUUID(motionEvent.hashCode());
        Object lookAtObject = ((B3D_MotionEvent) Wizard.getObjects().getB3D_Element(b3d_UUID)).getMotionPath().getLookAtObject();
        ColorRGBA color = ColorRGBA.Green;
        if (!(motionEvent.getSpatial() instanceof CameraNode))
        {
            int movingSpatialID = motionEvent.getSpatial().hashCode();
            movingObjectUUID = Wizard.getObjectReferences().getUUID(movingSpatialID);
        } else
            movingObjectUUID = B3D_MotionEvent.Cam.CAM_ID;
        if (lookAtObject != null)
            if (lookAtObject.equals("Camera"))
                color = ColorRGBA.Gray;
        Quaternion rot = null;
        if (motionEvent.getRotation() != null)
            rot = new Quaternion(motionEvent.getRotation());
        b3D_MotionPath = new B3D_MotionPath(
                lookAtObject,
                motionEvent.getPath().getCurveTension(),
                motionEvent.getSpeed(),
                color,
                motionEvent.getPath().isCycle(),
                rot,
                motionEvent.getDirectionType(),
                motionEvent.getLoopMode());
        for (int i = 0; i < motionEvent.getPath().getNbWayPoints(); i++)
            //If cycling, don't take the last waypoint
            if (!motionEvent.getPath().isCycle() || i < motionEvent.getPath().getNbWayPoints() - 1)
                b3D_MotionPath.getWayPoints().add(new Vector3f(motionEvent.getPath().getWayPoint(i)));
        UUID motionEventUUID = Wizard.getObjectReferences().getUUID(motionEvent.hashCode());
        b3D_MotionEvent = new B3D_MotionEvent(
                Wizard.getObjects().getB3D_Element(motionEventUUID).getName(),
                movingObjectUUID,
                b3D_MotionPath);
        return b3D_MotionEvent;
    }

    public static B3D_LightControl convertLightControl(B3D_Spatial b3D_Spatial, LightControl lightControl)
    {
        B3D_LightControl bdlc = null;
        Light l = lightControl.getLight();
        if (convertMode.equals(ConvertMode.CREATING_TWIN))
        {
            //Duplicating Light
            B3D_Light newB3D_Light = convertLight(l);
            Light newLight = ElementToObjectConverter.convertLight(newB3D_Light);
            Wizard.getObjects().add(newLight, newB3D_Light);
            Wizard.getSceneNode().addLight(newLight);
            //Creating LightControl
            UUID spatialUUID = b3D_Spatial.getUUID(), lightUUID = newB3D_Light.getUUID();
            bdlc = new B3D_LightControl(lightUUID, spatialUUID, lightControl.isEnabled());
        } else
        {
            bdlc = new B3D_LightControl(Wizard.getObjectReferences().getUUID(l.hashCode()), b3D_Spatial.getUUID(), lightControl.isEnabled());
        }
        return bdlc;
    }
}
