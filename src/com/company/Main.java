package com.company;

import com.company.Model.House;
import com.company.building.Director;
import com.company.building.OwnerBuilder;
import com.company.building.ThiefBuilder;
import com.company.lifelessModel.BackPack;
import com.company.models.Owner;
import com.company.models.Thief;

/*
 The 1st arg is for a number of owners, the 2nd is for thiefs ones.
 The 3rd arg needs for limit weight of backpack of thief. If there is no 3rd args then program uses value 100.
 */
public class Main {

    public static void main(String[] args) {
        //Owner owner = new OwnerBuilder().setHouse(house).setBackPack(new BackPack(1000)).build();
       // Thief thief = new ThiefBuilder().setHouse(house).setBackPack(new BackPack(300)).build();

        Director director = new Director();

        new Thread(director.buildOwner()).start();
        new Thread(director.buildThief()).start();
    }

}
