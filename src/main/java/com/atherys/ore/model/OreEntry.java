package com.atherys.ore.model;

import com.flowpowered.math.vector.Vector3i;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;
import org.spongepowered.api.block.BlockType;

import java.util.Objects;

@ConfigSerializable
public class OreEntry {

    @Setting("ore_type")
    private BlockType oreType;

    @Setting("percentage")
    private float percentage;

    @Setting("min")
    private Vector3i min;

    @Setting("max")
    private Vector3i max;

    @Setting("amount")
    private int amount;

    public OreEntry(BlockType oreType, Vector3i min, Vector3i max, float percentage) {
        this.oreType = oreType;
        this.min = min;
        this.max = max;
        this.percentage = percentage;
    }

    public BlockType getOreType() {
        return oreType;
    }

    public float getPercentage() {
        return percentage;
    }

    public Vector3i getMin() {
        return min;
    }

    public Vector3i getMax() {
        return max;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void addAmount(int amount) {
        setAmount(this.amount + amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OreEntry oreEntry = (OreEntry) o;
        return
                Float.compare(oreEntry.getPercentage(), getPercentage()) == 0 &&
                Objects.equals(getOreType(), oreEntry.getOreType()) &&
                Objects.equals(getMin(), oreEntry.getMin()) &&
                Objects.equals(getMax(), oreEntry.getMax());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOreType(), getPercentage(), getMin(), getMax());
    }
}
