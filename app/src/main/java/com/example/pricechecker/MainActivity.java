package com.example.pricechecker;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.pricechecker.databinding.ActivityMainBinding;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    private String debugBarcode = "7290000927044";
    private Map<String, Double> haziHinam = new HashMap<>();
    private Map<String, Double> shufersal = new HashMap<>();
    private Map<String, Double> ramiLevi = new HashMap<>();

    private Map<String, Double> keshet = new HashMap<>();
    private Map<String, Double> superpharm = new HashMap<>();
    private Map<String, Double> yohananof = new HashMap<>();



    private Button btnScan;
    private Button btnExit;
    private Button btnInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnScan = findViewById(R.id.btn_scan);
        btnInfo = findViewById(R.id.btn_info);
        btnExit = findViewById(R.id.btn_exit);

        fillMaps();

        btnScan.setOnClickListener(v->
        {
            // scanCode();
            debugScanCode();
        });

        btnExit.setOnClickListener(v->{
            finish();
        });

        btnInfo.setOnClickListener(v->{
            showInfo();
        });
    }

    private void fillMaps() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder ramiLeviBuilder = factory.newDocumentBuilder();
            DocumentBuilder shufersalBuilder = factory.newDocumentBuilder();
            DocumentBuilder hazihinamBuilder = factory.newDocumentBuilder();
            DocumentBuilder superpharmBuilder = factory.newDocumentBuilder();
            DocumentBuilder keshetBuilder = factory.newDocumentBuilder();
            DocumentBuilder yohananofBuilder = factory.newDocumentBuilder();

            Document ramiLeviDoc = ramiLeviBuilder.parse(getResources().openRawResource(R.raw.ramilevi));
            NodeList ramiLeviItems = ramiLeviDoc.getElementsByTagName("Item");

            Document shufersalDoc = ramiLeviBuilder.parse(getResources().openRawResource(R.raw.shufersal));
            NodeList shufersalItems = shufersalDoc.getElementsByTagName("Item");

            Document haziHinamDoc = ramiLeviBuilder.parse(getResources().openRawResource(R.raw.hazihinam));
            NodeList haziHinamItems = haziHinamDoc.getElementsByTagName("Item");

//            Document superpharmDoc = superpharmBuilder.parse(getResources().openRawResource(R.raw.superpharm));
//            NodeList superpharmItems = superpharmDoc.getElementsByTagName("Item");
//
//            Document keshetDoc = keshetBuilder.parse(getResources().openRawResource(R.raw.keshet));
//            NodeList keshetItems = keshetDoc.getElementsByTagName("Item");
//
//            Document yohananofDoc = yohananofBuilder.parse(getResources().openRawResource(R.raw.yohananof));
//            NodeList yohananofItems = yohananofDoc.getElementsByTagName("Item");

            fillSingleMap("Rami Levi", ramiLeviItems);
            fillSingleMap("Shufersal", shufersalItems);
            fillSingleMap("Hazi Hinam", haziHinamItems);
//            fillSingleMap("Super Pharm", superpharmItems);
//            fillSingleMap("Keshet", keshetItems);
//            fillSingleMap("Yohananof", yohananofItems);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fillSingleMap(String storeName, NodeList items){
        switch(storeName){
            case "Hazi Hinam":{
                int len = items.getLength();
                for (int i = 0; i < len; i++) {
                    Element item = (Element) items.item(i);
                    String itemCode = item.getElementsByTagName("ItemCode").item(0).getTextContent();
                    try{
                        double itemPrice = Double.parseDouble(item.getElementsByTagName("ItemPrice").item(0).getTextContent());
                        haziHinam.put(itemCode, itemPrice);
                    }
                    catch(Exception e2){
                        continue;
                    }
                }
                break;
            }
            case "Shufersal":{
                int len = items.getLength();
                for (int i = 0; i < len; i++) {
                    Element item = (Element) items.item(i);
                    String itemCode = item.getElementsByTagName("ItemCode").item(0).getTextContent();
                    try{
                        double itemPrice = Double.parseDouble(item.getElementsByTagName("ItemPrice").item(0).getTextContent());
                        shufersal.put(itemCode, itemPrice);
                    }
                    catch(Exception e2){
                        continue;
                    }
                }
                break;
            }
            case "Rami Levi":{
                int len = items.getLength();
                for (int i = 0; i < len; i++) {
                    Element item = (Element) items.item(i);
                    String itemCode = item.getElementsByTagName("ItemCode").item(0).getTextContent();
                    try{
                        double itemPrice = Double.parseDouble(item.getElementsByTagName("ItemPrice").item(0).getTextContent());
                        ramiLevi.put(itemCode, itemPrice);
                    }
                    catch(Exception e2){
                        continue;
                    }
                }
                break;
            }
            case "Super Pharm":{
                int len = items.getLength();
                for (int i = 0; i < len; i++) {
                    Element item = (Element) items.item(i);
                    String itemCode = item.getElementsByTagName("ItemCode").item(0).getTextContent();
                    try{
                        double itemPrice = Double.parseDouble(item.getElementsByTagName("ItemPrice").item(0).getTextContent());
                        superpharm.put(itemCode, itemPrice);
                    }
                    catch(Exception e2){
                        continue;
                    }
                }
                break;
            }
            case "Keshet":{
                int len = items.getLength();
                for (int i = 0; i < len; i++) {
                    Element item = (Element) items.item(i);
                    String itemCode = item.getElementsByTagName("ItemCode").item(0).getTextContent();
                    try{
                        double itemPrice = Double.parseDouble(item.getElementsByTagName("ItemPrice").item(0).getTextContent());
                        keshet.put(itemCode, itemPrice);
                    }
                    catch(Exception e2){
                        continue;
                    }
                }
                break;
            }
            case "Yohananof":{
                int len = items.getLength();
                for (int i = 0; i < len; i++) {
                    Element item = (Element) items.item(i);
                    String itemCode = item.getElementsByTagName("ItemCode").item(0).getTextContent();
                    try{
                        double itemPrice = Double.parseDouble(item.getElementsByTagName("ItemPrice").item(0).getTextContent());
                        yohananof.put(itemCode, itemPrice);
                    }
                    catch(Exception e2){
                        continue;
                    }
                }
                break;
            }
        }

    }

    private void showInfo(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Result");
        String text = "Imagine walking into a store and looking at a new product. \n" +
                "You see that the product is labeled with some price. \n" +
                "You have no idea what is the average price for that product. Maybe it's too high? Maybe it's very low? \n" +
                "Just simply scan the barcode and we'll check in the most popular shops around you. \n";
        builder.setMessage(text);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).show();
    }

    private void scanCode(){
        ScanOptions options = new ScanOptions();
        options.setPrompt("Volume up to flash on");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLauncher.launch(options);
    }

    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(), result -> {
       if(result.getContents() != null){
           AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
           builder.setTitle("Result");
           builder.setMessage(getResultStringFromBarcode(result.getContents()));
           builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
               }
           }).show();
       }
    });

    private void debugScanCode(){
        Set<String> commonKeys = new HashSet<>(haziHinam.keySet());
        commonKeys.retainAll(shufersal.keySet());
        commonKeys.retainAll(ramiLevi.keySet());


        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Result");
        builder.setMessage(getResultStringFromBarcode(debugBarcode));
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).show();
    }

    private String getResultStringFromBarcode(String code){
        String answer = "";

        String haziHizamPrice = getPriceFromStore("Hazi Hinam", code);
        String shufersalPrice = getPriceFromStore("Shufersal", code);
        String ramiLeviPrice = getPriceFromStore("Rami Levi", code);
        String superpharmPrice = getPriceFromStore("Super Pharm", code);
        String keshetPrice = getPriceFromStore("Keshet", code);
        String yohananofPrice = getPriceFromStore("Yohananof", code);

        answer += "Hazi Hinam: " + haziHizamPrice + " NIS. \n";
        answer += "Shufersal: " + shufersalPrice + " NIS. \n";
        answer += "Rami Levi: " + ramiLeviPrice + " NIS. \n";
        answer += "Super Pharm: " + superpharmPrice + " NIS. \n";
        answer += "Keshet: " + keshetPrice + " NIS. \n";
        answer += "Yohananof: " + yohananofPrice + " NIS. \n";

        return answer;
    }

    private String getPriceFromStore(String storeName, String itemCode){
        String answer = "";
        switch(storeName){
            case "Hazi Hinam":{
                try{
                    answer = haziHinam.get(itemCode).toString();
                } catch(Exception e){
                    answer = "";
                }
                break;
            }
            case "Shufersal":{
                try{
                    answer = shufersal.get(itemCode).toString();
                } catch(Exception e){
                    answer = "";
                }
                break;
            }
            case "Rami Levi":{
                try{
                    answer = ramiLevi.get(itemCode).toString();
                }catch(Exception e){
                    answer = "";
                }

                break;
            }
            case "Super Pharm":{
                try{
                    answer = superpharm.get(itemCode).toString();
                }catch(Exception e){
                    answer = "";
                }

                break;
            }
            case "Keshet":{
                try{
                    answer = keshet.get(itemCode).toString();
                }catch(Exception e){
                    answer = "";
                }

                break;
            }
            case "Yohananof":{
                try{
                    answer = yohananof.get(itemCode).toString();
                }catch(Exception e){
                    answer = "";
                }

                break;
            }
        }

        return answer.equals("") ? "NOT FOUND" : answer;
    }
}