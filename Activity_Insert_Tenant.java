package com.patrickgatirigmail.finale;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.patrickgatirigmail.finale.Dialog_Tenant_Profile_Picture.OnButtonClickedListener;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Activity_Insert_Tenant extends AppCompatActivity implements OnButtonClickedListener {
    private static final int CHOOSE_IMAGE_REQUEST_CODE = 1;
    private static final int PHONE_NUMBER_LENGTH = 10;
    private static final String TAG = "Activity_Insert_Tenant";
    private static final int TAKE_PHOTO_REQUEST_CODE = 2;
    private String current_picture_file_path;
    private TextInputLayout insert_first_name;
    private TextInputLayout insert_house_number;
    private TextInputLayout insert_last_name;
    /* access modifiers changed from: private */
    public TextInputLayout insert_phone_number;
    private TextInputLayout insert_rent_per_month;
    /* access modifiers changed from: private */
    public ImageView tenant_dp_image_view;
    private Uri tenant_profile_picture_URI;

    private static class getPickedImageAsyncTask extends AsyncTask<Uri, Void, Bitmap> {
        private WeakReference<Activity_Insert_Tenant> weakReference;

        private getPickedImageAsyncTask(Activity_Insert_Tenant activity_insert_tenant) {
            this.weakReference = new WeakReference<>(activity_insert_tenant);
        }

        /* access modifiers changed from: protected */
        public Bitmap doInBackground(Uri... uris) {
            Bitmap bitmap = null;
            try {
                ParcelFileDescriptor parcelFileDescriptor = ((Activity_Insert_Tenant) this.weakReference.get()).getContentResolver().openFileDescriptor(uris[0], "r");
                bitmap = BitmapFactory.decodeFileDescriptor(((ParcelFileDescriptor) Objects.requireNonNull(parcelFileDescriptor)).getFileDescriptor());
                parcelFileDescriptor.close();
                return bitmap;
            } catch (IOException e) {
                e.printStackTrace();
                return bitmap;
            }
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Bitmap bitmap) {
            ((Activity_Insert_Tenant) this.weakReference.get()).tenant_dp_image_view.setImageBitmap(bitmap);
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity__insert__tenant);
        init();
    }

    private void init() {
        setSupportActionBar((Toolbar) findViewById(R.id.insert_house_toolbar));
        setTitle(R.string.NEW_HOUSE);
        this.insert_house_number = (TextInputLayout) findViewById(R.id.insert_house_number);
        this.insert_first_name = (TextInputLayout) findViewById(R.id.insert_first_name);
        this.insert_last_name = (TextInputLayout) findViewById(R.id.insert_last_name);
        this.insert_phone_number = (TextInputLayout) findViewById(R.id.insert_phone_number);
        this.insert_rent_per_month = (TextInputLayout) findViewById(R.id.insert_rent_per_month);
        Button choose_tenant_dp_button = (Button) findViewById(R.id.choose_tenant_dp_button);
        this.tenant_dp_image_view = (ImageView) findViewById(R.id.tenant_dp_image_view);
        choose_tenant_dp_button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                new Dialog_Tenant_Profile_Picture().show(Activity_Insert_Tenant.this.getSupportFragmentManager(), Activity_Insert_Tenant.TAG);
            }
        });
        this.insert_phone_number.getEditText().addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().length() == 10) {
                    Activity_Insert_Tenant.this.insert_phone_number.setError(null);
                }
            }

            public void afterTextChanged(Editable editable) {
            }
        });
    }

    public void onButtonClick(int button_clicked) {
        if (button_clicked == R.id.choose_existing_pic_button) {
            view_photos();
        } else if (button_clicked == R.id.take_new_photo_button) {
            takeTenantProfilePicture();
        }
    }

    private void view_photos() {
        Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("image/*");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 1);
            Log.d(TAG, "view_photos: Viewing the photos on the phone");
        }
    }

    private void takeTenantProfilePicture() {
        if (getPackageManager().hasSystemFeature("android.hardware.camera.any")) {
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            if (intent.resolveActivity(getPackageManager()) == null) {
                return;
            }
            if (isExternalStorageAvailable()) {
                File profile_picture_file = createProfilePictureFile();
                if (profile_picture_file != null) {
                    intent.putExtra("output", FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID, profile_picture_file));
                    intent.setFlags(2);
                    startActivityForResult(intent, 2);
                    return;
                }
                Toast.makeText(this, R.string.ERROR, 0).show();
                return;
            }
            Toast.makeText(this, R.string.external_storage_unavailable, 0).show();
            return;
        }
        Toast.makeText(this, R.string.device_has_no_camera, 0).show();
    }

    @SuppressLint({"SimpleDateFormat"})
    private String generateFileName() {
        String current_time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append("JPEG_");
        sb.append(current_time);
        sb.append(".jpg");
        return sb.toString();
    }

    private boolean isExternalStorageAvailable() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    private File createProfilePictureFile() {
        File tenant_profile_picture_file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), generateFileName());
        this.current_picture_file_path = tenant_profile_picture_file.getAbsolutePath();
        return tenant_profile_picture_file;
    }

    private boolean input_validation() {
        if (this.insert_house_number.getEditText().getText().toString().trim().length() == 0) {
            this.insert_house_number.setError(getString(R.string.field_cant_be_empty));
            return false;
        } else if (this.insert_first_name.getEditText().getText().toString().trim().length() == 0) {
            this.insert_first_name.setError(getString(R.string.field_cant_be_empty));
            return false;
        } else if (this.insert_last_name.getEditText().getText().toString().trim().length() == 0) {
            this.insert_last_name.setError(getString(R.string.field_cant_be_empty));
            return false;
        } else if (this.insert_phone_number.getEditText().getText().toString().trim().length() != 10) {
            this.insert_phone_number.setError(getString(R.string.invalid_phone_number));
            return false;
        } else if (this.insert_rent_per_month.getEditText().getText().toString().trim().length() != 0) {
            return true;
        } else {
            this.insert_rent_per_month.setError(getString(R.string.field_cant_be_empty));
            return false;
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != R.id.save_button) {
            return super.onOptionsItemSelected(item);
        }
        if (input_validation()) {
            int house_number = Integer.parseInt(this.insert_house_number.getEditText().getText().toString().trim());
            String first_name = this.insert_first_name.getEditText().getText().toString().trim();
            String last_name = this.insert_last_name.getEditText().getText().toString().trim();
            long phone_number = Long.parseLong(this.insert_phone_number.getEditText().getText().toString().trim());
            double rent_per_month = Double.parseDouble(this.insert_rent_per_month.getEditText().getText().toString().trim());
            if (this.tenant_profile_picture_URI != null) {
                long j = phone_number;
                long j2 = phone_number;
                ViewModel_INSERTTENANT viewModel_INSERTTENANT = (ViewModel_INSERTTENANT) ViewModelProviders.of((FragmentActivity) this).get(ViewModel_INSERTTENANT.class);
                Entity_TENANTS entity_TENANTS = r2;
                Entity_TENANTS entity_TENANTS2 = new Entity_TENANTS(house_number, new TenantName(first_name, last_name), this.tenant_profile_picture_URI.toString(), j, rent_per_month);
                viewModel_INSERTTENANT.insert_tenant(this, entity_TENANTS);
            } else {
                long phone_number2 = phone_number;
                ViewModel_INSERTTENANT viewModel_INSERTTENANT2 = (ViewModel_INSERTTENANT) ViewModelProviders.of((FragmentActivity) this).get(ViewModel_INSERTTENANT.class);
                Entity_TENANTS entity_TENANTS3 = new Entity_TENANTS(house_number, new TenantName(first_name, last_name), null, phone_number2, rent_per_month);
                viewModel_INSERTTENANT2.insert_tenant(this, entity_TENANTS3);
            }
            finish();
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (1 == requestCode && -1 == resultCode && data != null) {
            this.tenant_profile_picture_URI = data.getData();
            new getPickedImageAsyncTask().execute(new Uri[]{data.getData()});
        }
        if (2 != requestCode || -1 != resultCode) {
            return;
        }
        if (data == null || data.getData() == null) {
            Toast.makeText(this, "Null", 0).show();
        } else {
            Toast.makeText(this, "Not null", 0).show();
        }
    }
}
