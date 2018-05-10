package com.yh.autodeeperlabel;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.yh.autodeep.LabelAdder;
import com.yh.autodeep.LabelInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    
    private LinearLayout mLabelParent;
    
    private final String mData = "[\n" + "    {\n" + "        \"name\": \"C\", \n" + "        \"color\": \"90A4AE\"\n" + "    }, \n" + "    {\n" + "        \"name\": \"C++\", \n" + "        \"color\": \"1976D2\"\n" + "    }, \n" + "    {\n" + "        \"name\": \"C#\", \n" + "        \"color\": \"4CAF50\"\n" + "    }, \n" + "    {\n" + "        \"name\": \"JAVA\", \n" + "        \"color\": \"EF5350\"\n" + "    }, \n" + "    {\n" + "        \"name\": \"PHP\", \n" + "        \"color\": \"AB47BC\"\n" + "    }, \n" + "    {\n" + "        \"name\": \"Python\", \n" + "        \"color\": \"F57C00\"\n" + "    }\n" + "]";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mLabelParent = findViewById(R.id.label_parent);
        
        ArrayList<LabelInfo> labels = getLabelData();
        
        LabelAdder.additionalLabel(this, mLabelParent, labels);
    }
    
    @NonNull
    private ArrayList<LabelInfo> getLabelData() {
        ArrayList<LabelInfo> labels = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(mData);
            for (int j = 0; j < 100; j++) {
                for (int i = 0; i < array.length(); i++) {
                    JSONObject obj = array.optJSONObject(i);
                    LabelInfo label = new LabelInfo();
                    label.name = obj.optString("name");
                    label.color = obj.optString("color");
                    labels.add(label);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return labels;
    }
}
