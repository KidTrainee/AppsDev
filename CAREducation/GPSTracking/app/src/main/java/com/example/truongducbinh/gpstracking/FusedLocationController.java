package com.example.truongducbinh.gpstracking;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class FusedLocationController {
    private FusedLocationProviderClient mFusedLocationClient;
    private Context mContext;

    public FusedLocationController(Context context) {
        this.mFusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
        mContext = context;
    }
    // The location object may be null in the following situations:
    // - Location is turned off in the device settings. The result could
    // be null even if the last location was previously retrieved because disabling
    // location also clears the cache.
    // - The device never recorded its location, which could be the case of a new device
    // or a device that has been restored to factory settings.
    // - Google Play services on the device has restarted, and there is
    // no active {@link FusedLocationProvider} client that has requested location after
    // the services restarted. To avoid this situation you can create a new client
    // and request location updates yourself. For more information,
    // see {@link ReceivingLocationUpdates}.
    @Nullable
    public Location getLastLocation() {
        if (ActivityCompat.checkSelfPermission(mContext,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(mContext,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }
        return mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Logic to handle location object

                        }
                    }
                })
                .getResult();
    }
}
