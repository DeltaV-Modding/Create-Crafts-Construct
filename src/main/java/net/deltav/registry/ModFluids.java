package net.deltav.registry;

import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import com.simibubi.create.AllFluids;
import com.simibubi.create.AllTags;
import com.simibubi.create.infrastructure.config.AllConfigs;
import com.tterrag.registrate.builders.FluidBuilder;
import com.tterrag.registrate.util.entry.FluidEntry;
import net.deltav.craftsconstruct;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer.FogMode;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import org.joml.Vector3f;

import java.util.function.Consumer;
import java.util.function.Supplier;

import static net.deltav.util.ModCreativeModeTabs.CC_TAB_KEY;

public class ModFluids {

    private static ResourceLocation modResource(String path) {
        return ResourceLocation.fromNamespaceAndPath(craftsconstruct.MOD_ID, path);
    }

    public static final FluidEntry<BaseFlowingFluid.Flowing> GREEN_JELLY =
            craftsconstruct.registrate()
                    .standardFluid("green_jelly",
                            SolidRenderedFluidType.create(
                                    0x52A04D,
                                    () -> 1f / 24f * AllConfigs.client().chocolateTransparencyMultiplier.getF(),
                                    "block/fluids/green_jelly_still",
                                    "block/fluids/green_jelly_flow"
                            )
                    )
                    .lang("Green Jelly")
                    .properties(b -> b.viscosity(2000)
                            .density(1400))
                    .fluidProperties(p -> p.levelDecreasePerBlock(2)
                            .tickRate(25)
                            .slopeFindDistance(3)
                            .explosionResistance(100f))
                    .tag(Tags.Fluids.HONEY)
                    .source(BaseFlowingFluid.Source::new)
                    .bucket()
                    .removeTab(CreativeModeTabs.SEARCH)
                    .tab(CC_TAB_KEY)
                    .tag(AllTags.commonItemTag("buckets/honey"))
                    .build()
                    .register();
    public static final FluidEntry<BaseFlowingFluid.Flowing> YELLOW_JELLY =
            craftsconstruct.registrate()
                    .standardFluid("yellow_jelly",
                            SolidRenderedFluidType.create(
                                    0xB8B71E,
                                    () -> 1f / 24f * AllConfigs.client().chocolateTransparencyMultiplier.getF(),
                                    "block/fluids/yellow_jelly_still",
                                    "block/fluids/yellow_jelly_flow"
                            )
                    )
                    .lang("Yellow Jelly")
                    .properties(b -> b.viscosity(2000)
                            .density(1400))
                    .fluidProperties(p -> p.levelDecreasePerBlock(2)
                            .tickRate(25)
                            .slopeFindDistance(3)
                            .explosionResistance(100f))
                    .tag(Tags.Fluids.HONEY)
                    .source(BaseFlowingFluid.Source::new)
                    .bucket()
                    .removeTab(CreativeModeTabs.SEARCH)
                    .tab(CC_TAB_KEY)
                    .tag(AllTags.commonItemTag("buckets/honey"))
                    .build()
                    .register();
    public static final FluidEntry<BaseFlowingFluid.Flowing> RED_JELLY =
            craftsconstruct.registrate()
                    .standardFluid("red_jelly",
                            SolidRenderedFluidType.create(
                                    0xAA232A,
                                    () -> 1f / 24f * AllConfigs.client().chocolateTransparencyMultiplier.getF(),
                                    "block/fluids/red_jelly_still",
                                    "block/fluids/red_jelly_flow"
                            )
                    )
                    .lang("Red Jelly")
                    .properties(b -> b.viscosity(2000)
                            .density(1400))
                    .fluidProperties(p -> p.levelDecreasePerBlock(2)
                            .tickRate(25)
                            .slopeFindDistance(3)
                            .explosionResistance(100f))
                    .tag(Tags.Fluids.HONEY)
                    .source(BaseFlowingFluid.Source::new)
                    .bucket()
                    .removeTab(CreativeModeTabs.SEARCH)
                    .tab(CC_TAB_KEY)
                    .tag(AllTags.commonItemTag("buckets/honey"))
                    .build()
                    .register();
    public static final FluidEntry<BaseFlowingFluid.Flowing> PINK_JELLY =
            craftsconstruct.registrate()
                    .standardFluid("pink_jelly",
                            SolidRenderedFluidType.create(
                                    0xB86BA6,
                                    () -> 1f / 24f * AllConfigs.client().chocolateTransparencyMultiplier.getF(),
                                    "block/fluids/pink_jelly_still",
                                    "block/fluids/pink_jelly_flow"
                            )
                    )
                    .lang("Pink Jelly")
                    .properties(b -> b.viscosity(2000)
                            .density(1400))
                    .fluidProperties(p -> p.levelDecreasePerBlock(2)
                            .tickRate(25)
                            .slopeFindDistance(3)
                            .explosionResistance(100f))
                    .tag(Tags.Fluids.HONEY)
                    .source(BaseFlowingFluid.Source::new)
                    .bucket()
                    .removeTab(CreativeModeTabs.SEARCH)
                    .tab(CC_TAB_KEY)
                    .tag(AllTags.commonItemTag("buckets/honey"))
                    .build()
                    .register();
    public static final FluidEntry<BaseFlowingFluid.Flowing> BLUE_JELLY =
            craftsconstruct.registrate()
                    .standardFluid("blue_jelly",
                            SolidRenderedFluidType.create(
                                    0x54ACC9,
                                    () -> 1f / 24f * AllConfigs.client().chocolateTransparencyMultiplier.getF(),
                                    "block/fluids/blue_jelly_still",
                                    "block/fluids/blue_jelly_flow"
                            )
                    )
                    .lang("Blue Jelly")
                    .properties(b -> b.viscosity(2000)
                            .density(1400))
                    .fluidProperties(p -> p.levelDecreasePerBlock(2)
                            .tickRate(25)
                            .slopeFindDistance(3)
                            .explosionResistance(100f))
                    .tag(Tags.Fluids.HONEY)
                    .source(BaseFlowingFluid.Source::new)
                    .bucket()
                    .removeTab(CreativeModeTabs.SEARCH)
                    .tab(CC_TAB_KEY)
                    .tag(AllTags.commonItemTag("buckets/honey"))
                    .build()
                    .register();



    //↓↓↓   DON'T TOUCH ANYTHING UNDER HERE   ↓↓↓


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

        @Override
        protected int getTintColor(FluidStack stack) {
            return 0x00FFFFFF;
        }
        @Override
        public int getTintColor(FluidState state, BlockAndTintGetter getter, BlockPos pos) {
            return 0x00FFFFFF;
        }

        @Override
        protected Vector3f getCustomFogColor() {
            return fogColor;
        }
        @Override
        protected float getFogDistanceModifier() {
            return fogDistance.get();
        }

        @Override
        public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
            consumer.accept(new IClientFluidTypeExtensions() {
                @Override
                public ResourceLocation getStillTexture() {
                    return stillTexture;
                }
                @Override
                public ResourceLocation getFlowingTexture() {
                    return flowingTexture;
                }
                @Override
                public int getTintColor(FluidStack stack) {
                    return 0xFFFFFFFF;
                }
                @Override
                public Vector3f modifyFogColor(Camera camera, float partialTick, ClientLevel level,
                                               int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor) {
                    return fogColor;
                }

                @Override
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