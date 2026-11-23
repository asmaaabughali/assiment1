
package com.example.hellotoast2;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hellotoast2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private int count = 0;

    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(mainBinding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main), (v, insets) -> {

                    Insets systemBars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars());

                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom);

                    return insets;
                });

        if (savedInstanceState != null) {
            count = savedInstanceState.getInt("count", 0);
        }

        mainBinding.textDisplayCount.setText(String.valueOf(count));

        updateColors();
    }

    public void showToast(View view) {

        Toast.makeText(this, "Hello Toast",
                Toast.LENGTH_SHORT).show();
    }

    public void showCount(View view) {

        count++;

        mainBinding.textDisplayCount.setText(String.valueOf(count));

        updateColors();
    }

    public void showZero(View view) {

        count = 0;

        mainBinding.textDisplayCount.setText(String.valueOf(count));

        updateColors();
    }

    private void updateColors() {

        if (count == 0) {

            mainBinding.buttonZero.setBackgroundColor(
                    ContextCompat.getColor(this, R.color.zero_gray));

        } else {

            mainBinding.buttonZero.setBackgroundColor(
                    ContextCompat.getColor(this, R.color.zero_orange));
        }

        if (count % 2 != 0) {

            mainBinding.buttonCount.setBackgroundColor(
                    ContextCompat.getColor(this, R.color.count_green));

        } else if (count != 0) {

            mainBinding.buttonCount.setBackgroundColor(
                    ContextCompat.getColor(this, R.color.count_blue));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);

        outState.putInt("count", count);
    }
}
