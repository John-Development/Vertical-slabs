package net.verticalslabs.block;

import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class CopperVerticalSlabBlock extends VerticalSlabBlock implements Oxidizable {
  private final OxidationLevel oxidationLevel;

  public CopperVerticalSlabBlock(OxidationLevel oxidationLevel, Settings settings) {
    super(settings);
    this.oxidationLevel = oxidationLevel;
  }

  public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
    this.tickDegradation(state, world, pos, random);
  }

  public boolean hasRandomTicks(BlockState state) {
    return Oxidizable.getIncreasedOxidationBlock(state.getBlock()).isPresent();
  }

  @Override
  public OxidationLevel getDegradationLevel() {
    return this.oxidationLevel;
  }
}
