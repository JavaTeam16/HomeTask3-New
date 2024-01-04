package org.example;

import java.util.Random;
import java.util.concurrent.*;

class ChargingStation {
    private final ExecutorService chargingThreadPool;
    private final BlockingQueue<String> chargingQueue;

    public ChargingStation(int numChargingStations) {
        this.chargingThreadPool = Executors.newFixedThreadPool(numChargingStations);
        this.chargingQueue = new LinkedBlockingQueue<>();
    }

    public void chargeVehicle(String vehicle) {
        chargingThreadPool.submit(() -> {
            long startTime = System.currentTimeMillis();
            System.out.println("Charging " + vehicle + " at station " + Thread.currentThread().getName());
            // Simulate charging time
            try {
                Thread.sleep(new Random().nextInt(5000) + 5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long endTime = System.currentTimeMillis();
            long chargingTime = endTime - startTime;
            System.out.println(vehicle + " charging completed in " + chargingTime + " milliseconds");
        });
    }

    public void addVehicleToQueue(String vehicle) {
        try {
            chargingQueue.put(vehicle);
            System.out.println(vehicle + " joined the charging queue.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void startCharging() {
        while (true) {
            try {
                String vehicle = chargingQueue.take();
                chargingThreadPool.submit(() -> chargeVehicle(vehicle));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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
