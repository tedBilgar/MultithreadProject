package com.company.building;

import com.company.Model.House;
import com.company.models.Owner;

public class OwnerBuilder implements Builder {
    private House house;

    @Override
    public void setHouse(House house) {
        this.house = house;
    }

    public Owner getResult(){
        return new Owner(house);
    }
}
