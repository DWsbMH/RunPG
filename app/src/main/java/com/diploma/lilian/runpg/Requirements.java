package com.diploma.lilian.runpg;

import com.diploma.lilian.database.entity.SportActivity;

class Requirements {

    static boolean check(SportActivity sportActivity) {
        boolean fit = false;

        switch (sportActivity.getActivityType()){
            case RUNNING:
                fit = sportActivity.getTotalDistance() >= 4000 || sportActivity.getDuration() >= 3600;
                // nagyobb mint 4 kilométer vagy tovább mint 1 óra
                break;
            case HIKING:
                fit = sportActivity.getTotalDistance() >= 10000 || sportActivity.getDuration() >= 14400;
                // nagyobb mint 10 kilométer vagy tovább mint 4 óra
                break;
            case CROSSFIT:
                fit = sportActivity.getDuration() >= 5400;
                // tovább mint 1,5 óra
                break;
            case CYCLING:
                fit = sportActivity.getTotalDistance() >= 15000 || sportActivity.getDuration() >= 9000;
                // nagyobb mint 15 kilométer vagy tovább mint 2,5 óra
                break;
            case YOGA:
                fit = sportActivity.getDuration() >= 5400;
                // tovább mint 1,5 óra
                break;
            case SNOWBOARDING:
                fit = sportActivity.getTotalDistance() >= 3000 || sportActivity.getDuration() >= 3600;
                // nagyobb mint 3 kilométer vagy tovább mint 1 óra
                break;
            case WALKING:
                fit = sportActivity.getTotalDistance() >= 7000 || sportActivity.getDuration() >= 7200;
                // nagyobb mint 7 kilométer vagy tovább mint 2 óra
                break;
            case SWIMMING:
                fit = sportActivity.getTotalDistance() >= 10000 || sportActivity.getDuration() >= 14400;
                // nagyobb mint 10 kilométer vagy tovább mint 4 óra
                break;
            case SKATING:
                fit = sportActivity.getDuration() >= 3600;
                // nagyobb mint 10 kilométer vagy tovább mint 1 óra
                break;
            case OTHER:
                fit = false;
                break;
        }

        return fit;
    }

}
