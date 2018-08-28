package com.company.Model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Thief implements Runnable{
    private BackPack backPack;
    private House house;

    public Thief() {
        backPack = new BackPack(300);
    }

    public Thief(House house) {
        this.house = house;
        backPack = new BackPack(300);
    }

    public BackPack getBackPack() {
        return backPack;
    }

    public void setBackPack(BackPack backPack) {
        this.backPack = backPack;
    }

    //Воровать может только один вор
    public synchronized void steal(){
      /*  while(!house.isIs_free()||(house.home_stuffs.isEmpty())){
            try {
                //System.out.println("UNDER wAIT");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
        /*List<Stuff> homeStuffs = house.get();
        Collections.sort(homeStuffs, new Comparator<Stuff>() {
            public int compare(Stuff o1, Stuff o2) {
                return o2.getPrice() - o1.getPrice();
            }
        });

        for (Stuff stuff: homeStuffs) {
            if (!backPack.setStuff(stuff)) break;
            homeStuffs.remove(stuff);
            System.out.println("STEAL " + stuff);
        }*/

        if (!house.isIs_free()||house.getHome_stuffs().isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("STEAL " + house.getHome_stuffs());
        notify();
    }

    @Override
    public void run() {
        this.steal();
    }
}
