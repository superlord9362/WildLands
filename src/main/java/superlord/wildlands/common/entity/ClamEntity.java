package superlord.wildlands.common.entity;

public class ClamEntity {/**extends AbstractFishEntity {
	private static final DataParameter<Boolean> OPEN = EntityDataManager.createKey(ClamEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> ONE = EntityDataManager.createKey(ClamEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> TWO = EntityDataManager.createKey(ClamEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> THREE = EntityDataManager.createKey(ClamEntity.class, DataSerializers.BOOLEAN);

	public ClamEntity(EntityType<? extends ClamEntity> p_i50196_1_, World p_i50196_2_) {
		super(p_i50196_1_, p_i50196_2_);
	}

	public boolean isOpen() {
		return this.dataManager.get(OPEN);
	}

	private void setOpen(boolean isOpen) {
		this.dataManager.set(OPEN, isOpen);
	}

	public boolean isOne() {
		return this.dataManager.get(ONE);
	}

	private void setOne(boolean isOne) {
		this.dataManager.set(ONE, isOne);
	}

	public boolean isTwo() {
		return this.dataManager.get(TWO);
	}

	private void setTwo(boolean isTwo) {
		this.dataManager.set(TWO, isTwo);
	}

	public boolean isThree() {
		return this.dataManager.get(THREE);
	}

	private void setThree(boolean isThree) {
		this.dataManager.set(THREE, isThree);
	}

	protected void registerGoals() {
	}

	public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
		int color = rand.nextInt(99);
		if (color <= 32) {
			this.setOne(true);
		} else if (color > 32 && color <= 65) {
			this.setTwo(true);
		} else {
			this.setThree(true);
		}
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	protected void registerData() {
		super.registerData();
		this.dataManager.register(OPEN, false);
		this.dataManager.register(ONE, false);
		this.dataManager.register(TWO, false);
		this.dataManager.register(THREE, false);
	}

	public static AttributeModifierMap.MutableAttribute createAttributes() {
		return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 12.0D);
	}

	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.setOpen(compound.getBoolean("Open"));
		this.setOne(compound.getBoolean("One"));
		this.setTwo(compound.getBoolean("Two"));
		this.setThree(compound.getBoolean("Three"));
	}

	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putBoolean("Open", this.isOpen());
		compound.putBoolean("One", this.isOne());
		compound.putBoolean("Two", this.isTwo());
		compound.putBoolean("Three", this.isThree());
	}

	public void livingTick() {
		super.livingTick();
		this.setMotion(0, -1, 0);
	}

	public boolean func_241845_aY() {
		return this.isAlive();
	}

	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 0.5F;
	}

	public int getMaxSpawnedInChunk() {
		return 1;
	}

	public void applyEntityCollision(Entity entityIn) {
	}

	public float getCollisionBorderSize() {
		return 0.0F;
	}

	private void spawnItem(ItemStack stack) {
		ItemEntity itemEntity = new ItemEntity(this.world, this.getPosX(), this.getPosY(), this.getPosZ(), stack);
		this.world.addEntity(itemEntity);
	}

	public ActionResultType func_230254_b_(PlayerEntity player, Hand hand) {
		ItemStack itemstack = player.getHeldItem(hand);
		ItemStack itemstack1 = new ItemStack(WildLandsItems.PEARL.get());
		Item item = itemstack.getItem();
		Random rand = new Random();
		if (!this.isOpen()) {
			if (item == Items.SAND || item == WildLandsItems.FINE_SAND.get() || item == Items.RED_SAND) {
				int chance = rand.nextInt(5);
				if (!player.isCreative()) {
					itemstack.shrink(1);					
				}
				if (chance == 0) {
					this.setOpen(true);
					world.playSound(player, this.getPosition(), WildLandsSounds.CLAM_OPEN, SoundCategory.BLOCKS, 1, 1);
					return ActionResultType.SUCCESS;
				}
				return ActionResultType.PASS;
			}
		}
		if (this.isOpen()) {
			player.attackEntityFrom(DamageSourceInit.CLAM, 1.0F);
			this.spawnItem(itemstack1);
			world.playSound(player, this.getPosition(), WildLandsSounds.CLAM_CLOSE, SoundCategory.BLOCKS, 1, 1);
			this.setOpen(false);
		}
		return super.func_230254_b_(player, hand);
	}

	public static boolean canSpawn(EntityType<? extends ClamEntity> type, IWorld worldIn, SpawnReason reason, BlockPos p_223363_3_, Random randomIn) {
		return worldIn.getBlockState(p_223363_3_).isIn(Blocks.WATER) && worldIn.getBlockState(p_223363_3_.up()).isIn(Blocks.WATER);
	}


	@Override
	protected ItemStack getFishBucket() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected SoundEvent getFlopSound() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack getPickedResult(RayTraceResult target) {
		return new ItemStack(WildLandsItems.CLAM_SPAWN_EGG.get());
	}
*/
}
