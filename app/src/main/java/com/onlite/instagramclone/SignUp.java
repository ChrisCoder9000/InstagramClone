package com.onlite.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private Button btnSave;
    private EditText edtKickboxerName, edtPunchSpeed, edtPunchPower, edtKickSpeed, edtKickPower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnSave = findViewById(R.id.btnSaveToServer);
        btnSave.setOnClickListener(SignUp.this);

        edtKickboxerName = findViewById(R.id.edtKickboxerName);
        edtPunchSpeed = findViewById(R.id.edtPunchSpeed);
        edtPunchPower = findViewById(R.id.edtPunchPower);
        edtKickSpeed = findViewById(R.id.edtKickSpeed);
        edtKickPower = findViewById(R.id.edtKickPower);
    }

//    public void helloWorldTapped(View view) {
////        ParseObject boxer = new ParseObject("Boxer");
////        boxer.put("punch_speed", 200);
////        boxer.saveInBackground(new SaveCallback() {
////            @Override
////            public void done(ParseException e) {
////                if (e == null) {
////                    Toast.makeText(SignUp.this, "boxer object is saved", Toast.LENGTH_LONG).show();
////                } else {
////                    Toast.makeText(SignUp.this, "Nope :/", Toast.LENGTH_SHORT).show();
////                }
////            }
////        });
//
//
//    }

    @Override
    public void onClick(View v) {
        try {
            ParseObject kickboxer = new ParseObject("Kickboxer");
            kickboxer.put("name", edtKickboxerName.getText().toString());
            kickboxer.put("punchSpeed", Integer.parseInt(edtPunchSpeed.getText().toString()));
            kickboxer.put("punchPower", Integer.parseInt(edtPunchPower.getText().toString()));
            kickboxer.put("kickSpeed", Integer.parseInt(edtKickSpeed.getText().toString()));
            kickboxer.put("kickPower", Integer.parseInt(edtKickPower.getText().toString()));
            kickboxer.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        FancyToast.makeText(SignUp.this, kickboxer.get("name") + " is saved to server", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                    } else {
                        FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                    }
                }
            });
        } catch (Exception e) {
            FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
        }
    }
}