/*
 * WorldEdit, a Minecraft world manipulation toolkit
 * Copyright (C) sk89q <http://www.sk89q.com>
 * Copyright (C) WorldEdit team and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.sk89q.worldedit.function.mask;

import com.sk89q.worldedit.math.BlockVector3;

import java.util.concurrent.ThreadLocalRandom;
import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkArgument;

public class SplatterMask extends AbstractMask {

    private final BlockVector3 position;
    private final double decay;
    private final double sizeSq;

    public SplatterMask(BlockVector3 position, double decay, double size) {
        checkArgument(decay >= 0, "decay must be >= 0");
        checkArgument(decay <= 1, "decay must be <= 1");

        this.position = position;
        this.decay = decay;
        this.sizeSq = size * size;
    }

    @Override
    public boolean test(BlockVector3 vector) {
        double distSq = vector.distanceSq(position);
        double distRatio = distSq / sizeSq;

        double decayChance = distRatio * decay * 2;

        return ThreadLocalRandom.current().nextDouble() > decayChance;
    }

    @Nullable
    @Override
    public Mask2D toMask2D() {
        return null;
    }
}
