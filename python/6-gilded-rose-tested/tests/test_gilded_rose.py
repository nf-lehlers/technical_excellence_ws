# -*- coding: utf-8 -*-

from gilded_rose import Item, GildedRose


class ItemBuilder:
    def __init__(self):
        self._name = ""
        self._sell_in = 0
        self._quality = 0

    def called(self, name):
        self._name = name
        return self

    def to_be_sold_in(self, sell_in):
        self._sell_in = sell_in
        return self

    def past_expiration_date(self):
        self._sell_in = -1
        return self

    def of_quality(self, quality):
        self._quality = quality
        return self

    def build(self):
        return Item(self._name, self._sell_in, self._quality)


def _update(item):
    app = GildedRose([item])
    app.update_quality()
    return app.items[0]


class TestGildedRose:
    def test_lowers_quality_and_sell_in_values_of_every_item_at_the_end_of_each_day(
        self,
    ):
        item = _update(
            ItemBuilder().called("Any Item").to_be_sold_in(10).of_quality(10).build()
        )

        assert item.sell_in == 9
        assert item.quality == 9

    def test_lowers_quality_twice_as_fast_when_past_expiration_date(self):
        item = _update(
            ItemBuilder()
            .called("Any Item")
            .past_expiration_date()
            .of_quality(10)
            .build()
        )

        assert item.sell_in == -2
        assert item.quality == 8

    def test_never_lowers_quality_value_below_zero(self):
        item = _update(
            ItemBuilder().called("Any Item").to_be_sold_in(10).of_quality(0).build()
        )

        assert item.sell_in == 9
        assert item.quality == 0

    def test_increases_aged_brie_quality_at_the_end_of_each_day(self):
        item = _update(
            ItemBuilder().called("Aged Brie").to_be_sold_in(10).of_quality(10).build()
        )

        assert item.sell_in == 9
        assert item.quality == 11

    def test_increases_aged_brie_quality_twice_as_fast_when_past_expiration_date(self):
        item = _update(
            ItemBuilder()
            .called("Aged Brie")
            .past_expiration_date()
            .of_quality(10)
            .build()
        )

        assert item.sell_in == -2
        assert item.quality == 12

    def test_never_increases_aged_brie_quality_above_50(self):
        item = _update(
            ItemBuilder().called("Aged Brie").to_be_sold_in(10).of_quality(50).build()
        )

        assert item.sell_in == 9
        assert item.quality == 50

    def test_never_lowers_sulfuras_quality_and_sell_in_values(self):
        item = _update(
            ItemBuilder()
            .called("Sulfuras, Hand of Ragnaros")
            .to_be_sold_in(10)
            .of_quality(10)
            .build()
        )

        assert item.sell_in == 10
        assert item.quality == 10

    def test_increases_backstage_passes_quality_at_the_end_of_each_day(self):
        item = _update(
            ItemBuilder()
            .called("Backstage passes to a TAFKAL80ETC concert")
            .to_be_sold_in(20)
            .of_quality(10)
            .build()
        )

        assert item.sell_in == 19
        assert item.quality == 11

    def test_never_increases_backstage_passes_quality_above_50(self):
        item = _update(
            ItemBuilder()
            .called("Backstage passes to a TAFKAL80ETC concert")
            .to_be_sold_in(20)
            .of_quality(50)
            .build()
        )

        assert item.sell_in == 19
        assert item.quality == 50

    def test_increases_backstage_passes_quality_twice_as_fast_when_sell_in_value_is_equal_or_less_than_10(
        self,
    ):
        item = _update(
            ItemBuilder()
            .called("Backstage passes to a TAFKAL80ETC concert")
            .to_be_sold_in(10)
            .of_quality(10)
            .build()
        )

        assert item.sell_in == 9
        assert item.quality == 12

    def test_increases_backstage_passes_quality_thrice_as_fast_when_sell_in_value_is_equal_or_less_than_5(
        self,
    ):
        item = _update(
            ItemBuilder()
            .called("Backstage passes to a TAFKAL80ETC concert")
            .to_be_sold_in(5)
            .of_quality(10)
            .build()
        )

        assert item.sell_in == 4
        assert item.quality == 13

    def test_lowers_backstage_passes_quality_to_zero_when_past_expiration_date(self):
        item = _update(
            ItemBuilder()
            .called("Backstage passes to a TAFKAL80ETC concert")
            .past_expiration_date()
            .of_quality(10)
            .build()
        )

        assert item.sell_in == -2
        assert item.quality == 0

    def test_increases_backstage_passes_quality_by_one_when_sell_in_is_above_10(self):
        item = _update(
            ItemBuilder()
            .called("Backstage passes to a TAFKAL80ETC concert")
            .to_be_sold_in(11)
            .of_quality(10)
            .build()
        )

        assert item.sell_in == 10
        assert item.quality == 11

    def test_never_increases_backstage_passes_quality_above_50_when_sell_in_is_10_or_less(
        self,
    ):
        item = _update(
            ItemBuilder()
            .called("Backstage passes to a TAFKAL80ETC concert")
            .to_be_sold_in(10)
            .of_quality(49)
            .build()
        )

        assert item.sell_in == 9
        assert item.quality == 50

    def test_increases_backstage_passes_quality_twice_when_sell_in_is_between_6_and_10(
        self,
    ):
        item = _update(
            ItemBuilder()
            .called("Backstage passes to a TAFKAL80ETC concert")
            .to_be_sold_in(6)
            .of_quality(10)
            .build()
        )

        assert item.sell_in == 5
        assert item.quality == 12

    def test_never_increases_backstage_passes_quality_above_50_when_sell_in_is_5_or_less(
        self,
    ):
        item = _update(
            ItemBuilder()
            .called("Backstage passes to a TAFKAL80ETC concert")
            .to_be_sold_in(5)
            .of_quality(49)
            .build()
        )

        assert item.sell_in == 4
        assert item.quality == 50

    def test_does_not_lower_quality_twice_as_fast_on_last_day_of_sale(self):
        item = _update(
            ItemBuilder().called("Any Item").to_be_sold_in(1).of_quality(10).build()
        )

        assert item.sell_in == 0
        assert item.quality == 9

    def test_never_increases_aged_brie_quality_above_50_when_past_expiration_date(self):
        item = _update(
            ItemBuilder()
            .called("Aged Brie")
            .past_expiration_date()
            .of_quality(49)
            .build()
        )

        assert item.sell_in == -2
        assert item.quality == 50
