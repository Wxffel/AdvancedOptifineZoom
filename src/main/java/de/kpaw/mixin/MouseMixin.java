package de.kpaw.mixin;

import de.kpaw.DataHolder;
import de.kpaw.AOZUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mouse.class)
public abstract class MouseMixin {

    @Shadow
    private MinecraftClient client;

    @Shadow
    private double eventDeltaWheel;

    @Inject(
            method = "onMouseScroll(JDD)V",
            at = @At(value = "FIELD", target = "Lnet/minecraft/client/Mouse;eventDeltaWheel:D", ordinal = 6),
            cancellable = true
    )
    private void changeZoomOnMouseScroll(CallbackInfo info) {
        if (DataHolder.INSTANCE.isPlayerZooming()) {
            AOZUtils.INSTANCE.updateZoom(this.eventDeltaWheel);

            if (DataHolder.INSTANCE.getCurrentZoom() != DataHolder.DEFAULT_ZOOM_VALUE)
                info.cancel();
        }
    }

    @Inject(
            method = "onMouseButton(JIII)V",
            at = @At(
                    value = "INVOKE",
                    target = "net/minecraft/client/options/KeyBinding.setKeyPressed(Lnet/minecraft/client/util/InputUtil$Key;Z)V",
                    shift = At.Shift.AFTER
            ),
            cancellable = true
    )
    private void resetZoomOnScrollWheelClick(long window, int button, int action, int mods, CallbackInfo info) {
        if (button == 2 && action == 1 && DataHolder.INSTANCE.isPlayerZooming()) {
            AOZUtils.INSTANCE.resetZoom();
            this.client.worldRenderer.scheduleTerrainUpdate();
            info.cancel();
        }
    }
}
