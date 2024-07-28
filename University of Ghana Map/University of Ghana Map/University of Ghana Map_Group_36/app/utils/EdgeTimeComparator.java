
package app.utils;

import app.Extent;

import java.util.Comparator;

public class EdgeTimeComparator implements Comparator<Extent>{

    @Override
    public int compare(Extent first, Extent second) {
        if (first.getTime() > second.getTime())
            return 1;
        else if (first.getTime()< second.getTime())
            return -1;
        return 0;
    }
}
