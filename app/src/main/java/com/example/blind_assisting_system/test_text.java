package com.example.blind_assisting_system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.document.FirebaseVisionDocumentText;
import com.google.firebase.ml.vision.document.FirebaseVisionDocumentTextRecognizer;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
//import com.google.firebase.ml.vision.text.FirebaseVisionTextDetector;
import com.google.firebase.ml.vision.text.RecognizedLanguage;

import java.util.List;

public class test_text extends AppCompatActivity {

    // creating variables for our
    // image view, text view and two buttons.
    static final int REQUEST_IMAGE_CAPTURE = 1;
    Bitmap imageBitmap;
    private ImageView img;
    private TextView textview;
    private Button snapBtn;
    private Button detectBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_text);
        // on below line we are initializing our variables.
        img = (ImageView) findViewById(R.id.image);
        textview = (TextView) findViewById(R.id.text);
        snapBtn = (Button) findViewById(R.id.snapbtn);
        detectBtn = (Button) findViewById(R.id.detectbtn);


        //Button To Capture Image
        snapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calling a method to capture our image.
                dispatchTakePictureIntent();

            }
        });

        // adding on click listener for detect button.
        detectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calling a method to
                // detect a text .
                detectTxt();
            }
        });


    }




    private void dispatchTakePictureIntent() {
        // in the method we are displaying an intent to capture our image.
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // on below line we are calling a start activity
        // for result method to get the image captured.
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // calling on activity result method.
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            // on below line we are getting
            // data from our bundles. .
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");

            // below line is to set the
            // image bitmap to our image.
//            img.setImageBitmap(imageBitmap);
            img.setImageBitmap(imageBitmap);
        }
    }

    private void detectTxt() {
        // this is a method to detect a text from image.
        // below line is to create variable for firebase
        // vision image and we are getting image bitmap.

        FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(imageBitmap);


                // below line is to create a variable for detector and we
                // are getting vision text detector from our firebase vision.




        FirebaseVisionDocumentTextRecognizer detector = FirebaseVision.getInstance()
                .getCloudDocumentTextRecognizer();

        detector.processImage(image)
                .addOnSuccessListener(new OnSuccessListener<FirebaseVisionDocumentText>() {
                    @Override
                    public void onSuccess(FirebaseVisionDocumentText result) {
                        // Task completed successfully
                        processTxt(result);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Task failed with an exception
                        Toast.makeText(test_text.this, "Fail to detect the text from image..", Toast.LENGTH_SHORT).show();
                    }
                });
//
                // adding on success listener method to detect the text from image.

    }

    private void processTxt(FirebaseVisionDocumentText result) {
        // below line is to create a list of vision blocks which
        // we will get from our firebase vision text.
        List<FirebaseVisionDocumentText.Block> blocks = result.getBlocks();

        // checking if the size of the
        // block is not equal to zero.
        if (blocks.size() == 0) {
            // if the size of blocks is zero then we are displaying
            // a toast message as no text detected.
            Toast.makeText(test_text.this, "No Text ", Toast.LENGTH_LONG).show();
            return;
        }
        // extracting data from each block using a for loop.
        String resultText = result.getText();
        for (FirebaseVisionDocumentText.Block block: result.getBlocks()) {
            String blockText = block.getText();
            Float blockConfidence = block.getConfidence();
            List<RecognizedLanguage> blockRecognizedLanguages = block.getRecognizedLanguages();
            Rect blockFrame = block.getBoundingBox();
            for (FirebaseVisionDocumentText.Paragraph paragraph: block.getParagraphs()) {
                String paragraphText = paragraph.getText();
                Float paragraphConfidence = paragraph.getConfidence();
                List<RecognizedLanguage> paragraphRecognizedLanguages = paragraph.getRecognizedLanguages();
                Rect paragraphFrame = paragraph.getBoundingBox();
                for (FirebaseVisionDocumentText.Word word: paragraph.getWords()) {
                    String wordText = word.getText();
                    Float wordConfidence = word.getConfidence();
                    List<RecognizedLanguage> wordRecognizedLanguages = word.getRecognizedLanguages();
                    Rect wordFrame = word.getBoundingBox();
                    for (FirebaseVisionDocumentText.Symbol symbol: word.getSymbols()) {
                        String symbolText = symbol.getText();
                        Float symbolConfidence = symbol.getConfidence();
                        List<RecognizedLanguage> symbolRecognizedLanguages = symbol.getRecognizedLanguages();
                        Rect symbolFrame = symbol.getBoundingBox();
                    }
                }
            }
        }
        Toast.makeText(test_text.this, resultText, Toast.LENGTH_SHORT).show();

    }
}

