package com.gildedrose;

import java.util.Arrays;

public enum ItemType {
    AGED_BRIE("Aged Brie"),
    SULFURAS("Sulfuras, Hand of Ragnaros"),
    BACKSTAGE_PASS("Backstage passes to a TAFKAL80ETC concert"),
    DEFAULT("");

    private String itemName;

    ItemType(String itemName) {
        this.itemName = itemName;
    }

    public static ItemType getByItemName(String searchName) {
        return Arrays.stream(ItemType.values()).
            filter(itemType -> itemType.itemName.equals(searchName)).findFirst().orElse(DEFAULT);
    }
}
