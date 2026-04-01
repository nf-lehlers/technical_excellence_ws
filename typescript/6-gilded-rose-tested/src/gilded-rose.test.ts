import { Item, GildedRose } from '@/gilded-rose';

class ItemBuilder {
  private name: string = '';
  private sellIn: number = 0;
  private quality: number = 0;

  called(name: string): this {
    this.name = name;
    return this;
  }

  toBeSoldIn(sellIn: number): this {
    this.sellIn = sellIn;
    return this;
  }

  pastExpirationDate(): this {
    this.sellIn = -1;
    return this;
  }

  ofQuality(quality: number): this {
    this.quality = quality;
    return this;
  }

  build(): Item {
    return new Item(this.name, this.sellIn, this.quality);
  }
}

describe('Gilded Rose', () => {
  it('lowers quality and sellIn values of every item at the end of each day', () => {
    const items = [new ItemBuilder().called('Any Item').toBeSoldIn(10).ofQuality(10).build()];
    const app = new GildedRose(items);

    app.updateQuality();

    expect(app.items[0].sellIn).toBe(9);
    expect(app.items[0].quality).toBe(9);
  });

  it('lowers quality twice as fast when past expiration date', () => {
    const items = [new ItemBuilder().called('Any Item').pastExpirationDate().ofQuality(10).build()];
    const app = new GildedRose(items);

    app.updateQuality();

    expect(app.items[0].sellIn).toBe(-2);
    expect(app.items[0].quality).toBe(8);
  });

  it('never lowers quality value below zero', () => {
    const items = [new ItemBuilder().called('Any Item').toBeSoldIn(10).ofQuality(0).build()];
    const app = new GildedRose(items);

    app.updateQuality();

    expect(app.items[0].sellIn).toBe(9);
    expect(app.items[0].quality).toBe(0);
  });

  it('increases Aged Brie quality at the end of each day', () => {
    const items = [new ItemBuilder().called('Aged Brie').toBeSoldIn(10).ofQuality(10).build()];
    const app = new GildedRose(items);

    app.updateQuality();

    expect(app.items[0].sellIn).toBe(9);
    expect(app.items[0].quality).toBe(11);
  });

  it('increases Aged Brie quality twice as fast when past expiration date', () => {
    const items = [new ItemBuilder().called('Aged Brie').pastExpirationDate().ofQuality(10).build()];
    const app = new GildedRose(items);

    app.updateQuality();

    expect(app.items[0].sellIn).toBe(-2);
    expect(app.items[0].quality).toBe(12);
  });

  it('never increases Aged Brie quality above 50', () => {
    const items = [new ItemBuilder().called('Aged Brie').toBeSoldIn(10).ofQuality(50).build()];
    const app = new GildedRose(items);

    app.updateQuality();

    expect(app.items[0].sellIn).toBe(9);
    expect(app.items[0].quality).toBe(50);
  });

  it('never lowers Sulfuras quality and sellIn values', () => {
    const items = [new ItemBuilder().called('Sulfuras, Hand of Ragnaros').toBeSoldIn(10).ofQuality(10).build()];
    const app = new GildedRose(items);

    app.updateQuality();

    expect(app.items[0].sellIn).toBe(10);
    expect(app.items[0].quality).toBe(10);
  });

  it('increases Backstage passes quality at the end of each day', () => {
    const items = [new ItemBuilder().called('Backstage passes to a TAFKAL80ETC concert').toBeSoldIn(20).ofQuality(10).build()];
    const app = new GildedRose(items);

    app.updateQuality();

    expect(app.items[0].sellIn).toBe(19);
    expect(app.items[0].quality).toBe(11);
  });

  it('never increases Backstage passes quality above 50', () => {
    const items = [new ItemBuilder().called('Backstage passes to a TAFKAL80ETC concert').toBeSoldIn(20).ofQuality(50).build()];
    const app = new GildedRose(items);

    app.updateQuality();

    expect(app.items[0].sellIn).toBe(19);
    expect(app.items[0].quality).toBe(50);
  });

  it('increases Backstage passes quality twice as fast when sellIn value is equal or less than 10', () => {
    const items = [new ItemBuilder().called('Backstage passes to a TAFKAL80ETC concert').toBeSoldIn(10).ofQuality(10).build()];
    const app = new GildedRose(items);

    app.updateQuality();

    expect(app.items[0].sellIn).toBe(9);
    expect(app.items[0].quality).toBe(12);
  });

  it('increases Backstage passes quality thrice as fast when sellIn value is equal or less than 5', () => {
    const items = [new ItemBuilder().called('Backstage passes to a TAFKAL80ETC concert').toBeSoldIn(5).ofQuality(10).build()];
    const app = new GildedRose(items);

    app.updateQuality();

    expect(app.items[0].sellIn).toBe(4);
    expect(app.items[0].quality).toBe(13);
  });

  it('lowers Backstage passes quality to zero when past expiration date', () => {
    const items = [new ItemBuilder().called('Backstage passes to a TAFKAL80ETC concert').pastExpirationDate().ofQuality(10).build()];
    const app = new GildedRose(items);

    app.updateQuality();

    expect(app.items[0].sellIn).toBe(-2);
    expect(app.items[0].quality).toBe(0);
  });

  it('increases Backstage passes quality by one when sellIn is above 10', () => {
    const items = [new ItemBuilder().called('Backstage passes to a TAFKAL80ETC concert').toBeSoldIn(11).ofQuality(10).build()];
    const app = new GildedRose(items);

    app.updateQuality();

    expect(app.items[0].sellIn).toBe(10);
    expect(app.items[0].quality).toBe(11);
  });

  it('never increases Backstage passes quality above 50 when sellIn is 10 or less', () => {
    const items = [new ItemBuilder().called('Backstage passes to a TAFKAL80ETC concert').toBeSoldIn(10).ofQuality(49).build()];
    const app = new GildedRose(items);

    app.updateQuality();

    expect(app.items[0].sellIn).toBe(9);
    expect(app.items[0].quality).toBe(50);
  });

  it('increases Backstage passes quality twice when sellIn is between 6 and 10', () => {
    const items = [new ItemBuilder().called('Backstage passes to a TAFKAL80ETC concert').toBeSoldIn(6).ofQuality(10).build()];
    const app = new GildedRose(items);

    app.updateQuality();

    expect(app.items[0].sellIn).toBe(5);
    expect(app.items[0].quality).toBe(12);
  });

  it('never increases Backstage passes quality above 50 when sellIn is 5 or less', () => {
    const items = [new ItemBuilder().called('Backstage passes to a TAFKAL80ETC concert').toBeSoldIn(5).ofQuality(49).build()];
    const app = new GildedRose(items);

    app.updateQuality();

    expect(app.items[0].sellIn).toBe(4);
    expect(app.items[0].quality).toBe(50);
  });

  it('does not lower quality twice as fast on last day of sale', () => {
    const items = [new ItemBuilder().called('Any Item').toBeSoldIn(1).ofQuality(10).build()];
    const app = new GildedRose(items);

    app.updateQuality();

    expect(app.items[0].sellIn).toBe(0);
    expect(app.items[0].quality).toBe(9);
  });

  it('never increases Aged Brie quality above 50 when past expiration date', () => {
    const items = [new ItemBuilder().called('Aged Brie').pastExpirationDate().ofQuality(49).build()];
    const app = new GildedRose(items);

    app.updateQuality();

    expect(app.items[0].sellIn).toBe(-2);
    expect(app.items[0].quality).toBe(50);
  });
});
