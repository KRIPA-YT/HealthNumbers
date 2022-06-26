package de.kripa.healthnumbers.mixins;

import de.kripa.healthnumbers.HealthNumbers;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameMenuScreen.class)
public class GameMenuScreenMixin extends Screen {
    protected GameMenuScreenMixin(Text title) {
        super(title);
    }

    @Inject(at= @At("HEAD"), method = "initWidgets")
    private void initWidgets(CallbackInfo ci) {
        this.addDrawableChild(new ButtonWidget(10, 10, 120, 20, Text.translatable(HealthNumbers.getMod().modOnline ? "healthnumbers.options.disable" : "healthnumbers.options.enable"), (button) -> {
            HealthNumbers.getMod().modOnline = !HealthNumbers.getMod().modOnline;
            client.setScreen(this);
        }));
    }
}
