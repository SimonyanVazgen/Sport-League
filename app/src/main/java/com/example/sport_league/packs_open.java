package com.example.sport_league;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class packs_open extends AppCompatActivity {
    private String[] imagePaths = {
            "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/apedipele.png?alt=media&token=cba783b8-24bb-4aed-9023-d77e0cae0a00",
            "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/DGunOgtXYAIg4oH.png?alt=media&token=aa4c7db5-72a6-403e-bfe0-5a65a4631696",
            "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/DHHdOk7XUAEDnD2.png?alt=media&token=2bcc3b4d-e3f4-49d2-af27-a46dfbe3e9bf",
            "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/DHHdfeWWAAE5I1J.png?alt=media&token=283bca78-3497-47ff-b48c-ed2b02661d01",
            "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/Rodrigo.png?alt=media&token=312705ab-01b0-49f1-bff3-8d2cd7eb3459",
            "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/cordoba.png?alt=media&token=5f77fa5e-8ded-4405-b4bc-54b6a997bafe",
            "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/donarumma.png?alt=media&token=603b0df6-5884-42cd-9bef-e42e1c2d4553",
            "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/gomez.png?alt=media&token=a8d31bfa-248d-464c-b62d-1bf6b4ba6fbf",
            "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/haland.png?alt=media&token=db841016-0a48-4856-a753-f69b87b22ead",
            "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/pngfind.com-kobe-bryant-png-328894.png?alt=media&token=80eadee8-a517-4bec-a71c-90cb4df04e4b",
            "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/pngfind.com-nba-2k16-png-2754401.png?alt=media&token=2c82ff16-3ded-43ea-9ea9-b02758d9229f",
            "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/DGunNJZXkAAj1sq.png?alt=media&token=b310592c-02d8-4a57-9cbb-80ebc433dee8",
            "https://firebasestorage.googleapis.com/v0/b/loginapp-3ec87.appspot.com/o/pngfind.com-nba-2k17-png-2617263.png?alt=media&token=40576ae0-8c3d-4eff-a3a0-883a485376ea"
    };

    private Set<Integer> displayedImageIndices = new HashSet<>();
    private FirebaseStorage storage = FirebaseStorage.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packs_open);

        ImageView imageView1 = findViewById(R.id.randomImageView1);
        ImageView imageView2 = findViewById(R.id.randomImageView2);
        ImageView imageView3 = findViewById(R.id.randomImageView3);
        ImageView imageView4 = findViewById(R.id.randomImageView4);

        Set<Integer> indices = getRandomIndices(imagePaths.length, 4);  // Get 4 unique random indices
        int i = 0;
        for (int index : indices) {
            switch (i) {
                case 0:
                    displayImage(imageView1, index);
                    break;
                case 1:
                    displayImage(imageView2, index);
                    break;
                case 2:
                    displayImage(imageView3, index);
                    break;
                case 3:
                    displayImage(imageView4, index);
                    break;
            }
            i++;
        }
    }

    private Set<Integer> getRandomIndices(int max, int count) {
        Random random = new Random();
        while (displayedImageIndices.size() < count) {
            displayedImageIndices.add(random.nextInt(max));
        }
        return new HashSet<>(displayedImageIndices);
    }

    private void displayImage(ImageView imageView, int index) {
        StorageReference imageRef = storage.getReferenceFromUrl(imagePaths[index]);
        Glide.with(this)
                .load(imageRef)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        Log.e("GlideError", "Load failed for image: " + imagePaths[index], e);
                        Toast.makeText(packs_open.this, "Load failed for image: " + imagePaths[index], Toast.LENGTH_SHORT).show();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        Log.i("GlideSuccess", "Image loaded successfully: " + imagePaths[index]);
                        return false;
                    }
                })
                .into(imageView);
    }
}