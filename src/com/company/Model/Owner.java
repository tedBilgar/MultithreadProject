package com.company.Model;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Owner implements Runnable{
    private List<Stuff> stuffs = new ArrayList<>();
    private House house;

    public Owner() {
    }

    public Owner(House house) {
        this.house = house;

        for(int i =0;i<30;i++){
            stuffs.add(new Stuff((int)(Math.random()*(100-10+1)+10),(int) (Math.random()*(100-10+1))+10));
        }

    }

    //Внести вещи в квартиру
    public void deployStuffs(){
        //List<Stuff> threadSafeList = Collections.synchronizedList(house.getHome_stuffs());
        //List<Stuff> threadSafeList = Collections.synchronizedList(new ArrayList<>(house.getHome_stuffs()));
        List<Stuff> threadSafeList = house.getHome_stuffs();
        String name = Thread.currentThread().getName();
        AtomicBoolean atomicBoolean = new AtomicBoolean();

        try {

            /*while(atomicBoolean.compareAndSet(true,house.Is_thief())) {
                house.wait();
            }*/
            while (house.getIs_thief2().get()){
                synchronized (house) {
                    house.wait();
                }
            }
            //house.setIs_free(false);
            //house.getAtomicInteger().getAndIncrement();

            house.getAtomicBoolean().set(true);
            house.getAtomicInteger().incrementAndGet();
            //house.setIs_free(false);
            int i = 0;

            for (Stuff stuff: stuffs) {
                if (stuff == null) System.out.println("-----------------------------------------------ЗДЕСЬ НУЛ ------------------");
                synchronized (threadSafeList) {
                    threadSafeList.add(stuff);
                }
                System.out.println("LOOK :" + threadSafeList.contains(stuff));
                System.out.println(name + " " + (i++) + " : " + stuff);
            }


            System.out.println("COUNT " + house.getAtomicInteger().get());
            if(house.getAtomicInteger().compareAndSet(0,house.getAtomicInteger().decrementAndGet())) {
                System.out.println("СПИСОК ПОСЛЕ ПЕРЕДАЧИ " + threadSafeList);
                house.getAtomicBoolean().set(false);
                synchronized (house) {
                    house.notify();
                    System.out.println("ОТДАЛИ ");
                }
            }
          /*  house.setIs_free(true);
            house.notify();*/

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(name + " стартовал хозяин");
        this.deployStuffs();
    }
}
