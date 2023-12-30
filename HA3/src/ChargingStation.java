import java.util.Random;
import java.util.concurrent.Semaphore;

class ChargingStation {
    private final Semaphore availableStations;

    public ChargingStation(int numStations) {
        this.availableStations = new Semaphore(numStations, true);
    }

    public void chargeVehicle(String vehicle) {
        try {
            availableStations.acquire();
            System.out.println("Charging " + vehicle + " at station " + Thread.currentThread().getName());
            // Simulate charging time
            Thread.sleep(new Random().nextInt(5000) + 5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            availableStations.release();
        }
    }

    public void stopCharging() {
    }

    public void startCharging() {
    }
}
