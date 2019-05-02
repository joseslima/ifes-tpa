package taddicionario;

public class TDicItem<K,V> {
    private K key;
    private V value;
    
    public TDicItem(K key, V value){
        this.value = value;
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public K getKey() {
        return key;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
