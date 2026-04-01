package com.gildedrose;

public class ItemBuilder {
    private String name;
    private int sellIn;
    private int quality;

    public ItemBuilder called(String name) {
        this.name = name;
        return this;
    }

    public ItemBuilder toBeSoldIn(int sellIn) {
        this.sellIn = sellIn;
        return this;
    }

    public ItemBuilder pastExpirationDate() {
        this.sellIn = -1;
        return this;
    }

    public ItemBuilder ofQuality(int quality) {
        this.quality = quality;
        return this;
    }

    public Item build() {
        return new Item(name, sellIn, quality);
    }
}
