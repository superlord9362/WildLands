package superlord.wildlands.client.render;

import com.mojang.datafixers.util.Pair;

import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import superlord.wildlands.WildLands;
import superlord.wildlands.common.entity.WLBoat;

@OnlyIn(Dist.CLIENT)
public class WLBoatRenderer extends BoatRenderer {
    public static final ModelLayerLocation COCONUT_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(WildLands.MOD_ID, "boat/coconut"), "main");
    public static final ModelLayerLocation CHARRED_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(WildLands.MOD_ID, "boat/charred"), "main");
    public static final ModelLayerLocation CYPRESS_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(WildLands.MOD_ID, "boat/bald_cypress"), "main");

    private final Pair<ResourceLocation, BoatModel> coconut;
    private final Pair<ResourceLocation, BoatModel> charred;
    private final Pair<ResourceLocation, BoatModel> cypress;

    public WLBoatRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.8F;
        coconut = new Pair<>(new ResourceLocation(WildLands.MOD_ID, "textures/entity/boat/coconut.png"), new BoatModel(context.bakeLayer(COCONUT_LAYER_LOCATION)));
        charred = new Pair<>(new ResourceLocation(WildLands.MOD_ID, "textures/entity/boat/charred.png"), new BoatModel(context.bakeLayer(CHARRED_LAYER_LOCATION)));
        cypress = new Pair<>(new ResourceLocation(WildLands.MOD_ID, "textures/entity/boat/bald_cypress.png"), new BoatModel(context.bakeLayer(CYPRESS_LAYER_LOCATION)));
    }

    @Override
    public Pair<ResourceLocation, BoatModel> getModelWithLocation(Boat boat) {
        switch (((WLBoat)boat).getWLBoatType()) {
            case COCONUT:
                return coconut;
            case CHARRED:
                return charred;
            default:
                return cypress;
        }
    }
}