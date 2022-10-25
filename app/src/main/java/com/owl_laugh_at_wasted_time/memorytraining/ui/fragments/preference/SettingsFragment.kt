package com.owl_laugh_at_wasted_time.memorytraining.ui.fragments.preference

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.owl_laugh_at_wasted_time.memorytraining.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings_preference, rootKey)
    }


}

