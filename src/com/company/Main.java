package com.company;

import com.company.Model.House;
import com.company.Model.Owner;
import com.company.Model.Stuff;
import com.company.Model.Thief;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        int ownerCount = 0;
        int thiefCount = 0;
        for (String str: args) {
            ownerCount = Integer.parseInt(args[0]);
            thiefCount = Integer.parseInt(args[1]);
        }
        House house = new House();

        for (int i = 0; i<ownerCount;i++){
            new Thread(new Owner(house)).start();
        }
        for (int i = 0; i<thiefCount;i++){
            new Thread(new Thief(house)).start();
        }


    }


}
