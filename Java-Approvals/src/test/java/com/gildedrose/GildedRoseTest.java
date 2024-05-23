package com.gildedrose;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    private GildedRose gildedRose;

    @Test
    void testNormalItem_qualityDecreasesEachDay() {
        Item item = getNormalItem();
        gildedRose = new GildedRose(new Item[]{item});
        gildedRose.updateQuality();
        assertEquals(9, item.sellIn);
        assertEquals(19, item.quality);
    }

    @Test
    void testNormalItem_whenSellByDatePassed_qualityDecreasesTwiceAsFastEachDay() {
        Item item = getNormalItem();
        gildedRose = new GildedRose(new Item[]{item});
        updateQualityForMultipleDays(item.sellIn);
        assertEquals(-1, item.sellIn);
        assertEquals(8, item.quality);
    }

    @Test
    void testNormalItem_qualityNeverLessThanZero() {
        Item item = getNormalItem();
        gildedRose = new GildedRose(new Item[]{item});
        updateQualityForMultipleDays(21);
        assertEquals(-12, item.sellIn);
        assertEquals(0, item.quality);
    }

    @Test
    void testAgedBrie_qualityIncreasesEachDay() {
        Item item = getAgedBrieItem();
        gildedRose = new GildedRose(new Item[]{item});
        gildedRose.updateQuality();
        assertEquals(9, item.sellIn);
        assertEquals(21, item.quality);
    }

    @Test
    void testAgedBrie_whenSellByDatePassed_qualityIncreasesTwiceAsFastEachDay() {
        Item item = getAgedBrieItem();
        gildedRose = new GildedRose(new Item[]{item});
        updateQualityForMultipleDays(item.sellIn);
        assertEquals(-1, item.sellIn);
        assertEquals(32, item.quality);
    }

    @Test
    void testAgedBrie_qualityNeverMoreThanFifty() {
        Item item = getAgedBrieItem();
        gildedRose = new GildedRose(new Item[]{item});
        updateQualityForMultipleDays(21);
        assertEquals(-12, item.sellIn);
        assertEquals(50, item.quality);
    }

    @Test
    void testSulfuras_qualityOrSellInNeverDecreasesEachDay() {
        Item item = getSulfurasItem();
        gildedRose = new GildedRose(new Item[]{item});
        gildedRose.updateQuality();
        assertEquals(10, item.sellIn);
        assertEquals(80, item.quality);
    }

    @Test
    void testSulfuras_whenSellByDatePassed_qualityOrSellInNeverDecreases() {
        Item item = getSulfurasItem();
        gildedRose = new GildedRose(new Item[]{item});
        updateQualityForMultipleDays(item.sellIn);
        assertEquals(10, item.sellIn);
        assertEquals(80, item.quality);
    }

    @Test
    void testBackstagePass_qualityIncreasesEachDay() {
        Item item = getBackstagePassItem();
        gildedRose = new GildedRose(new Item[]{item});
        gildedRose.updateQuality();
        assertEquals(19, item.sellIn);
        assertEquals(21, item.quality);
    }

    @ParameterizedTest(name = "testBackstagePass_{0}")
    @CsvSource({"whenSellInIsTenOrLess_qualityIncreasesByTwoEachDay,10,9,32",
        "whenSellInIsFiveOrLess_qualityIncreasesByThreeEachDay,15,4,43",
        "qualityNeverMoreThanFifty,18,1,50"})
    void testBackstagePass_multipleScenarios(String testName, int updateQualityForDays, int expectedSellIn,
                                             int expectedQuality) {
        Item item = getBackstagePassItem();
        gildedRose = new GildedRose(new Item[]{item});
        updateQualityForMultipleDays(updateQualityForDays);
        assertEquals(expectedSellIn, item.sellIn);
        assertEquals(expectedQuality, item.quality);
    }

    @Test
    void testBackstagePass_whenSellByDatePassed_qualityDropsToZero() {
        Item item = getBackstagePassItem();
        gildedRose = new GildedRose(new Item[]{item});
        updateQualityForMultipleDays(20);
        assertEquals(-1, item.sellIn);
        assertEquals(0, item.quality);
    }

    @Test
    void testConjured_qualityDecreasesByTwoEachDay() {
        Item item = getConjuredItem();
        gildedRose = new GildedRose(new Item[]{item});
        gildedRose.updateQuality();
        assertEquals(19, item.sellIn);
        assertEquals(48, item.quality);
    }

    @Test
    void testConjured_whenSellByDatePassed_qualityDecreasesTwiceAsFastEachDay() {
        Item item = getConjuredItem();
        gildedRose = new GildedRose(new Item[]{item});
        updateQualityForMultipleDays(item.sellIn);
        assertEquals(-1, item.sellIn);
        assertEquals(6, item.quality);
        gildedRose.updateQuality();
        assertEquals(-2, item.sellIn);
        assertEquals(2, item.quality);
    }

    @Test
    void testConjured_qualityNeverLessThanZero() {
        Item item = getConjuredItem();
        gildedRose = new GildedRose(new Item[]{item});
        updateQualityForMultipleDays(22);
        assertEquals(-3, item.sellIn);
        assertEquals(0, item.quality);
    }

    private Item getConjuredItem() {
        return new Item("Conjured Mana Cake", 20, 50);
    }

    private void updateQualityForMultipleDays(int days) {
        for (int dayIndex = days; dayIndex > -1; dayIndex--) {
            gildedRose.updateQuality();
        }
    }

    private static Item getNormalItem() {
        return new Item("+5 Dexterity Vest", 10, 20);
    }

    private static Item getAgedBrieItem() {
        return new Item("Aged Brie", 10, 20);
    }

    private static Item getSulfurasItem() {
        return new Item("Sulfuras, Hand of Ragnaros", 10, 80);
    }

    private static Item getBackstagePassItem() {
        return new Item("Backstage passes to a TAFKAL80ETC concert", 20, 20);
    }

}
