package de.kpaw

object DataHolder {
    const val DEFAULT_ZOOM_VALUE = 20.0
    var currentZoom: Double = DEFAULT_ZOOM_VALUE
    var isPlayerZooming: Boolean = false
    var smoothCameraWasEnabled: Boolean = false
}