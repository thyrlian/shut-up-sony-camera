package com.dreiri.sh;

import java.io.File;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnHush = (Button) findViewById(R.id.btn_hush);
        TextView txtRootInfo = (TextView) findViewById(R.id.txt_root_info);
        if (RootManager.isRooted()) {
            txtRootInfo.setText(getResources().getString(R.string.txt_root_pos));
            txtRootInfo.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
            btnHush.setVisibility(android.view.View.VISIBLE);
            btnHush.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    disableCameraSound();
                }
            });
        } else {
            txtRootInfo.setText(getResources().getString(R.string.txt_root_neg));
            txtRootInfo.setTextColor(Color.RED);
            btnHush.setVisibility(android.view.View.INVISIBLE);
        }
    }

    private void disableCameraSound() {
        String path = "system/media/audio/ui/";
        renameFile(path + "camera_click.ogg");
        renameFile(path + "camera_focus.ogg");
    }

    private void renameFile(String filePathAndName) {
        try {
            File file = new File(filePathAndName);
            String path = file.getPath();
            String name = file.getName();
            String newName = name + "_backup";
            File newFile = new File(path, newName);
            file.renameTo(newFile);

            String newFilePathAndName = path + newName;
            Runtime.getRuntime().exec("su mv " + filePathAndName + " " + newFilePathAndName);

            Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
