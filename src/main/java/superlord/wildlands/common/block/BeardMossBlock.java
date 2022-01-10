package superlord.wildlands.common.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.AbstractBodyPlantBlock;
import net.minecraft.block.AbstractTopPlantBlock;
import net.minecraft.block.Block;
import net.minecraft.util.Direction;
import net.minecraft.util.math.shapes.VoxelShape;
import superlord.wildlands.init.WildLandsBlocks;

public class BeardMossBlock extends AbstractBodyPlantBlock {
   public static final VoxelShape SHAPE = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

   public BeardMossBlock(AbstractBlock.Properties properties) {
      super(properties, Direction.DOWN, SHAPE, false);
   }

   protected AbstractTopPlantBlock getTopPlantBlock() {
      return (AbstractTopPlantBlock)WildLandsBlocks.BEARD_MOSS_TOP.get();
   }
}
