package com.company.building;

import com.company.lifelessModel.Stuff;

public class StuffBuilder {
    private int weight;
    private int price;

    public int getWeight() {
        return weight;
    }

    public StuffBuilder setWeight(int minWeight,int maxWeight) {
        this.weight = (int) (Math.random() * (maxWeight - minWeight + 1) + minWeight);
        return this;
    }

    public int getPrice() {
        return price;
    }

    public StuffBuilder setPrice(int minPrice,int maxPrice) {
        this.price = (int) (Math.random() * (maxPrice - minPrice + 1) + minPrice);
        return this;
    }

    public Stuff build() {
        return new Stuff(this);
    }
}
