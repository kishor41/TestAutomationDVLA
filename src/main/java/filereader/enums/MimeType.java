package filereader.enums;

import java.util.HashMap;
import java.util.Map;

public enum MimeType {
    CSV("text/csv"),
    EXCEL("application/vnd.ms-excel");

    private String kind;

    private static class Holder {
        static Map<String, MimeType> MAP = new HashMap<>();
    }

    MimeType (String kind) {
        this.kind = kind;
        Holder.MAP.put(kind,this);
    }

    public String type(){
        return kind;
    }

   
    public static MimeType find(String kind) {
        MimeType mimeType = Holder.MAP.get(kind);
        if(mimeType == null) {
            throw new IllegalStateException(String.format("Unsupported type %s.", kind));
        }
        return mimeType;
    }
}
