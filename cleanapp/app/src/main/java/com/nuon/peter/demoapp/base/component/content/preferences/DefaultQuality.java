package com.nuon.peter.demoapp.base.component.content.preferences;

import android.content.Context;
import com.nuon.peter.demoapp.utils.PrefUtils;
import java.util.List;

public class DefaultQuality {

    public static String get(Context context, List<String> availableQualities) {
        String quality = PrefUtils.get(context, Prefs.QUALITY_DEFAULT, "720p");
        String[] fallbackOrder = new String[] {"720p", "480p", "1080p"};

        if(availableQualities.indexOf(quality) == -1) {
            for (String fallbackQuality : fallbackOrder) {
                if (availableQualities.indexOf(fallbackQuality) != -1) {
                    quality = fallbackQuality;
                    break;
                }
            }
        }

        return quality;
    }

}
