package com.example.noga.myapplication;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.tooltip.Tooltip;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements   TextToSpeech.OnInitListener{
    LinearLayout backToFrizzlLayout;
    TextToSpeech tts;
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Context context = getApplicationContext();

        backToFrizzlLayout = findViewById(R.id.backToFrizzlButton);
        presentTooltip(context);
        backToFrizzlLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_MAIN, null);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                final ComponentName cn = new ComponentName("com.frizzl.app.frizzleapp",
                        "com.frizzl.app.frizzleapp.appBuilder.AppBuilderActivity");
                intent.setComponent(cn);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                try
                {
                    startActivity(intent);
                }catch(ActivityNotFoundException e){
                    Toast.makeText(context,"There was an error returning to Frizzl",Toast.LENGTH_SHORT).show();
                }
            }
        });
        tts = new TextToSpeech(this, this, "com.google.android.tts");
        speakOut("hello to you");


    }

    private void speakOut(String textToSay) {
        tts.speak(textToSay, TextToSpeech.QUEUE_ADD, null);
    }

    private void presentTooltip(Context context) {
        Tooltip tooltip = new Tooltip.Builder(backToFrizzlLayout)
                .setText("Here you can\n go back to Frizzl")
                .setTextColor(context.getResources().getColor(R.color.darkGrey))
                .setTextSize(16f)
                .setTypeface(ResourcesCompat.getFont(context, R.font.calibri_regular))
                .setMargin(4f)
                .setGravity(Gravity.TOP)
                .setBackgroundColor(context.getResources().getColor(R.color.lightGrey))
                .setCornerRadius(15f)
                .setPadding(35f)
                .setDismissOnClick(true)
                .setCancelable(true)
                .show();
    }

    @Override
    public void onDestroy() {
        // Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }

    }
}

//package com.example.noga.myapplication;
// import android.content.ActivityNotFoundException;
// import android.content.ComponentName;
// import android.content.Context;
// import android.content.Intent;
// import android.graphics.Color;
// import android.net.Uri;
// import android.support.v4.content.FileProvider;
// import android.support.v4.content.res.ResourcesCompat;
// import android.support.v7.app.AppCompatActivity;
// import android.os.Bundle;
// import android.view.Gravity;
// import android.view.View;
// import android.widget.LinearLayout;
// import android.widget.TextView;
// import android.widget.Toast;
//
// import com.tooltip.Tooltip;
//
//public class MainActivity extends AppCompatActivity {
//    LinearLayout backToFrizzlLayout;
//     @Override protected void onCreate(Bundle savedInstanceState) {
//         super.onCreate(savedInstanceState);
//         setContentView(R.layout.activity_main);
//         final Context context = getApplicationContext();
//
//         backToFrizzlLayout = findViewById(R.id.backToFrizzlButton);
//         presentTooltip(context);
//         backToFrizzlLayout.setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View view) {
//                 Intent intent = new Intent(Intent.ACTION_MAIN, null);
//                 intent.addCategory(Intent.CATEGORY_LAUNCHER);
//                 final ComponentName cn = new ComponentName("com.frizzl.app.frizzleapp",
//                         "com.frizzl.app.frizzleapp.appBuilder.AppBuilderActivity");
//                 intent.setComponent(cn);
//                 intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                 try
//                 {
//                     startActivity(intent);
//                 }catch(ActivityNotFoundException e){
//                     Toast.makeText(context,"There was an error returning to Frizzl",Toast.LENGTH_SHORT).show();
//                 }
//             }
//         });
//     }
//
//    private void presentTooltip(Context context) {
//        Tooltip tooltip = new Tooltip.Builder(backToFrizzlLayout)
//                .setText("Here you can\n go back to Frizzl")
//                .setTextColor(context.getResources().getColor(R.color.darkGrey))
//                .setTextSize(16f)
//                .setTypeface(ResourcesCompat.getFont(context, R.font.calibri_regular))
//                .setMargin(4f)
//                .setGravity(Gravity.TOP)
//                .setBackgroundColor(context.getResources().getColor(R.color.lightGrey))
//                .setCornerRadius(15f)
//                .setPadding(35f)
//                .setDismissOnClick(true)
//                .setCancelable(true)
//                .show();
//    }
//}
