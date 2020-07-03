package io.github.tehstoneman.redstonepp.common.Item;

import io.github.tehstoneman.redstonepp.common.block.RedstonePPBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class RedstonePPItemGroup extends ItemGroup
{
	public RedstonePPItemGroup()
	{
		super( "redstone_plus_plus" );
	}

	@Override
	public ItemStack createIcon()
	{
		return new ItemStack( RedstonePPBlocks.REDSTONE_INVERTER.get() );
	}
}
