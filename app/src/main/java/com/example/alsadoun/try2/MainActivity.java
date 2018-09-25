package com.example.alsadoun.try2;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.telephony.TelephonyManager;
import android.widget.TextView;
import android.Manifest;

import static android.Manifest.*;

//import android.
public class MainActivity extends AppCompatActivity {
    TextView tv;
    @Override
    public void onRequestPermissionsResult ( int requestCode, String[] permissions,
                                             int[] grantResults){
        if (requestCode == PERMISSIONS_REQUEST_READ_PHONE_STATE
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getDeviceImei();
        }
    }

    private void getDeviceImei() {

        mTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String deviceid = mTelephonyManager.getDeviceId();
        Log.d("msg", "DeviceImei " + deviceid);
    }

    private static final int PERMISSIONS_REQUEST_READ_PHONE_STATE = 999;

    private TelephonyManager mTelephonyManager;

    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        if (checkSelfPermission(permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{permission.READ_PHONE_STATE},
                    PERMISSIONS_REQUEST_READ_PHONE_STATE);
        } else {
            getDeviceImei();
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onSubmit(View v) {

Log.i("234" , "after submit");





    TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
    int callstate = tm.getCallState();
    String callstat = "";
        Log.i("234" , "after call");
    switch(callstate)

    {
        case TelephonyManager.CALL_STATE_IDLE:
            callstat = "phone is idle";
            break;

        case TelephonyManager.CALL_STATE_OFFHOOK:
            callstat = "phone is in use";
            break;

        case TelephonyManager.CALL_STATE_RINGING:
            callstat = "phone is Ringing";
            break;
    }Log.i("234" , "after callswitch");

    int phoneType = tm.getPhoneType();
    String ptype = "";
    switch(phoneType)

    {
        case TelephonyManager.PHONE_TYPE_CDMA:
            ptype = "\n phone type :CDMA";
            break;
        case TelephonyManager.PHONE_TYPE_GSM:
            ptype = "\n phone type :GSM";
            break;
        case TelephonyManager.PHONE_TYPE_SIP:
            ptype = "\n phone type : SIP";
            break;
        case TelephonyManager.PHONE_TYPE_NONE:
            ptype = "\n phone type :NONE";

            break;
    }

    int sim = tm.getSimState();
    String sstate = "";
    switch(sim)

    {
        case TelephonyManager.SIM_STATE_ABSENT:
            sstate = "Sim State:Absent\n";
            break;
        case TelephonyManager.SIM_STATE_NETWORK_LOCKED:
            sstate = "Sim State:Network Locked\n";
            break;
        case TelephonyManager.SIM_STATE_PIN_REQUIRED:
            sstate = "Sim State:Pin Requred\n";
            break;

        case TelephonyManager.SIM_STATE_PUK_REQUIRED:
            sstate = "Sim State:Puk Required\n";
            break;

        case TelephonyManager.SIM_STATE_READY:
            sstate = "Sim State:Ready\n";
            break;

        case TelephonyManager.SIM_STATE_UNKNOWN:
            sstate = "Sim State:Unknown\n";
            break;
    } //Log.i("234" , "after Sim switch");
    tv.setText(callstat +ptype+sstate);

}

public boolean onCreateOptionMenu(Menu mnue ){

    getMenuInflater().inflate(R.menu.main,menu);
    return true;
}
    }













