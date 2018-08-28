package com.company.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SearchManager {
    public List<Stuff> getOptima(List<Stuff> stuffs, int maxWeight){
        List<Stuff> stealedStuffAll = new ArrayList<>(stuffs);
        System.out.println("Будем воровать : " +stealedStuffAll);

        int maxSumAll = 0;
        List<Stuff> optimalStuff = new ArrayList<>();

        for(int i = 0;i<stealedStuffAll.size();i++){
            if(stealedStuffAll.get(i).getWeight()>maxWeight) continue;

            List<Stuff> stealedStuffLevel = levelSearch(stealedStuffAll,i,maxWeight);
            //System.out.println("взяли " + stealedStuffLevel);
            int levelSum = 0;
            for (Stuff stuff: stealedStuffLevel) {
                levelSum += stuff.getPrice();
            }
            if (levelSum>maxSumAll){
                maxSumAll = levelSum;
                optimalStuff.clear();
                optimalStuff.addAll(stealedStuffLevel);
            }
            //System.out.println("now optima is " + optimalStuff);
        }
        return optimalStuff;
    }

    public List<Stuff> levelSearch(List<Stuff> stealedStuffAll,int index,int maxWeight){
        List<Stuff> levelSearchOptimal = new ArrayList<>();
        int maxSum = 0;

        List<Stuff> currLevelSearch = new ArrayList<>();
        List<Stuff> bufferList = new ArrayList<>();

        int curWeight = 0;
        int curSum = 0;
        int lastPoint = index+1;

        while(lastPoint != -1){
            curSum = 0;
            curWeight = 0;

            lastPoint = branchSearch(stealedStuffAll,bufferList,index,lastPoint,maxWeight);
            System.out.println("MAS " + bufferList);
            System.out.println("GIVE " + lastPoint);
            //System.out.println("взяли ветку" + bufferList + " " + lastPoint );

            currLevelSearch.clear();
            currLevelSearch.addAll(bufferList);

            for (Stuff stuff: currLevelSearch) {
                curSum += stuff.getPrice();
            }

            if(curSum>maxSum) {
                levelSearchOptimal.clear();
                levelSearchOptimal.addAll(currLevelSearch);
            }
            //System.out.println("run there");
        }
        return levelSearchOptimal;
    }
    public int branchSearch(List<Stuff> stealedStuffAll,List<Stuff> bufferList,int index,int index2,int maxWeight){
        int curWeight = 0;
        int curSum = 0;
        int lastPoint = index2;
        bufferList.clear();

        bufferList.add(stealedStuffAll.get(index));
        curWeight += stealedStuffAll.get(index).getWeight();
        curSum += stealedStuffAll.get(index).getPrice();

        for(int i = index2; i<stealedStuffAll.size();i++){
            //System.out.println(" I " + i);

            if(maxWeight >= curWeight + stealedStuffAll.get(i).getWeight()) {
                bufferList.add(stealedStuffAll.get(i));
                curWeight += stealedStuffAll.get(i).getWeight();
                curSum += stealedStuffAll.get(i).getPrice();
                lastPoint = i+1;
                //System.out.println("LAST POINT: " + lastPoint);
            }else{
                lastPoint = i+1;
            }

        }
        if (lastPoint>=stealedStuffAll.size()){
            lastPoint = -1;
        }
        //System.out.println("T " + bufferList);
        //System.out.println("LAST POINT: " + lastPoint);
        return lastPoint;
    }
}
