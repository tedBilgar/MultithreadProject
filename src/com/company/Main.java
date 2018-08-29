package com.company;

import com.company.Model.House;
import com.company.factories.MainFactory;
import com.company.factories.OwnerFactory;
import com.company.factories.ThiefFactory;
import com.company.models.Owner;
import com.company.models.Thief;

/*
 The 1st arg is for a number of owners, the 2nd is for thiefs ones.
 The 3rd arg needs for limit weight of backpack of thief. If there is no 3rd args then program uses value 100.
 */
public class Main {

    public static void main(String[] args) {
        int ownerCount = 0;
        int thiefCount = 0;

        MainFactory mainFactory;
        House house = new House();

        //создание хозяев
        for(int i=0;i<Integer.parseInt(args[0]);i++){
            mainFactory = new OwnerFactory();
            mainFactory.start(house);
        }
        for(int i=0;i<Integer.parseInt(args[1]);i++){
            mainFactory = new ThiefFactory();
            mainFactory.start(house);
        }
    }


}
