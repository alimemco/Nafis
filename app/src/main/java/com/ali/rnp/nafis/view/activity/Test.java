package com.ali.rnp.nafis.view.activity;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ali.rnp.nafis.R;
import com.ali.rnp.nafis.view.DataModel.ApiService;
import com.ali.rnp.nafis.view.DataModel.Product;

import java.util.List;


public class Test extends AppCompatActivity {

private TextView txt;
private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        txt = findViewById(R.id.test_txt);
        btn = findViewById(R.id.test_btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiService apiService = new ApiService(Test.this);
                apiService.getProductByCategory("aromatic", new ApiService.onGetProductCategory() {
                    @Override
                    public void onProductReceived(List<Product> productList) {
                        if (productList!=null){
                            Product product = productList.get(1);
                            Log.i("testJsonData", "onProductReceived: "+product.getStatus());
                            txt.setText(product.getTitle());
                        }else {
                            txt.setText("error");
                        }



                    }
                });

            }
        });


    }
}
