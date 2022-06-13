package com.accidentaldeveloper.yourprofile;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.accidentaldeveloper.yourprofile.databinding.ActivityMain2Binding;
import java.io.File;
import java.io.FileOutputStream;

public class MainActivity2 extends AppCompatActivity {
    ActivityMain2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //Working with share button
        binding.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Share the image
                Bitmap image = getBitmapFromView(binding.tushar);
                shareImageAndText(image);

            }
        });
    }

    private void shareImageAndText(Bitmap image) {
        Uri uri = getImageToShare(image);

        Intent intent = new Intent(Intent.ACTION_SEND);

        //putting the uri of image to be shared
        intent.putExtra(Intent.EXTRA_STREAM,uri);
        //Adding the message of my profile
        intent.putExtra(Intent.EXTRA_SUBJECT,"MY PROFILE!!");
        //Setting type of image
        intent.setType("image/png");

        //calling StartACtivity to share
        startActivity(Intent.createChooser(intent,"Share Image Via"));
    }

    private Uri getImageToShare(Bitmap image) {
        File imageFolder = new File(getCacheDir(),"images");
        Uri uri = null;
        try{
            imageFolder.mkdirs();
            File file = new File(imageFolder,"shared_image.png");
            FileOutputStream outputStream = new FileOutputStream(file);
            image.compress(Bitmap.CompressFormat.PNG,100,outputStream);
            outputStream.flush();
            outputStream.close();
            uri= FileProvider.getUriForFile(this,"com.accidentaldeveloper.shareImage.fileProvider",file);

        }catch(Exception e){
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        }
        return uri;
    }

    private Bitmap getBitmapFromView(View view) {
        //define a bitmap with same hieght and width
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(),view.getHeight(),Bitmap.Config.ARGB_8888);
        //Bind a Canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //get the background view of layout
        Drawable background = view.getBackground();
        if(background!= null){
            background.draw(canvas);
        }else{
            canvas.drawColor(Color.BLACK);
        }
        //drawing the view on canvas
        view.draw(canvas);
        return returnedBitmap;

    }

    public void jump2(View view) {
        Intent jump2 = new Intent(MainActivity2.this,MainActivity3.class);
        startActivity(jump2);
    }
}
