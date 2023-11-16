package org.zaccarias.fernandes.christian.aramis.enums;

public enum TiposEnum {

    HORISTA ("Horista"),

    MENSALISTA ("Mensalista");

    private String label;

    TiposEnum(String label){
        this.label = label;
    }

    public String getLabel(){
        return this.label;
    }

    public static TiposEnum fromString(String text) {
        for (TiposEnum b : TiposEnum.values()) {
            if (b.getLabel().equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
