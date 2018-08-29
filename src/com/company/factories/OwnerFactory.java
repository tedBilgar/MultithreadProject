package com.company.factories;

import com.company.Model.House;
import com.company.models.Owner;

public class OwnerFactory extends MainFactory {

    @Override
    public Runnable createEntity(House house) {
        return new Owner(house);
    }
}
