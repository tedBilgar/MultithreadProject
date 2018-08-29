package com.company.factories;

import com.company.Model.House;
import com.company.Model.SearchManager;
import com.company.lifelessModel.BackPack;
import com.company.models.Functioning;
import com.company.models.Thief;

public class ThiefFactory extends MainFactory {
    @Override
    public Functioning createEntity(House house) {
        int limitWeight = 100;
        BackPack backPack = new BackPack(limitWeight);
        SearchManager searchManager = new SearchManager();

        return new Thief(house,backPack,searchManager);
    }
}
