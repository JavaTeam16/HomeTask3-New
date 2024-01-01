package org.example;

class Vehicle implements Runnable {
    private final ChargingStation chargingStation;
    private final String name;

    public Vehicle(String name, ChargingStation chargingStation) {
        this.name = name;
        this.chargingStation = chargingStation;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        chargingStation.chargeVehicle(name);
        long endTime = System.currentTimeMillis();
        long chargingTime = endTime - startTime;
        System.out.println(name + " total charging time: " + chargingTime + " milliseconds");
    }
}