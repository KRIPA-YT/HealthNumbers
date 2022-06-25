package de.kripa.healthnumbers.mixins;

import de.kripa.healthnumbers.HealthNumbers;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin extends DrawableHelper {
    @Shadow
    private int scaledHeight;
    @Shadow
    private int scaledWidth;
    @Shadow
    abstract PlayerEntity getCameraPlayer();
    @Shadow
    abstract LivingEntity getRiddenEntity();

    @Inject(
            method = "renderStatusBars",
            at = @At("HEAD"),
            cancellable = true
    )
    private void renderStatusBars(MatrixStack matrices, CallbackInfo info) {
        if(HealthNumbers.getMod().modOnline) {
            MinecraftClient client = MinecraftClient.getInstance();
            client.textRenderer.drawWithShadow(matrices, (int) getCameraPlayer().getHealth() + "/" + (int) getCameraPlayer().getMaxHealth(), scaledWidth / 2 - 91, scaledHeight - 40, getCameraPlayer().getActiveStatusEffects().containsKey(StatusEffects.WITHER) ? 0x272727 : 0xdd2222);
            int food = getCameraPlayer().getHungerManager().getFoodLevel();
            client.textRenderer.drawWithShadow(matrices, food + "/20", scaledWidth / 2 + 62 + (food < 10 ? client.textRenderer.getWidth("5") : 0),  scaledHeight - 40, 0xa76a40);
            int air = (getCameraPlayer().getAir() + 20) / 16;
            if(air == 20) {
                // Saturation
                client.textRenderer.drawWithShadow(matrices, (int) getCameraPlayer().getHungerManager().getSaturationLevel() + "/20", scaledWidth / 2 + 62 + (air < 10 ? client.textRenderer.getWidth("5") : 0), scaledHeight - 50, 0xc98c62);
            } else {
                // Air
                client.textRenderer.drawWithShadow(matrices, air + "/20", scaledWidth / 2 + 62 + (air < 10 ? client.textRenderer.getWidth("5") : 0), scaledHeight - 50, 0x009aff);
            }
            info.cancel();
        }
    }

    @Inject(
            method = "renderMountHealth",
            at = @At("HEAD"),
            cancellable = true
    )
    private void renderMountHealth(MatrixStack matrices, CallbackInfo info) {
        if(HealthNumbers.getMod().modOnline) {
            if(getRiddenEntity() != null) {
                MinecraftClient client = MinecraftClient.getInstance();
                client.textRenderer.drawWithShadow(matrices, (int) getRiddenEntity().getHealth() + "/" + (int) getRiddenEntity().getMaxHealth(), scaledWidth / 2 - 91, scaledHeight - 50, 0xda662b);
            }
            info.cancel();
        }
    }
}
