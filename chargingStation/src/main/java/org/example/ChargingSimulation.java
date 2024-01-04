package org.example;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChargingSimulation {
    public static void main(String[] args) {
        int numChargingStations = 5;
        int numVehicles = 10;

        ChargingStation chargingStation = new ChargingStation(numChargingStations);
        ExecutorService executor = Executors.newFixedThreadPool(numChargingStations);

        for (int i = 1; i <= numVehicles; i++) {
            executor.submit(new Vehicle("Car" + i, chargingStation));
            // Introduce random delay between vehicle arrivals
            try {
                Thread.sleep(new Random().nextInt(3000) + 2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();
    }
}
