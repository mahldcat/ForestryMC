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
package forestry.greenhouse.multiblock;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import forestry.api.greenhouse.IInternalBlock;
import forestry.api.greenhouse.IInternalBlockFace;

public class InternalBlock implements IInternalBlock {
	@Nonnull
	private final BlockPos pos;
	@Nonnull
	private final World world;
	@Nonnull
	private final List<IInternalBlockFace> faces = new ArrayList<>(6);
	@Nullable
	private final IInternalBlock root;
	
	public InternalBlock(@Nonnull World world, @Nonnull BlockPos pos) {
		this.world = world;
		this.pos = pos;
		this.root = null;
		
		for (EnumFacing facing : EnumFacing.VALUES) {
			this.faces.add(new InternalBlockFace(facing, getPos().offset(facing), false));
		}
	}
	
	public InternalBlock(@Nonnull World world, @Nonnull BlockPos pos, EnumFacing rootFace, @Nonnull IInternalBlock root) {
		this.world = world;
		this.pos = pos;
		this.root = root;
		
		for (EnumFacing face : EnumFacing.VALUES) {
			this.faces.add(new InternalBlockFace(face, getPos().offset(face), face == rootFace));
		}
	}
	
	@Nonnull
	@Override
	public World getWorld() {
		return world;
	}
	
	@Nonnull
	@Override
	public BlockPos getPos() {
		return pos;
	}
	
	@Nonnull
	@Override
	public Collection<IInternalBlockFace> getFaces() {
		return faces;
	}
	
	@Nullable
	@Override
	public IInternalBlock getRoot() {
		return root;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof IInternalBlock)) {
			return false;
		}
		IInternalBlock internalBlock = (IInternalBlock) obj;
		return internalBlock.getPos().equals(getPos());
	}
}