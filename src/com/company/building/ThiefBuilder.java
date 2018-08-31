package com.company.building;

import com.company.Model.House;
import com.company.Model.SearchManager;
import com.company.lifelessModel.BackPack;
import com.company.models.Thief;

public class ThiefBuilder implements Builder {
    private House house;
    private BackPack backPack;
    private SearchManager searchManager;

    @Override
    public void setHouse(House house) {
        this.house = house;
    }

    @Override
    public void setItems() {
        backPack = new BackPack(100);
    }

    @Override
    public void setExtra() {
        searchManager = new SearchManager();
    }


    public Thief getResult(){
        return new Thief(house,backPack,searchManager);
    }
}
