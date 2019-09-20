package com.binakarir.binakarirapss.Fragment;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bk.binakarir.binakarir.Home;
import com.bk.binakarir.binakarir.Login_google.LoginActivity;
import com.bk.binakarir.binakarir.My_Profile;
import com.bk.binakarir.binakarir.Pembayaran;
import com.bk.binakarir.binakarir.Pendaftaran;
import com.bk.binakarir.binakarir.Pendaftaran2;
import com.bk.binakarir.binakarir.PointActivity;
import com.bk.binakarir.binakarir.R;
import com.bk.binakarir.binakarir.Refund;
import com.bk.binakarir.binakarir.Setting;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfilFragment extends Fragment {
    FirebaseAuth auth;
    Button btnsignout;
    TextView TVmyprofile, TVrefund, TVpoint,TV_Tpendaftaran, TV_email_profil,TV_pembayaran;
    private ProgressBar progressBar;
    private FirebaseAuth.AuthStateListener authListener;
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_profil, container, false);

            TV_email_profil = view.findViewById(R.id.txt_email_profil);
            TVmyprofile = view.findViewById(R.id.TV_user_my_profile);
            TVrefund = view.findViewById(R.id.TV_user_refund);
            TVpoint = view.findViewById(R.id.TV_user_point);
            TV_Tpendaftaran = view.findViewById(R.id.TV_fp_pendaftaran);
            TV_pembayaran = view.findViewById(R.id.TV_user_pembayaran);


            TV_pembayaran.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent inmypembayaran = new Intent(getActivity(), Pembayaran.class);
                    startActivity(inmypembayaran);
                }
            });
            TVmyprofile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent inmyprofile = new Intent(getActivity(), My_Profile.class);
                    startActivity(inmyprofile);
                }
            });

            TVrefund.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent inrefund = new Intent(getActivity(), Refund.class);
                    startActivity(inrefund);
                }
            });

            TVpoint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent inpoint = new Intent(getActivity(),PointActivity.class);
                    startActivity(inpoint);
                }
            });

            TV_Tpendaftaran.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent inpendaftaran = new Intent(getActivity(), Pendaftaran.class);
                    startActivity(inpendaftaran);
                }
            });

            //end
            auth = FirebaseAuth.getInstance();
            btnsignout = view.findViewById(R.id.btnsignout);
            //get current user
            final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            if (progressBar != null) {
                progressBar.setVisibility(View.GONE);
            }
            // this listener will be called when there is change in firebase user session
            authListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    if (user == null) {
                        // user auth state is changed - user is null
                        // launch login activity
                        startActivity(new Intent(getActivity(), LoginActivity.class));
                        getActivity().finish();
                    }
                }
            };

            btnsignout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   signOut();
                }
            });


            return view;
        }

        public void signOut(){
            auth.signOut();
            Intent intsignout = new Intent(getActivity(), Home.class);
            startActivity(intsignout);
        }
    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }
}



