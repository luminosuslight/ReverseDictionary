package org.luminosuslight.reversedictionary;

import java.util.ArrayList;

public class ArrayListIgnoreCase extends ArrayList<String> {

	private static final long serialVersionUID = 1L;

    public int getIndexIgnoreCase(Object o) {
        String paramStr = (String)o;
        for (String s : this) {
            if (paramStr.equalsIgnoreCase(s)) return this.indexOf(s);
        }
        return -1;
    }
}