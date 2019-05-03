
package taddicionario;

import java.util.LinkedList;

public class TADDicChain<K, V> {
    
    private LinkedList<TDicItem>[] vetBuckets;
    private Hash_engine hashEngine;
    private int quant_entradas = 0; //Quantidade de elementos
    private double fator_de_carga = 0.75;
    private boolean achou = false;
    
    public TADDicChain() {
        this.hashEngine = new HashEngineDefault();
        int len = (int) (100 / this.fator_de_carga);
        this.vetBuckets = new LinkedList[len];
        for (int i = 0; i < len; i++) {
            this.vetBuckets[i] = new LinkedList<>();
        }
    }
    
    public TADDicChain(int quantEntradas) {
        this.hashEngine = new HashEngineDefault();
        int len = (int) (quantEntradas / this.fator_de_carga);
        this.vetBuckets = new LinkedList[len];
        for (int i = 0; i < len; i++) {
            this.vetBuckets[i] = new LinkedList<>();
        }
    }
     public TADDicChain(Hash_engine hashEngine) {
        this.hashEngine = hashEngine;
        int len = (int) (100 / this.fator_de_carga);
        this.vetBuckets = new LinkedList[len];
        for (int i = 0; i < len; i++) {
            this.vetBuckets[i] = new LinkedList<>();
        }
    }
     
    public boolean NO_SUCH_KEY() {
        return !this.achou;
    }
    
    public int size() {
        return this.quant_entradas;
    }
    private int getIndex(K key) {
        Long hash = this.hashEngine.generateHash(key);
        return (int) (hash % this.vetBuckets.length);
    }
    public void insertItem(K key, V item) {
        int index = getIndex(key);
        this.vetBuckets[index].add(new TDicItem(key, item));
        this.quant_entradas++;
    }
    
    public TDicItem<K, V> findElement(K key) {
        int index = this.getIndex(key);
        if (!this.vetBuckets[index].isEmpty()) {
            int i = 0;
            while (i < this.vetBuckets[index].size()) {
                TDicItem item = this.vetBuckets[index].get(i);
                if (item != null && key.equals(item.getKey())) {
                    this.achou = true;
                    return item;
                }
                i++;
            }
        }
        return null;
    }
    public void removeElement(K key) {
        int index = this.getIndex(key);
        if (!this.vetBuckets[index].isEmpty()) {
            this.vetBuckets[index].remove();
            this.quant_entradas--;
        }
    }
    public boolean isEmpty() {
        return (this.quant_entradas == 0);
    }
    
    public LinkedList<K> keys(){
        LinkedList<TDicItem> list;    
        LinkedList<K> keys = new LinkedList<>();
                
        for( int i = 0; i < this.vetBuckets.length ; i++){
            list = this.vetBuckets[i];
            for (TDicItem<K, V> element : list) {
  
                keys.add(element.getKey());
            }
        }
        return keys;
    }
    
    public LinkedList<V> elements(){
        LinkedList<TDicItem> list;
        LinkedList<V> values = new LinkedList<>();
        
        for( int i = 0; i < this.vetBuckets.length ; i++){
            list = this.vetBuckets[i];
            for (TDicItem<K, V> element : list) {
                values.add(element.getValue());
            }
        }
        
        return values;
    }
    
    public TADDicChain<K, V> clone(){
        TADDicChain<K, V> clone = new TADDicChain();
        LinkedList<TDicItem> list;
        
        for( int i = 0; i < this.vetBuckets.length ; i++){
            list = this.vetBuckets[i];
            for (TDicItem<K, V> element : list) {
                clone.insertItem(element.getKey(), element.getValue());
            }
        }
        
        return clone;
    }
    public boolean equals(TADDicChain source) {
        if ((this.getLen() != source.getLen()) || (this.getSize() != source.getSize())) return false;   
        for (K key : this.keys()) {                    
            Object value = source.findElement(key);
            if (source.NO_SUCH_KEY()) return false;    
            if (value != this.findElement(key)) return false;  
        }
        return true;
    }
    
    public int[] getColisoes(){
        int colisoes[] = new int[this.vetBuckets.length];
        for(int i = 0 ; i < this.vetBuckets.length ; i++) {
            colisoes[i] = this.vetBuckets[i].size();
        }
        return colisoes;
    }
    
    public int getLen(){
        return this.vetBuckets.length;
    }
    
    public int getSize(){
        return this.quant_entradas;
    }
  
}
