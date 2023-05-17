package eu.midnightdust.motschen.verticalslabs.block;

import eu.midnightdust.motschen.verticalslabs.block.enums.VerticalSlabType;
import net.minecraft.block.*;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;

import javax.annotation.Nullable;
import java.util.Objects;


public class VerticalSlabBlock extends HorizontalFacingBlock implements Waterloggable {
  public static final BooleanProperty WATERLOGGED;
  public static final EnumProperty<VerticalSlabType> TYPE;
  private static final VoxelShape NORTH_SHAPE;
  private static final VoxelShape EAST_SHAPE;
  private static final VoxelShape SOUTH_SHAPE;
  private static final VoxelShape WEST_SHAPE;

  public VerticalSlabBlock(Settings settings) {
    super(settings);
    this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(WATERLOGGED, true).with(TYPE, VerticalSlabType.HALF));
  }

  @Override
  public FluidState getFluidState(BlockState blockState) {
    return blockState.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(blockState);
  }

  public boolean tryFillWithFluid(WorldAccess world, BlockPos pos, BlockState state, FluidState fluidState) {
    return Waterloggable.super.tryFillWithFluid(world, pos, state, fluidState);
  }

  public boolean canFillWithFluid(BlockView world, BlockPos pos, BlockState state, Fluid fluid) {
    return Waterloggable.super.canFillWithFluid(world, pos, state, fluid);
  }

  public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
    if (state.get(WATERLOGGED)) {
      world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
    }

    return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
  }

  public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
    switch (type) {
      case WATER:
        return world.getFluidState(pos).isIn(FluidTags.WATER);
      default:
        return false;
    }
  }

  @Override
  public BlockRenderType getRenderType(BlockState blockState) {
    return BlockRenderType.MODEL;
  }

  @Override
  protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
    builder.add(WATERLOGGED, FACING, TYPE);
  }

  @Override
  public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
    if (state.get(TYPE) == VerticalSlabType.DOUBLE) {
      return VoxelShapes.fullCube();
    }

    return switch (state.get(FACING)) {
      case NORTH -> NORTH_SHAPE;
      case EAST -> EAST_SHAPE;
      case SOUTH -> SOUTH_SHAPE;
      case WEST -> WEST_SHAPE;
      default -> super.getOutlineShape(state, view, pos, context);
    };
  }

  @Nullable
  public BlockState getPlacementState(ItemPlacementContext ctx) {
    BlockPos blockPos = ctx.getBlockPos();
    BlockState blockState = ctx.getWorld().getBlockState(blockPos);
    if (blockState.isOf(this)) {
      return (blockState.with(TYPE, VerticalSlabType.DOUBLE)).with(WATERLOGGED, false);
    } else {
      FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
      boolean waterLog = fluidState.isIn(FluidTags.WATER) && fluidState.getLevel() == 8;

      return Objects.requireNonNull(super.getPlacementState(ctx)).with(WATERLOGGED, waterLog)
        .with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }
  }

  public boolean canReplace(BlockState state, ItemPlacementContext context) {
    ItemStack itemStack = context.getStack();
    VerticalSlabType verticalSlabType = state.get(TYPE);
    Direction facing = state.get(FACING);

    if (verticalSlabType != VerticalSlabType.DOUBLE && itemStack.isOf(this.asItem())) {
      if (context.canReplaceExisting()) {
        boolean bl = context.getHitPos().y - (double)context.getBlockPos().getY() > 0.5; // true si pincha mas arriba false si pincha mas abajo
        boolean blSouth = context.getHitPos().z - (double)context.getBlockPos().getZ() > 0.5; // true si pincha mas sur false si pincha mas norte
        boolean blEast = context.getHitPos().x - (double)context.getBlockPos().getX() > 0.5; // true si pincha mas este false si pincha mas oeste
        Direction direction = context.getSide();
        return switch (facing) {
          case NORTH -> direction == Direction.SOUTH || blSouth && direction.getAxis().isHorizontal();
          case SOUTH -> direction == Direction.NORTH || !blSouth && direction.getAxis().isHorizontal();
          case EAST -> direction == Direction.WEST || blEast && direction.getAxis().isHorizontal();
          case WEST -> direction == Direction.EAST || !blEast && direction.getAxis().isHorizontal();
          default -> false;
        };
      } else {
        return true;
      }
    } else {
      return false;
    }
  }

  static {
    WATERLOGGED = Properties.WATERLOGGED;
    TYPE = EnumProperty.of("type", VerticalSlabType.class);
    NORTH_SHAPE = createCuboidShape(0, 0, 8, 16, 16, 16);
    EAST_SHAPE = createCuboidShape(0, 0, 0, 8, 16, 16);
    SOUTH_SHAPE = createCuboidShape(0, 0, 0, 16, 16, 8);
    WEST_SHAPE = createCuboidShape(8, 0, 0, 16, 16, 16);
  }
}
