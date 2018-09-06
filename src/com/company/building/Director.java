package com.company.building;

import com.company.Model.House;
import com.company.Model.SearchManager;
import com.company.lifelessModel.BackPack;

public class Director {
    House house = new House();

    public Runnable buildOwner(){
        return new OwnerBuilder().setHouse(house).setBackPack(new BackPack(1000)).build();
    }

    public Runnable buildThief(){
        return new ThiefBuilder().setHouse(house).setBackPack(new BackPack(300))
                .setSearchManager(new SearchManager()).build();
    }
}
