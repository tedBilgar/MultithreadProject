package com.company;

import com.company.Model.House;
import com.company.Model.Owner;
import com.company.Model.Stuff;
import com.company.Model.Thief;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        House house = new House();
        Owner owner = new Owner(house);
        Owner owner2 = new Owner(house);
        Thief thief = new Thief(house);
        Thief thief2 = new Thief(house);
        Thief thief3 = new Thief(house);
        Thief thief4 = new Thief(house);

        new Thread(owner).start();
        new Thread(owner2).start();
        new Thread(thief).start();
        new Thread(thief2).start();
        new Thread(thief3).start();
        new Thread(thief4).start();
    }
}
