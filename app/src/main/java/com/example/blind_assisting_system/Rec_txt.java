package com.example.blind_assisting_system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.provider.MediaStore;
import android.speech.tts.Voice;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.document.FirebaseVisionCloudDocumentRecognizerOptions;
import com.google.firebase.ml.vision.document.FirebaseVisionDocumentText;
import com.google.firebase.ml.vision.document.FirebaseVisionDocumentTextRecognizer;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.RecognizedLanguage;

import java.util.Arrays;
import java.util.List;

public class Rec_txt extends AppCompatActivity {
    final int REQUEST_IMAGE_CAPTURE = 1;
    FirebaseVisionTextRecognizer textRecognizer;
    Bitmap imageBitmap;
    private ImageView img;
    private TextView textview;
    private Button snapBtn;
    private Button detectBtn;
    String txt = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rec_txt);

        // on below line we are initializing our variables.
        img = (ImageView) findViewById(R.id.image);
        textview = (TextView) findViewById(R.id.textview);
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
//            FirebaseVisionDocumentTextRecognizer detector = FirebaseVision.getInstance()
//                    .getCloudDocumentTextRecognizer();

            textRecognizer = FirebaseVision.getInstance()
                    .getOnDeviceTextRecognizer();


            textRecognizer.processImage(image)
                    .addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
                        @Override
                        public void onSuccess(FirebaseVisionText result) {

//                            Voice.speak(activity, result.getText(), false);
                            // TextTranslation.translation(result.getText());
                            Log.d("IN OCR",result.getText());
                            textview.setText(result.getText());
                        }
                    })
                    .addOnFailureListener(
                            new OnFailureListener() {
                                @Override
                                public void onFailure(Exception e) {
                                    Toast.makeText(Rec_txt.this, "Fail to detect the text from image..", Toast.LENGTH_SHORT).show();

//                                  Voice.speak(activity, "no Text found", false);
                                }
                            });


//            textRecognizer.processImage(image)
//                    .addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
//                        @Override
//                        public void onSuccess(FirebaseVisionText result) {
////                            processTxt(result);
//                            int d = Log.d(result.getText());
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            // Task failed with an exception
//                            // ...
//                            Toast.makeText(Rec_txt.this, "Fail to detect the text from image..", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//




//            detector.processImage(image)
//                    .addOnSuccessListener(new OnSuccessListener<FirebaseVisionDocumentText>() {
//                        @Override
//                        public void onSuccess(FirebaseVisionDocumentText result) {
//                            // Task completed successfully
//                            // ...
//                            processTxt(result);
////                            Toast.makeText(Rec_txt.this, "detect the text from image..", Toast.LENGTH_SHORT).show();
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            // Task failed with an exception
//                            // ...
//                            Toast.makeText(Rec_txt.this, "Fail to detect the text from image..", Toast.LENGTH_SHORT).show();
//                        }
//                    });


        }

//    private void processTxt(FirebaseVisionText result) {
//
//        List<FirebaseVisionDocumentText.Block> blocks = result.getBlocks();
//
//        // checking if the size of the
//        // block is not equal to zero.
//        if (blocks.size() == 0) {
//            // if the size of blocks is zero then we are displaying
//            // a toast message as no text detected.
//            Toast.makeText(Rec_txt.this, "No Text ", Toast.LENGTH_LONG).show();
//            return;
//        }
//        // extracting data from each block using a for loop.
////        for (FirebaseVisionDocumentText.Block block : result.getBlocks()) {
////            // below line is to get text
////            // from each block.
////            String txt = block.getText();
////            textview.setText(txt);
////        }
//        String resultText = result.getText();
//        for (FirebaseVisionDocumentText.Block block: result.getBlocks()) {
//            String blockText = block.getText();
//            Float blockConfidence = block.getConfidence();
//            List<RecognizedLanguage> blockRecognizedLanguages = block.getRecognizedLanguages();
//            Rect blockFrame = block.getBoundingBox();
//            for (FirebaseVisionDocumentText.Paragraph paragraph: block.getParagraphs()) {
//                String paragraphText = paragraph.getText();
//                Float paragraphConfidence = paragraph.getConfidence();
//                List<RecognizedLanguage> paragraphRecognizedLanguages = paragraph.getRecognizedLanguages();
//                Rect paragraphFrame = paragraph.getBoundingBox();
//                for (FirebaseVisionDocumentText.Word word: paragraph.getWords()) {
//                    String wordText = word.getText();
//                    Float wordConfidence = word.getConfidence();
//                    List<RecognizedLanguage> wordRecognizedLanguages = word.getRecognizedLanguages();
//                    Rect wordFrame = word.getBoundingBox();
//                    for (FirebaseVisionDocumentText.Symbol symbol: word.getSymbols()) {
//                        String symbolText = symbol.getText();
//                        Float symbolConfidence = symbol.getConfidence();
//                        List<RecognizedLanguage> symbolRecognizedLanguages = symbol.getRecognizedLanguages();
//                        Rect symbolFrame = symbol.getBoundingBox();
//
//                    }
//                }
//
//            }
//        }
//        Toast.makeText(Rec_txt.this,resultText , Toast.LENGTH_LONG).show();
//        textview.setText(resultText);
//
//    }
}

