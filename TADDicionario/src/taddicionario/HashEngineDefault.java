/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testedic;


public class HashEngineDefault extends Hash_engine{
    public HashEngineDefault(){
        super();
    }
    @Override
    public Long generateHash(Object key) {
        String stringKey = key.toString();
        long hash = 0;
        for(int i=0; i<stringKey.length();i++){
            hash += (int)stringKey.charAt(i);
        }
        return hash;
    }
    
}