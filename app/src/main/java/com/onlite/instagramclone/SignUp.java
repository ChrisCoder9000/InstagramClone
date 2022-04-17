package com.onlite.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private Button btnSave;
    private EditText edtKickboxerName, edtPunchSpeed, edtPunchPower, edtKickSpeed, edtKickPower;
    private TextView txtGetData;
    private Button btnGetAllData;
    private String allKickboxers;
    private Button btnTransition;

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

        btnTransition = findViewById(R.id.btnNextActivity);

        btnGetAllData = findViewById(R.id.btnGetAllKickboxers);

        txtGetData = findViewById(R.id.txtGetData);

        txtGetData.setOnClickListener(v -> {
            ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("Kickboxer");
            parseQuery.getInBackground("ChaorOqaqU", new GetCallback<ParseObject>() {
                @Override
                public void done(ParseObject object, ParseException e) {
                    if (object != null && e == null) {
                        txtGetData.setText(object.get("name") + " - " + "Punch Power: " + object.get("punchPower"));
                    }
                }
            });
        });

        btnGetAllData.setOnClickListener(v -> {
            allKickboxers = "";
            ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("Kickboxer");

//            queryAll.whereGreaterThan("punchPower", 1000);
//            queryAll.whereGreaterThanOrEqualTo("punchPower", 3000);

            queryAll.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, ParseException e) {
                    if (e == null) {
                        if (objects.size() > 0) {
                            for (ParseObject kickboxer : objects) {
                                allKickboxers = allKickboxers + kickboxer.get("name") + "\n";
                            }
                            FancyToast.makeText(SignUp.this, allKickboxers, FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                        }
                    } else {
                        FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                    }
                }
            });
        });

        btnTransition.setOnClickListener(v -> {
            Intent intent = new Intent(SignUp.this, SignUpLoginActivity.class);
            startActivity(intent);
        });
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