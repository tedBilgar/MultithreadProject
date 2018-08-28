package com.company.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Thief implements Runnable{
    private BackPack backPack;
    private House house;

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
        synchronized (house) {
            if (!house.isIs_free() || house.getHome_stuffs().isEmpty()) {
                try {
                    house.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        house.setIs_free(false);

        List<Stuff> stuffsForStealing = new ArrayList<>(house.getHome_stuffs());
        Collections.sort(stuffsForStealing, new Comparator<Stuff>() {
            public int compare(Stuff o1, Stuff o2) {
                return o2.getPrice() - o1.getPrice();
            }
        });
        System.out.println("STEAL SUFF " + stuffsForStealing);

        for (Stuff stuff: stuffsForStealing) {
            if (!backPack.setStuff(stuff)) break;
            house.getHome_stuffs().remove(stuff);
            System.out.println("STEAL " + stuff);
        }
        house.setIs_free(true);
        synchronized (house) {
            house.notify();
        }
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(name + " стартовал вор");
        this.steal();
    }
}
