package com.atherys.ore.persistence;

import com.atherys.ore.AtherysOre;
import com.atherys.ore.model.OreEntry;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.scheduler.Task;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class OreManager {

    private Map<BlockType, Set<OreEntry>> oreEntries = new HashMap<>();

    private Task task;

    public OreManager() {
        AtherysOre.getConfig().ORE_CONFIGS.forEach(oreConfig -> {
            oreEntries.getOrDefault(oreConfig.getOreType(), new HashSet<>()).add(oreConfig);
        });

        task = Task.builder()
                .interval(AtherysOre.getConfig().INTERVAL, TimeUnit.MINUTES)
                .execute(this::tick)
                .submit(AtherysOre.getInstance());
    }

    private void tick() {
        oreEntries.forEach((type, entry) -> {

        });
    }

    public void increment(BlockType type, Location<World> location, int amount) {
        getEntry(type, location).ifPresent(oreEntry -> oreEntry.addAmount(amount));
    }

    private Optional<OreEntry> getEntry(BlockType type, Location<World> location) {
        for (OreEntry oreEntry : oreEntries.getOrDefault(type, new HashSet<>())) {
            boolean fitsX = location.getPosition().getX() > oreEntry.getMin().getX() && location.getPosition().getX() < oreEntry.getMax().getX();
            boolean fitsY = location.getPosition().getY() > oreEntry.getMin().getY() && location.getPosition().getY() < oreEntry.getMax().getY();
            boolean fitsZ = location.getPosition().getZ() > oreEntry.getMin().getZ() && location.getPosition().getZ() < oreEntry.getMax().getZ();
            if (fitsX && fitsY && fitsZ) return Optional.of(oreEntry);
        }

        return Optional.empty();
    }

}
