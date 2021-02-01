package com.github.psm.slidingrootnav;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.card.MaterialCardView;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

public class MainActivity extends AppCompatActivity {

    private SlidingRootNav slidingRootNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        slidingRootNav = new SlidingRootNavBuilder(this)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.left_menu_layout)
                .withToolbarMenuToggle(findViewById(R.id.tool_bar))
                .inject();

        View slideView = slidingRootNav.getLayout();

        TextView openFragment_a = slideView.findViewById(R.id.fragment_a);
        TextView openFragment_b = slideView.findViewById(R.id.fragment_b);
        TextView openActivity_a = slideView.findViewById(R.id.activity_a);
        TextView openActivity_b = slideView.findViewById(R.id.activity_b);


        View.OnClickListener menuListener = v -> {
            if (v.getId() == R.id.activity_a) {
                startActivity(new Intent(getBaseContext(), ActivityA.class));
            } else if (v.getId() == R.id.activity_b) {
                startActivity(new Intent(getBaseContext(), ActivityB.class));
            } else if (v.getId() == R.id.fragment_a) {
                startFragment(new FragmentA());
            } else if (v.getId() == R.id.fragment_b) {
                startFragment(new FragmentB());
            }
            slidingRootNav.closeMenu();
        };

        openFragment_a.setOnClickListener(menuListener);
        openFragment_b.setOnClickListener(menuListener);
        openActivity_a.setOnClickListener(menuListener);
        openActivity_b.setOnClickListener(menuListener);
    }

    private void startFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }
}