package superlord.wildlands.client.render;

import java.util.Map;
import java.util.stream.Stream;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;

import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;
import superlord.wildlands.WildLands;
import superlord.wildlands.common.entity.WLBoat.WLBoatTypes;
import superlord.wildlands.common.entity.WLChestBoat;

public class WLChestBoatRenderer extends BoatRenderer {
	private final Map<WLBoatTypes, Pair<ResourceLocation, ListModel<Boat>>> modChestBoatResources;

    public WLChestBoatRenderer(EntityRendererProvider.Context renderContext, boolean isChestBoot) {
        super(renderContext, isChestBoot);

                modChestBoatResources = Stream.of(WLBoatTypes.values()).collect(ImmutableMap.toImmutableMap((boatType) -> {
            return boatType;
        }, (boatType) -> {
            return Pair.of(
                    new ResourceLocation(WildLands.MOD_ID, "textures/entity/chest_boat/" + boatType.getName() + ".png"),
                    new ChestBoatModel(renderContext.bakeLayer(
                            new ModelLayerLocation(
                                    new ResourceLocation("chest_boat/oak"),
                                    "main"
                            )
                    ))
            );
        }));
    }

    public WLChestBoatRenderer(EntityRendererProvider.Context renderContext) {
        this(renderContext, true);
    }

    @Override
    public Pair<ResourceLocation, ListModel<Boat>> getModelWithLocation(Boat boat) {
        WLChestBoat moddedBoat = (WLChestBoat) boat;
        return modChestBoatResources.get(moddedBoat.getWLChestBoatType());
    }
}
