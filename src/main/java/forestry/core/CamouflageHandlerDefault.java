/*******************************************************************************
 * Copyright (c) 2011-2014 SirSengir.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Various Contributors including, but not limited to:
 * SirSengir (original work), CovertJaguar, Player, Binnie, MysteriousAges
 ******************************************************************************/
package forestry.core;

import forestry.api.core.CamouflageManager;
import forestry.api.core.ICamouflageHandler;
import forestry.api.core.ICamouflageItemHandler;
import forestry.api.core.ICamouflagedTile;
import org.apache.commons.lang3.tuple.Pair;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockModelShapes;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.item.ItemStack;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CamouflageHandlerDefault implements ICamouflageItemHandler {

	@Override
	public boolean canHandle(ItemStack stack) {
		if(stack == null || stack.getItem() == null || stack.stackSize <= 0 || Block.getBlockFromItem(stack.getItem()) == null){
			return false;
		}
		Block block = Block.getBlockFromItem(stack.getItem());
		IBlockState stateFromMeta = block.getStateFromMeta(stack.getItemDamage());
		
		return stateFromMeta.isOpaqueCube() && !block.hasTileEntity(stateFromMeta) && block.isNormalCube(stateFromMeta) && block.isFullCube(stateFromMeta) && block.isBlockNormalCube(stateFromMeta);
	}

	@Override
	public String getType() {
		return CamouflageManager.DEFAULT;
	}

	@Override
	public float getLightTransmittance(ItemStack stack, ICamouflageHandler camouflageHandler) {
		return 0F;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public Pair<IBlockState, IBakedModel> getModel(ItemStack stack, ICamouflageHandler camouflageHandler, ICamouflagedTile camouflageTile) {
		if(camouflageHandler == null || stack == null || stack.getItem() == null || stack.stackSize <= 0 || Block.getBlockFromItem(stack.getItem()) == null){
			return null;
		}
		BlockModelShapes modelShapes = Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes();
		Block block = Block.getBlockFromItem(stack.getItem());
		IBlockState state = block.getStateFromMeta(stack.getItemDamage());

		return Pair.of(state, modelShapes.getModelForState(state));
	}

}
