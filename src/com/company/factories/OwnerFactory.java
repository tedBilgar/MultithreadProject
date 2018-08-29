package com.company.factories;

import com.company.Model.House;
import com.company.models.Functioning;
import com.company.models.Owner;

public class OwnerFactory extends MainFactory {

    @Override
    public Functioning createEntity(House house) {
        return new Owner(house);
    }
}
