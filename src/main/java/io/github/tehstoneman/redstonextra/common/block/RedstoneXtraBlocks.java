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
	public static RegistryObject< OrGateBlock >				OR_GATE				= REGISTERY.register( "or_gate", () -> new OrGateBlock() );
	public static RegistryObject< NandGateBlock >			NAND_GATE			= REGISTERY.register( "nand_gate", () -> new NandGateBlock() );
	public static RegistryObject< NorGateBlock >			NOR_GATE			= REGISTERY.register( "nor_gate", () -> new NorGateBlock() );
}
