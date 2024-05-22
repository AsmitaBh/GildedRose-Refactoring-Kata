package com.gildedrose;

class GildedRoseUtils {

    public static final int MAX_QUALITY_LIMIT = 50;
    public static final int MIN_QUALITY_LIMIT = 0;

    private GildedRoseUtils() {
        throw new IllegalStateException("Utility class");
    }

    static boolean isQualityAboveMinLimit(Item item) {
        return item.quality > MIN_QUALITY_LIMIT;
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

    static void incrementQualityByOneWithMaxLimit(Item item) {
        item.quality = Math.min(item.quality + 1, MAX_QUALITY_LIMIT);
    }

    static void incrementQualityByGivenValWithMaxLimit(Item item, int incrementBy) {
        item.quality = Math.min(item.quality + incrementBy, MAX_QUALITY_LIMIT);
    }


    static void decrementQualityByOne(Item item) {
        item.quality = item.quality - 1;
    }
    static void decrementQualityByGivenValWithMinLimit(Item item, int decrementBy) {
        item.quality = Math.max(item.quality - decrementBy, MIN_QUALITY_LIMIT);
    }

    static boolean isQualityUnderMaxLimit(Item item) {
        return item.quality < MAX_QUALITY_LIMIT;
    }
}
