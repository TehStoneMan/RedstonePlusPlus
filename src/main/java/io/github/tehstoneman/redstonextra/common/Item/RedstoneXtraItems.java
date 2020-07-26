package io.github.tehstoneman.redstonextra.common.Item;

import io.github.tehstoneman.redstonextra.ModInfo;
import io.github.tehstoneman.redstonextra.RedstoneXtra;
import io.github.tehstoneman.redstonextra.common.block.RedstoneXtraBlocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RedstoneXtraItems
{
	public static final DeferredRegister< Item >	REGISTERY			= new DeferredRegister<>( ForgeRegistries.ITEMS, ModInfo.MOD_ID );

	public static RegistryObject< BlockItem >		REDSTONE_INVERTER	= REGISTERY.register( "inverter",
			() -> new BlockItem( RedstoneXtraBlocks.REDSTONE_INVERTER.get(), new Item.Properties().group( RedstoneXtra.ITEM_GROUP ) ) );
	public static RegistryObject< BlockItem >		AND_GATE			= REGISTERY.register( "and_gate",
			() -> new BlockItem( RedstoneXtraBlocks.AND_GATE.get(), new Item.Properties().group( RedstoneXtra.ITEM_GROUP ) ) );
}
