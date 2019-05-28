package testedic;

public class TDicItem {
    private Object key;
    private Object value;
    
    public TDicItem(Object key, Object value){
        this.value = value;
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public Object getKey() {
        return key;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
