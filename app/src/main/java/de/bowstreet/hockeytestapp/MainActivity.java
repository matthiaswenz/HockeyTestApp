package de.bowstreet.hockeytestapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import net.hockeyapp.android.CrashManager;
import net.hockeyapp.android.FeedbackManager;
import net.hockeyapp.android.LoginManager;
import net.hockeyapp.android.UpdateManager;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    public static final String APP_IDENTIFIER = "560eb1f4ada02d23d8c843682b867fca";
    public static final String APP_SECRET = "1df33596fe9969edb7d22136d815c6b9";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        final MainActivity ctx = this;
        final Intent intent = getIntent();

        Button crashButton = (Button) findViewById(R.id.crash_button);
        crashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> list = new ArrayList<>();
                String fail = list.get(0);
            }
        });

        Button feedbackButton = (Button) findViewById(R.id.feedback_button);
        feedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FeedbackManager.showFeedbackActivity(ctx);
            }
        });

        Button loginUserPasswordButton = (Button) findViewById(R.id.login_user_password);
        loginUserPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.register(ctx, APP_IDENTIFIER, APP_SECRET, LoginManager.LOGIN_MODE_EMAIL_PASSWORD, MainActivity.class);
                LoginManager.verifyLogin(ctx, intent);
            }
        });

        Button loginUserButton = (Button) findViewById(R.id.login_user);
        loginUserPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.register(ctx, APP_IDENTIFIER, APP_SECRET, LoginManager.LOGIN_MODE_EMAIL_ONLY, MainActivity.class);
                LoginManager.verifyLogin(ctx, intent);
            }
        });

        FeedbackManager.register(this, APP_IDENTIFIER);

        checkForCrashes();
        checkForUpdates();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void checkForCrashes() {
        CrashManager.register(this, "560eb1f4ada02d23d8c843682b867fca");
    }

    private void checkForUpdates() {
        // Remove this for store builds!
        UpdateManager.register(this, "560eb1f4ada02d23d8c843682b867fca");
    }

}