package superlord.wildlands.common.world.feature.decorator;

import java.util.List;
import java.util.Random;
import java.util.Set;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import superlord.wildlands.common.block.BeardMossBlock;
import superlord.wildlands.common.core.world.WLFeatures;

public class BeardMossTreeDecorator extends TreeDecorator {

	@Override
	protected TreeDecoratorType<?> func_230380_a_() {
		return WLFeatures.BEARD_MOSS.get();
	}

	@Override
	public void func_225576_a_(ISeedReader p_225576_1_, Random p_225576_2_, List<BlockPos> p_225576_3_, List<BlockPos> p_225576_4_, Set<BlockPos> p_225576_5_, MutableBoundingBox p_225576_6_) {
		p_225576_3_.forEach((p_236880_5_) -> {
	         if (p_225576_2_.nextInt(3) > 0) {
	            BlockPos blockpos = p_236880_5_.west();
	            if (Feature.isAirAt(p_225576_1_, blockpos)) {
	               this.func_227424_a_(p_225576_1_, blockpos, BeardMossBlock.EAST, p_225576_5_, p_225576_6_);
	            }
	         }

	         if (p_225576_2_.nextInt(3) > 0) {
	            BlockPos blockpos1 = p_236880_5_.east();
	            if (Feature.isAirAt(p_225576_1_, blockpos1)) {
	               this.func_227424_a_(p_225576_1_, blockpos1, BeardMossBlock.WEST, p_225576_5_, p_225576_6_);
	            }
	         }

	         if (p_225576_2_.nextInt(3) > 0) {
	            BlockPos blockpos2 = p_236880_5_.north();
	            if (Feature.isAirAt(p_225576_1_, blockpos2)) {
	               this.func_227424_a_(p_225576_1_, blockpos2, BeardMossBlock.SOUTH, p_225576_5_, p_225576_6_);
	            }
	         }

	         if (p_225576_2_.nextInt(3) > 0) {
	            BlockPos blockpos3 = p_236880_5_.south();
	            if (Feature.isAirAt(p_225576_1_, blockpos3)) {
	               this.func_227424_a_(p_225576_1_, blockpos3, BeardMossBlock.NORTH, p_225576_5_, p_225576_6_);
	            }
	         }

	      });
	}

}
