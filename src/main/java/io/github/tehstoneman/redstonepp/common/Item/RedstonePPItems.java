package io.github.tehstoneman.redstonepp.common.Item;

import io.github.tehstoneman.redstonepp.ModInfo;
import io.github.tehstoneman.redstonepp.RedstonePlusPlus;
import io.github.tehstoneman.redstonepp.common.block.RedstonePPBlocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RedstonePPItems
{
	public static final DeferredRegister< Item >	REGISTERY			= new DeferredRegister<>( ForgeRegistries.ITEMS, ModInfo.MOD_ID );

	public static RegistryObject< BlockItem >		REDSTONE_INVERTER	= REGISTERY.register( "inverter",
			() -> new BlockItem( RedstonePPBlocks.REDSTONE_INVERTER.get(), new Item.Properties().group( RedstonePlusPlus.ITEM_GROUP ) ) );
	public static RegistryObject< BlockItem >		AND_GATE			= REGISTERY.register( "and_gate",
			() -> new BlockItem( RedstonePPBlocks.AND_GATE.get(), new Item.Properties().group( RedstonePlusPlus.ITEM_GROUP ) ) );
}
