package org.example;

import java.util.Random;
import java.util.concurrent.*;

class EnergySource {
    private final ExecutorService chargingThreadPool;
    private final BlockingQueue<String> chargingQueue;
    private final Semaphore availableBatteries;

    public EnergySource(int numBatteries) {
        this.chargingThreadPool = Executors.newFixedThreadPool(numBatteries);
        this.chargingQueue = new LinkedBlockingQueue<>();
        this.availableBatteries = new Semaphore(numBatteries, true);
    }

    public void chargeVehicle(String vehicle) {
        try {
            availableBatteries.acquire();
            chargingThreadPool.submit(() -> {
                System.out.println("Charging " + vehicle + " from energy source " + Thread.currentThread().getName());
                // Simulate charging time
                try {
                    Thread.sleep(new Random().nextInt(5000) + 5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(vehicle + " charging completed.");
                availableBatteries.release();
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
                chargeVehicle(vehicle);
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
