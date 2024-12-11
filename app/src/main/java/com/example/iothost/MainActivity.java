package com.example.iothost;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;



public class MainActivity extends AppCompatActivity {


    TextView GyroX,GyroY, GyroZ, AccelX, AccelY, AccelZ;
    SensorManager sensorManager;
    Sensor gyroscope;
    Sensor accelerometer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Obtener el SensorManager
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // Obtener los sensore: acelerómetro, giroscopio
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


        GyroX = findViewById(R.id.tvX);
        GyroY = findViewById(R.id.tvY);
        GyroZ = findViewById(R.id.tvZ);

        AccelX = findViewById(R.id.acX);
        AccelY = findViewById(R.id.acY);
        AccelZ = findViewById(R.id.acZ);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
    }

    public void onResume(){
        // Registrar el listener para ambos sensores
        super.onResume();
        if (accelerometer != null) {
            sensorManager.registerListener(gyroListener,gyroscope,SensorManager.SENSOR_DELAY_FASTEST);
        }
        if (gyroscope != null) {
            sensorManager.registerListener(accelListener,accelerometer,SensorManager.SENSOR_DELAY_FASTEST);
        }


    }

    public void onStop(){
        super.onStop();
        sensorManager.unregisterListener(gyroListener);
    }
    // Configurar el SensorEventListener para manejar los cambios de los sensores
    public SensorEventListener gyroListener = new SensorEventListener(){
        @SuppressLint("SetTextI18n")
        @Override
        public void onSensorChanged(SensorEvent event){
            float x = event.values[0];
            float y = event.values[0];
            float z = event.values[0];

            GyroX.setText("Gyro X: " + x);
            GyroY.setText("Gyro Y: " + y);
            GyroZ.setText("Gyro Z: " + z);

        }

        @Override
        public void onAccuracyChanged(Sensor gyroscope, int i){

        }
    };
    public SensorEventListener accelListener = new SensorEventListener(){
        @SuppressLint("SetTextI18n")
        @Override
        public void onSensorChanged(SensorEvent event){
            float x = event.values[0];
            float y = event.values[0];
            float z = event.values[0];

            AccelX.setText("Accel X: " + x);
            AccelY.setText("Accel Y: " + y);
            AccelZ.setText("Accel Z: " + z);

        }

        @Override
        public void onAccuracyChanged(Sensor accelerometer, int i){

        }
    };



}