package com.example.httpserver.app.ui.view.home;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Looper;
import android.widget.TableLayout;
import android.widget.Toast;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager2.widget.ViewPager2;
import com.example.httpserver.R;
import com.example.httpserver.app.services.wifi.WifiDirectService;
import com.example.httpserver.app.ui.adapter.HomePageAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class HomeFragment extends Fragment {

    private HomeViewModel model;

    private TabLayout tab;
    private ViewPager2 pager2;
    private HomePageAdapter adapter;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tab = view.findViewById(R.id.tabs);
        pager2 = view.findViewById(R.id.container);
        pager2.setAdapter((adapter = new HomePageAdapter(this)));

        // attach pager2 to tab layout
        new TabLayoutMediator(tab, pager2, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Services");
                    break;
                case 1:
                    tab.setText("Devices");
                    break;
                default:
                    // todo handle default case
                    throw new IllegalStateException("Position is invalid!");
            }
        }).attach();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        model = new ViewModelProvider(this).get(HomeViewModel.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private boolean wifiDirectPermissionCheck() {
        String[] permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.CHANGE_WIFI_STATE,
                Manifest.permission.CHANGE_NETWORK_STATE, Manifest.permission.ACCESS_NETWORK_STATE};
        if(PackageManager.PERMISSION_DENIED ==
                requireActivity().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)) {
            requestPermissions(permissions, 0);
            return false;
        };
        if(PackageManager.PERMISSION_DENIED ==
                requireActivity().checkSelfPermission(Manifest.permission.ACCESS_WIFI_STATE)) {
            requestPermissions(permissions, 0);
            return false;
        };
        if(PackageManager.PERMISSION_DENIED ==
                requireActivity().checkSelfPermission(Manifest.permission.CHANGE_WIFI_STATE)) {
            requestPermissions(permissions, 0);
            return false;
        };
        if(PackageManager.PERMISSION_DENIED ==
                requireActivity().checkSelfPermission(Manifest.permission.CHANGE_NETWORK_STATE)) {
            requestPermissions(permissions, 0);
            return false;
        };
        if(PackageManager.PERMISSION_DENIED ==
                requireActivity().checkSelfPermission(Manifest.permission.ACCESS_NETWORK_STATE)) {
            requestPermissions(permissions, 0);
            return false;
        };
        return true;
    }

    private void startWifiDirect() {
        if(!wifiDirectPermissionCheck()) {
            Toast.makeText(requireContext(), "Permission is needed!", Toast.LENGTH_SHORT).show();
            return;
        };
        requireActivity().startService(new Intent(requireContext(), WifiDirectService.class));
    }

    private void stopWifiDirect() {
        requireActivity().stopService(new Intent(requireContext(), WifiDirectService.class));
    }
}