package collectionPart;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import texting.Thing;

import java.util.Comparator;

public class ThingComparator implements Comparator<Thing> {
    public ThingComparator() {
    }

    public int compare(Thing a, Thing b) {
        if(a.getValueThing() > b.getValueThing()){
            return 1;
        } else if(a.getValueThing() == b.getValueThing()){return 0;} else return -1;
    }
}
