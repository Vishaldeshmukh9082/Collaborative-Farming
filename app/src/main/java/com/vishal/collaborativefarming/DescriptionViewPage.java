package com.vishal.collaborativefarming;

import static java.nio.file.Files.createFile;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.pdf.PdfDocument;
import android.graphics.pdf.PdfRenderer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.vishal.collaborativefarming.database.DBHelper;
import com.vishal.collaborativefarming.database.DbHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Documented;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DescriptionViewPage extends AppCompatActivity {
    String surveyno, village, taluka, district, feildarea, description, image, landownerid;
    Connection conn;
    ImageView imageView;
    Button button;
    int pdfHeight =1080;
    int pdfWidth=720;
    private PdfDocument document;
    TextView surveynotxt, villagetxt, talukatxt, districttxt, feildareatxt, descriptiontxt, ownernametxt, ownercontacttxt, owneraddresstxt;

    private static final int CREATE_FILE=1;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_description_view_page);

        surveynotxt = findViewById(R.id.surveyno);
        villagetxt = findViewById(R.id.village);
        talukatxt = findViewById(R.id.taluka);
        districttxt = findViewById(R.id.district);
        feildareatxt = findViewById(R.id.feildarea);
        descriptiontxt = findViewById(R.id.description);
        ownernametxt = findViewById(R.id.ownername);
        ownercontacttxt = findViewById(R.id.ownercontact);
        owneraddresstxt = findViewById(R.id.owneraddress);
        imageView = findViewById(R.id.imageView11);
        imageView.setImageResource(R.drawable.agro_image);
        button = findViewById(R.id.button4);

        getAndSetIntentData();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                generatePdf();

            }
        });


    }
//    private void generatePdfFromView(View view){
//        Bitmap bitmap=getBitmapFromView(view);
//        document=new PdfDocument();
//        PdfDocument.PageInfo mypageinfo=new PdfDocument.PageInfo.Builder(bitmap.getWidth(), bitmap.getHeight());
//        PdfDocument.Page myPage=document.startPage(mypageinfo);
//        Canvas canvas=myPage.getCanvas();
//        canvas.drawBitmap(bitmap,0,0,null);
//        document.finishPage(myPage);
//        CreatFile();
//    }



//    private Bitmap getBitmapFromView (View view) {
//        Bitmap returnedBitmap=Bitmap.createBitmap(view.getWidth(),view.getHeight(),Bitmap.Config.ARGB_8888);
//
//        Canvas canvas=new Canvas(returnedBitmap);
//        Drawable drawable=view.getBackground();
//        if(drawable!=null){
//            drawable.draw(canvas);
//        }else{
//
//            canvas.drawColor(Color.WHITE);
//        }
//        view.draw(canvas);
//        return returnedBitmap;
//    }

    void generatePdf(){
        document= new PdfDocument();

        PdfDocument.PageInfo pageInfo=new PdfDocument.PageInfo.Builder(pdfWidth,pdfHeight,1).create();
        PdfDocument.Page page=document.startPage(pageInfo);

        Canvas canvas = page.getCanvas();
        Paint painttext = new Paint();
        painttext.setColor(ContextCompat.getColor(this,R.color.black));
        painttext.setTextSize(25);
        painttext.setTypeface(Typeface.create(Typeface.DEFAULT_BOLD,Typeface.NORMAL));
        painttext.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(("Land Rent Aggrement"),396,50,painttext);

        canvas.drawText(((TextView)findViewById(R.id.surveyno)).getText().toString(),100,100,painttext);
        document.finishPage(page);
        createFile();

    }

    private void createFile () {
        Intent intent=new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("application/pdf");
        intent.putExtra(Intent.EXTRA_TITLE,"landnumber.pdf");
        startActivityForResult(intent,CREATE_FILE);
    }

    public void onActivityResult(int requestCode,int resultCode,Intent resultData) {

        super.onActivityResult(requestCode, resultCode, resultData);
        if(requestCode==CREATE_FILE && resultCode== Activity.RESULT_OK){
            Uri uri=null;
            if(resultData!=null){
                uri =resultData.getData();
                if(document!=null){
                    ParcelFileDescriptor pdf=null;
                    try{
                        pdf=getContentResolver().openFileDescriptor(uri,"w");
                        FileOutputStream fileOutputStream=new FileOutputStream(pdf.getFileDescriptor());
                        document.writeTo(fileOutputStream);
                        document.close();
                        Toast.makeText(this, "Pdf Saved Successfully", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        try{
                            DocumentsContract.deleteDocument(getContentResolver(), uri);

                        }catch (FileNotFoundException ex){
                            ex.printStackTrace();;
                        }
                        e.printStackTrace();
                    }
                }
            }
        }
    }
//    private void CreatePDf (View view ) {
//        final Dialog feilddocument=new Dialog(this);
//        feilddocument.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        feilddocument.setContentView(R.layout.activity_description_view_page);
//        feilddocument.setCancelable(true);
//        WindowManager.LayoutParams lp=new WindowManager.LayoutParams();
//        lp.copyFrom(feilddocument.getWindow().getAttributes());
//
//
//
//        PdfDocument pdfDocument = new PdfDocument();
//
//
//        // Create a page description
//        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(300, 600, 1).create();
//
//        // Start a page
//        PdfDocument.Page page = pdfDocument.startPage(pageInfo);
//
//        Canvas canvas = page.getCanvas();
//        Paint paint = new Paint();
//        paint.setColor(android.graphics.Color.BLACK);
//        paint.setTextSize(8);
//
//        // Draw text on the canvas
//        canvas.drawText("Lessor:\n" +
//                "[Government Department/Agency Name]\n" +
//                "Address: [Government Department/Agency Address]\n" +
//                "Contact: [Contact Person Name, Title, and Contact Information]Lessee:\n" +
//                "[Lessee's Full Name]\n" +
//                "Address: [Lessee's Address]\n" +
//                "Contact: [Lessee's Contact Information]", 10, 50, paint);
//
//
//        // Finish the page
//        pdfDocument.finishPage(page);
//
//        // Save the document to a file
//        String directoryPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getPath() + "/";
//        File file = new File(directoryPath, "sample.pdf");
//
//        try {
//            pdfDocument.writeTo(new FileOutputStream(file));
//
//            pdfDocument.close();
//            Toast.makeText(DescriptionViewPage.this, "Please Check your Credentials!!", Toast.LENGTH_SHORT).show();
//
//            // Notify the user about the success
//            // You can use a Toast or any other method to notify the user
//        } catch (IOException e) {
//            e.printStackTrace();
//            // Notify the user about the error
//        }
//
//
//    }


    void getAndSetIntentData () {
        if (getIntent().hasExtra("surveyno") && getIntent().hasExtra("village") &&
                getIntent().hasExtra("taluka") && getIntent().hasExtra("district") && getIntent().hasExtra("feildarea") &&
                getIntent().hasExtra("description") && getIntent().hasExtra("image")) {
            //Getting Data from Intent
            surveyno = getIntent().getStringExtra("surveyno");
            village = getIntent().getStringExtra("village");
            taluka = getIntent().getStringExtra("taluka");
            district = getIntent().getStringExtra("district");
            feildarea = getIntent().getStringExtra("feildarea");
            description = getIntent().getStringExtra("description");
            landownerid = getIntent().getStringExtra("landowner");
            //image = getIntent().getStringExtra("image");

            //Setting Intent Data
            surveynotxt.setText(surveynotxt.getText() + surveyno);
            villagetxt.setText(villagetxt.getText() + village);
            talukatxt.setText(talukatxt.getText() + taluka);
            districttxt.setText(districttxt.getText() + district);
            feildareatxt.setText(feildareatxt.getText() + feildarea);
            descriptiontxt.setText(descriptiontxt.getText() + description);

            //Log.d("stev", title+" "+author+" "+pages);
            DBHelper db = new DBHelper();
            conn = db.connectionclass();
            try {
                if (conn != null) {
                    String query = "select * from user where uid='" + landownerid + "';";
                    PreparedStatement statement = conn.prepareStatement(query);
                    ResultSet rs = statement.executeQuery();
                    while (rs.next()) {
                        ownernametxt.setText(ownernametxt.getText() + rs.getString(3));
                        ownercontacttxt.setText(ownercontacttxt.getText() + rs.getString(4));
                        owneraddresstxt.setText(owneraddresstxt.getText() + rs.getString(6));

                    }
                }
            } catch (Exception ex) {
                Log.d("connection", ex.toString());
            }
        } else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

}