package org.example;

class Vehicle implements Runnable {
    private final String name;
    private final ChargingStation chargingStation;

    public Vehicle(String name, ChargingStation chargingStation) {
        this.name = name;
        this.chargingStation = chargingStation;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        chargingStation.chargeVehicle(name);
        long endTime = System.currentTimeMillis();
        long waitingTime = endTime - startTime;
        if (waitingTime > 900000) { // 15 minutes in milliseconds
            System.out.println(name + " left the queue due to waiting time exceeding 15 minutes.");
        } else {
            System.out.println(name + " charging completed in " + waitingTime + " milliseconds.");
        }
    }
}