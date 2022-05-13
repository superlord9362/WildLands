package superlord.wildlands.common.block;

import java.util.Random;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import superlord.wildlands.common.util.TreeSpawner;
import superlord.wildlands.init.WildLandsBlocks;

public class WLSapling extends BushBlock implements BonemealableBlock {
    public static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D);
    public static final IntegerProperty STAGE = BlockStateProperties.STAGE;
    private final TreeSpawner tree;

    public WLSapling(TreeSpawner tree, Block.Properties properties) {
        super(properties);
        this.tree = tree;
        this.registerDefaultState(this.stateDefinition.any().setValue(STAGE, Integer.valueOf(0)));
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter worldIn, BlockPos pos) {
    	if (this == WildLandsBlocks.COCONUT_SAPLING.get()) {
    		return state.is(BlockTags.DIRT) || state.is(BlockTags.SAND);
    	} else return state.is(BlockTags.DIRT);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @SuppressWarnings("deprecation")
	@Override
    public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, Random rand) {
        super.tick(state, worldIn, pos, rand);
        if (!worldIn.isAreaLoaded(pos, 1))
            return;
        if (worldIn.getLightEmission(pos.above()) >= 9 && rand.nextInt(7) == 0) {
            this.grow(worldIn, pos, state, rand);
        }

    }

    public void grow(ServerLevel world, BlockPos pos, BlockState state, Random rand) {
        if (state.getValue(STAGE) == 0) {
            world.setBlock(pos, state.cycle(STAGE), 4);
        } else {
            if (!net.minecraftforge.event.ForgeEventFactory.saplingGrowTree(world, rand, pos)) return;
            this.tree.spawn(world, world.getChunkSource().getGenerator(), pos, state, rand);
        }

    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level worldIn, Random rand, BlockPos pos, BlockState state) {
        return (double) worldIn.random.nextFloat() < 0.45D;
    }

    @Override
	public void performBonemeal(ServerLevel world, Random rand, BlockPos pos, BlockState state) {
        this.grow(world, pos, state, rand);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(STAGE);
    }

}