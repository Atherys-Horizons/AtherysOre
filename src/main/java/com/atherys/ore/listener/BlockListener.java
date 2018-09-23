package com.atherys.ore.listener;

import com.atherys.ore.AtherysOre;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.block.ChangeBlockEvent;

public class BlockListener {

    @Listener
    public void onBlockDestroy(ChangeBlockEvent.Break event) {
        event.getTransactions().forEach(transaction -> {
            BlockType type = transaction.getOriginal().getState().getType();
            transaction.getOriginal().getLocation().ifPresent(location -> AtherysOre.getOreManager().increment(type, location, 1));
        });
    }

}
