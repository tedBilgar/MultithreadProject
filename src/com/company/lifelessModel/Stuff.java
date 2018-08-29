package com.company.lifelessModel;

public class Stuff {
    private int weight;
    private int price;

    public Stuff(int weight, int price) {
        this.weight = weight;
        this.price = price;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Stuff{" +
                "weight=" + weight +
                ", price=" + price +
                '}';
    }
}
