package io.github.tehstoneman.redstonextra.common.Item;

import io.github.tehstoneman.redstonextra.common.block.RedstoneXtraBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class RedstoneXtraItemGroup extends ItemGroup
{
	public RedstoneXtraItemGroup()
	{
		super( "redstonextra" );
	}

	@Override
	public ItemStack createIcon()
	{
		return new ItemStack( RedstoneXtraBlocks.REDSTONE_INVERTER.get() );
	}
}
