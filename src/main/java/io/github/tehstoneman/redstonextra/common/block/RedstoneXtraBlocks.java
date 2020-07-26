package io.github.tehstoneman.redstonextra.common.block;

import io.github.tehstoneman.redstonextra.ModInfo;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RedstoneXtraBlocks
{
	public static final DeferredRegister< Block >			REGISTERY			= new DeferredRegister<>( ForgeRegistries.BLOCKS, ModInfo.MOD_ID );

	public static RegistryObject< RedstoneInverterBlock >	REDSTONE_INVERTER	= REGISTERY.register( "inverter", () -> new RedstoneInverterBlock() );
	public static RegistryObject< AndGateBlock >			AND_GATE			= REGISTERY.register( "and_gate", () -> new AndGateBlock() );
}
