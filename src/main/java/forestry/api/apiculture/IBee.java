/*******************************************************************************
 * Copyright 2011-2014 SirSengir
 *
 * This work (the API) is licensed under the "MIT" License, see LICENSE.txt for details.
 ******************************************************************************/
package forestry.api.apiculture;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.Biome;

import forestry.api.arboriculture.ITree;
import forestry.api.core.IErrorState;
import forestry.api.genetics.IEffectData;
import forestry.api.genetics.IIndividualLiving;

/**
 * Other implementations than Forestry's default one are not supported.
 *
 * @author SirSengir
 */
public interface IBee extends IIndividualLiving {

	/**
	 * @return Bee's genetic information.
	 */
	@Nonnull
	@Override
	IBeeGenome getGenome();

	/**
	 * @return Genetic information of the bee's mate, null if unmated.
	 */
	@Nullable
	@Override
	IBeeGenome getMate();

	/**
	 * @return true if the individual is originally of natural origin.
	 */
	boolean isNatural();

	/**
	 * @return generation this individual is removed from the original individual.
	 */
	int getGeneration();

	/**
	 * Set the natural flag on this bee.
	 * @param flag
	 */
	void setIsNatural(boolean flag);

	IEffectData[] doEffect(IEffectData[] storedData, IBeeHousing housing);

	IEffectData[] doFX(IEffectData[] storedData, IBeeHousing housing);

	/**
	 * @return true if the bee may spawn offspring
	 */
	boolean canSpawn();

	/**
	 * Determines whether the queen can work.
	 * @param housing the {@link IBeeHousing} the bee currently resides in.
	 * @return an empty set if the queen can work, a set of error states if the queen can not work
	 */
	Set<IErrorState> getCanWork(IBeeHousing housing);

	List<Biome> getSuitableBiomes();

	ItemStack[] getProduceList();

	ItemStack[] getSpecialtyList();

	ItemStack[] produceStacks(IBeeHousing housing);

	IBee spawnPrincess(IBeeHousing housing);

	IBee[] spawnDrones(IBeeHousing housing);

	void plantFlowerRandom(IBeeHousing housing);

	ITree retrievePollen(IBeeHousing housing);

	boolean pollinateRandom(IBeeHousing housing, ITree pollen);

}
