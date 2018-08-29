package com.company.factories;

import com.company.Model.House;

public abstract class MainFactory {

    public void start(House house){
        Runnable entity = createEntity(house);
        new Thread(entity).start();
    }

    public abstract Runnable createEntity(House house);

}
