package com.company.models;

import com.company.Model.House;
import com.company.building.OwnerBuilder;
import com.company.lifelessModel.BackPack;
import com.company.lifelessModel.Stuff;

import java.util.ArrayList;
import java.util.List;

public class Owner implements Runnable, Carrying{
    //private List<Stuff> stuffs = new ArrayList<>();
    private BackPack backPack;
    private House house;

    public Owner(OwnerBuilder ownerBuilder){
        this.backPack = ownerBuilder.getBackPack();
        this.house = ownerBuilder.getHouse();
    }
    //Внести вещи в квартиру
    public void deployStuffs(){
        /*
         Используются потокозащищенные поля: лист и флаги
         */
        List<Stuff> threadSafeList = house.getHome_stuffs();
        String name = Thread.currentThread().getName();

        try {
            /*
                Закрывается проход хозяину, если флаг вора активен (флаг AtomicBoolean)
             */
            while (house.getIs_thief().get()){
                synchronized (house) {
                    house.wait();
                }
            }

            /*
                Ставятся флаг, что зашел хозяин
                getOwnerCounter - потокозащищенный integer для мониторинга количества вошедших хозяев в дом
                в дальнейшем сможем отследить, когда ушел последний вышедный хозяин для закрытия флага хозяина
                и открытия вора
             */
            house.getIs_owner().set(true);
            house.getOwnerCounter().incrementAndGet();
            int i = 0;

            /*
                threadSafeList- потокозащищенный список, к тому же по док-ции oracle обрамляем synchronized для
                атомарности работы со списком, если несколько хозяев в доме. НО сам дом доступен нескольким хозяинам,
                лишь операция со списком синхронизирована
             */
            for (Stuff stuff: backPack.getStuffs()) {
                synchronized (threadSafeList) {
                    threadSafeList.add(stuff);
                }
                System.out.println(name + " " + (i++) + " : " + stuff);
            }

            /*
                мониторинг за вышедшими хозяинами
                для того чтобы именно последний (а не 2ой) "выключил свет" - убрал флаг и открыл вход вору
             */
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
    public boolean toPack(Stuff stuff) {
        return backPack.setStuff(stuff);
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(name + " стартовал хозяин");
        this.deployStuffs();
        /*while(true) {
            for (int i = 0; i < 10; i++) {
                backPack.setStuff(new Stuff((int) (Math.random() * (100 - 10 + 1) + 10), (int) (Math.random() * (100 - 10 + 1)) + 10));
            }
            this.deployStuffs();

            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
    }

}
