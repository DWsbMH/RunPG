package com.diploma.lilian.runkeeper;

import com.diploma.lilian.runkeeper.models.FitnessActivityFeed;

import org.junit.Test;

/**
 * Created by Lilian on 2016. 04. 29..
 */
public class RunkeeperServiceTest {


    @Test
    public void runkeeperTest() throws Exception {
        RunkeeperService runkeeperService = new RunkeeperService("c337db657687470887b60f127f38db50");
        FitnessActivityFeed faf = runkeeperService.fitnessActivityWrapper.getFitnessActivityFeed();
        for(FitnessActivityFeed.Item item : faf.getItems()){
            System.out.println(item);
        }
        System.out.println(faf);
    }

}