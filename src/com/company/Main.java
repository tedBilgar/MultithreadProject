package com.company;

import com.company.Model.House;
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
        for (String str: args) {
            ownerCount = Integer.parseInt(args[0]);
            thiefCount = Integer.parseInt(args[1]);
        }
        House house = new House();

        /*for (int i = 0; i<ownerCount;i++){
            new Thread(new Owner(house)).start();
        }*/
        if (args[2] != null){
            for (int i = 0; i<thiefCount;i++){
                new Thread(new Thief(house,Integer.parseInt(args[2]))).start();
            }
        }else {
            for (int i = 0; i < thiefCount; i++) {
                new Thread(new Thief(house)).start();
            }
        }
        for (int i = 0; i<ownerCount;i++){
            new Thread(new Owner(house)).start();
        }
    }


}
