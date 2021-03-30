package de.kpaw.main

import de.kpaw.ZoomModKeyBindings
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment

@Environment(EnvType.CLIENT)
fun init() { ZoomModKeyBindings }