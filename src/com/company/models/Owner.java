package com.company.models;

import com.company.Model.House;
import com.company.lifelessModel.Stuff;

import java.util.ArrayList;
import java.util.List;

public class Owner implements Runnable, Functioning{
    private List<Stuff> stuffs = new ArrayList<>();
    private House house;

    public Owner() {
    }

    public Owner(House house) {
        this.house = house;
    }

    //Внести вещи в квартиру
    public void deployStuffs(){
        List<Stuff> threadSafeList = house.getHome_stuffs();
        String name = Thread.currentThread().getName();

        try {
            while (house.getIs_thief().get()){
                synchronized (house) {
                    house.wait();
                }
            }

            house.getIs_owner().set(true);
            house.getOwnerCounter().incrementAndGet();
            int i = 0;

            for (Stuff stuff: stuffs) {
                synchronized (threadSafeList) {
                    threadSafeList.add(stuff);
                }
                System.out.println(name + " " + (i++) + " : " + stuff);
            }

            if(house.getOwnerCounter().compareAndSet(0,house.getOwnerCounter().decrementAndGet())) {
                System.out.println("СПИСОК ПОСЛЕ ПЕРЕДАЧИ " + threadSafeList);
                house.getIs_owner().set(false);
                synchronized (house) {
                    house.notify();
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(name + " стартовал хозяин");

        while(true) {
            for (int i = 0; i < 10; i++) {
                stuffs.add(new Stuff((int) (Math.random() * (100 - 10 + 1) + 10), (int) (Math.random() * (100 - 10 + 1)) + 10));
            }
            this.deployStuffs();

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void toDoOwnJob() {

    }
}
