package superlord.wildlands.init;

import java.util.Set;
import java.util.stream.Stream;

import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import net.minecraft.world.level.block.state.properties.WoodType;

public class WLWoodTypes extends WoodType {
	
	private static final Set<WoodType> VALUES = new ObjectArraySet<>();
	public static final WoodType CYPRESS = WLWoodTypes.register(new WLWoodTypes("bald_cypress"));
	public static final WoodType COCONUT = WLWoodTypes.register(new WLWoodTypes("coconut"));
	public static final WoodType CHARRED = WLWoodTypes.register(new WLWoodTypes("charred"));
	
	private final String name;
	
	protected WLWoodTypes(String name) {
		super(name);
		this.name = name;
	}
	
	public static WoodType register(WoodType type) {
		WLWoodTypes.VALUES.add(type);
		return type;
	}
	
	public static Stream<WoodType> getValues() {
		return WLWoodTypes.VALUES.stream();
	}
	
	@Override
	public String name() {
		return this.name;
	}

}
