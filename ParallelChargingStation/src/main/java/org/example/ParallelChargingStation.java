package org.example;

public class ParallelChargingStation {
    public static void main(String[] args) {
        int numChargingStations = 5;
        int numVehicles = 10;

        ChargingStation chargingStation = new ChargingStation(numChargingStations);

        // Start the charging process in a separate thread
        new Thread(chargingStation::startCharging).start();

        for (int i = 1; i <= numVehicles; i++) {
            new Thread(new Vehicle("Car" + i, chargingStation)).start();
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