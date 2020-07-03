package io.github.tehstoneman.redstonepp;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.tehstoneman.redstonepp.common.Item.RedstonePPItemGroup;
import io.github.tehstoneman.redstonepp.common.Item.RedstonePPItems;
import io.github.tehstoneman.redstonepp.common.block.RedstonePPBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod( ModInfo.MOD_ID )
public class RedstonePlusPlus
{
	// Directly reference a log4j logger.
	public static final Logger		LOGGER		= LogManager.getLogger();
	public static final ItemGroup	ITEM_GROUP	= new RedstonePPItemGroup();

	public static Random			RANDOM;

	public RedstonePlusPlus()
	{
		// Initialize random numbers
		RANDOM = new Random();

		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		RedstonePPBlocks.REGISTERY.register( modEventBus );
		RedstonePPItems.REGISTERY.register( modEventBus );
	}
}
