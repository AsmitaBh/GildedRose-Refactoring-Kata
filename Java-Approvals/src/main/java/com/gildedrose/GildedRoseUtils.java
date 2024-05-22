package com.gildedrose;

class GildedRoseUtils {
    private GildedRoseUtils() {
        throw new IllegalStateException("Utility class");
    }

    static boolean isQualityAboveMinLimit(Item item) {
        return item.quality > 0;
    }

    static void dropQualityToZero(Item item) {
        item.quality = 0;
    }

    static void decrementSellInByOne(Item item) {
        item.sellIn = item.sellIn - 1;
    }

    static boolean hasSellByDatePassed(Item item) {
        return item.sellIn < 0;
    }

    static boolean isSellInFiveDaysOrLess(Item item) {
        return item.sellIn < 6;
    }

    static boolean isSellInTenDaysOrLess(Item item) {
        return item.sellIn < 11;
    }

    static void incrementQualityByOne(Item item) {
        item.quality = item.quality + 1;
    }

    static void decrementQualityByOne(Item item) {
        item.quality = item.quality - 1;
    }

    static boolean isQualityUnderMaxLimit(Item item) {
        return item.quality < 50;
    }
}
