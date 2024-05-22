package com.gildedrose;

import java.util.EnumMap;
import java.util.Map;

import static com.gildedrose.GildedRoseUtils.*;
import static com.gildedrose.ItemType.*;

class GildedRose {
    Item[] items;

    private final Map<ItemType, QualityUpdateStrategy> itemTypeUpdateStrategyMap = new EnumMap<>(ItemType.class);

    public GildedRose(Item[] items) {
        this.items = items;
        itemTypeUpdateStrategyMap.put(AGED_BRIE, this::updateQualityForAgedBrie);
        itemTypeUpdateStrategyMap.put(SULFURAS, sulfuras -> {
        });
        itemTypeUpdateStrategyMap.put(BACKSTAGE_PASS, this::updateQualityForBackstagePass);
        itemTypeUpdateStrategyMap.put(DEFAULT, this::updateQualityForNormalItem);
    }

    public void updateQuality() {
        for (Item item : items) {
            ItemType itemType = ItemType.getByItemName(item.name);
            itemTypeUpdateStrategyMap.get(itemType).update(item);
        }
    }

    private void updateQualityForNormalItem(Item item) {
        if (isQualityAboveMinLimit(item)) {
            decrementQualityByOne(item);
        }
        decrementSellInByOne(item);
        if (hasSellByDatePassed(item) && isQualityAboveMinLimit(item)) {
            decrementQualityByOne(item);
        }
    }

    private void updateQualityForBackstagePass(Item item) {
        if (isSellInFiveDaysOrLess(item)) {
            incrementQualityByGivenValWithMaxLimit(item, 3);
        } else if (isSellInTenDaysOrLess(item)) {
            incrementQualityByGivenValWithMaxLimit(item, 2);
        } else {
            incrementQualityByOneWithMaxLimit(item);
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
}
