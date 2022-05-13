package superlord.wildlands.common.block;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantBodyBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.phys.shapes.VoxelShape;
import superlord.wildlands.init.WildLandsBlocks;

public class BeardMossBlock extends GrowingPlantBodyBlock {
   public static final VoxelShape SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

   public BeardMossBlock(Properties properties) {
      super(properties, Direction.DOWN, SHAPE, false);
   }

   @Override
   protected GrowingPlantHeadBlock getHeadBlock() {
      return (GrowingPlantHeadBlock)WildLandsBlocks.BEARD_MOSS_TOP.get();
   }
}
