package sample.gopi.com.fastapplication.imagegameguess.activities;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.DrawableRes;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.constraint.Group;
import android.support.constraint.Placeholder;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import sample.gopi.com.fastapplication.R;

public class ConstraintLayoutActivity extends AppCompatActivity {

    private Placeholder consPlaceholder;
    private ImageView consfirstIv;
    private ImageView consSecondIv;
    private ConstraintLayout constraintLayout;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_advanced);

       /* final Group firstGroup = findViewById(R.id.first_group_id);
        firstGroup.setOnClickListener(view -> {
            Toast.makeText(ConstraintLayoutActivity.this, "clicked ", Toast.LENGTH_LONG).show();
        });
        firstGroup.setVisibility(View.GONE);
        final int[] referencedIds = firstGroup.getReferencedIds();
        System.out.println("referencedIds = " + referencedIds);*/
        constraintLayout = ((ConstraintLayout) findViewById(R.id.parentConstraintLayout));
        consPlaceholder = (Placeholder) findViewById(R.id.consPlaceholder);
        consfirstIv = (ImageView) findViewById(R.id.consfirstIv);
        consSecondIv = (ImageView) findViewById(R.id.consSecondIv);
        final TextView textView = findViewById(R.id.txt_msg);

        /*ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);*/
//        constraintSet.setMargin();
        editText = findViewById(R.id.constraintEditText);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0) {
                    textView.setText(s.toString());
                }
            }
        });

//        final Drawable drawable = changeDrawableColor(this, R.drawable.ic_action_basket, Color.WHITE);
        final Drawable drawable1 = getDrawableMethod(R.drawable.ic_action_basket);
        drawable1.setColorFilter(new PorterDuffColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN));
        consfirstIv.setImageDrawable(drawable1);
    }

     public static Drawable getDrawableMethod(@DrawableRes int resId) {
        try {
            return ContextCompat.getDrawable(GameApplication.getAppContext(), resId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Drawable changeDrawableColor(Context context, int icon, int newColor) {
        Drawable mDrawable = ContextCompat.getDrawable(context, icon).mutate();
        mDrawable.setColorFilter(new PorterDuffColorFilter(newColor, PorterDuff.Mode.SRC_IN));
        return mDrawable;
    }

    public void swapViews(View view) {
        TransitionManager.beginDelayedTransition(constraintLayout);
        consPlaceholder.setContentId(view.getId());
        /*if (consfirstIv.getId() == view.getId()) {
        }*/
    }
}
