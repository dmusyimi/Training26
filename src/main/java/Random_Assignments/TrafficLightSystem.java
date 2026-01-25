package Random_Assignments;
import java.util.Scanner;

enum LightState {
    RED, YELLOW, GREEN
}

class TrafficLight {
    private String location;
    private LightState currentState;
    private int redDuration;    // seconds
    private int yellowDuration; // seconds
    private int greenDuration;  // seconds

    public TrafficLight(String location, int redDuration, int yellowDuration, int greenDuration) {
        this.location = location;
        this.currentState = LightState.RED;
        this.redDuration = redDuration;
        this.yellowDuration = yellowDuration;
        this.greenDuration = greenDuration;
    }

    public String getLocation() {
        return location;
    }

    public LightState getCurrentState() {
        return currentState;
    }

    public void changeState(LightState newState) {
        this.currentState = newState;
    }

    public int getCurrentDuration() {
        switch (currentState) {
            case RED:
                return redDuration;
            case YELLOW:
                return yellowDuration;
            case GREEN:
                return greenDuration;
            default:
                return 0;
        }
    }

    public void nextState() {
        switch (currentState) {
            case RED:
                currentState = LightState.GREEN;
                break;
            case GREEN:
                currentState = LightState.YELLOW;
                break;
            case YELLOW:
                currentState = LightState.RED;
                break;
        }
    }

    public void displayLight() {
        System.out.println("\n╔══════════════════════════╗");
        System.out.println("║  " + location + " Traffic Light  ║");
        System.out.println("╠══════════════════════════╣");

        if (currentState == LightState.RED) {
            System.out.println("║         ⬤ RED            ║");
            System.out.println("║         ○ YELLOW         ║");
            System.out.println("║         ○ GREEN          ║");
        } else if (currentState == LightState.YELLOW) {
            System.out.println("║         ○ RED            ║");
            System.out.println("║         ⬤ YELLOW         ║");
            System.out.println("║         ○ GREEN          ║");
        } else {
            System.out.println("║         ○ RED            ║");
            System.out.println("║         ○ YELLOW         ║");
            System.out.println("║         ⬤ GREEN          ║");
        }

        System.out.println("╚══════════════════════════╝");
        System.out.println("Status: " + currentState + " (" + getCurrentDuration() + " seconds)");
    }
}

public class TrafficLightSystem {
    private TrafficLight[] lights;
    private Scanner scanner;
    private boolean autoMode;

    public TrafficLightSystem() {
        this.scanner = new Scanner(System.in);
        this.autoMode = false;
        initializeLights();
    }

    private void initializeLights() {
        lights = new TrafficLight[4];
        lights[0] = new TrafficLight("North", 30, 5, 25);
        lights[1] = new TrafficLight("South", 30, 5, 25);
        lights[2] = new TrafficLight("East", 30, 5, 25);
        lights[3] = new TrafficLight("West", 30, 5, 25);

        // Set initial states - North and South green, East and West red
        lights[0].changeState(LightState.GREEN);
        lights[1].changeState(LightState.GREEN);
    }

    public void displayAllLights() {
        System.out.println("\n═══════════════════════════════════════════");
        System.out.println("        TRAFFIC LIGHT SYSTEM STATUS        ");
        System.out.println("═══════════════════════════════════════════");

        for (TrafficLight light : lights) {
            System.out.printf("%-8s: [%s] %d sec\n",
                    light.getLocation(),
                    getColorEmoji(light.getCurrentState()),
                    light.getCurrentDuration());
        }
        System.out.println("═══════════════════════════════════════════");
    }

    private String getColorEmoji(LightState state) {
        switch (state) {
            case RED:
                return "🔴 RED   ";
            case YELLOW:
                return "🟡 YELLOW";
            case GREEN:
                return "🟢 GREEN ";
            default:
                return "        ";
        }
    }

    public void manualControl() {
        System.out.println("\n--- Manual Control Mode ---");
        System.out.println("1. North Light");
        System.out.println("2. South Light");
        System.out.println("3. East Light");
        System.out.println("4. West Light");
        System.out.print("Select light to change: ");

        int choice = scanner.nextInt();

        if (choice >= 1 && choice <= 4) {
            TrafficLight selectedLight = lights[choice - 1];
            selectedLight.displayLight();

            System.out.println("\nChange to:");
            System.out.println("1. RED");
            System.out.println("2. YELLOW");
            System.out.println("3. GREEN");
            System.out.print("Select state: ");

            int stateChoice = scanner.nextInt();

            switch (stateChoice) {
                case 1:
                    selectedLight.changeState(LightState.RED);
                    break;
                case 2:
                    selectedLight.changeState(LightState.YELLOW);
                    break;
                case 3:
                    selectedLight.changeState(LightState.GREEN);
                    break;
                default:
                    System.out.println("Invalid state!");
                    return;
            }

            System.out.println("\n✓ Light changed successfully!");
            selectedLight.displayLight();
        } else {
            System.out.println("Invalid light selection!");
        }
    }

    public void simulateAutoCycle() {
        System.out.println("\n--- Automatic Cycle Simulation ---");
        System.out.println("Running one complete cycle...\n");

        try {
            // North-South GREEN, East-West RED
            System.out.println("Phase 1: North-South GREEN (25 sec)");
            lights[0].changeState(LightState.GREEN);
            lights[1].changeState(LightState.GREEN);
            lights[2].changeState(LightState.RED);
            lights[3].changeState(LightState.RED);
            displayAllLights();
            Thread.sleep(3000); // 3 seconds for demo

            // North-South YELLOW
            System.out.println("\nPhase 2: North-South YELLOW (5 sec)");
            lights[0].changeState(LightState.YELLOW);
            lights[1].changeState(LightState.YELLOW);
            displayAllLights();
            Thread.sleep(2000);

            // North-South RED, East-West GREEN
            System.out.println("\nPhase 3: East-West GREEN (25 sec)");
            lights[0].changeState(LightState.RED);
            lights[1].changeState(LightState.RED);
            lights[2].changeState(LightState.GREEN);
            lights[3].changeState(LightState.GREEN);
            displayAllLights();
            Thread.sleep(3000);

            // East-West YELLOW
            System.out.println("\nPhase 4: East-West YELLOW (5 sec)");
            lights[2].changeState(LightState.YELLOW);
            lights[3].changeState(LightState.YELLOW);
            displayAllLights();
            Thread.sleep(2000);

            // Back to North-South GREEN
            System.out.println("\n✓ Cycle complete! Returning to start...");
            lights[0].changeState(LightState.GREEN);
            lights[1].changeState(LightState.GREEN);
            lights[2].changeState(LightState.RED);
            lights[3].changeState(LightState.RED);
            displayAllLights();

        } catch (InterruptedException e) {
            System.out.println("Simulation interrupted!");
        }
    }

    public void emergencyMode() {
        System.out.println("\n🚨 EMERGENCY MODE ACTIVATED 🚨");
        System.out.println("All lights set to RED!");

        for (TrafficLight light : lights) {
            light.changeState(LightState.RED);
        }

        displayAllLights();
    }

    public void showMenu() {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║    Traffic Light Control System    ║");
        System.out.println("╠════════════════════════════════════╣");
        System.out.println("║ 1. View All Lights Status          ║");
        System.out.println("║ 2. Manual Control                  ║");
        System.out.println("║ 3. Simulate Auto Cycle             ║");
        System.out.println("║ 4. Emergency Mode (All Red)        ║");
        System.out.println("║ 5. View Single Light               ║");
        System.out.println("║ 6. Exit                            ║");
        System.out.println("╚════════════════════════════════════╝");
        System.out.print("Select option: ");
    }

    public void viewSingleLight() {
        System.out.println("\n--- View Single Light ---");
        System.out.println("1. North");
        System.out.println("2. South");
        System.out.println("3. East");
        System.out.println("4. West");
        System.out.print("Select light: ");

        int choice = scanner.nextInt();

        if (choice >= 1 && choice <= 4) {
            lights[choice - 1].displayLight();
        } else {
            System.out.println("Invalid selection!");
        }
    }

    public void start() {
        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║  Welcome to Traffic Light System     ║");
        System.out.println("║  4-Way Intersection Controller       ║");
        System.out.println("╚═══════════════════════════════════════╝");

        boolean exit = false;

        while (!exit) {
            showMenu();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayAllLights();
                    break;
                case 2:
                    manualControl();
                    break;
                case 3:
                    simulateAutoCycle();
                    break;
                case 4:
                    emergencyMode();
                    break;
                case 5:
                    viewSingleLight();
                    break;
                case 6:
                    System.out.println("\nShutting down Traffic Light System...");
                    System.out.println("Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("\n❌ Invalid option! Please try again.");
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        TrafficLightSystem system = new TrafficLightSystem();
        system.start();
    }
}