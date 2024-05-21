package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {
    @Test
    void testNormalItem_qualityDecreasesEachDay() {
        Item item = new Item("+5 Dexterity Vest", 10, 20);
        GildedRose gildedRose = new GildedRose(new Item[] {item});
        gildedRose.updateQuality();
        assertEquals(9, item.sellIn);
        assertEquals(19, item.quality);
    }
}
