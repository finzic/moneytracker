package it.finzicontini.digiledger;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private final int REQ_CODE_SPEECH_INPUT = 100;
    private RecyclerView ledgerListRecyclerView;
    private LedgerDataListAdapter ledgerDataListAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<LedgerData> ledgerDataList;
    List<String> testData = new ArrayList<>();

//    private TextView voiceInput;
//    private TextView speakButton;
//    private TextView txtCategory;
//    private TextView txtAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupTestData();
        ledgerListRecyclerView = (RecyclerView) findViewById(R.id.rclExpensesView);
        ledgerListRecyclerView.setHasFixedSize(false);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        ledgerListRecyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        // get the data for the table...
        ledgerDataList = new ArrayList<LedgerData>() ;
        // adding data at random...
        for (int j = 0; j < 3; j++){
            int i = new Random().nextInt(testData.size());
            LedgerData newRow = parseLedgerData(testData.get(i));
            this.ledgerDataList.add(newRow);
        }
        ledgerDataListAdapter = new LedgerDataListAdapter(ledgerDataList);
        ledgerListRecyclerView.setAdapter(ledgerDataListAdapter);

    }

    private void setupTestData(){
        testData.add("Benzina 10.24");
        testData.add("Cibo 34.76");
        testData.add("Bancomat 400.00");
        testData.add("Benzina 50.00");
        testData.add("Bancomat 200.00");
        testData.add("Cibo 127.75");
        testData.add("Tasse 300.00");

    }

    public void onAddNewExpense(View view){
        addRandomTestData();
        //askSpeechInput();
    }

    private void addRandomTestData(){
        int i = new Random().nextInt(testData.size());
        LedgerData newRow = parseLedgerData(testData.get(i));
        this.ledgerDataList.add(newRow);
        logLedgerData();
        runOnUiThread(new Runnable(){
            @Override
            public void run() {
                ledgerDataListAdapter.notifyDataSetChanged();
            }
        });
    }

    private void logLedgerData(){
        Iterator<LedgerData> i = this.ledgerDataList.listIterator();
        while (i.hasNext()){
            LedgerData ld = i.next();
            System.out.println(" i = : " + i + ": "+ ld.getDate() + " -> " + "Amount = " + ld.getAmount() + " ; category = " + ld.getCategory());
        }
    }

    // Showing google speech input dialog
    private void askSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Please say category and amount");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {

        }
    }

    // Receiving speech input

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    CharSequence c = result.get(0);
                    String dataString = c.toString();

                    LedgerData newRow = parseLedgerData(dataString);
                    this.ledgerDataList.add(newRow);
                    //ledgerDataListAdapter.notifyItemInserted(this.ledgerDataList.size() - 1);
                    ledgerDataListAdapter.notifyDataSetChanged();
                }
                break;
            }

        }
    }

    private LedgerData parseLedgerData(String dataString) {
        String[] split = dataString.split(" ");
        String category = split[0];
        String amountString = split[1];
        Float amount = Float.parseFloat(amountString);
        Date today = GregorianCalendar.getInstance().getTime();
        System.out.println(" Parsing from voice: " + today + " -> " + "Amount = " + amount + " ; category = " + category);

        return new LedgerData(today, amount, category);
    }
}
