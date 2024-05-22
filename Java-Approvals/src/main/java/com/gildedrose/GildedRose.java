package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (isAgedBrie(item)) {
                updateQualityForAgedBrie(item);
            } else if (isSulfuras(item)) {
            } else if (isBackstagePass(item)) {
                updateQualityForBackstagePass(item);
            } else {
                if (isQualityAboveMinLimit(item)) {
                    decrementQualityByOne(item);
                }
                decrementSellInByOne(item);
                if (hasSellByDatePassed(item)) {
                    if (isQualityAboveMinLimit(item)) {
                        decrementQualityByOne(item);
                    }
                }
            }
        }
    }

    private void updateQualityForBackstagePass(Item item) {
        if (isQualityUnderMaxLimit(item)) {
            incrementQualityByOne(item);

            if (isSellInTenDaysOrLess(item) && isQualityUnderMaxLimit(item)) {
                incrementQualityByOne(item);
            }

            if (isSellInFiveDaysOrLess(item) && isQualityUnderMaxLimit(item)) {
                incrementQualityByOne(item);
            }
        }
        decrementSellInByOne(item);

        if (hasSellByDatePassed(item)) {
            dropQualityToZero(item);
        }
    }

    private void updateQualityForAgedBrie(Item item) {
        if (isQualityUnderMaxLimit(item)) {
            incrementQualityByOne(item);
        }
        decrementSellInByOne(item);
        if (hasSellByDatePassed(item) && isQualityUnderMaxLimit(item)) {
            incrementQualityByOne(item);
        }
    }

    private static boolean isQualityAboveMinLimit(Item item) {
        return item.quality > 0;
    }

    private static void dropQualityToZero(Item item) {
        item.quality = 0;
    }

    private static void decrementSellInByOne(Item item) {
        item.sellIn = item.sellIn - 1;
    }

    private static boolean hasSellByDatePassed(Item item) {
        return item.sellIn < 0;
    }

    private static boolean isSellInFiveDaysOrLess(Item item) {
        return item.sellIn < 6;
    }

    private static boolean isSellInTenDaysOrLess(Item item) {
        return item.sellIn < 11;
    }

    private static boolean isSulfuras(Item item) {
        return item.name.equals("Sulfuras, Hand of Ragnaros");
    }

    private static boolean isBackstagePass(Item item) {
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    private static boolean isAgedBrie(Item item) {
        return item.name.equals("Aged Brie");
    }

    private static void incrementQualityByOne(Item item) {
        item.quality = item.quality + 1;
    }

    private static void decrementQualityByOne(Item item) {
        item.quality = item.quality - 1;
    }

    private static boolean isQualityUnderMaxLimit(Item item) {
        return item.quality < 50;
    }
}
