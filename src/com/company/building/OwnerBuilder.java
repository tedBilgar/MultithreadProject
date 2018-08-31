package com.company.building;

import com.company.Model.House;
import com.company.lifelessModel.Stuff;
import com.company.models.Owner;

import java.util.ArrayList;
import java.util.List;

public class OwnerBuilder implements Builder {
    private House house;
    private List<Stuff> stuffs;

    @Override
    public void setHouse(House house) {
        this.house = house;
    }

    @Override
    public void setItems() {
        stuffs = new ArrayList<>();
    }

    @Override
    public void setExtra() {
        //Для хозяина нет дополнительных полей
    }

    public Owner getResult(){
        return new Owner(house);
    }
}
