package de.kpaw

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.options.KeyBinding
import net.minecraft.client.util.InputUtil
import org.lwjgl.glfw.GLFW

object AOZKeyBindings {

    private const val KEY_ZOOM_MOD = "key.aoz_mod."

    val zoomKeyBinding: KeyBinding = KeyBindingHelper.registerKeyBinding(KeyBinding(
        "${KEY_ZOOM_MOD}zoom",
        InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_G,
        "key.categories.misc"))

}