package com.company.building;

import com.company.Model.House;
import com.company.Model.SearchManager;
import com.company.lifelessModel.BackPack;
import com.company.lifelessModel.Stuff;

public class Director {

    public Runnable buildOwner(){
        House house = House.getInstance();
        return new OwnerBuilder().setHouse(house).setBackPack(new BackPack(1000)).build();
    }

    public Runnable buildThief(){
        House house = House.getInstance();
        return new ThiefBuilder().setHouse(house).setBackPack(new BackPack(300))
                .setSearchManager(new SearchManager()).build();
    }

    public Stuff buildStuff(){
        return  new StuffBuilder().setWeight(20,100).setPrice(60,200).build();
    }
}
