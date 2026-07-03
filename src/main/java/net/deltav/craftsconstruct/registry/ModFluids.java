package net.deltav.craftsconstruct.registry;

import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import com.simibubi.create.AllFluids;
import com.simibubi.create.AllTags;
import com.simibubi.create.infrastructure.config.AllConfigs;
import com.tterrag.registrate.builders.FluidBuilder;
import com.tterrag.registrate.util.entry.FluidEntry;
import net.deltav.craftsconstruct.craftsconstruct;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer.FogMode;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidType;
import org.joml.Vector3f;

import java.util.function.Consumer;
import java.util.function.Supplier;

import static net.deltav.craftsconstruct.registry.ModCreativeModeTabs.CC_TAB_KEY;

public class ModFluids {
    private static final Supplier<Float> JELLY_FOG_DISTANCE =
            () -> 1f / 24f * AllConfigs.client().chocolateTransparencyMultiplier.getF();

    private static ResourceLocation modResource(String path) {
        return new ResourceLocation(craftsconstruct.MOD_ID, path);
    }

    public static final FluidEntry<ForgeFlowingFluid.Flowing> GREEN_JELLY =
            jelly("green_jelly", "Green Jelly", 0x52A04D);
    public static final FluidEntry<ForgeFlowingFluid.Flowing> YELLOW_JELLY =
            jelly("yellow_jelly", "Yellow Jelly", 0xB8B71E);
    public static final FluidEntry<ForgeFlowingFluid.Flowing> RED_JELLY =
            jelly("red_jelly", "Red Jelly", 0xAA232A);
    public static final FluidEntry<ForgeFlowingFluid.Flowing> PINK_JELLY =
            jelly("pink_jelly", "Pink Jelly", 0xB86BA6);
    public static final FluidEntry<ForgeFlowingFluid.Flowing> BLUE_JELLY =
            jelly("blue_jelly", "Blue Jelly", 0x54ACC9);

    private static FluidEntry<ForgeFlowingFluid.Flowing> jelly(String name, String lang, int fogColor) {
        return craftsconstruct.registrate()
                .standardFluid(name,
                        SolidRenderedFluidType.create(
                                fogColor,
                                JELLY_FOG_DISTANCE,
                                "block/fluids/" + name + "_still",
                                "block/fluids/" + name + "_flow"
                        )
                )
                .lang(lang)
                .properties(b -> b.viscosity(2000)
                        .density(1400))
                .fluidProperties(p -> p.levelDecreasePerBlock(2)
                        .tickRate(25)
                        .slopeFindDistance(3)
                        .explosionResistance(100f))
                .source(ForgeFlowingFluid.Source::new)
                .bucket()
                .removeTab(CreativeModeTabs.SEARCH)
                .tab(CC_TAB_KEY)
                .tag(net.minecraft.tags.ItemTags.create(new ResourceLocation("forge", "buckets/honey")))
                .build()
                .register();
    }

    public static class SolidRenderedFluidType extends AllFluids.TintedFluidType {
        private final ResourceLocation stillTexture;
        private final ResourceLocation flowingTexture;
        private final Vector3f fogColor;
        private final Supplier<Float> fogDistance;


        public static FluidBuilder.FluidTypeFactory create(int fogColorHex,
                                                           Supplier<Float> fogDistance,
                                                           String stillTexturePath,
                                                           String flowingTexturePath) {
            return (props, still, flowing) -> new SolidRenderedFluidType(
                    props,
                    ModFluids.modResource(stillTexturePath),
                    ModFluids.modResource(flowingTexturePath),
                    fogColorHex,
                    fogDistance
            );
        }
        public SolidRenderedFluidType(FluidType.Properties properties,
                                            ResourceLocation stillTexture,
                                            ResourceLocation flowingTexture,
                                            int fogColorHex,
                                            Supplier<Float> fogDistance) {
            super(properties, stillTexture, flowingTexture);
            this.stillTexture = stillTexture;
            this.flowingTexture = flowingTexture;
            this.fogColor = hexToVector(fogColorHex);
            this.fogDistance = fogDistance;
        }

        private Vector3f hexToVector(int hexColor) {
            return new Vector3f(
                    ((hexColor >> 16) & 0xFF) / 255f,
                    ((hexColor >> 8) & 0xFF) / 255f,
                    (hexColor & 0xFF) / 255f
            );
        }
        protected int getTintColor(FluidStack stack) {
            return 0x00FFFFFF;
        }
        public int getTintColor(FluidState state, BlockAndTintGetter getter, BlockPos pos) {
            return 0x00FFFFFF;
        }
        protected Vector3f getCustomFogColor() {
            return fogColor;
        }
        protected float getFogDistanceModifier() {
            return fogDistance.get();
        }
        public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
            consumer.accept(new IClientFluidTypeExtensions() {
                public ResourceLocation getStillTexture() {
                    return stillTexture;
                }
                public ResourceLocation getFlowingTexture() {
                    return flowingTexture;
                }
                public int getTintColor(FluidStack stack) {
                    return 0xFFFFFFFF;
                }
                public Vector3f modifyFogColor(Camera camera, float partialTick, ClientLevel level,
                                               int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor) {
                    return fogColor;
                }
                public void modifyFogRender(Camera camera, FogMode mode, float renderDistance, float partialTick,
                                            float nearDistance, float farDistance, FogShape shape) {
                    RenderSystem.setShaderFogShape(FogShape.CYLINDER);
                    RenderSystem.setShaderFogStart(-8);
                    RenderSystem.setShaderFogEnd(96 * getFogDistanceModifier());
                }
            });
        }
    }
    public static void register(IEventBus modEventBus) {
    }
}
