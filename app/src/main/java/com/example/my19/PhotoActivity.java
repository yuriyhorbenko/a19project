package com.example.my19;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PhotoActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_WRITE_STORAGE = 8;
    final int TYPE_PHOTO = 1;
    final int REQUEST_CODE_PHOTO = 1;
    File directory;
    ImageView ivPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        createDirectory();
        ivPhoto = findViewById(R.id.ivPhoto);
        Button btnPhoto = findViewById(R.id.btnPhoto);
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUserPermissionToReadStorage();
                //onClickPhoto(v);
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void checkUserPermissionToReadStorage() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (!shouldShowRequestPermissionRationale(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                showUserDeniedSnackbar();
            }
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_WRITE_STORAGE);
        } else {
            onClickPhoto();
            //selectPhotoCaptureMethod();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_WRITE_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    onClickPhoto();
                    //	selectPhotoCaptureMethod();
                } else {
                    showUserDeniedSnackbar();
                }
                return;
            }
        }
    }

    public void showUserDeniedSnackbar() {
        Toast.makeText(this, "No permissions to read storage", Toast.LENGTH_LONG).show();
    }

    public void onClickPhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, generateFileUri());
        startActivityForResult(intent, REQUEST_CODE_PHOTO);
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, generateFileUri());
//        startActivityForResult(intent, REQUEST_CODE_PHOTO);
    }


    private Uri generateFileUri() {

        String tstamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File file = new File(new File(Environment.getExternalStorageDirectory().getAbsolutePath() +
                "/Android/data/" + getPackageName() + "/cache/"), "IMG_" + tstamp + ".jpg");
        return FileProvider.getUriForFile(this,
                BuildConfig.APPLICATION_ID + ".provider",
                file);
    }

    private void createDirectory() {
        directory = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                "MyFolder");
        if (!directory.exists())
            directory.mkdirs();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {
        if (requestCode == REQUEST_CODE_PHOTO) {
            if (resultCode == RESULT_OK) {
                if (intent == null) {
                    Log.d("okok", "Intent is null");
                } else {
                    Log.d("okok", "Photo uri: " + intent.getData());
                    Bundle bndl = intent.getExtras();
                    if (bndl != null) {
                        Object obj = intent.getExtras().get("data");
                        if (obj instanceof Bitmap) {
                            Bitmap bitmap = (Bitmap) obj;
                            Log.d("okok", "bitmap " + bitmap.getWidth() + " x "
                                    + bitmap.getHeight());
                            ivPhoto.setImageBitmap(bitmap);
                        }
                    }
                }
            } else if (resultCode == RESULT_CANCELED) {
                Log.d("okok", "Canceled");
            }
        }
        super.onActivityResult(requestCode, resultCode, intent);
    }
}
