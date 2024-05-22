package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (!isAgedBrie(item)
                && !isBackstagePass(item)) {
                if (item.quality > 0 && !isSulfuras(item)) {
                    decrementQualityByOne(item);

                }
            } else {
                if (isQualityUnderMaxLimit(item)) {
                    incrementQualityByOne(item);

                    if (isBackstagePass(item)) {
                        if (item.sellIn < 11 && isQualityUnderMaxLimit(item)) {
                            incrementQualityByOne(item);
                        }

                        if (item.sellIn < 6 && isQualityUnderMaxLimit(item)) {
                            incrementQualityByOne(item);
                        }
                    }
                }
            }

            if (!isSulfuras(item)) {
                item.sellIn = item.sellIn - 1;
            }

            if (item.sellIn < 0) {
                if (!isAgedBrie(item)) {
                    if (!isBackstagePass(item)) {
                        if (item.quality > 0 && !isSulfuras(item)) {
                            decrementQualityByOne(item);
                        }
                    } else {
                        item.quality = 0;
                    }
                } else {
                    if (isQualityUnderMaxLimit(item)) {
                        incrementQualityByOne(item);
                    }
                }
            }
        }
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
