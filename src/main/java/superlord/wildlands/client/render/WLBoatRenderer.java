package superlord.wildlands.client.render;

import java.util.Map;
import java.util.stream.Stream;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;

import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import superlord.wildlands.WildLands;
import superlord.wildlands.common.entity.WLBoat;
import superlord.wildlands.common.entity.WLBoat.WLBoatTypes;

@OnlyIn(Dist.CLIENT)
public class WLBoatRenderer extends BoatRenderer {
	private final Map<WLBoatTypes, Pair<ResourceLocation, ListModel<Boat>>> modBoatResources;

    public WLBoatRenderer(EntityRendererProvider.Context renderContext, boolean isChestBoot) {
        super(renderContext, isChestBoot);
        modBoatResources = Stream.of(WLBoatTypes.values()).collect(ImmutableMap.toImmutableMap((boatType) -> {
            return boatType;
        }, (boatType) -> {
            return Pair.of(
                new ResourceLocation(WildLands.MOD_ID, "textures/entity/boat/" + boatType.getName() + ".png"),
                new BoatModel(renderContext.bakeLayer(
                    new ModelLayerLocation(
                        new ResourceLocation("boat/oak"),
                        "main"
                    )
                ))
            );
        }));
    }

    public WLBoatRenderer(EntityRendererProvider.Context renderContext) {
        this(renderContext, false);
    }
    
    @Override
    public Pair<ResourceLocation, ListModel<Boat>> getModelWithLocation(Boat boat) {
        WLBoat moddedBoat = (WLBoat) boat;
        return modBoatResources.get(moddedBoat.getWLBoatType());
    }

	
}