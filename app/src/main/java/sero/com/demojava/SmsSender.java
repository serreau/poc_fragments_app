package sero.com.demojava;

import android.Manifest;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class SmsSender extends Fragment {
    Button sendButton;
    EditText inputreceiver;
    EditText inputmessage;
    String receiver;
    String message;

    private static final int PERMISSIONS_REQUEST_SEND_SMS =0 ;
    private static final int REQUEST_READ_PHONE_STATE=1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_smssender, container, false);

        sendButton = view.findViewById(R.id.button_smssender);
        inputreceiver = view.findViewById(R.id.input_receiver);
        inputmessage = view.findViewById(R.id.input_message);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                receiver = inputreceiver.getText().toString();
                message = inputmessage.getText().toString();

                requestSmsPermission();
            }
        });

        return view;
    }

    private void requestSmsPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.SEND_SMS}, PERMISSIONS_REQUEST_SEND_SMS);
        } else if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
        } else {
            sendSms(receiver, message);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {

        if(requestCode == PERMISSIONS_REQUEST_SEND_SMS){
            if ((grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) ) {
                sendSms(receiver,message);
                Toast.makeText(getActivity(), "SMS sent.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getActivity(), "SMS failed, please try again.", Toast.LENGTH_LONG).show();
                return;
            }
        }
        if(requestCode == REQUEST_READ_PHONE_STATE){
            if ((grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) ) {
                sendSms(receiver,message);
                Toast.makeText(getActivity(), "SMS sent.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getActivity(), "SMS failed, please try again.", Toast.LENGTH_LONG).show();
                return;
            }
        }
    }

    private void sendSms(String receiver, String message){
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(receiver, null, message, null, null);
    }
}