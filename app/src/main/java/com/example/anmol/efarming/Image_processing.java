package com.example.anmol.efarming;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.ibm.watson.developer_cloud.android.library.camera.CameraHelper;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyImagesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ImageClassification;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassification;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassifier;

import java.io.File;

public class Image_processing extends AppCompatActivity {
    private VisualRecognition vrClient;
    private CameraHelper helper;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_processing);
        ImageView camera=(ImageView)findViewById(R.id.camera);
        ImageView gallery=(ImageView)findViewById(R.id.gallery);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                helper.dispatchTakePictureIntent();

            }
        });
gallery.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

    }
});

        vrClient = new VisualRecognition(
                VisualRecognition.VERSION_DATE_2016_05_20,"11f2a2e34cfe0f901600c29510f07de7f32527ca");
        //Initialize camera helper
        helper = new CameraHelper(Image_processing.this);

        context = getApplicationContext();


    }

    // below code will TAKE PICTURE
    //PASS IT TO THE WATSON
    //WATSON VISUALLY RECOGNIZE and GIVE OUTPUT DETAIL.
    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CameraHelper.REQUEST_IMAGE_CAPTURE) {
            final Bitmap photo = helper.getBitmap(resultCode);
            final File photoFile = helper.getFile(resultCode);

            ImageView preview = findViewById(R.id.preview);
            preview.setImageBitmap(photo);


            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    VisualClassification response =
                            vrClient.classify(
                                    new ClassifyImagesOptions.Builder()
                                            .images(photoFile)
                                            .build()
                            ).execute();

                    ImageClassification classification = response.getImages().get(0);

                    VisualClassifier classifier = classification.getClassifiers().get(0);

                    final StringBuffer output = new StringBuffer();
                    for (VisualClassifier.VisualClass object : classifier.getClasses()) {
                        if (object.getScore() > 0.4f)
                            output.append("<")
                                    .append(object.getName())
                                    .append("> ");
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TextView detectedObjects =
                                    findViewById(R.id.detail);
                            detectedObjects.setText(output);
                        }
                    });
                }
            });

        }
    }


}
