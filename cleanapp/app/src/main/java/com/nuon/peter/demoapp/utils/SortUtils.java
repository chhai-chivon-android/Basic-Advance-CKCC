package com.nuon.peter.demoapp.utils;

import java.util.Arrays;
import java.util.Comparator;

public class SortUtils {

    public static String[] sortQualities(String[] qualities) {
        Arrays.sort(qualities, new Comparator<String>() {
            @Override
            public int compare(String lhs, String rhs) {
                if (lhs.contains("p") && rhs.contains("p")) {
                    int q1 = Integer.parseInt(lhs.substring(0, lhs.indexOf('p')));
                    int q2 = Integer.parseInt(rhs.substring(0, rhs.indexOf('p')));
                    if (q1 < q2) {
                        return 1;
                    } else if (q1 < q2) {
                        return -1;
                    } else {
                        return 0;
                    }
                }

                if(lhs.equals("3D"))
                    return 1;

                return 0;
            }
        });
        return qualities;
    }

}
