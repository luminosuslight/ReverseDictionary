package org.luminosuslight.reversedictionary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
	
	ArrayListIgnoreCase word_list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		try {
			word_list = getWordList("reverseDict.txt");
		} catch (IOException e) {
			e.printStackTrace();
			word_list = new ArrayListIgnoreCase();
			for (int i = 0; i < 40; i++)
			{
				word_list.add("value"+i);
			}
		}
		ListAdapter adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.dictionary_item, word_list);
		final ListView lv = (ListView)findViewById(R.id.listView1);
		lv.setAdapter(adapter);
		
		final EditText searchBox = (EditText)findViewById(R.id.searchBox);
		
		searchBox.addTextChangedListener(new TextWatcher(){
	        public void afterTextChanged(Editable s) {
	        	int index = word_list.getIndexIgnoreCase(s.toString());
	            if (index >= 0){
	            	lv.setSelection(index); // smoothScrollToPosition(21);
	            }
	        }
	        public void beforeTextChanged(CharSequence s, int start, int count, int after){}
	        public void onTextChanged(CharSequence s, int start, int before, int count){}
	    }); 
	}
	
	private ArrayListIgnoreCase getWordList(String filename) throws IOException{
		ArrayListIgnoreCase list = new ArrayListIgnoreCase();
		String str = "";
		InputStream is = this.getResources().getAssets().open(filename);
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		if (is!=null) {
	        while ((str = reader.readLine()) != null) {
	            list.add(str);
	        }
	    }
		is.close();
		return list;
	}

}
