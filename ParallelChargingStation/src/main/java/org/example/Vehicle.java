package org.example;

import java.util.Random;

class Vehicle implements Runnable {
    private final ChargingStation chargingStation;
    private final String name;

    public Vehicle(String name, ChargingStation chargingStation) {
        this.name = name;
        this.chargingStation = chargingStation;
    }

    @Override
    public void run() {
        long arrivalTime = System.currentTimeMillis();
        chargingStation.addVehicleToQueue(name);

        // Simulate random time between arrivals
        try {
            Thread.sleep(new Random().nextInt(3000) + 2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long startTime = System.currentTimeMillis();
        chargingStation.chargeVehicle(name);
        long endTime = System.currentTimeMillis();
        long waitingTime = startTime - arrivalTime;
        if (waitingTime > 900000) { // 15 minutes in milliseconds
            System.out.println(name + " left the queue due to waiting time exceeding 15 minutes.");
        }
    }
}