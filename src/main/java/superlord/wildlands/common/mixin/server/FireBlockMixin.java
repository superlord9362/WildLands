package superlord.wildlands.common.mixin.server;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FireBlock;

@Mixin(FireBlock.class)
public interface FireBlockMixin {

	@Invoker("setFlammable")
	void wl_setFlammable(Block block, int flameOdds, int burnOdds);

}
