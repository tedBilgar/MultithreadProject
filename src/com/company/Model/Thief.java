package com.company.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Thief implements Runnable{
    private BackPack backPack;
    private House house;
    private SearchManager searchManager;

    public Thief(House house) {
        this.house = house;
        backPack = new BackPack(300);
        searchManager = new SearchManager();
    }

    //Воровать может только один вор
    public synchronized void steal(){
        String name = Thread.currentThread().getName();

        synchronized (house) {
            while (!house.isIs_free() || house.getHome_stuffs().isEmpty()) {
                try {
                    house.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // Выставляем замки для критической зоны
            house.setIs_free(false);
            house.setIs_owner(false);
            //локальная коллекция для отсортированной коллекции вора
            List<Stuff> stuffsForStealing = new ArrayList<>(house.getHome_stuffs());

            Collections.sort(stuffsForStealing, new Comparator<Stuff>() {
                public int compare(Stuff o1, Stuff o2) {
                    return o2.getWeight() - o1.getWeight();
                }
            });

            //TODO
            System.out.println("STEALING STUFFS: " + stuffsForStealing);
            //System.out.println("Stealed stuff : " + searchManager.getOptima(stuffsForStealing,100));
            //house.getHome_stuffs().removeAll(searchManager.getOptima(stuffsForStealing,100));

            //Эмитация работы вора
            for (Stuff stuff: searchManager.getOptima(stuffsForStealing,100)) {
                house.getHome_stuffs().remove(stuff);
                System.out.println(name + " stealed : " + stuff);
            }


            house.setIs_free(true);

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
