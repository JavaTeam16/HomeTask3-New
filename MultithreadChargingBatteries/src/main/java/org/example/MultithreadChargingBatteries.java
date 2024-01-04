package org.example;

public class MultithreadChargingBatteries {
    public static void main(String[] args) {
        int numEnergySources = 3; // Number of reserved batteries
        int numVehicles = 10;

        EnergySource energySource = new EnergySource(numEnergySources);

        // Start the charging process in a separate thread
        new Thread(energySource::startCharging).start();

        for (int i = 1; i <= numVehicles; i++) {
            new Thread(new Vehicle("Car" + i, energySource)).start();
        }

        // Stop the charging process after a certain time (e.g., 1 hour)
        try {
            Thread.sleep(3600000); // 1 hour in milliseconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        energySource.stopCharging();
    }
}

