package net.verticalslabs.block;

import com.mojang.serialization.MapCodec;
import net.verticalslabs.block.enums.VerticalSlabType;
import net.minecraft.block.*;
import net.minecraft.entity.ai.pathing.NavigationType;
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
import org.jetbrains.annotations.Nullable;

import java.util.Objects;


public class VerticalSlabBlock extends HorizontalFacingBlock implements Waterloggable {
  public static final MapCodec<VerticalSlabBlock> CODEC = createCodec(VerticalSlabBlock::new);
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
  protected MapCodec<? extends HorizontalFacingBlock> getCodec() {
    return CODEC;
  }

  @Override
  public FluidState getFluidState(BlockState blockState) {
    return blockState.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(blockState);
  }

  public boolean tryFillWithFluid(WorldAccess world, BlockPos pos, BlockState state, FluidState fluidState) {
    return Waterloggable.super.tryFillWithFluid(world, pos, state, fluidState);
  }

  public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
    if (state.get(WATERLOGGED)) {
      world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
    }

    return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
  }

  public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
    if (Objects.requireNonNull(type) == NavigationType.WATER) {
      return world.getFluidState(pos).isIn(FluidTags.WATER);
    }

    return false;
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
  public boolean hasSidedTransparency(BlockState state) {
    return state.get(TYPE) != VerticalSlabType.DOUBLE;
  }


  @Override
  public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
    if (!hasSidedTransparency(state)) {
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
    Direction facing = state.get(FACING);

    if (hasSidedTransparency(state) && itemStack.isOf(this.asItem())) {
      if (context.canReplaceExisting()) {
        boolean blSouth = context.getHitPos().z - (double)context.getBlockPos().getZ() > 0.5;
        boolean blEast = context.getHitPos().x - (double)context.getBlockPos().getX() > 0.5;
        Direction direction = context.getSide();

        return switch (facing) {
          case NORTH -> direction == Direction.NORTH || !blSouth && direction.getAxis().isHorizontal();
          case SOUTH -> direction == Direction.SOUTH || blSouth && direction.getAxis().isHorizontal();
          case EAST -> direction == Direction.EAST || blEast && direction.getAxis().isHorizontal();
          case WEST -> direction == Direction.WEST || !blEast && direction.getAxis().isHorizontal();
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
    NORTH_SHAPE = Block.createCuboidShape(0.0, 0.0, 8.0, 16.0, 16.0, 16.0);
    EAST_SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 8.0, 16.0, 16.0);
    SOUTH_SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 8.0);
    WEST_SHAPE = Block.createCuboidShape(8.0, 0.0, 0.0, 16.0, 16.0, 16.0);
  }
}
