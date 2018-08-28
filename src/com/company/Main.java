package com.company;

import com.company.Model.House;
import com.company.Model.Owner;
import com.company.Model.Stuff;
import com.company.Model.Thief;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        House house = new House();
        Owner owner = new Owner(house);
        Owner owner1 = new Owner(house);
        Owner owner2 = new Owner(house);
        Owner owner3 = new Owner(house);
        Thief thief = new Thief(house);


        new Thread(owner).start();
        new Thread(owner1).start();
        new Thread(owner2).start();
        new Thread(owner3).start();
        new Thread(thief).start();

    }


}
