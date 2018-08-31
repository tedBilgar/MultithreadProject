package com.company.building;

import com.company.Model.House;
import com.company.Model.SearchManager;
import com.company.lifelessModel.BackPack;
import com.company.models.Thief;

public class ThiefBuilder implements Builder {
    private House house;

    @Override
    public void setHouse(House house) {
        this.house = house;
    }

    //TODO
    // Доделать создание рюкзака и менеджера
    public Thief getResult(){
        return new Thief(house,new BackPack(100),new SearchManager());
    }
}
