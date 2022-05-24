package superlord.wildlands.common.entity.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import superlord.wildlands.init.WLBlockEntities;

public class WLSignBlockEntity extends SignBlockEntity {
    public WLSignBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
    }

    @Override
    public BlockEntityType<?> getType() {
        return WLBlockEntities.WL_SIGNS.get();
    }

}
