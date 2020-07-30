package io.github.tehstoneman.redstonextra.client;

import io.github.tehstoneman.redstonextra.ModInfo;
import io.github.tehstoneman.redstonextra.common.block.RedstoneXtraBlocks;
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
		RenderTypeLookup.setRenderLayer( RedstoneXtraBlocks.REDSTONE_INVERTER.get(), RenderType.getCutout() );
		RenderTypeLookup.setRenderLayer( RedstoneXtraBlocks.AND_GATE.get(), RenderType.getCutout() );
		RenderTypeLookup.setRenderLayer( RedstoneXtraBlocks.OR_GATE.get(), RenderType.getCutout() );
	}
}
