package io.github.tehstoneman.redstonextra.common.block;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class NorGateBlock extends ComponantBaseBlock
{
	public static final BooleanProperty	A	= BooleanProperty.create( "a" );
	public static final BooleanProperty	B	= BooleanProperty.create( "b" );

	protected NorGateBlock()
	{
		setDefaultState( stateContainer.getBaseState().with( HORIZONTAL_FACING, Direction.NORTH ).with( A, Boolean.valueOf( false ) )
				.with( B, Boolean.valueOf( false ) ).with( POWERED, Boolean.valueOf( false ) ) );
	}

	@Override
	public BlockState getStateForPlacement( BlockItemUseContext context )
	{
		final Direction direction = context.getPlacementHorizontalFacing().getOpposite();
		final int powerA = getPowerFromSide( context.getWorld(), context.getPos(), direction.rotateY() );
		final int powerB = getPowerFromSide( context.getWorld(), context.getPos(), direction.rotateYCCW() );
		final boolean powerOutput = powerA > 0 || powerB > 0;

		return getDefaultState().with( HORIZONTAL_FACING, direction ).with( A, powerA > 0 ).with( B, powerB > 0 ).with( POWERED, !powerOutput );
	}

	@Override
	protected void fillStateContainer( StateContainer.Builder< Block, BlockState > builder )
	{
		super.fillStateContainer( builder );
		builder.add( A, B );
	}

	@Override
	public void neighborChanged( BlockState thisState, World world, BlockPos thisPos, Block fromBlock, BlockPos fromPos, boolean isMoving )
	{
		if( thisState.isValidPosition( world, thisPos ) )
		{
			final Direction direction = thisState.get( HORIZONTAL_FACING );
			final boolean isPowered = thisState.get( POWERED );
			final int powerA = getPowerFromSide( world, thisPos, direction.rotateY() );
			final int powerB = getPowerFromSide( world, thisPos, direction.rotateYCCW() );
			final boolean powerOutput = powerA > 0 || powerB > 0;

			world.setBlockState( thisPos, thisState.with( A, powerA > 0 ).with( B, powerB > 0 ).with( POWERED, !powerOutput ), 3 );
		}
		else
		{
			// Position is invalid - Drop self
			final TileEntity tileentity = thisState.hasTileEntity() ? world.getTileEntity( thisPos ) : null;
			spawnDrops( thisState, world, thisPos, tileentity );
			world.removeBlock( thisPos, false );

			for( final Direction direction : Direction.values() )
				world.notifyNeighborsOfStateChange( thisPos.offset( direction ), this );

		}
	}

	@Override
	public boolean canConnectRedstone( BlockState state, IBlockReader world, BlockPos pos, @Nullable Direction side )
	{
		final Direction facing = state.get( HORIZONTAL_FACING );
		return side != null && side.getYOffset() == 0 && side != facing.getOpposite();
	}

	@Override
	public int getWeakPower( BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side )
	{
		if( !blockState.get( POWERED ) )
			return 0;
		else
			return blockState.get( HORIZONTAL_FACING ) == side ? getActiveSignal( blockAccess, pos, blockState ) : 0;
	}
}
