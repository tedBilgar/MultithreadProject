package com.company.building;

import com.company.Model.House;
import com.company.Model.SearchManager;
import com.company.lifelessModel.BackPack;
import com.company.models.Thief;

public class ThiefBuilder {
    private BackPack backPack;
    private House house;
    private SearchManager searchManager;

    public BackPack getBackPack() {
        return backPack;
    }

    public ThiefBuilder setBackPack(BackPack backPack) {
        this.backPack = backPack;
        return this;
    }

    public House getHouse() {
        return house;
    }

    public ThiefBuilder setHouse(House house) {
        this.house = house;
        return this;
    }

    public SearchManager getSearchManager() {
        return searchManager;
    }

    public ThiefBuilder setSearchManager(SearchManager searchManager) {
        this.searchManager = searchManager;
        return this;
    }

    public Runnable build(){
        return new Thief(this);
    }
}
