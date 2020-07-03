package io.github.tehstoneman.redstonepp.client;

import io.github.tehstoneman.redstonepp.ModInfo;
import io.github.tehstoneman.redstonepp.common.block.RedstonePPBlocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber( modid = ModInfo.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD )
public class ClientSetup
{
	@SubscribeEvent
	public static void init( final FMLClientSetupEvent event )
	{
		RenderTypeLookup.setRenderLayer( RedstonePPBlocks.REDSTONE_INVERTER.get(), RenderType.getCutout() );
	}
}
