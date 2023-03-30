package com.example.camera2;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

public class GPSTracker implements LocationListener {

    Context context;

    //definição da classe GPSTracker
    public GPSTracker(Application c) {
        context = c;
    }

    //obter localização
    public Location getLocation() {


        //verifica a permissão de uso do GPS
        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {

            //mensagem que será exibida caso a permissão de uso do GPS não tenha sido aprovada
            Toast.makeText(context, "Não foi possível!", Toast.LENGTH_SHORT).show();
            return null;
        }

        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean isGPSEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);

        //Caso o GPS esteja habilitado, solicita updates da localização e retorna a última localização conhecida
        if (isGPSEnabled) {
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 10, this);
            Location l = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            return l;

        } else {

            //mensagem que será exibida caso o GPS não esteja habilitado
            Toast.makeText(context, "Por favor, habilitar o GPS!", Toast.LENGTH_LONG).show();

        }
//

        return null;
    }

    //metodo chamado quando o GPS é desligado
    @Override
    public void onProviderDisabled(@NonNull String provider) { }

    //metodo chamado quando uma nova localização for encontrada
    @Override
    public void onLocationChanged(@NonNull Location location) { }

    //chamado quando ha alguma alteração no status do GPS
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) { }


}







