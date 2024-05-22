package com.gildedrose;

@FunctionalInterface
public interface QualityUpdateStrategy {
    void update(Item item);
}
