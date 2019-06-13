package com.akjung.biometric.androidx;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.biometric.BiometricPrompt;
import androidx.core.hardware.fingerprint.FingerprintManagerCompat;

import java.util.concurrent.Executor;

/**
 * Android 6.0 ~ Android 9.0
 * FingerPrint recognition
 */
public class MainActivity extends AppCompatActivity {
    private BiometricPrompt mBiometricPrompt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void OnClickFingerPrint(View view) {
        FingerprintManagerCompat fingerprintManagerCompat = FingerprintManagerCompat.from(this);

        if(!fingerprintManagerCompat.hasEnrolledFingerprints()) {
            Toast.makeText(MainActivity.this, "No! Fingerprints ", Toast.LENGTH_SHORT).show();

        } else {
            Executor executor = new Executor(){
                @Override
                public void execute(Runnable command) {
                    new Handler(Looper.getMainLooper()).post(command);
                }
            };

            //Show Dialog popup
            mBiometricPrompt = new BiometricPrompt(this
                    , executor, new BiometricPrompt.AuthenticationCallback() {
                @Override
                public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                    super.onAuthenticationError(errorCode, errString);
                    Toast.makeText(MainActivity.this, "onAuthenticationError : " + errString, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                    super.onAuthenticationSucceeded(result);
                    Toast.makeText(MainActivity.this, "onAuthenticationSucceeded!!", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onAuthenticationFailed() {
                    super.onAuthenticationFailed();
                    Toast.makeText(MainActivity.this, "onAuthenticationFailed!", Toast.LENGTH_SHORT).show();

                }
            });

        }

    }
}