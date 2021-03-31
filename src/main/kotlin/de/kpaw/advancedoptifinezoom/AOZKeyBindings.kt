package de.kpaw.advancedoptifinezoom

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.options.KeyBinding
import net.minecraft.client.util.InputUtil
import org.lwjgl.glfw.GLFW

object AOZKeyBindings {

    private const val TRANSLATION_KEY_AOZ_MOD = "key.aoz_mod."

    val zoomKeyBinding: KeyBinding = KeyBindingHelper.registerKeyBinding(KeyBinding(
        "${TRANSLATION_KEY_AOZ_MOD}zoom",
        InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_Z,
        "key.categories.misc"))

}