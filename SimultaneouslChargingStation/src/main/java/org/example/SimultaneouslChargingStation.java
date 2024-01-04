package org.example;

import java.util.Random;

public class SimultaneouslChargingStation {
    public static void main(String[] args) {

        int numChargingStations = 5;
        int numVehicles = 10;

        ChargingStation chargingStation = new ChargingStation(numChargingStations);

        for (int i = 1; i <= numVehicles; i++) {
            new Thread(new Vehicle("Car" + i, chargingStation)).start();
            // Introduce random delay between vehicle arrivals
            try {
                Thread.sleep(new Random().nextInt(3000) + 2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Stop the charging process after a certain time (e.g., 1 hour)
        try {
            Thread.sleep(3600000); // 1 hour in milliseconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        chargingStation.stopCharging();
    }

}