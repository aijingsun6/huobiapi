package org.alking.huobiapi.domain;

public class HuobiOrderBookEntry {

    private double price;

    private double qty;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public HuobiOrderBookEntry() {
    }

    public HuobiOrderBookEntry(double price, double qty) {
        this.price = price;
        this.qty = qty;
    }

    @Override
    public String toString() {
        return String.format("(%s,%s)", String.valueOf(price), String.valueOf(qty));
    }
}
