package com.company.factories;

import com.company.Model.House;
import com.company.models.Functioning;

public abstract class MainFactory {
    public void doSmth(){

    }

    public abstract Functioning createEntity(House house);
}
