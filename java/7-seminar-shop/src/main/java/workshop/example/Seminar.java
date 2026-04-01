package workshop.example;

import static workshop.example.Constants.*;

public class Seminar {
    private String title;
    private double netPriceValue;
    private boolean taxFree;

    public Seminar(String title, double netPrice, boolean taxFree) {
        this.title = title;
        this.netPriceValue = netPrice;
        this.taxFree = taxFree;
    }

    public double getGrossPrice() {
        return getNetPrice() * taxRate();
    }

    public double getOriginalNetPrice() {
        return netPriceValue;
    }

    public double getNetPrice() {
        return netPriceValue + (netPriceValue > RESALE_FEE_LIMIT ? UPPER_RESALE_FEE : LOWER_RESALE_FEE);
    }

    public void setNetPrice(double value) {
        this.netPriceValue = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isTaxFree() {
        return taxFree;
    }

    public void setTaxFree(boolean taxFree) {
        this.taxFree = taxFree;
    }

    public double taxRate() {
        return taxFree ? 1 : TAX_RATE;
    }
}
