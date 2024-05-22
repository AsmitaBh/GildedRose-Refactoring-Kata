package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (!item.name.equals("Aged Brie")
                && !item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                if (item.quality > 0 && !item.name.equals("Sulfuras, Hand of Ragnaros")) {
                    decrementQualityByOne(item);

                }
            } else {
                if (isQualityUnderMaxLimit(item)) {
                    incrementQualityByOne(item);

                    if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (item.sellIn < 11 && isQualityUnderMaxLimit(item)) {
                            incrementQualityByOne(item);
                        }

                        if (item.sellIn < 6 && isQualityUnderMaxLimit(item)) {
                            incrementQualityByOne(item);
                        }
                    }
                }
            }

            if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                item.sellIn = item.sellIn - 1;
            }

            if (item.sellIn < 0) {
                if (!item.name.equals("Aged Brie")) {
                    if (!item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (item.quality > 0 && !item.name.equals("Sulfuras, Hand of Ragnaros")) {
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
