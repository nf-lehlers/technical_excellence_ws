package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

	@Test
	public void lowers_quality_and_sellIn_values_of_every_item_at_the_end_of_each_day() throws Exception {
		Item[] items = new Item[]{new ItemBuilder().called("Any Item").toBeSoldIn(10).ofQuality(10).build()};
		GildedRose app = new GildedRose(items);

		app.updateQuality();

		assertEquals(9, app.items[0].sellIn);
		assertEquals(9, app.items[0].quality);
	}

	@Test
	public void lowers_quality_twice_as_fast_when_past_expiration_date() throws Exception {
		Item[] items = new Item[]{new ItemBuilder().called("Any Item").pastExpirationDate().ofQuality(10).build()};
		GildedRose app = new GildedRose(items);

		app.updateQuality();

		assertEquals(-2, app.items[0].sellIn);
		assertEquals(8, app.items[0].quality);
	}

	@Test
	public void never_lowers_quality_value_below_zero() throws Exception {
		Item[] items = new Item[]{new ItemBuilder().called("Any Item").toBeSoldIn(10).ofQuality(0).build()};
		GildedRose app = new GildedRose(items);

		app.updateQuality();

		assertEquals(9, app.items[0].sellIn);
		assertEquals(0, app.items[0].quality);
	}

	@Test
	public void increases_aged_brie_quality_at_the_end_of_each_day() throws Exception {
		Item[] items = new Item[]{new ItemBuilder().called("Aged Brie").toBeSoldIn(10).ofQuality(10).build()};
		GildedRose app = new GildedRose(items);

		app.updateQuality();

		assertEquals(9, app.items[0].sellIn);
		assertEquals(11, app.items[0].quality);
	}

	@Test
	public void increases_aged_brie_quality_twice_as_fast_when_past_expiration_date() throws Exception {
		Item[] items = new Item[]{new ItemBuilder().called("Aged Brie").pastExpirationDate().ofQuality(10).build()};
		GildedRose app = new GildedRose(items);

		app.updateQuality();

		assertEquals(-2, app.items[0].sellIn);
		assertEquals(12, app.items[0].quality);
	}

	@Test
	public void never_increases_aged_brie_quality_above_50() throws Exception {
		Item[] items = new Item[]{new ItemBuilder().called("Aged Brie").toBeSoldIn(10).ofQuality(50).build()};
		GildedRose app = new GildedRose(items);

		app.updateQuality();

		assertEquals(9, app.items[0].sellIn);
		assertEquals(50, app.items[0].quality);
	}

	@Test
	public void never_lowers_sulfuras_quality_and_sellIn_values() throws Exception {
		Item[] items = new Item[]{new ItemBuilder().called("Sulfuras, Hand of Ragnaros").toBeSoldIn(10).ofQuality(10).build()};
		GildedRose app = new GildedRose(items);

		app.updateQuality();

		assertEquals(10, app.items[0].sellIn);
		assertEquals(10, app.items[0].quality);
	}

	@Test
	public void increases_backstage_passes_quality_at_the_end_of_each_day() throws Exception {
		Item[] items = new Item[]{new ItemBuilder().called("Backstage passes to a TAFKAL80ETC concert").toBeSoldIn(20).ofQuality(10).build()};
		GildedRose app = new GildedRose(items);

		app.updateQuality();

		assertEquals(19, app.items[0].sellIn);
		assertEquals(11, app.items[0].quality);
	}

	@Test
	public void never_increases_backstage_passes_quality_above_50() throws Exception {
		Item[] items = new Item[]{new ItemBuilder().called("Backstage passes to a TAFKAL80ETC concert").toBeSoldIn(20).ofQuality(50).build()};
		GildedRose app = new GildedRose(items);

		app.updateQuality();

		assertEquals(19, app.items[0].sellIn);
		assertEquals(50, app.items[0].quality);
	}

	@Test
	public void increases_backstage_passes_quality_twice_as_fast_when_sellIn_value_is_equal_or_less_than_10() throws Exception {
		Item[] items = new Item[]{new ItemBuilder().called("Backstage passes to a TAFKAL80ETC concert").toBeSoldIn(10).ofQuality(10).build()};
		GildedRose app = new GildedRose(items);

		app.updateQuality();

		assertEquals(9, app.items[0].sellIn);
		assertEquals(12, app.items[0].quality);
	}

	@Test
	public void increases_backstage_passes_quality_thrice_as_fast_when_sellIn_value_is_equal_or_less_than_5() throws Exception {
		Item[] items = new Item[]{new ItemBuilder().called("Backstage passes to a TAFKAL80ETC concert").toBeSoldIn(5).ofQuality(10).build()};
		GildedRose app = new GildedRose(items);

		app.updateQuality();

		assertEquals(4, app.items[0].sellIn);
		assertEquals(13, app.items[0].quality);
	}

	@Test
	public void lowers_backstage_passes_quality_to_zero_when_past_expiration_date() throws Exception {
		Item[] items = new Item[]{new ItemBuilder().called("Backstage passes to a TAFKAL80ETC concert").pastExpirationDate().ofQuality(10).build()};
		GildedRose app = new GildedRose(items);

		app.updateQuality();

		assertEquals(-2, app.items[0].sellIn);
		assertEquals(0, app.items[0].quality);
	}

	@Test
	public void increases_backstage_passes_quality_by_one_when_sellIn_is_above_10() throws Exception {
		Item[] items = new Item[]{new ItemBuilder().called("Backstage passes to a TAFKAL80ETC concert").toBeSoldIn(11).ofQuality(10).build()};
		GildedRose app = new GildedRose(items);

		app.updateQuality();

		assertEquals(10, app.items[0].sellIn);
		assertEquals(11, app.items[0].quality);
	}

	@Test
	public void never_increases_backstage_passes_quality_above_50_when_sellIn_is_10_or_less() throws Exception {
		Item[] items = new Item[]{new ItemBuilder().called("Backstage passes to a TAFKAL80ETC concert").toBeSoldIn(10).ofQuality(49).build()};
		GildedRose app = new GildedRose(items);

		app.updateQuality();

		assertEquals(9, app.items[0].sellIn);
		assertEquals(50, app.items[0].quality);
	}

	@Test
	public void increases_backstage_passes_quality_twice_when_sellIn_is_between_6_and_10() throws Exception {
		Item[] items = new Item[]{new ItemBuilder().called("Backstage passes to a TAFKAL80ETC concert").toBeSoldIn(6).ofQuality(10).build()};
		GildedRose app = new GildedRose(items);

		app.updateQuality();

		assertEquals(5, app.items[0].sellIn);
		assertEquals(12, app.items[0].quality);
	}

	@Test
	public void never_increases_backstage_passes_quality_above_50_when_sellIn_is_5_or_less() throws Exception {
		Item[] items = new Item[]{new ItemBuilder().called("Backstage passes to a TAFKAL80ETC concert").toBeSoldIn(5).ofQuality(49).build()};
		GildedRose app = new GildedRose(items);

		app.updateQuality();

		assertEquals(4, app.items[0].sellIn);
		assertEquals(50, app.items[0].quality);
	}

	@Test
	public void does_not_lower_quality_twice_as_fast_on_last_day_of_sale() throws Exception {
		Item[] items = new Item[]{new ItemBuilder().called("Any Item").toBeSoldIn(1).ofQuality(10).build()};
		GildedRose app = new GildedRose(items);

		app.updateQuality();

		assertEquals(0, app.items[0].sellIn);
		assertEquals(9, app.items[0].quality);
	}

	@Test
	public void never_increases_aged_brie_quality_above_50_when_past_expiration_date() throws Exception {
		Item[] items = new Item[]{new ItemBuilder().called("Aged Brie").pastExpirationDate().ofQuality(49).build()};
		GildedRose app = new GildedRose(items);

		app.updateQuality();

		assertEquals(-2, app.items[0].sellIn);
		assertEquals(50, app.items[0].quality);
	}
}
