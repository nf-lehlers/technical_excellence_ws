from constants import RESALE_FEE_LIMIT, UPPER_RESALE_FEE, LOWER_RESALE_FEE, TAX_RATE


class Seminar:
    def __init__(self, title: str, net_price: float, tax_free: bool):
        self.title = title
        self._net_price = net_price
        self.tax_free = tax_free

    @property
    def gross_price(self) -> float:
        return self.net_price * self.tax_rate()

    @property
    def original_net_price(self) -> float:
        return self._net_price

    @property
    def net_price(self) -> float:
        return self._net_price + (
            UPPER_RESALE_FEE if self._net_price > RESALE_FEE_LIMIT else LOWER_RESALE_FEE
        )

    @net_price.setter
    def net_price(self, value: float):
        self._net_price = value

    def tax_rate(self) -> float:
        return 1 if self.tax_free else TAX_RATE
