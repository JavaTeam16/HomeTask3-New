package org.example;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class ChargingStation {
    private final ExecutorService chargingThreadPool;

    public ChargingStation(int numChargingStations) {
        this.chargingThreadPool = Executors.newFixedThreadPool(numChargingStations);
    }

    public void chargeVehicle(String vehicle) {
        chargingThreadPool.submit(() -> {
            System.out.println("Charging " + vehicle + " at station " + Thread.currentThread().getName());
            // Simulate charging time
            try {
                Thread.sleep(new Random().nextInt(5000) + 5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(vehicle + " charging completed.");
        });
    }

    public void stopCharging() {
        chargingThreadPool.shutdown();
        try {
            if (!chargingThreadPool.awaitTermination(10, TimeUnit.SECONDS)) {
                chargingThreadPool.shutdownNow();
            }
        } catch (InterruptedException e) {
            chargingThreadPool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}

