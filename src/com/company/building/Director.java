package com.company.building;

import com.company.Model.House;
/*
    Здесь мы можем (необязателен этот класс вообще для паттерна)
    добавить порядок строительства объекта или осбенностей при ег остроительстве
 */
public class Director {
    public void constructOwner(Builder builder,House house){
        builder.setHouse(house);
    }

    public void constructThief(Builder builder,House house){
        builder.setHouse(house);
    }
}
