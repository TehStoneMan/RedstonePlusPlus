package io.github.tehstoneman.redstonextra.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.RedstoneWireBlock;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class ComponantBaseBlock extends HorizontalBlock
{
	protected static final VoxelShape	SHAPE	= Block.makeCuboidShape( 0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D );
	public static final BooleanProperty	POWERED	= BlockStateProperties.POWERED;

	public ComponantBaseBlock()
	{
		super( Properties.from( Blocks.REPEATER ) );
		// TODO Auto-generated constructor stub
	}

	@Override
	public VoxelShape getShape( BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context )
	{
		return SHAPE;
	}

	@Override
	protected void fillStateContainer( StateContainer.Builder< Block, BlockState > builder )
	{
		builder.add( HORIZONTAL_FACING, POWERED );
	}

	@Override
	public boolean isValidPosition( BlockState state, IWorldReader worldIn, BlockPos pos )
	{
		return hasSolidSideOnTop( worldIn, pos.down() );
	}

	@Override
	public boolean canProvidePower( BlockState state )
	{
		return true;
	}

	@Override
	public int getStrongPower( BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side )
	{
		return blockState.getWeakPower( blockAccess, pos, side );
	}

	protected int getPowerFromSide( World world, BlockPos pos, Direction direction )
	{
		final BlockPos offset = pos.offset( direction );
		final int power = world.getRedstonePower( offset, direction );
		if( power > 0 )
			return power;
		else
		{
			final BlockState blockstate = world.getBlockState( offset );
			return Math.max( power, blockstate.getBlock() == Blocks.REDSTONE_WIRE ? blockstate.get( RedstoneWireBlock.POWER ) : 0 );
		}
	}


	protected int getActiveSignal( IBlockReader world, BlockPos pos, BlockState state )
	{
		return 15;
	}
}
