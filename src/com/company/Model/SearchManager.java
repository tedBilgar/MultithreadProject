package com.company.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SearchManager {
    public List<Stuff> getOptima(List<Stuff> stuffs, int maxWeight){
        List<Stuff> stealedStuffAll = new ArrayList<>(stuffs);

        int maxSumAll = 0;
        List<Stuff> optimalStuff = new ArrayList<>();

        for(int i = 0;i<stealedStuffAll.size();i++){
            if(stealedStuffAll.get(i).getWeight()>maxWeight) continue;

            List<Stuff> stealedStuffLevel = levelSearch(stealedStuffAll,i,maxWeight);

            int levelSum = 0;
            for (Stuff stuff: stealedStuffLevel) {
                levelSum += stuff.getPrice();
            }
            if (levelSum>maxSumAll){
                maxSumAll = levelSum;
                optimalStuff.clear();
                optimalStuff.addAll(stealedStuffLevel);
            }

        }
        return optimalStuff;
    }

    private List<Stuff> levelSearch(List<Stuff> stealedStuffAll,int index,int maxWeight){
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

            currLevelSearch.clear();
            currLevelSearch.addAll(bufferList);

            for (Stuff stuff: currLevelSearch) {
                curSum += stuff.getPrice();
            }

            if(curSum>maxSum) {
                levelSearchOptimal.clear();
                levelSearchOptimal.addAll(currLevelSearch);
            }
        }
        return levelSearchOptimal;
    }
    private int branchSearch(List<Stuff> stealedStuffAll,List<Stuff> bufferList,int index,int index2,int maxWeight){
        int curWeight = 0;
        int curSum = 0;
        int lastPoint = index2;
        bufferList.clear();

        bufferList.add(stealedStuffAll.get(index));
        curWeight += stealedStuffAll.get(index).getWeight();
        curSum += stealedStuffAll.get(index).getPrice();

        for(int i = index2; i<stealedStuffAll.size();i++){

            if(maxWeight >= curWeight + stealedStuffAll.get(i).getWeight()) {
                bufferList.add(stealedStuffAll.get(i));
                curWeight += stealedStuffAll.get(i).getWeight();
                curSum += stealedStuffAll.get(i).getPrice();
                lastPoint = i+1;

            }else{
                lastPoint = i+1;
            }

        }
        if (lastPoint>=stealedStuffAll.size()){
            lastPoint = -1;
        }

        return lastPoint;
    }
}
