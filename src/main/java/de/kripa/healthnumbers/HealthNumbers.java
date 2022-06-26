package de.kripa.healthnumbers;
import net.fabricmc.api.ClientModInitializer;

public class HealthNumbers implements ClientModInitializer {
    public boolean modOnline = true;
    private static HealthNumbers self;

    public static int x = 100;
    public static int y = 100;

    @Override
    public void onInitializeClient() {
        self = this;

    }

    public static HealthNumbers getMod() {
        return self;
    }
}
