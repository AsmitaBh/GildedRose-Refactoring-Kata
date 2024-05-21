package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    private GildedRose gildedRose;

    @Test
    void testNormalItem_qualityDecreasesEachDay() {
        Item item = new Item("+5 Dexterity Vest", 10, 20);
        gildedRose = new GildedRose(new Item[]{item});
        gildedRose.updateQuality();
        assertEquals(9, item.sellIn);
        assertEquals(19, item.quality);
    }

    @Test
    void testNormalItem_whenSellByDatePassed_qualityDecreasesTwiceAsFastEachDay() {
        Item item = new Item("+5 Dexterity Vest", 10, 20);
        gildedRose = new GildedRose(new Item[]{item});
        updateQualityForMultipleDays(item.sellIn);
        assertEquals(-1, item.sellIn);
        assertEquals(8, item.quality);
    }

    @Test
    void testNormalItem_qualityNeverLessThanZero() {
        Item item = new Item("+5 Dexterity Vest", 10, 20);
        gildedRose = new GildedRose(new Item[]{item});

        updateQualityForMultipleDays(21);
        assertEquals(-12, item.sellIn);
        assertEquals(0, item.quality);
    }

    private void updateQualityForMultipleDays(int days) {
        for (int dayIndex = days; dayIndex > -1; dayIndex--) {
            gildedRose.updateQuality();
        }
    }
}
