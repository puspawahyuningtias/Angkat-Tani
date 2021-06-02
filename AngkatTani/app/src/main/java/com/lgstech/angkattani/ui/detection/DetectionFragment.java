package com.lgstech.angkattani.ui.detection;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.lgstech.angkattani.DataViewModel;
import com.lgstech.angkattani.R;
import com.lgstech.angkattani.databinding.FragmentDetectionBinding;
import com.lgstech.angkattani.databinding.FragmentPredictionBinding;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class DetectionFragment extends Fragment {

    private FragmentDetectionBinding binding;
    private static DataViewModel dataViewModel;
    public static final int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 9921;
    private static final int CAMERA_REQUEST = 0;
    private static final int MY_CAMERA_PERMISSION_CODE = 0;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        View root = inflater.inflate(R.layout.fragment_detection, container, false);
        binding = FragmentDetectionBinding.inflate(inflater, container, false);
        dataViewModel = new ViewModelProvider(this).get(DataViewModel.class);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.plant_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        binding.spinnerDetection.setAdapter(adapter);
        binding.ibCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = getLayoutInflater();
                View vw = inflater.inflate(R.layout.selected, null);
                ImageView camera = vw.findViewById(R.id.camera);
                ImageView galeri = vw.findViewById(R.id.galeri);
                camera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (getActivity().checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                                requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                            } else {
                                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(cameraIntent, CAMERA_REQUEST);
                            }
                        } else {
                            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(cameraIntent, CAMERA_REQUEST);
                        }
                    }
                });
                galeri.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);

                    }
                });
                builder.setView(vw);
                builder.show();



            }
        });
        return binding.getRoot();
    }



//    public static void checkForCamaraWritePermissions(final Fragment fragment, WorkFinish workFinish) {
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
//            workFinish.onWorkFinish(true);
//        } else {
//            List<String> permissionsNeeded = new ArrayList<String>();
//            final List<String> permissionsList = new ArrayList<String>();
//            if (!addPermission(permissionsList, Manifest.permission.CAMERA, fragment.getActivity()))
//                permissionsNeeded.add("CAMERA");
//            if (!addPermission(permissionsList, Manifest.permission.WRITE_EXTERNAL_STORAGE, fragment.getActivity()))
//                permissionsNeeded.add("WRITE_EXTERNAL_STORAGE");
//            if (permissionsList.size() > 0) {
//                fragment.requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
//                        REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
//            } else {
//                workFinish.onWorkFinish(true);
//            }
//        }
//    }
//
//    public static void checkForCamaraWritePermissions(final Fragment fragment, WorkFinish workFinish) {
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
//            workFinish.onWorkFinish(true);
//        } else {
//            List<String> permissionsNeeded = new ArrayList<String>();
//            final List<String> permissionsList = new ArrayList<String>();
//            if (!addPermission(permissionsList, Manifest.permission.CAMERA, fragment.getActivity()))
//                permissionsNeeded.add("CAMERA");
//            if (!addPermission(permissionsList, Manifest.permission.WRITE_EXTERNAL_STORAGE, fragment.getActivity()))
//                permissionsNeeded.add("WRITE_EXTERNAL_STORAGE");
//            if (permissionsList.size() > 0) {
//                fragment.requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
//                        REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
//            } else {
//                workFinish.onWorkFinish(true);
//            }
//        }
//    }

//    public interface WorkFinish {
//        void onWorkFinish(Boolean check);
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.M)
//    private static boolean addPermission(List<String> permissionsList, String permission, Activity ac) {
//        if (ac.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
//            permissionsList.add(permission);
//            // Check for Rationale Option
//            return ac.shouldShowRequestPermissionRationale(permission);
//        }
//        return true;
//    }


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
//                }
//                break;
//        }
//    }



//    private void setToImageView(Bitmap bmp) {
//        //compress image
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, bytes);
//        decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(bytes.toByteArray()));
//
//        //menampilkan gambar yang dipilih dari camera/gallery ke ImageView
//        imageView.setImageBitmap(decoded);
//        upload = true;
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