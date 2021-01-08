package com.patrickgatirigmail.finale;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.patrickgatirigmail.finale.Dialog_Update_Tenant_Profile_Picture.OnButtonSelectedListener;
import com.squareup.picasso.Picasso;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Activity_Update_Tenant extends AppCompatActivity implements OnButtonSelectedListener {
    private static final int PHONE_NUMBER_LENGTH = 10;
    private static final String TAG = "Activity_Update_Tenant";
    private static final int TAKE_NEW_IMAGE_REQUEST_CODE = 11;
    private static final int UPDATE_IMAGE_REQUEST_CODE = 10;
    public static final String UPDATE_TENANT = "UPDATE_TENANT";
    private String current_file_path;
    private Entity_TENANTS mentity_tenants;
    private Uri tenant_profile_picture_file_uri;
    private TextInputLayout update_first_name;
    private TextInputLayout update_last_name;
    private TextInputLayout update_phone_number;
    private TextInputLayout update_rent_per_month;
    /* access modifiers changed from: private */
    public ImageView update_tenant_dp_image_view;

    private static class getPickedImageAsyncTask extends AsyncTask<Uri, Void, Bitmap> {
        private WeakReference<Activity_Update_Tenant> weakReference;

        private getPickedImageAsyncTask(Activity_Update_Tenant activity_update_tenant) {
            this.weakReference = new WeakReference<>(activity_update_tenant);
        }

        /* access modifiers changed from: protected */
        public Bitmap doInBackground(Uri... uris) {
            try {
                return BitmapFactory.decodeStream(((Activity_Update_Tenant) this.weakReference.get()).getContentResolver().openInputStream(uris[0]));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Bitmap bitmap) {
            ((Activity_Update_Tenant) this.weakReference.get()).update_tenant_dp_image_view.setImageBitmap(bitmap);
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity__update__tenant);
        init();
    }

    private void init() {
        Toolbar update_tenant_toolbar = (Toolbar) findViewById(R.id.update_tenant_toolbar);
        this.update_first_name = (TextInputLayout) findViewById(R.id.update_first_name);
        this.update_last_name = (TextInputLayout) findViewById(R.id.update_last_name);
        this.update_phone_number = (TextInputLayout) findViewById(R.id.update_phone_number);
        this.update_rent_per_month = (TextInputLayout) findViewById(R.id.update_rent_per_month);
        this.update_tenant_dp_image_view = (ImageView) findViewById(R.id.update_tenant_dp_image_view);
        Button update_tenant_dp_button = (Button) findViewById(R.id.update_tenant_dp_button);
        setSupportActionBar(update_tenant_toolbar);
        setTitle(R.string.UPDATE_TENANT);
        this.mentity_tenants = (Entity_TENANTS) getIntent().getParcelableExtra(UPDATE_TENANT);
        if (this.mentity_tenants.getPROFILE_PICTURE_URI() != null) {
            this.tenant_profile_picture_file_uri = Uri.parse(this.mentity_tenants.getPROFILE_PICTURE_URI());
        }
        this.update_first_name.getEditText().setText(this.mentity_tenants.getTenantName().getFIRST_NAME());
        this.update_last_name.getEditText().setText(this.mentity_tenants.getTenantName().getLAST_NAME());
        EditText editText = this.update_phone_number.getEditText();
        StringBuilder sb = new StringBuilder();
        sb.append("0");
        sb.append(String.valueOf(this.mentity_tenants.getPHONE_NUMBER()));
        editText.setText(sb.toString());
        this.update_rent_per_month.getEditText().setText(String.valueOf(this.mentity_tenants.getRENT_PER_MONTH()));
        if (this.mentity_tenants.getPROFILE_PICTURE_URI() != null) {
            Picasso.get().load(Uri.parse(this.mentity_tenants.getPROFILE_PICTURE_URI())).fit().centerInside().into(this.update_tenant_dp_image_view);
        }
        update_tenant_dp_button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                new Dialog_Update_Tenant_Profile_Picture().show(Activity_Update_Tenant.this.getSupportFragmentManager(), Activity_Update_Tenant.TAG);
            }
        });
    }

    private boolean input_validation() {
        if (this.update_first_name.getEditText().getText().toString().trim().length() == 0) {
            this.update_first_name.setError(getString(R.string.field_cant_be_empty));
            return false;
        } else if (this.update_last_name.getEditText().getText().toString().trim().length() == 0) {
            this.update_last_name.setError(getString(R.string.field_cant_be_empty));
            return false;
        } else if (this.update_phone_number.getEditText().getText().toString().trim().length() != 10) {
            this.update_phone_number.setError(getString(R.string.invalid_phone_number));
            return false;
        } else if (this.update_rent_per_month.getEditText().getText().toString().trim().length() != 0) {
            return true;
        } else {
            this.update_rent_per_month.setError(getString(R.string.field_cant_be_empty));
            return false;
        }
    }

    private String generateFileName() {
        String current_time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append("JPEG_");
        sb.append(current_time);
        sb.append("_");
        return sb.toString();
    }

    private File createFile() {
        File tenant_profile_picture_file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), generateFileName());
        this.current_file_path = tenant_profile_picture_file.getAbsolutePath();
        return tenant_profile_picture_file;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save, menu);
        return true;
    }

    private void update_tenant() {
        Entity_TENANTS entity_tenants;
        if (input_validation() && this.mentity_tenants != null) {
            if (this.tenant_profile_picture_file_uri != null) {
                entity_tenants = new Entity_TENANTS(this.mentity_tenants.getHOUSE_NUMBER(), new TenantName(this.update_first_name.getEditText().getText().toString().trim(), this.update_last_name.getEditText().getText().toString().trim()), this.tenant_profile_picture_file_uri.toString(), Long.parseLong(this.update_phone_number.getEditText().getText().toString().trim()), Double.parseDouble(this.update_rent_per_month.getEditText().getText().toString().trim()));
            } else {
                entity_tenants = new Entity_TENANTS(this.mentity_tenants.getHOUSE_NUMBER(), new TenantName(this.update_first_name.getEditText().getText().toString().trim(), this.update_last_name.getEditText().getText().toString().trim()), null, Long.parseLong(this.update_phone_number.getEditText().getText().toString().trim()), Double.parseDouble(this.update_rent_per_month.getEditText().getText().toString().trim()));
            }
            entity_tenants.setTENANT_ID(this.mentity_tenants.getTENANT_ID());
            ((ViewModel_UPDATETENANT) ViewModelProviders.of((FragmentActivity) this).get(ViewModel_UPDATETENANT.class)).update_tenant(this, entity_tenants);
            Toast.makeText(this, R.string.Tenant_updated, 0).show();
            finish();
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save_button) {
            update_tenant();
            return true;
        }
        Toast.makeText(this, R.string.ERROR, 0).show();
        return super.onOptionsItemSelected(item);
    }

    public void onButtonSelected(int button_id) {
        if (button_id == R.id.remove_photo_button) {
            this.tenant_profile_picture_file_uri = null;
            this.update_tenant_dp_image_view.setImageResource(R.drawable.ic_person);
        } else if (button_id == R.id.take_updated_photo_button) {
            takeProfilePicture();
        } else if (button_id == R.id.update_existing_pic_button) {
            choosePicture();
        }
    }

    private void choosePicture() {
        Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, getString(R.string.choose_from)), 10);
    }

    private boolean isExternalStorageAvailable() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    private void takeProfilePicture() {
        if (getPackageManager().hasSystemFeature("android.hardware.camera.any")) {
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            if (intent.resolveActivity(getPackageManager()) != null && this.mentity_tenants != null) {
                if (isExternalStorageAvailable()) {
                    File profilePictureFile = createFile();
                    if (profilePictureFile != null) {
                        intent.putExtra("output", FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID, profilePictureFile));
                        intent.setFlags(2);
                        startActivityForResult(intent, 11);
                        return;
                    }
                    Toast.makeText(this, R.string.ERROR, 0).show();
                    return;
                }
                Toast.makeText(this, R.string.external_storage_unavailable, 0).show();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (10 == requestCode && -1 == resultCode && data != null && data.getData() != null) {
            this.tenant_profile_picture_file_uri = data.getData();
            new getPickedImageAsyncTask().execute(new Uri[]{data.getData()});
        }
        if (11 == requestCode && -1 == resultCode) {
            this.tenant_profile_picture_file_uri = Uri.fromFile(new File(this.current_file_path));
            this.update_tenant_dp_image_view.setImageURI(this.tenant_profile_picture_file_uri);
        }
    }
}
