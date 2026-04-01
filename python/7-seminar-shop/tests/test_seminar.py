from seminar import Seminar
from three_letter_discount import ThreeLetterDiscount


def test_should_calculate_correct_gross_prices():
    seminar = Seminar("OOP", 700, False)

    discount = ThreeLetterDiscount(seminar)
    discount.apply()
    assert seminar.gross_price == 1044.2131

    seminar.net_price = 300
    discount.apply()
    assert seminar.gross_price == 373.65999999999997

    seminar.tax_free = True
    discount.apply()
    assert seminar.gross_price == 299.75

    seminar.title = "Objekt-Orientierte Programmierung"
    discount.apply()
    assert seminar.gross_price == 299.75
