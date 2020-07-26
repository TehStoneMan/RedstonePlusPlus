package io.github.tehstoneman.redstonextra;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.tehstoneman.redstonextra.common.Item.RedstoneXtraItemGroup;
import io.github.tehstoneman.redstonextra.common.Item.RedstoneXtraItems;
import io.github.tehstoneman.redstonextra.common.block.RedstoneXtraBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod( ModInfo.MOD_ID )
public class RedstoneXtra
{
	// Directly reference a log4j logger.
	public static final Logger		LOGGER		= LogManager.getLogger();
	public static final ItemGroup	ITEM_GROUP	= new RedstoneXtraItemGroup();

	public static Random			RANDOM;

	public RedstoneXtra()
	{
		// Initialize random numbers
		RANDOM = new Random();

		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		RedstoneXtraBlocks.REGISTERY.register( modEventBus );
		RedstoneXtraItems.REGISTERY.register( modEventBus );
	}
}
