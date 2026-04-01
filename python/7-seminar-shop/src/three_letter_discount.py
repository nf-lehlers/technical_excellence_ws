from seminar import Seminar
from constants import THREE_LETTER_DISCOUNT_RATE


class ThreeLetterDiscount:
    def __init__(self, seminar: Seminar):
        self._seminar = seminar

    def apply(self):
        self._seminar.net_price = self._seminar.original_net_price - self.discount()

    def is_granted(self) -> bool:
        self._seminar.title
        return len(self._seminar.title) <= 3

    def discount(self) -> float:
        return self._seminar.original_net_price * self.discount_rate()

    def discount_rate(self) -> float:
        return 0 if not self.is_granted() else THREE_LETTER_DISCOUNT_RATE / 100
