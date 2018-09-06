package com.company.lifelessModel;

import com.company.building.StuffBuilder;

public class Stuff {
    private int weight;
    private int price;

    public Stuff(StuffBuilder stuffBuilder) {
        this.weight = stuffBuilder.getWeight();
        this.price = stuffBuilder.getPrice();
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
