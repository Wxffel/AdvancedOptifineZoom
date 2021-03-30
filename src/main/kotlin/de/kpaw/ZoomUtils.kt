package de.kpaw

object ZoomUtils {

    fun updateZoom(eventDeltaWheel: Double) {
        val currentZoom = DataHolder.currentZoom
        val result = currentZoom / 4

        if (eventDeltaWheel > 0.0) {
            if (currentZoom - result > 0)
                DataHolder.currentZoom -= result
        } else if (eventDeltaWheel < 0.0) {
            if (currentZoom + result > DataHolder.DEFAULT_ZOOM_VALUE)
                DataHolder.currentZoom = DataHolder.DEFAULT_ZOOM_VALUE
            else DataHolder.currentZoom += result
        }
    }

    fun resetZoom() { DataHolder.currentZoom = DataHolder.DEFAULT_ZOOM_VALUE }

}
