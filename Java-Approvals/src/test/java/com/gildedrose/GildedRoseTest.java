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

    @Test
    void testNormalItem_whenSellByDatePassed_qualityDecreasesTwiceAsFastEachDay() {
        Item item = new Item("+5 Dexterity Vest", 10, 20);
        GildedRose gildedRose = new GildedRose(new Item[] {item});

        for (int dayIndex = item.sellIn; dayIndex > -1; dayIndex--) {
            gildedRose.updateQuality();
        }
        assertEquals(-1, item.sellIn);
        assertEquals(8, item.quality);
    }
}
