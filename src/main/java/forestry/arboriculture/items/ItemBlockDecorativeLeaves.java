package forestry.arboriculture.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import forestry.api.arboriculture.IFruitProvider;
import forestry.api.arboriculture.ITreeGenome;
import forestry.arboriculture.PluginArboriculture;
import forestry.arboriculture.blocks.BlockDecorativeLeaves;
import forestry.arboriculture.genetics.TreeDefinition;
import forestry.core.items.IColoredItem;
import forestry.core.items.ItemBlockForestry;
import forestry.core.utils.Translator;

public class ItemBlockDecorativeLeaves extends ItemBlockForestry<BlockDecorativeLeaves> implements IColoredItem {
	public ItemBlockDecorativeLeaves(Block block) {
		super(block);
	}

	@Override
	public String getItemStackDisplayName(ItemStack itemStack) {
		int meta = itemStack.getMetadata();
		BlockDecorativeLeaves block = getBlock();
		TreeDefinition treeDefinition = block.getTreeType(meta);
		if (treeDefinition == null) {
			return Translator.translateToLocal("trees.grammar.leaves.type");
		}

		String unlocalizedSpeciesName = treeDefinition.getGenome().getPrimary().getUnlocalizedName();
		return ItemBlockLeaves.getDisplayName(unlocalizedSpeciesName);
	}

	@Override
	public int getColorFromItemstack(ItemStack itemStack, int renderPass) {
		int meta = itemStack.getMetadata();
		BlockDecorativeLeaves block = getBlock();
		TreeDefinition treeDefinition = block.getTreeType(meta);
		if (treeDefinition == null) {
			return PluginArboriculture.proxy.getFoliageColorBasic();
		}

		ITreeGenome genome = treeDefinition.getGenome();

		if (renderPass == 0) {
			return genome.getPrimary().getLeafSpriteProvider().getColor(false);
		} else {
			IFruitProvider fruitProvider = genome.getFruitProvider();
			return fruitProvider.getDecorativeColor();
		}
	}
}
