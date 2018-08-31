package com.company;

import com.company.Model.House;
import com.company.building.Director;
import com.company.building.OwnerBuilder;
import com.company.building.ThiefBuilder;
import com.company.models.Owner;
import com.company.models.Thief;

/*
 The 1st arg is for a number of owners, the 2nd is for thiefs ones.
 The 3rd arg needs for limit weight of backpack of thief. If there is no 3rd args then program uses value 100.
 */
public class Main {

    public static void main(String[] args) {
        House house = new House();
        Director director = new Director();

        OwnerBuilder ownerBuilder = new OwnerBuilder();
        ThiefBuilder thiefBuilder = new ThiefBuilder();

        for(int i=0;i<Integer.parseInt(args[0]);i++){
            director.constructOwner(ownerBuilder,house);
            Thread
            ownerBuilder.getResult()
        }

        for(int i=0;i<Integer.parseInt(args[1]);i++){
            director.constructThief(thiefBuilder,house);
        }
    }

}
