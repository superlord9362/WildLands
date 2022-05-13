package superlord.wildlands.common.entity.controllers;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ambient.AmbientCreature;

public class SwimMoveController extends MoveControl {
    private final AmbientCreature entity;
    private float speedMulti;
    private float yawLimit = 3.0F;
    public SwimMoveController(AmbientCreature entity, float speedMulti) {
        super(entity);
        this.entity = entity;
        this.speedMulti = speedMulti;
    }

    public SwimMoveController(AmbientCreature entity, float speedMulti, float yawLimit) {
        super(entity);
        this.entity = entity;
        this.yawLimit = yawLimit;
        this.speedMulti = speedMulti;
    }

    public void tick() {
        if (this.entity.isInWater()) {
            this.entity.setDeltaMovement(this.entity.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
        }
        if (this.operation == Operation.MOVE_TO && !this.entity.getNavigation().isDone()) {
            double d0 = this.wantedX - this.entity.getX();
            double d1 = this.wantedY - this.entity.getY();
            double d2 = this.wantedZ - this.entity.getZ();
            double d3 = (double)Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
            d1 /= d3;
            float f = (float)(Mth.atan2(d2, d0) * 57.2957763671875D) - 90.0F;
            this.entity.yRotO = this.rotlerp(this.entity.getYRot(), f, yawLimit);
            this.entity.yBodyRot = this.entity.getYRot();
            float f1 = (float)(this.speedModifier * this.entity.getAttributeValue(Attributes.MOVEMENT_SPEED) * speedMulti);
            this.entity.setSpeed(f1 * 0.4F);
            this.entity.setDeltaMovement(this.entity.getDeltaMovement().add(0.0D, (double)this.entity.getSpeed() * d1 * 0.6D, 0.0D));
        } else {
            this.entity.setSpeed(0.0F);
        }
    }
}