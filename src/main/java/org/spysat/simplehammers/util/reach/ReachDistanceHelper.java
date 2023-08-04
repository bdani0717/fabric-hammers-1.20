package org.spysat.simplehammers.util.reach;

import net.minecraft.entity.player.PlayerEntity;

public class ReachDistanceHelper {
    public static double getReachDistance(PlayerEntity playerEntity) {
        return playerEntity.isCreative() ? 5.0F : 4.5F;
    }

    private ReachDistanceHelper() {
        // NO-OP
    }
}
