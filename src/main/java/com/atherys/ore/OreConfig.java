package com.atherys.ore;

import com.atherys.core.utils.PluginConfig;
import com.atherys.ore.model.OreEntry;
import com.flowpowered.math.vector.Vector3i;
import ninja.leaping.configurate.objectmapping.Setting;
import org.spongepowered.api.block.BlockTypes;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class OreConfig extends PluginConfig {

    @Setting("is_default")
    public boolean IS_DEFAULT = true;

    @Setting("minute_interval")
    public long INTERVAL = 5;

    /*

    ore_configs = [
        {
            ore_type = "minecraft:coal_ore",
            percentage = 0.5,
            min = {
                x = 0,
                y = 0,
                z = 0
            },
            max = {
                x = 64,
                y = 64,
                z = 64
            },
            amount = 32
        },
        {
            ore_type = "minecraft:coal_ore",
            percentage = 0.78,
            min = {
                x = 67,
                y = 4,
                z = 100
            },
            max = {
                x = 2432,
                y = 17,
                z = 4231
            },
            amount = 32423
        }
    ]

     */

    @Setting("ore_configs")
    public Set<OreEntry> ORE_CONFIGS = new HashSet<>();

    {
        OreEntry coalEntry = new OreEntry(BlockTypes.COAL_ORE, Vector3i.from(0), Vector3i.from(64), 0.5f);
        coalEntry.setAmount(32);

        ORE_CONFIGS.add(coalEntry);
    }

    protected OreConfig() throws IOException {
        super("config/" + AtherysOre.ID, "config.conf");
    }
}
