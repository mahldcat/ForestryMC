package forestry.api.arboriculture;

import net.minecraft.util.IStringSerializable;

/**
 * @see EnumForestryWoodType
 * @see EnumVanillaWoodType
 */
public interface IWoodType extends IStringSerializable {
	int getMetadata();

	float getHardness();

	int getCarbonization();

	int getCombustability();
	
	String getPlankTexture();
	
	String getDoorLowerTexture();
	
	String getDoorUpperTexture();
	
	String getBarkTexture();
	
	String getHeartTexture();
}
