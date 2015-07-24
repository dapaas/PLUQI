package com.sirmamobile.saltlux.fragments;

import com.sirmamobile.saltlux.navigation.NavigationType;

/**
 * Created by Martin on 5/7/2015.
 */
public interface SaltluxListener {
    void demandMenu(NavigationType type);
    void demandTitle(String title);
}
