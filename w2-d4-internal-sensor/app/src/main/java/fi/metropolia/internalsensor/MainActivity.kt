package fi.metropolia.internalsensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private var light: Sensor? = null
    private var isCoveringEyes = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)?.let {
            light = it
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        when (event?.sensor?.type) {
            Sensor.TYPE_LIGHT -> {
                val lightValue = event.values[0]
                tvLightValue.text = lightValue.toString()
                if (lightValue < 100 &&!isCoveringEyes) {
                    isCoveringEyes = true
                    ivMonkey.setImageResource(R.mipmap.monkey_covered)
                } else if (lightValue > 100 && isCoveringEyes) {
                    isCoveringEyes = false
                    ivMonkey.setImageResource(R.mipmap.monkey_gasp)
                }
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    override fun onResume() {
        super.onResume()
        light?.also { light ->
            sensorManager.registerListener(this, light, SensorManager.SENSOR_DELAY_GAME)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }
}
