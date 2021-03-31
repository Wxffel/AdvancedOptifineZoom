package de.kpaw.mixin;

import de.kpaw.DataHolder;
import de.kpaw.AOZUtils;
import de.kpaw.AOZKeyBindings;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {

    @Shadow
    private MinecraftClient client;

    private boolean firstIteration = true;

    @Inject(
            method = "getFov(Lnet/minecraft/client/render/Camera;FZ)D",
            at = @At("RETURN"),
            cancellable = true
    )
    private void getZoomFOV(Camera camera, float tickDelta, boolean changingFov, CallbackInfoReturnable<Double> infoReturnable) {
        if (AOZKeyBindings.INSTANCE.getZoomKeyBinding().isPressed()) {
            if (firstIteration) {
                firstIteration = false;
                DataHolder.INSTANCE.setPlayerZooming(true);
                DataHolder.INSTANCE.setSmoothCameraWasEnabled(this.client.options.smoothCameraEnabled);
                this.client.options.smoothCameraEnabled = true;
            }
            infoReturnable.setReturnValue(DataHolder.INSTANCE.getCurrentZoom());
        } else if (DataHolder.INSTANCE.isPlayerZooming()) {
            firstIteration = true;
            DataHolder.INSTANCE.setPlayerZooming(false);
            AOZUtils.INSTANCE.resetZoom();
            this.client.options.smoothCameraEnabled = DataHolder.INSTANCE.getSmoothCameraWasEnabled();
            this.client.worldRenderer.scheduleTerrainUpdate();
        }
    }

}
