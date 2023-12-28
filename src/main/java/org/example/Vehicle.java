package org.example;

import java.util.Random;

class Vehicle implements Runnable {
    private final EnergySource energySource;
    private final String name;

    public Vehicle(String name, EnergySource energySource) {
        this.name = name;
        this.energySource = energySource;
    }

    @Override
    public void run() {
        long arrivalTime = System.currentTimeMillis();
        energySource.addVehicleToQueue(name);

        // Simulate random time between arrivals
        try {
            Thread.sleep(new Random().nextInt(3000) + 2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long startTime = System.currentTimeMillis();
        energySource.chargeVehicle(name);
        long endTime = System.currentTimeMillis();
        long waitingTime = startTime - arrivalTime;
        System.out.println(name + " total waiting time: " + waitingTime + " milliseconds");
    }
}

