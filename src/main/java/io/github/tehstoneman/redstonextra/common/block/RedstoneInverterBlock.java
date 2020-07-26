package io.github.tehstoneman.redstonextra.common.block;

import java.util.Random;

import javax.annotation.Nullable;

import io.github.tehstoneman.redstonextra.RedstoneXtra;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.RedstoneWireBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class RedstoneInverterBlock extends HorizontalBlock
{
	protected static final VoxelShape	SHAPE	= Block.makeCuboidShape( 0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D );
	public static final BooleanProperty	POWERED	= BlockStateProperties.POWERED;

	protected RedstoneInverterBlock()
	{
		super( Properties.from( Blocks.REPEATER ) );
		setDefaultState( stateContainer.getBaseState().with( HORIZONTAL_FACING, Direction.NORTH ).with( POWERED, Boolean.valueOf( true ) ) );
	}

	@Override
	public VoxelShape getShape( BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context )
	{
		return SHAPE;
	}

	@Override
	public boolean isValidPosition( BlockState state, IWorldReader worldIn, BlockPos pos )
	{
		return hasSolidSideOnTop( worldIn, pos.down() );
	}

	@Override
	public BlockState getStateForPlacement( BlockItemUseContext context )
	{
		final Direction direction = context.getPlacementHorizontalFacing().getOpposite();
		final int power = getPowerFromSide( context.getWorld(), context.getPos(), direction );

		return getDefaultState().with( HORIZONTAL_FACING, direction ).with( POWERED, power == 0 );
	}

	@Override
	protected void fillStateContainer( StateContainer.Builder< Block, BlockState > builder )
	{
		builder.add( HORIZONTAL_FACING, POWERED );
	}

	@Override
	public void neighborChanged( BlockState thisState, World world, BlockPos thisPos, Block fromBlock, BlockPos fromPos, boolean isMoving )
	{
		if( thisState.isValidPosition( world, thisPos ) )
		{
			final Direction direction = thisState.get( HORIZONTAL_FACING );
			final boolean isPowered = thisState.get( POWERED );
			final int power = getPowerFromSide( world, thisPos, direction );

			if( isPowered && power > 0 )
				world.setBlockState( thisPos, thisState.with( POWERED, false ), 3 );
			else if( !isPowered && power == 0 )
				world.setBlockState( thisPos, thisState.with( POWERED, true ), 3 );
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

	protected void updateState( World world, BlockPos pos, BlockState state )
	{
		final boolean isPowered = state.get( POWERED );
		final boolean shouldPowered = shouldBePowered( world, pos, state );
		RedstoneXtra.LOGGER.info( "UpdateState ==== {} : {} ====", isPowered, shouldPowered );

		if( isPowered && !shouldPowered )
			world.setBlockState( pos, state.with( POWERED, false ), 3 );
		else if( !isPowered )
			world.setBlockState( pos, state.with( POWERED, true ), 3 );
	}

	protected boolean shouldBePowered( World world, BlockPos pos, BlockState state )
	{
		return calculateInputStrength( world, pos, state ) == 0;
	}

	protected int calculateInputStrength( World world, BlockPos pos, BlockState state )
	{
		final Direction direction = state.get( HORIZONTAL_FACING );
		final BlockPos blockpos = pos.offset( direction );
		final int power = world.getRedstonePower( blockpos, direction );
		if( power >= 15 )
			return power;
		else
		{
			final BlockState blockstate = world.getBlockState( blockpos );
			return Math.max( power, blockstate.getBlock() == Blocks.REDSTONE_WIRE ? blockstate.get( RedstoneWireBlock.POWER ) : 0 );
		}
	}

	@Override
	public boolean canConnectRedstone( BlockState state, IBlockReader world, BlockPos pos, @Nullable Direction side )
	{
		final Direction facing = state.get( HORIZONTAL_FACING );
		return side == facing || side == facing.getOpposite();
	}

	@Override
	public int getStrongPower( BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side )
	{
		return blockState.getWeakPower( blockAccess, pos, side );
	}

	@Override
	public int getWeakPower( BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side )
	{
		if( !blockState.get( POWERED ) )
			return 0;
		else
			return blockState.get( HORIZONTAL_FACING ) == side ? getActiveSignal( blockAccess, pos, blockState ) : 0;
	}

	protected int getActiveSignal( IBlockReader world, BlockPos pos, BlockState state )
	{
		return 15;
	}

	@Override
	@OnlyIn( Dist.CLIENT )
	public void animateTick( BlockState state, World world, BlockPos pos, Random rand )
	{
		final Direction direction = state.get( HORIZONTAL_FACING );
		final double d0 = pos.getX() + 0.5F + ( rand.nextFloat() - 0.5F ) * 0.2D;
		final double d1 = pos.getY() + 0.4F + ( rand.nextFloat() - 0.5F ) * 0.2D;
		final double d2 = pos.getZ() + 0.5F + ( rand.nextFloat() - 0.5F ) * 0.2D;
		float f = 7.0F;
		if( state.get( POWERED ) )
			f = -5.0F;
		f = f / 16.0F;
		final double d3 = f * direction.getXOffset();
		final double d4 = f * direction.getZOffset();
		world.addParticle( RedstoneParticleData.REDSTONE_DUST, d0 + d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D );
	}
}
