package com.diploma.lilian.runpg;

import com.diploma.lilian.database.entity.SportActivity;

class Requirements {

    static boolean check(SportActivity sportActivity) {
        boolean fit = false;

        switch (sportActivity.getActivityType()){
            case RUNNING:
                fit = sportActivity.getTotalDistance() >= 4000 || sportActivity.getDuration() >= 3600;
                break;
            case HIKING:
                fit = sportActivity.getTotalDistance() >= 10000 || sportActivity.getDuration() >= 14400;
                break;
            case CROSSFIT:
                fit = sportActivity.getDuration() >= 5400;
                break;
            case CYCLING:
                fit = sportActivity.getTotalDistance() >= 15000 || sportActivity.getDuration() >= 9000;
                break;
            case YOGA:
                fit = sportActivity.getDuration() >= 5400;
                break;
            case SNOWBOARDING:
                fit = sportActivity.getTotalDistance() >= 3000 || sportActivity.getDuration() >= 3600;
                break;
            case WALKING:
                fit = sportActivity.getTotalDistance() >= 7000 || sportActivity.getDuration() >= 7200;
                break;
            case SWIMMING:
                fit = sportActivity.getTotalDistance() >= 1000 || sportActivity.getDuration() >= 1800;
                break;
            case SKATING:
                fit = sportActivity.getDuration() >= 3600;
                break;
            case OTHER:
                fit = false;
                break;
        }

        return fit;
    }

}
