package com.lgstech.angkattani;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 0;
    private static final int MY_CAMERA_PERMISSION_CODE = 0;
    Bitmap bitmap, decoded;
    int bitmap_size = 60;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_prediction, R.id.navigation_detection, R.id.navigation_about)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
//                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(cameraIntent, CAMERA_REQUEST);
//            } else {
//                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
//            }
//        }
//    }
//
//
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode) {
//            case 0:
//                if (resultCode == RESULT_OK) {
////                    Uri selectedImage = data.getData();
////                    imageView.setImageURI(selectedImage);
//                    bitmap = (Bitmap) data.getExtras().get("data");
////                    imageView.setImageBitmap(selectedImage);
//                    setToImageView(getResizedBitmap(bitmap, 512));
//                }
//
//                break;
//            case 1:
//                if (resultCode == RESULT_OK) {
////                    Uri selectedImage = data.getData();
////                    imageView.setImageURI(selectedImage);
//                    Uri filePath = data.getData();
//                    try {
//                        //mengambil fambar dari Gallery
//                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
//                        // 512 adalah resolusi tertinggi setelah image di resize, bisa di ganti.
//                        setToImageView(getResizedBitmap(bitmap, 512));
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    Toast.makeText(this,String.valueOf(bitmap),Toast.LENGTH_SHORT).show();
//                }
//                break;
//        }
//    }
//
//
//
//    private void setToImageView(Bitmap bmp) {
//        //compress image
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, bytes);
//        decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(bytes.toByteArray()));
//
//        //menampilkan gambar yang dipilih dari camera/gallery ke ImageView
////        imageView.setImageBitmap(decoded);
////        upload = true;
//    }
//
//    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
//        int width = image.getWidth();
//        int height = image.getHeight();
//
//        float bitmapRatio = (float) width / (float) height;
//        if (bitmapRatio > 1) {
//            width = maxSize;
//            height = (int) (width / bitmapRatio);
//        } else {
//            height = maxSize;
//            width = (int) (height * bitmapRatio);
//        }
//        return Bitmap.createScaledBitmap(image, width, height, true);
//    }
//
//    public String getStringImage(Bitmap bmp) {
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, baos);
//        byte[] imageBytes = baos.toByteArray();
//        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
//        return encodedImage;
//    }
}