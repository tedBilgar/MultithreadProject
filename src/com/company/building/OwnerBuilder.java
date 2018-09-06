package com.company.building;

import com.company.Model.House;
import com.company.lifelessModel.BackPack;
import com.company.models.Owner;

public class OwnerBuilder implements Builder{
    private BackPack backPack;
    private House house;

    public BackPack getBackPack() {
        return backPack;
    }

    public OwnerBuilder setBackPack(BackPack backPack) {
        this.backPack = backPack;
        return this;
    }

    public House getHouse() {
        return house;
    }

    public OwnerBuilder setHouse(House house) {
        this.house = house;
        return this;
    }

    public Runnable build(){
        return new Owner(this);
    }
}
