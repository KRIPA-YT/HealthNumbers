package de.kripa.healthnumbers;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import de.kripa.healthnumbers.mixins.InGameHudMixin;
import lombok.Getter;
import lombok.Setter;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;

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
