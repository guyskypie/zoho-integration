package outcastfoods.integration.zoho.mapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum RegionMapping {

    GAUTENG(new String []{"HC","NC","GC","GD","GF","GH","NF","NG","NH"}),
    KZN(new String []{"KC","KF"}),
    WC(new String []{"WC","WD","WF"});


    private String [] codes;


    RegionMapping(String [] codes) {
        this.codes = codes;

    }

    public String[] getCodes() {
        return codes;
    }
}
