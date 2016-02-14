package other;

import monkeyStuff.SpotLightShadowFilterWithGetters;
import monkeyStuff.DirectionalLightShadowFilterWithGetters;
import monkeyStuff.WaterFilterWithGetters;
import monkeyStuff.PointLightShadowFilterWithGetters;
import monkeyStuff.CustomParticleEmitter;
import monkeyStuff.ColorScaleFilterWithGetters;
import b3dElements.B3D_Element;
import b3dElements.animations.keyframeAnimations.AnimationType;
import b3dElements.animations.keyframeAnimations.B3D_KeyframeAnimation;
import b3dElements.animations.keyframeAnimations.B3D_KeyframeProperty;
import b3dElements.animations.keyframeAnimations.B3D_KeyframeUpdater;
import b3dElements.animations.keyframeAnimations.Properties.BoolProperty;
import b3dElements.animations.keyframeAnimations.Properties.ColorRGBAProperty;
import b3dElements.animations.keyframeAnimations.Properties.IntProperty;
import b3dElements.animations.keyframeAnimations.Properties.QuaternionProperty;
import b3dElements.animations.keyframeAnimations.Properties.Vector3fProperty;
import b3dElements.controls.B3D_Control;
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
import static b3dElements.filters.B3D_Shadow.LightType.Point;
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
import com.jme3.animation.LoopMode;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.collision.shapes.ConeCollisionShape;
import com.jme3.bullet.collision.shapes.CylinderCollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.cinematic.MotionPath;
import com.jme3.cinematic.events.MotionEvent;
import com.jme3.effect.ParticleMesh;
import com.jme3.effect.shapes.EmitterBoxShape;
import com.jme3.effect.shapes.EmitterPointShape;
import com.jme3.effect.shapes.EmitterShape;
import com.jme3.effect.shapes.EmitterSphereShape;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.light.Light;
import com.jme3.light.PointLight;
import com.jme3.light.SpotLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Spline;
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
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;
import com.jme3.scene.control.LightControl;
import com.jme3.scene.shape.Cylinder;
import com.jme3.scene.shape.Sphere;
import com.jme3.scene.shape.Torus;
import static com.jme3.shadow.EdgeFilteringMode.Bilinear;
import static com.jme3.shadow.EdgeFilteringMode.Dither;
import static com.jme3.shadow.EdgeFilteringMode.Nearest;
import static com.jme3.shadow.EdgeFilteringMode.PCF4;
import static com.jme3.shadow.EdgeFilteringMode.PCF8;
import static com.jme3.shadow.EdgeFilteringMode.PCFPOISSON;
import com.jme3.shadow.SpotLightShadowFilter;
import com.jme3.terrain.geomipmap.TerrainQuad;
import com.jme3.terrain.heightmap.ImageBasedHeightMap;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture2D;
import com.jme3.util.SkyFactory;
import com.shaderblow.filter.basicssao.BasicSSAO;
import com.shaderblow.filter.frostedglass.FrostedGlassFilter;
import com.shaderblow.filter.oldfilm.OldFilmFilter;
import com.simsilica.lemur.geom.MBox;
import java.util.ArrayList;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import monkeyStuff.LightScatteringMotionControl;
import monkeyStuff.keyframeAnimation.LiveKeyframeAnimation;
import monkeyStuff.keyframeAnimation.LiveKeyframeProperty;
import monkeyStuff.keyframeAnimation.LiveKeyframeUpdater;
import monkeyStuff.keyframeAnimation.Updaters.LiveLightUpdater;
import monkeyStuff.keyframeAnimation.Updaters.LiveParticleEmitterUpdater;
import monkeyStuff.keyframeAnimation.Updaters.LiveSpatialUpdater;

public class ElementToObjectConverter
{

    public static Object convertToObject(B3D_Element element)
    {
        Object object = null;
        if (element instanceof B3D_Filter)
        {
            object = convertFilter((B3D_Filter) element);
        } else if (element instanceof B3D_Light)
        {
            object = convertLight((B3D_Light) element);
        } else if (element instanceof B3D_Spatial)
        {
            object = convertSpatial((B3D_Spatial) element);
        } else if (element instanceof B3D_MotionEvent)
        {
            object = convertMotionEvent((B3D_MotionEvent) element);
        } else if (element instanceof B3D_Material)
        {
            object = convertMaterial((B3D_Material) element);
        } else if (element instanceof B3D_KeyframeAnimation)
        {
            object = convertKeyframeAnimation((B3D_KeyframeAnimation) element);
        }
        return object;
    }

    public static Filter convertFilter(B3D_Filter b3D_Filter)
    {
        Filter filter = null;
        if (b3D_Filter instanceof B3D_BasicSSAO)
        {
            filter = convertBasicSSAO((B3D_BasicSSAO) b3D_Filter);
        }
        if (b3D_Filter instanceof B3D_Bloom)
        {
            filter = convertBloom((B3D_Bloom) b3D_Filter);
        } else if (b3D_Filter instanceof B3D_Cartoon)
        {
            filter = convertCartoon((B3D_Cartoon) b3D_Filter);
        } else if (b3D_Filter instanceof B3D_ColorScale)
        {
            filter = convertColorScale((B3D_ColorScale) b3D_Filter);
        } else if (b3D_Filter instanceof B3D_Crosshatch)
        {
            filter = convertCrosshatch((B3D_Crosshatch) b3D_Filter);
        } else if (b3D_Filter instanceof B3D_DepthOfField)
        {
            filter = convertDOF((B3D_DepthOfField) b3D_Filter);
        } else if (b3D_Filter instanceof B3D_LightScattering)
        {
            filter = convertLightScattering((B3D_LightScattering) b3D_Filter);
        } else if (b3D_Filter instanceof B3D_Fog)
        {
            filter = convertFog((B3D_Fog) b3D_Filter);
        } else if (b3D_Filter instanceof B3D_FrostedGlass)
        {
            filter = convertFrostedGlass((B3D_FrostedGlass) b3D_Filter);
        } else if (b3D_Filter instanceof B3D_OldFilm)
        {
            filter = convertOldFilm((B3D_OldFilm) b3D_Filter);
        } else if (b3D_Filter instanceof B3D_Posterization)
        {
            filter = convertPosterization((B3D_Posterization) b3D_Filter);
        } else if (b3D_Filter instanceof B3D_SSAO)
        {
            filter = convertSSAO((B3D_SSAO) b3D_Filter);
        } else if (b3D_Filter instanceof B3D_Water)
        {
            filter = convertWater((B3D_Water) b3D_Filter);
        } else if (b3D_Filter instanceof B3D_Shadow)
        {
            filter = convertShadowFilter((B3D_Shadow) b3D_Filter);
        }
        filter.setEnabled(b3D_Filter.isEnabled());
        return filter;
    }

    public static Filter convertShadowFilter(B3D_Shadow b3D_Shadow)
    {
        switch (b3D_Shadow.getLightType())
        {
            case Direction:
                return convertDirectionalLightShadow(b3D_Shadow);
            case Point:
                return convertPointLightShadow(b3D_Shadow);
            case Spot:
                return convertSpotLightShadow(b3D_Shadow);
        }
        return null;
    }

    public static DirectionalLightShadowFilterWithGetters convertDirectionalLightShadow(B3D_Shadow b3D_Shadow)
    {
        DirectionalLightShadowFilterWithGetters dlsf = new DirectionalLightShadowFilterWithGetters(Wizard.getAssetManager(), b3D_Shadow.getShadowMapSize(), 4);
        switch (b3D_Shadow.getEdgeFilteringMode())
        {
            case Bilinear:
                dlsf.setEdgeFilteringMode(Bilinear);
                break;
            case Dither:
                dlsf.setEdgeFilteringMode(Dither);
                break;
            case Nearest:
                dlsf.setEdgeFilteringMode(Nearest);
                break;
            case PCF4:
                dlsf.setEdgeFilteringMode(PCF4);
                break;
            case PCF8:
                dlsf.setEdgeFilteringMode(PCF8);
                break;
            case PCFPOISSON:
                dlsf.setEdgeFilteringMode(PCFPOISSON);
        }
        dlsf.setEdgesThickness(b3D_Shadow.getEdgeThickness());
        dlsf.setFlushQueues(b3D_Shadow.isFlushQueues());
        dlsf.setShadowIntensity(b3D_Shadow.getIntensity());
        dlsf.setName(b3D_Shadow.getName());
        UUID lightUUID = b3D_Shadow.getLightUUID();
        System.out.println("lightUUID: " + lightUUID);
        int lightID = Wizard.getObjectReferences().getID(lightUUID);
        dlsf.setLight((DirectionalLight) Wizard.getObjects().getOriginalObject(lightID));
        return dlsf;
    }

    public static SpotLightShadowFilter convertSpotLightShadow(B3D_Shadow b3D_Shadow)
    {
        SpotLightShadowFilterWithGetters slsf = new SpotLightShadowFilterWithGetters(Wizard.getAssetManager(), b3D_Shadow.getShadowMapSize());
        switch (b3D_Shadow.getEdgeFilteringMode())
        {
            case Bilinear:
                slsf.setEdgeFilteringMode(Bilinear);
                break;
            case Dither:
                slsf.setEdgeFilteringMode(Dither);
                break;
            case Nearest:
                slsf.setEdgeFilteringMode(Nearest);
                break;
            case PCF4:
                slsf.setEdgeFilteringMode(PCF4);
                break;
            case PCF8:
                slsf.setEdgeFilteringMode(PCF8);
                break;
            case PCFPOISSON:
                slsf.setEdgeFilteringMode(PCFPOISSON);
        }
        slsf.setEdgesThickness(b3D_Shadow.getEdgeThickness());
        slsf.setFlushQueues(b3D_Shadow.isFlushQueues());
        slsf.setShadowIntensity(b3D_Shadow.getIntensity());
        slsf.setName(b3D_Shadow.getName());
        UUID lightUUID = b3D_Shadow.getUUID();
        int lightID = Wizard.getObjectReferences().getID(lightUUID);
        slsf.setLight((SpotLight) Wizard.getObjects().getOriginalObject(lightID));
        return slsf;
    }

    public static PointLightShadowFilterWithGetters convertPointLightShadow(B3D_Shadow b3D_Shadow)
    {
        PointLightShadowFilterWithGetters plsf = new PointLightShadowFilterWithGetters(Wizard.getAssetManager(), b3D_Shadow.getShadowMapSize());
        switch (b3D_Shadow.getEdgeFilteringMode())
        {
            case Bilinear:
                plsf.setEdgeFilteringMode(Bilinear);
                break;
            case Dither:
                plsf.setEdgeFilteringMode(Dither);
                break;
            case Nearest:
                plsf.setEdgeFilteringMode(Nearest);
                break;
            case PCF4:
                plsf.setEdgeFilteringMode(PCF4);
                break;
            case PCF8:
                plsf.setEdgeFilteringMode(PCF8);
                break;
            case PCFPOISSON:
                plsf.setEdgeFilteringMode(PCFPOISSON);
        }
        plsf.setEdgesThickness(b3D_Shadow.getEdgeThickness());
        plsf.setFlushQueues(b3D_Shadow.isFlushQueues());
        plsf.setShadowIntensity(b3D_Shadow.getIntensity());
        plsf.setName(b3D_Shadow.getName());
        UUID lightUUID = b3D_Shadow.getUUID();
        int lightID = Wizard.getObjectReferences().getID(lightUUID);
        plsf.setLight((PointLight) Wizard.getObjects().getOriginalObject(lightID));
        return plsf;
    }

    public static BasicSSAO convertBasicSSAO(B3D_BasicSSAO b3D_BasicSSAO)
    {
        BasicSSAO basicSSAO = new BasicSSAO();
        basicSSAO.setBias(b3D_BasicSSAO.getBias());
        basicSSAO.setDetailBias(b3D_BasicSSAO.getDetailBias());
        basicSSAO.setDetailIntensity(b3D_BasicSSAO.getDetailIntensity());
        basicSSAO.setDetailSampleRadius(b3D_BasicSSAO.getSampleRadius());
        basicSSAO.setDetailScale(b3D_BasicSSAO.getDetailScale());
        basicSSAO.setFalloffRate(b3D_BasicSSAO.getFalloffRate());
        basicSSAO.setFalloffStartDistance(b3D_BasicSSAO.getFalloffStartDistance());
        basicSSAO.setIntensity(b3D_BasicSSAO.getIntensity());
        basicSSAO.setSampleRadius(b3D_BasicSSAO.getSampleRadius());
        basicSSAO.setScale(b3D_BasicSSAO.getScale());
        basicSSAO.setSmoothMore(b3D_BasicSSAO.isSmoothMore());
        basicSSAO.setUseAo(b3D_BasicSSAO.isUseAO());
        basicSSAO.setUseDetailPass(b3D_BasicSSAO.isUseDetailPass());
        basicSSAO.setUseDistanceFalloff(b3D_BasicSSAO.isUseDistanceFalloff());
        basicSSAO.setUseOnlyAo(b3D_BasicSSAO.isUseOnlyAO());
        basicSSAO.setUseSmoothing(b3D_BasicSSAO.isUseSmoothing());
        return basicSSAO;
    }

    public static BloomFilter convertBloom(B3D_Bloom b3D_Bloom)
    {
        BloomFilter bloomFilter = new BloomFilter();
        bloomFilter.setName(b3D_Bloom.getName());
        bloomFilter.setBloomIntensity(b3D_Bloom.getIntensity());
        bloomFilter.setBlurScale(b3D_Bloom.getBlurScale());
        bloomFilter.setDownSamplingFactor(b3D_Bloom.getDownSamplingFactor());
        bloomFilter.setExposureCutOff(b3D_Bloom.getExposureCutOff());
        bloomFilter.setExposurePower(b3D_Bloom.getExposurePower());
        return bloomFilter;
    }

    public static CartoonEdgeFilter convertCartoon(B3D_Cartoon b3D_Cartoon)
    {
        CartoonEdgeFilter cartoonEdgeFilter = new CartoonEdgeFilter();
        cartoonEdgeFilter.setName(b3D_Cartoon.getName());
        cartoonEdgeFilter.setDepthSensitivity(b3D_Cartoon.getDepthSensivity());
        cartoonEdgeFilter.setDepthThreshold(b3D_Cartoon.getDepthTreshold());
        cartoonEdgeFilter.setEdgeColor(new ColorRGBA(b3D_Cartoon.getColor()));
        cartoonEdgeFilter.setEdgeIntensity(b3D_Cartoon.getEdgeIntensity());
        cartoonEdgeFilter.setEdgeWidth(b3D_Cartoon.getEdgeWidth());
        cartoonEdgeFilter.setNormalSensitivity(b3D_Cartoon.getNormalSensivity());
        cartoonEdgeFilter.setNormalThreshold(b3D_Cartoon.getNormalTreshold());
        return cartoonEdgeFilter;
    }

    public static ColorScaleFilterWithGetters convertColorScale(B3D_ColorScale b3D_ColorScale)
    {
        ColorScaleFilterWithGetters colorScaleFilter = new ColorScaleFilterWithGetters(new ColorRGBA(b3D_ColorScale.getColor()), b3D_ColorScale.getColorDensity());
        colorScaleFilter.setMultiply(b3D_ColorScale.isMultiply());
        colorScaleFilter.setOverlay(b3D_ColorScale.isOverlay());
        return colorScaleFilter;
    }

    public static CrossHatchFilter convertCrosshatch(B3D_Crosshatch b3D_Crosshatch)
    {
        CrossHatchFilter crossHatchFilter = new CrossHatchFilter(new ColorRGBA(b3D_Crosshatch.getLineColor()), new ColorRGBA(b3D_Crosshatch.getPaperColor()));
        crossHatchFilter.setName(b3D_Crosshatch.getName());
        crossHatchFilter.setColorInfluenceLine(b3D_Crosshatch.getColorInfluenceLine());
        crossHatchFilter.setColorInfluencePaper(b3D_Crosshatch.getColorInfluencePaper());
        crossHatchFilter.setFillValue(b3D_Crosshatch.getFillValue());
        crossHatchFilter.setLineDistance(b3D_Crosshatch.getLineDistance());
        crossHatchFilter.setLineThickness(b3D_Crosshatch.getLineThickness());
        crossHatchFilter.setLuminanceLevels(
                b3D_Crosshatch.getLuminanceLevels()[0],
                b3D_Crosshatch.getLuminanceLevels()[1],
                b3D_Crosshatch.getLuminanceLevels()[2],
                b3D_Crosshatch.getLuminanceLevels()[3],
                b3D_Crosshatch.getLuminanceLevels()[4]);
        return crossHatchFilter;
    }

    public static DepthOfFieldFilter convertDOF(B3D_DepthOfField b3D_DepthOfField)
    {
        DepthOfFieldFilter depthOfFieldFilter = new DepthOfFieldFilter();
        depthOfFieldFilter.setName(b3D_DepthOfField.getName());
        depthOfFieldFilter.setFocusDistance(b3D_DepthOfField.getFocusDistance());
        depthOfFieldFilter.setFocusRange(b3D_DepthOfField.getFocusRange());
        depthOfFieldFilter.setBlurScale(b3D_DepthOfField.getBlurScale());
        return depthOfFieldFilter;
    }

    public static LightScatteringFilter convertLightScattering(B3D_LightScattering b3D_LightScattering)
    {
        LightScatteringFilter lightScatteringFilter = new LightScatteringFilter(new Vector3f(b3D_LightScattering.getPosition()));
        lightScatteringFilter.setName(b3D_LightScattering.getName());
        lightScatteringFilter.setBlurStart(b3D_LightScattering.getBlurStart());
        lightScatteringFilter.setBlurWidth(b3D_LightScattering.getBlurWidth());
        lightScatteringFilter.setLightDensity(b3D_LightScattering.getDensity());
        lightScatteringFilter.setNbSamples(b3D_LightScattering.getSamples());
        return lightScatteringFilter;
    }

    public static FogFilter convertFog(B3D_Fog b3D_Fog)
    {
        FogFilter fogFilter = new FogFilter(new ColorRGBA(b3D_Fog.getColor()), b3D_Fog.getDensity(), b3D_Fog.getDistance());
        fogFilter.setName(b3D_Fog.getName());
        return fogFilter;
    }

    public static FrostedGlassFilter convertFrostedGlass(B3D_FrostedGlass b3D_FrostedGlass)
    {
        FrostedGlassFilter frostedGlassFilter = new FrostedGlassFilter();
        frostedGlassFilter.setRandomFactor(b3D_FrostedGlass.getRandomFactor());
        frostedGlassFilter.setRandomScale(b3D_FrostedGlass.getRandomScale());
        return frostedGlassFilter;
    }

    public static OldFilmFilter convertOldFilm(B3D_OldFilm b3D_OldFilm)
    {
        return new OldFilmFilter(
                new ColorRGBA(b3D_OldFilm.getColor()),
                b3D_OldFilm.getColorDensity(),
                b3D_OldFilm.getNoiseDensity(),
                b3D_OldFilm.getScratchDensity(),
                b3D_OldFilm.getVignetteValue());
    }

    public static PosterizationFilter convertPosterization(B3D_Posterization b3D_Posterization)
    {
        PosterizationFilter posterizationFilter = new PosterizationFilter(b3D_Posterization.getNumColors(), b3D_Posterization.getGamma());
        posterizationFilter.setName(b3D_Posterization.getName());
        posterizationFilter.setStrength(b3D_Posterization.getStrength());
        return posterizationFilter;
    }

    public static SSAOFilter convertSSAO(B3D_SSAO b3D_SSAO)
    {
        SSAOFilter sSAOFilter = new SSAOFilter(b3D_SSAO.getSampleRadius(), b3D_SSAO.getIntensity(), b3D_SSAO.getScale(), b3D_SSAO.getBias());
        sSAOFilter.setName(b3D_SSAO.getName());
        return sSAOFilter;
    }

    public static WaterFilterWithGetters convertWater(B3D_Water b3D_Water)
    {
        WaterFilterWithGetters waterFilter = new WaterFilterWithGetters();
        waterFilter.setName(b3D_Water.getName());
        waterFilter.setCausticsIntensity(b3D_Water.getCausistsIntensity());
        waterFilter.setColorExtinction(new Vector3f(b3D_Water.getColorExtinction()));
        waterFilter.setDeepWaterColor(new ColorRGBA(b3D_Water.getDeepWaterColor()));
        waterFilter.setFoamExistence(new Vector3f(b3D_Water.getFoamExistence()));
        waterFilter.setFoamHardness(b3D_Water.getFoamHardness());
        waterFilter.setFoamIntensity(b3D_Water.getFoamIntensity());
        waterFilter.setLightColor(new ColorRGBA(b3D_Water.getLightColor()));
        waterFilter.setLightDirection(new Vector3f(b3D_Water.getLightDirection()));
        waterFilter.setMaxAmplitude(b3D_Water.getMaxAmplitude());
        waterFilter.setNormalScale(b3D_Water.getNormalScale());
        waterFilter.setReflectionDisplace(b3D_Water.getReflectionDisplace());
        waterFilter.setReflectionMapSize(b3D_Water.getResolution());
        waterFilter.setRefractionStrength(b3D_Water.getRefractionStrength());
        waterFilter.setShininess(b3D_Water.getShininess());
        waterFilter.setShoreHardness(b3D_Water.getShoreHardness());
        waterFilter.setSpeed(b3D_Water.getSpeed());
        waterFilter.setSunScale(b3D_Water.getSunScale());
        waterFilter.setUnderWaterFogDistance(b3D_Water.getUnderWaterFogDistance());
        waterFilter.setUseCaustics(b3D_Water.isUseCausists());
        waterFilter.setUseFoam(b3D_Water.isUseFoam());
        waterFilter.setUseHQShoreline(b3D_Water.isUseHQShoreLine());
        waterFilter.setUseRefraction(b3D_Water.isUseRefraction());
        waterFilter.setUseRipples(b3D_Water.isUseRipples());
        waterFilter.setUseSpecular(b3D_Water.isUseSpecular());
        waterFilter.setWaterColor(new ColorRGBA(b3D_Water.getColor()));
        waterFilter.setWaterHeight(b3D_Water.getHeight());
        waterFilter.setWaterTransparency(b3D_Water.getTransparency());
        waterFilter.setWaveScale(b3D_Water.getWaveScale());
        waterFilter.setWindDirection(new Vector2f(b3D_Water.getWindDirection()));
        waterFilter.setCausticsTexture((Texture2D) Wizard.getAssetManager().loadTexture(b3D_Water.getCausistsTexture()));
        waterFilter.setFoamTexture((Texture2D) Wizard.getAssetManager().loadTexture(b3D_Water.getFoamTexture()));
        waterFilter.setHeightTexture((Texture2D) Wizard.getAssetManager().loadTexture(b3D_Water.getHeightTexture()));
        waterFilter.setNormalTexture((Texture2D) Wizard.getAssetManager().loadTexture(b3D_Water.getNormalTexture()));
        waterFilter.setReflectionScene(Wizard.getSceneNode());
        return waterFilter;
    }

    public static Light convertLight(B3D_Light b3D_Light)
    {
        if (b3D_Light instanceof B3D_DirectionalLight)
        {
            return convertDirectionalLight((B3D_DirectionalLight) b3D_Light);
        } else if (b3D_Light instanceof B3D_AmbientLight)
        {
            return convertAmbientLight((B3D_AmbientLight) b3D_Light);
        } else if (b3D_Light instanceof B3D_PointLight)
        {
            return convertPointLight((B3D_PointLight) b3D_Light);
        } else if (b3D_Light instanceof B3D_SpotLight)
        {
            return convertSpotLight((B3D_SpotLight) b3D_Light);
        }
        return null;
    }

    public static DirectionalLight convertDirectionalLight(B3D_DirectionalLight b3D_DirectionalLight)
    {
        DirectionalLight directionalLight = new DirectionalLight();
        directionalLight.setName(b3D_DirectionalLight.getName());
        directionalLight.setColor(new ColorRGBA(b3D_DirectionalLight.getColor()));
        directionalLight.setDirection(new Vector3f(b3D_DirectionalLight.getDirection()));
        return directionalLight;
    }

    public static AmbientLight convertAmbientLight(B3D_AmbientLight b3D_AmbientLight)
    {
        AmbientLight ambientLight = new AmbientLight();
        ambientLight.setName(b3D_AmbientLight.getName());
        ambientLight.setColor(new ColorRGBA(b3D_AmbientLight.getColor()));
        return ambientLight;
    }

    public static PointLight convertPointLight(B3D_PointLight b3D_PointLight)
    {
        PointLight pointLight = new PointLight();
        pointLight.setName(b3D_PointLight.getName());
        pointLight.setColor(new ColorRGBA(b3D_PointLight.getColor()));
        pointLight.setPosition(new Vector3f(b3D_PointLight.getPosition()));
        pointLight.setRadius(b3D_PointLight.getRadius());
        return pointLight;
    }

    public static SpotLight convertSpotLight(B3D_SpotLight b3D_SpotLight)
    {
        SpotLight spotLight = new SpotLight();
        spotLight.setName(b3D_SpotLight.getName());
        spotLight.setColor(new ColorRGBA(b3D_SpotLight.getColor()));
        spotLight.setPosition(new Vector3f(b3D_SpotLight.getPosition()));
        spotLight.setDirection(new Vector3f(b3D_SpotLight.getDirection()));
        spotLight.setSpotInnerAngle(b3D_SpotLight.getInnerAngle());
        spotLight.setSpotOuterAngle(b3D_SpotLight.getOuterAngle());
        spotLight.setSpotRange(b3D_SpotLight.getRange());
        return spotLight;
    }

    public static Spatial convertSpatial(B3D_Spatial b3D_Spatial)
    {
        Spatial spatial = null;
        RigidBodyControl rigidBodyControl = null;
        if (b3D_Spatial instanceof B3D_Node)
        {
            spatial = convertNode((B3D_Node) b3D_Spatial);
        } else if (b3D_Spatial instanceof B3D_Heightmap)
        {
            spatial = convertHeightmap((B3D_Heightmap) b3D_Spatial);
        } else if (b3D_Spatial instanceof B3D_HeightmapLink)
        {
            spatial = convertHeightmapLink((B3D_HeightmapLink) b3D_Spatial);
        } else if (b3D_Spatial instanceof B3D_Model)
        {
            spatial = convertModel((B3D_Model) b3D_Spatial);
        } else if (b3D_Spatial instanceof B3D_Box)
        {
            spatial = convertBox((B3D_Box) b3D_Spatial);
            spatial.setUserData("xSlices", ((B3D_Box) b3D_Spatial).getxSlices());
            spatial.setUserData("ySlices", ((B3D_Box) b3D_Spatial).getySlices());
            spatial.setUserData("zSlices", ((B3D_Box) b3D_Spatial).getzSlices());
        } else if (b3D_Spatial instanceof B3D_Cylinder)
        {
            spatial = convertCylinder((B3D_Cylinder) b3D_Spatial);
        } else if (b3D_Spatial instanceof B3D_ParticleEffect)
        {
            CustomParticleEmitter particleEmitter = convertParticleEmitter((B3D_ParticleEffect) b3D_Spatial);
            particleEmitter.setMaterial(new Material(Wizard.getAssetManager(), "Common/MatDefs/Misc/Particle.j3md"));
            particleEmitter.getMaterial().getAdditionalRenderState().setDepthWrite(((B3D_ParticleEffect) b3D_Spatial).isDepthWrite());
            String newValue = ((B3D_ParticleEffect) b3D_Spatial).getTextureName();
            String tempString = "Texture2D Texture : Flip ";
            if (newValue.startsWith(tempString))
                newValue = newValue.substring(tempString.length());
            particleEmitter.getMaterial().setTexture("Texture", Wizard.getAssetManager().loadTexture(newValue));
            spatial = particleEmitter;
        } else if (b3D_Spatial instanceof B3D_MultipleTextureSkyBox)
        {
            spatial = convertMutlipleTextureSkyBox((B3D_MultipleTextureSkyBox) b3D_Spatial);
            spatial.setUserData("north", ((B3D_MultipleTextureSkyBox) b3D_Spatial).getNorthTextureName());
            spatial.setUserData("south", ((B3D_MultipleTextureSkyBox) b3D_Spatial).getSoutTextureName());
            spatial.setUserData("west", ((B3D_MultipleTextureSkyBox) b3D_Spatial).getWestTextureName());
            spatial.setUserData("east", ((B3D_MultipleTextureSkyBox) b3D_Spatial).getEastTextureName());
            spatial.setUserData("top", ((B3D_MultipleTextureSkyBox) b3D_Spatial).getTopTextureName());
            spatial.setUserData("bottom", ((B3D_MultipleTextureSkyBox) b3D_Spatial).getBottomTextureName());
        } else if (b3D_Spatial instanceof B3D_SingleTextureSkyBox)
        {
            spatial = convertSingleTextureSkyBox((B3D_SingleTextureSkyBox) b3D_Spatial);
            spatial.setUserData("north", ((B3D_SingleTextureSkyBox) b3D_Spatial).getNorthTextureName());
        } else if (b3D_Spatial instanceof B3D_Sphere)
        {
            spatial = convertSphere((B3D_Sphere) b3D_Spatial);
        } else if (b3D_Spatial instanceof B3D_Torus)
        {
            spatial = convertTorus((B3D_Torus) b3D_Spatial);
        }
        // spatial.setUserData("ID", Wizard.getObjectReferences().getID(b3D_Spatial.getUUID()));
        spatial.setUserData("autoSyncPhysicsToTransform", "y");
        spatial.setLocalScale(new Vector3f(b3D_Spatial.getScale()));
        spatial.setLocalRotation(new Quaternion(b3D_Spatial.getRotation()));
        spatial.setUserData("angles", new Vector3f(b3D_Spatial.getAngles()));
        spatial.setUserData("scale", new Vector3f(spatial.getLocalScale()));
        if (b3D_Spatial.getShadowMode().equals("Cast"))
        {
            spatial.setShadowMode(RenderQueue.ShadowMode.Cast);
        } else if (b3D_Spatial.getShadowMode().equals("Recieve"))
        {
            spatial.setShadowMode(RenderQueue.ShadowMode.Receive);
        } else if (b3D_Spatial.getShadowMode().equals("Cast&Recieve"))
        {
            spatial.setShadowMode(RenderQueue.ShadowMode.CastAndReceive);
        } else if (b3D_Spatial.getShadowMode().equals("Inherit"))
        {
            spatial.setShadowMode(RenderQueue.ShadowMode.Inherit);
        } else
        {
            spatial.setShadowMode(RenderQueue.ShadowMode.Off);
        }
        spatial.setLocalTranslation(new Vector3f(b3D_Spatial.getTranslation()));
        if (b3D_Spatial.getPhysics() != null)
        {
            rigidBodyControl = convertPhysics(b3D_Spatial.getPhysics(), spatial);
            spatial.addControl(rigidBodyControl);
            spatial.setUserData("adjust", "t");
        }
        for (B3D_Control control : b3D_Spatial.getControls())
        {
            AbstractControl c = convertControl(control, spatial);
            spatial.addControl(c);
        }
        for (String key : b3D_Spatial.getUserData().keySet())
        {
            spatial.setUserData(key, b3D_Spatial.getUserData().get(key));
        }
        spatial.setName(b3D_Spatial.getName());
        return spatial;
    }

    public static AbstractControl convertControl(B3D_Control control, Spatial spatial)
    {
        if (control instanceof B3D_LightControl)
            return convertLightControl((B3D_LightControl) control);
        else if (control instanceof B3D_LightScatteringMotionControl)
            return convertLightScatteringMotionControl((B3D_LightScatteringMotionControl) control);
        return null;
    }

    public static LightScatteringMotionControl convertLightScatteringMotionControl(B3D_LightScatteringMotionControl control)
    {
        return new LightScatteringMotionControl(control.getFilterID(), control.isEnabled());
    }

    public static Node convertNode(B3D_Node b3D_Node)
    {
        Node node = new Node(b3D_Node.getName());
        for (B3D_Spatial b3D_Spatial : b3D_Node.getChildren())
        {
            Spatial child = convertSpatial(b3D_Spatial);
            Wizard.getObjects().add(child, b3D_Spatial);
            node.attachChild(child);
        }
        return node;
    }

    public static Spatial convertBox(B3D_Box b3D_Box)
    {
        Geometry geometry = new Geometry(b3D_Box.getName(), new MBox(1, 1, 1, b3D_Box.getxSlices(), b3D_Box.getySlices(), b3D_Box.getzSlices()));
        geometry.setMaterial(convertMaterial(b3D_Box.getMaterial()));
        return geometry;
    }

    public static Spatial convertModel(B3D_Model b3D_Model)
    {
        Spatial spatial = Wizard.getAssetManager().loadModel(b3D_Model.getPath());
        spatial.setUserData("modelName", b3D_Model.getPath());
        spatial.setName(b3D_Model.getName());
        //All spatials gotta to have a name
        if (spatial instanceof Node)
        {
            Vector<Spatial> spatials = new Vector<Spatial>();
            Wizard.insertAllSpatials((Node) spatial, spatials);
            for (Spatial s : spatials)
            {
                s.setName("modelChild");
                s.setUserData("modelChild", "yup");
            }
        }
        return spatial;
    }

    public static Spatial convertCylinder(B3D_Cylinder b3D_Cylinder)
    {
        Geometry geometry = new Geometry(b3D_Cylinder.getName(), new Cylinder(
                b3D_Cylinder.getAxisSamples(),
                b3D_Cylinder.getRadialSamples(),
                b3D_Cylinder.getRadius(),
                b3D_Cylinder.getRadius2(),
                b3D_Cylinder.getHeight(),
                b3D_Cylinder.isIsClosed(),
                b3D_Cylinder.isIsInverted()));
        geometry.setMaterial(convertMaterial(b3D_Cylinder.getMaterial()));
        return geometry;
    }

    public static CustomParticleEmitter convertParticleEmitter(B3D_ParticleEffect b3D_ParticleEffect)
    {
        CustomParticleEmitter particleEmitter = null;
        if (b3D_ParticleEffect.getType().equals(B3D_ParticleEffect.Type.Point))
        {
            particleEmitter = new CustomParticleEmitter(b3D_ParticleEffect.getName(), ParticleMesh.Type.Point, b3D_ParticleEffect.getMaxParticles());
        } else
        {
            particleEmitter = new CustomParticleEmitter(b3D_ParticleEffect.getName(), ParticleMesh.Type.Triangle, b3D_ParticleEffect.getMaxParticles());
        }
        EmitterShape emitterShape = null;
        if (b3D_ParticleEffect.getStartShape() instanceof B3D_PointStartShape)
        {
            emitterShape = new EmitterPointShape(new Vector3f(((B3D_PointStartShape) b3D_ParticleEffect.getStartShape()).getPoint()));
        } else if (b3D_ParticleEffect.getStartShape() instanceof B3D_BoxStartShape)
        {
            emitterShape = new EmitterBoxShape(
                    new Vector3f(((B3D_BoxStartShape) b3D_ParticleEffect.getStartShape()).getMini()),
                    new Vector3f(((B3D_BoxStartShape) b3D_ParticleEffect.getStartShape()).getMaxi()));
        } else if (b3D_ParticleEffect.getStartShape() instanceof B3D_SphereStartShape)
        {
            emitterShape = new EmitterSphereShape(
                    new Vector3f(((B3D_SphereStartShape) b3D_ParticleEffect.getStartShape()).getCenter()),
                    ((B3D_SphereStartShape) b3D_ParticleEffect.getStartShape()).getRadius());
        }
        particleEmitter.setEnabled(!b3D_ParticleEffect.isFrozen());
        particleEmitter.setFiring(b3D_ParticleEffect.isFiring());
        particleEmitter.setEndColor(new ColorRGBA(b3D_ParticleEffect.getEndColor()));
        particleEmitter.setShape(emitterShape);
        particleEmitter.setEndSize(b3D_ParticleEffect.getEndSize());
        particleEmitter.setGravity(new Vector3f(b3D_ParticleEffect.getGravity()));
        particleEmitter.setHighLife(b3D_ParticleEffect.getHighLife());
        particleEmitter.setImagesX(b3D_ParticleEffect.getImgX());
        if (b3D_ParticleEffect.getFaceNormal() == null)
            particleEmitter.setFaceNormal(null);
        else
            particleEmitter.setFaceNormal(new Vector3f(b3D_ParticleEffect.getFaceNormal()));
        particleEmitter.setFacingVelocity(b3D_ParticleEffect.isFaceVelocity());
        particleEmitter.setImagesY(b3D_ParticleEffect.getImgY());
        particleEmitter.setLowLife(b3D_ParticleEffect.getLowLife());
        particleEmitter.setParticlesPerSec(b3D_ParticleEffect.getParticlesPerSecond());
        particleEmitter.setRotateSpeed(b3D_ParticleEffect.getRotateSpeed());
        particleEmitter.setStartColor(new ColorRGBA(b3D_ParticleEffect.getStartColor()));
        particleEmitter.setStartSize(b3D_ParticleEffect.getStartSize());
        particleEmitter.getParticleInfluencer().setInitialVelocity(new Vector3f(b3D_ParticleEffect.getDirectionVelocity()));
        particleEmitter.getParticleInfluencer().setVelocityVariation(b3D_ParticleEffect.getDirectionVariation());
        particleEmitter.setQueueBucket(RenderQueue.Bucket.Translucent);
        return particleEmitter;
    }

    public static Spatial convertMutlipleTextureSkyBox(B3D_MultipleTextureSkyBox b3D_MSkyBox)
    {
        return SkyFactory.createSky(
                Wizard.getAssetManager(),
                Wizard.getAssetManager().loadTexture(b3D_MSkyBox.getWestTextureName()),
                Wizard.getAssetManager().loadTexture(b3D_MSkyBox.getEastTextureName()),
                Wizard.getAssetManager().loadTexture(b3D_MSkyBox.getNorthTextureName()),
                Wizard.getAssetManager().loadTexture(b3D_MSkyBox.getSoutTextureName()),
                Wizard.getAssetManager().loadTexture(b3D_MSkyBox.getTopTextureName()),
                Wizard.getAssetManager().loadTexture(b3D_MSkyBox.getBottomTextureName()));
    }

    public static Spatial convertSingleTextureSkyBox(B3D_SingleTextureSkyBox b3D_SSkyBox)
    {
        return SkyFactory.createSky(Wizard.getAssetManager(), Wizard.getAssetManager().loadTexture(b3D_SSkyBox.getNorthTextureName()), true);
    }

    public static Spatial convertSphere(B3D_Sphere b3D_Sphere)
    {
        Geometry geometry = new Geometry(b3D_Sphere.getName(), new Sphere(b3D_Sphere.getzSamples(), b3D_Sphere.getRadialSamples(), b3D_Sphere.getRadius()));
        geometry.setMaterial(convertMaterial(b3D_Sphere.getMaterial()));
        return geometry;
    }

    public static Spatial convertTorus(B3D_Torus b3D_Torus)
    {
        Geometry geometry = new Geometry(b3D_Torus.getName(), new Torus(
                b3D_Torus.getCircleSamples(),
                b3D_Torus.getRadialSamples(),
                b3D_Torus.getInnerRadius(),
                b3D_Torus.getOuterRadius()));
        geometry.setMaterial(convertMaterial(b3D_Torus.getMaterial()));
        return geometry;
    }

    public static Material convertMaterial(B3D_Material b3D_Material)
    {
        Material material = new Material(Wizard.getAssetManager(), b3D_Material.getDefName());
        //System.out.println("Doing " + b3D_Material.getDefName());
        for (Map.Entry<String, B3D_MaterialPropertyList.Property> entry : b3D_Material.getPropertyList().getProperties().entrySet())
        {
            // System.out.println("Setting " + entry.getKey() + " to " + entry.getValue().getPropertyValue());
            if (material.getMaterialDef().getMaterialParam(entry.getKey()) != null)
            {
                //     System.out.println("Param accepted");
                if (entry.getValue().getPropertyType().toLowerCase().equals("boolean"))
                {
                    material.setBoolean(entry.getKey(), Boolean.parseBoolean(entry.getValue().getPropertyValue()));
                } else if (entry.getValue().getPropertyType().toLowerCase().equals("color"))
                {
                    StringTokenizer tokenizer = new StringTokenizer(entry.getValue().getPropertyValue(), ", ");
                    int floatNmbr = 0;
                    ColorRGBA newValue = new ColorRGBA();
                    while (tokenizer.hasMoreTokens())
                    {
                        String nextToken = tokenizer.nextToken();
                        if (floatNmbr == 0)
                        {
                            newValue.r = Float.parseFloat(nextToken);
                        } else if (floatNmbr == 1)
                        {
                            newValue.g = Float.parseFloat(nextToken);
                        } else if (floatNmbr == 2)
                        {
                            newValue.b = Float.parseFloat(nextToken);
                        } else if (floatNmbr == 3)
                        {
                            newValue.a = Float.parseFloat(nextToken);
                        }
                        floatNmbr++;
                    }
                    material.setColor(entry.getKey(), newValue);
                } else if (entry.getValue().getPropertyType().toLowerCase().equals("texture"))
                {
                    //Correct the friggn name
                    if (entry.getValue().getPropertyValue().startsWith("Flip "))
                    {
                        entry.getValue().setPropertyValue(entry.getValue().getPropertyValue().substring(5));
                    }
                    material.setTexture(entry.getKey(), Wizard.getAssetManager().loadTexture(entry.getValue().getPropertyValue()));
                } else if (entry.getValue().getPropertyType().toLowerCase().equals("float"))
                {
                    material.setFloat(entry.getKey(), Float.parseFloat(entry.getValue().getPropertyValue()));
                } else if (entry.getValue().getPropertyType().toLowerCase().equals("int"))
                {
                    material.setInt(entry.getKey(), Integer.parseInt(entry.getValue().getPropertyValue()));
                } else if (entry.getValue().getPropertyType().toLowerCase().equals("vector2"))
                {
                    String value = entry.getValue().getPropertyValue().replaceAll(",", "");
                    StringTokenizer tokenizer = new StringTokenizer(value.substring(1, value.length() - 1), " ");
                    material.setVector2(entry.getKey(), new Vector2f(
                            Float.parseFloat(tokenizer.nextToken()),
                            Float.parseFloat(tokenizer.nextToken())));

                } else if (entry.getValue().getPropertyType().toLowerCase().equals("vector3"))
                {
                    String value = entry.getValue().getPropertyValue().replaceAll(",", "");
                    StringTokenizer tokenizer = new StringTokenizer(value.substring(1, value.length() - 1), " ");
                    material.setVector3(entry.getKey(), new Vector3f(
                            Float.parseFloat(tokenizer.nextToken()),
                            Float.parseFloat(tokenizer.nextToken()),
                            Float.parseFloat(tokenizer.nextToken())));
                } else if (entry.getValue().getPropertyType().toLowerCase().equals("vector4"))
                {
                    String value = entry.getValue().getPropertyValue().replaceAll(",", "");
                    StringTokenizer tokenizer = new StringTokenizer(value.substring(1, value.length() - 1), " ");
                    material.setVector4(entry.getKey(), new Vector4f(
                            Float.parseFloat(tokenizer.nextToken()),
                            Float.parseFloat(tokenizer.nextToken()),
                            Float.parseFloat(tokenizer.nextToken()),
                            Float.parseFloat(tokenizer.nextToken())));
                }
            } else
            {
                // System.out.println("Param not accepted!");
            }
        }
        return material;
    }

    public static RigidBodyControl convertPhysics(B3D_Physics b3D_Physics, Spatial spatial)
    {
        RigidBodyControl rigidBodyControl = new RigidBodyControl();
        rigidBodyControl.setCollisionShape(convertCollisionShape(b3D_Physics.getcShape(), spatial));
        rigidBodyControl.setMass(b3D_Physics.getMass());
        rigidBodyControl.setRestitution(b3D_Physics.getResitution());
        rigidBodyControl.setKinematic(b3D_Physics.isKinematic());
        rigidBodyControl.setCcdMotionThreshold(b3D_Physics.getMotionThreshold());
        rigidBodyControl.setPhysicsLocation(new Vector3f(b3D_Physics.getPhysicsLocation()));
        rigidBodyControl.setPhysicsRotation(new Quaternion(b3D_Physics.getPhysicsRotation()));
        rigidBodyControl.setFriction(b3D_Physics.getFriction());
        return rigidBodyControl;
    }

    public static CollisionShape convertCollisionShape(B3D_CShape b3D_CShape, Spatial spatial)
    {
        CollisionShape collisionShape = null;
        if (b3D_CShape instanceof B3D_BoxShape)
        {
            collisionShape = new BoxCollisionShape(new Vector3f(((B3D_BoxShape) b3D_CShape).getHalfExtends()));
        } else if (b3D_CShape instanceof B3D_CapsuleShape)
        {
            collisionShape = new CapsuleCollisionShape(((B3D_CapsuleShape) b3D_CShape).getRadius(), ((B3D_CapsuleShape) b3D_CShape).getHeight());
        } else if (b3D_CShape instanceof B3D_ConeShape)
        {
            collisionShape = new ConeCollisionShape(((B3D_ConeShape) b3D_CShape).getRadius(), ((B3D_ConeShape) b3D_CShape).getHeight());
        } else if (b3D_CShape instanceof B3D_CylinderShape)
        {
            collisionShape = new CylinderCollisionShape(new Vector3f(((B3D_CylinderShape) b3D_CShape).getHalfExtends()));
        } else if (b3D_CShape instanceof B3D_DynamicMeshShape)
        {
            collisionShape = CollisionShapeFactory.createDynamicMeshShape(spatial);
            spatial.setUserData("cShape", "dynamic");
        } else if (b3D_CShape instanceof B3D_StaticMeshShape)
        {
            collisionShape = CollisionShapeFactory.createDynamicMeshShape(spatial);
            spatial.setUserData("cShape", "static");
        }
        return collisionShape;
    }

    public static MotionEvent convertMotionEvent(B3D_MotionEvent b3D_MotionEvent)
    {
        MotionPath motionPath = new MotionPath();
        Spatial spatial;
        if (b3D_MotionEvent.getObjectProbablyUUID().equals(B3D_MotionEvent.Cam.CAM_ID))
            spatial = Wizard.getCameraNode();
        else
            spatial = (Spatial) Wizard.getObjects().getOriginalObject(Wizard.getObjectReferences().getID((UUID) b3D_MotionEvent.getObjectProbablyUUID()));
        for (Vector3f vec3 : b3D_MotionEvent.getMotionPath().getWayPoints())
            motionPath.addWayPoint(new Vector3f(vec3));
        motionPath.setCycle(b3D_MotionEvent.getMotionPath().isCycled());
        motionPath.setCurveTension(b3D_MotionEvent.getMotionPath().getCurveTension());
        LoopMode loopMode;
        if (b3D_MotionEvent.getMotionPath().getLoopMode().equals(B3D_MotionPath.LoopMode.Cycle))
            loopMode = LoopMode.Cycle;
        else if (b3D_MotionEvent.getMotionPath().getLoopMode().equals(B3D_MotionPath.LoopMode.DontLoop))
            loopMode = LoopMode.DontLoop;
        else
            loopMode = LoopMode.Loop;
        /*
         * Following error happens here: The spatial that is assigned to this MotionEvent is not an Object in the scene!
         * However, it has the correct ID-UserData, just like the Spatial inside the SceneGraph that will not move even though it
         * seems to be assigned to the MotionEvent. (only if the spatial is not the camNode)
         * I did not find the cause of this mistake, but at least a workaround.
         * The following code finds that very Spatial and replaces the other one with it.
         * So yeah... it only took me about 8 hours to get that shit "fixed".
         */
        if (!b3D_MotionEvent.getObjectProbablyUUID().equals(B3D_MotionEvent.Cam.CAM_ID) && !Wizard.getSceneNode().hasChild(spatial))
        {
            Vector<Spatial> spats = new Vector<Spatial>();
            Wizard.insertAllSpatials(Wizard.getSceneNode(), spats);
            for (Spatial s : spats)
            {
                int sID = s.hashCode();
                UUID sUUID = Wizard.getObjectReferences().getUUID(sID);
                if (sUUID.equals(b3D_MotionEvent.getObjectProbablyUUID().toString()))
                {
                    spatial = s;
                    break;
                }
            }
        }
        MotionEvent motionEvent = new MotionEvent(spatial, motionPath, loopMode);
        motionEvent.setSpeed(b3D_MotionEvent.getMotionPath().getSpeed());
        if (b3D_MotionEvent.getMotionPath().getDirectionType().equals(B3D_MotionPath.DirectionType.LookAt))
            motionEvent.setDirectionType(MotionEvent.Direction.LookAt);
        else if (b3D_MotionEvent.getMotionPath().getDirectionType().equals(B3D_MotionPath.DirectionType.None))
            motionEvent.setDirectionType(MotionEvent.Direction.None);
        else if (b3D_MotionEvent.getMotionPath().getDirectionType().equals(B3D_MotionPath.DirectionType.Path))
            motionEvent.setDirectionType(MotionEvent.Direction.Path);
        else if (b3D_MotionEvent.getMotionPath().getDirectionType().equals(B3D_MotionPath.DirectionType.PathAndRotation))
            motionEvent.setDirectionType(MotionEvent.Direction.PathAndRotation);
        else
            motionEvent.setDirectionType(MotionEvent.Direction.Rotation);
        if (b3D_MotionEvent.getMotionPath().getRotation() != null)
            motionEvent.setRotation(new Quaternion(b3D_MotionEvent.getMotionPath().getRotation()));
        if (b3D_MotionEvent.getMotionPath().getLookAtObject() != null)
            if (b3D_MotionEvent.getMotionPath().getLookAtObject().equals("Camera"))
                motionEvent.setLookAt(Wizard.getCamera().getLocation(), Vector3f.UNIT_Y);
            else
            {
                UUID uuid = (UUID) b3D_MotionEvent.getMotionPath().getLookAtObject();
                motionEvent.setLookAt(((Spatial) Wizard.getObjects().getOriginalObject(Wizard.getObjectReferences().getID(uuid))).getWorldTranslation(), Vector3f.UNIT_Y);
            }
        motionPath.setPathSplineType(Spline.SplineType.CatmullRom);
        return motionEvent;
    }

    public static LightControl convertLightControl(B3D_LightControl bdlc)
    {
        int lightID = Wizard.getObjectReferences().getID(bdlc.getLightUUID());
        Light light = (Light) Wizard.getObjects().getOriginalObject(lightID);
        LightControl lc = new LightControl(light, LightControl.ControlDirection.SpatialToLight);
        lc.setEnabled(bdlc.isEnabled());
        return lc;
    }

    private static Spatial convertHeightmap(B3D_Heightmap b3D_Heightmap)
    {
        TerrainQuad terrain = new TerrainQuad(b3D_Heightmap.getName(), b3D_Heightmap.getPatchSize(), b3D_Heightmap.getTotalSize(), b3D_Heightmap.getHeight());
        terrain.setUserData("angles", new Vector3f());
        terrain.setUserData("scale", new Vector3f(1, 1, 1));
        terrain.setMaterial(convertMaterial(b3D_Heightmap.getMaterial()));
        return terrain;
    }

    private static Spatial convertHeightmapLink(B3D_HeightmapLink b3d_HeightmapLink)
    {
        Texture heightMapImage = Wizard.getAssetManager().loadTexture(b3d_HeightmapLink.getPath());
        ImageBasedHeightMap heightmap = new ImageBasedHeightMap(heightMapImage.getImage());
        heightmap.load();
        TerrainQuad terrain = new TerrainQuad(b3d_HeightmapLink.getName(), 65, heightmap.getSize() + 1, heightmap.getHeightMap());
        terrain.setUserData("angles", new Vector3f());
        terrain.setUserData("scale", new Vector3f(1, 1, 1));
        terrain.setMaterial(convertMaterial(b3d_HeightmapLink.getMaterial()));
        terrain.setUserData("heightmapLink", b3d_HeightmapLink.getPath());
        return terrain;
    }

    public static LiveKeyframeAnimation convertKeyframeAnimation(B3D_KeyframeAnimation animationElement)
    {
        LiveKeyframeAnimation lka = new LiveKeyframeAnimation(animationElement.getName());
        for (B3D_KeyframeUpdater updaterElement : animationElement.getUpdaters())
            lka.addUpdater(convertKeyframeUpdater(updaterElement));
        return lka;
    }

    private static LiveKeyframeUpdater convertKeyframeUpdater(B3D_KeyframeUpdater updaterElement)
    {
        Object object = Wizard.getObjects().getOriginalObject(Wizard.getObjectReferences().getID(updaterElement.getObjectID()));
        LiveKeyframeUpdater updater = null;
        if (object instanceof CustomParticleEmitter)
            updater = new LiveParticleEmitterUpdater((CustomParticleEmitter) object);
        else if (object instanceof Spatial)
            updater = new LiveSpatialUpdater((Spatial) object);
        else if (object instanceof Light)
            updater = new LiveLightUpdater((Light) object);
        for (Object keyframeProperty : updaterElement.getKeyframeProperties())
            updater.getKeyframeProperties().add(convertKeyframeProperty((B3D_KeyframeProperty) keyframeProperty, updater));
        return updater;
    }

    private static LiveKeyframeProperty convertKeyframeProperty(B3D_KeyframeProperty keyframeProperty, LiveKeyframeUpdater updater)
    {
        LiveKeyframeProperty lkp = null;
        if (keyframeProperty.type.equals(AnimationType.Scale) || keyframeProperty.type.equals(AnimationType.Translation))
        {
            Vector3f[] vecVals = (Vector3f[]) keyframeProperty.getValues();
            try
            {
                lkp = new Vector3fProperty(
                        keyframeProperty.type,
                        vecVals.length,
                        vecVals[0],
                        vecVals[vecVals.length - 1],
                        updater);
            } catch (Exception ex)
            {
                Logger.getLogger(ElementToObjectConverter.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } else if (keyframeProperty.type.equals(AnimationType.Rotation))
        {
            Quaternion[] quatVals = (Quaternion[]) keyframeProperty.getValues();
            try
            {
                lkp = new QuaternionProperty(
                        keyframeProperty.type,
                        quatVals.length,
                        quatVals[0],
                        quatVals[quatVals.length - 1],
                        updater);
            } catch (Exception ex)
            {
                Logger.getLogger(ElementToObjectConverter.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } else if (keyframeProperty.type.equals(AnimationType.Particles_Per_Second))
        {
            Integer[] intVals = (Integer[]) keyframeProperty.getValues();
            try
            {
                lkp = new IntProperty(
                        keyframeProperty.type,
                        intVals.length,
                        intVals[0],
                        intVals[intVals.length - 1],
                        updater);
            } catch (Exception ex)
            {
                Logger.getLogger(ElementToObjectConverter.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } else if (keyframeProperty.type.equals(AnimationType.Frozen) || keyframeProperty.type.equals(AnimationType.Emit_All))
        {
            Boolean[] boolVals = (Boolean[]) keyframeProperty.getValues();
            try
            {
                lkp = new BoolProperty(
                        keyframeProperty.type,
                        boolVals.length,
                        boolVals[0],
                        boolVals[boolVals.length - 1],
                        updater);
            } catch (Exception ex)
            {
                Logger.getLogger(ElementToObjectConverter.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } else if (keyframeProperty.type.equals(AnimationType.End_Color_Blend) || keyframeProperty.type.equals(AnimationType.Start_Color_Blend) || keyframeProperty.type.equals(AnimationType.Light_Color_Blend))
        {
            ColorRGBA[] boolVals = (ColorRGBA[]) keyframeProperty.getValues();
            try
            {
                lkp = new ColorRGBAProperty(
                        keyframeProperty.type,
                        boolVals.length,
                        boolVals[0],
                        boolVals[boolVals.length - 1],
                        updater);
            } catch (Exception ex)
            {
                Logger.getLogger(ElementToObjectConverter.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        //For all
        System.out.println("lkp: " + lkp);
        System.out.println("kfp: " + keyframeProperty);
        for (int i = 1; i < keyframeProperty.getValues().length - 1; i++)
        {
            lkp.getValues()[i] = keyframeProperty.getValues()[i];
        }
        lkp.setIndices(new ArrayList<Integer>(keyframeProperty.getIndices()));
        lkp.uncalcValues();
        return lkp;
    }
}
