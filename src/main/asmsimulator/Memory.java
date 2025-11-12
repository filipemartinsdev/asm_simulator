package main.asmsimulator;

import java.util.HashMap;
import java.util.Map;

public final class Memory {
    private Map<String, Integer> fieldsAddress;
    private Map<Integer, byte[]> fields;

    private int topAddress = -1;

    public Memory(){
        this.fieldsAddress = new HashMap<>();
        this.fields = new HashMap<>();
    }

    public byte[] read(String field){
        return fields.get(fieldsAddress.get(field));
    }

    public byte[] read(int address){
        return fields.get(address);
    }

    public void overwrite(String field, byte[] bytes){
        this.fieldsAddress.put(field, ++this.topAddress);
        this.fields.put(this.topAddress, bytes);
    }

    public void alloc(String field, byte[] bytes){
        this.fieldsAddress.put(field, ++this.topAddress);
        this.fields.put(this.topAddress, bytes);
    }

    public void reserveBytes(String field, int length){
        this.fieldsAddress.put(field, ++this.topAddress);
        this.fields.put(this.topAddress, new byte[length]);
    }

    public boolean fieldExists(String field){
        return this.fieldsAddress.containsKey(field);
    }

    public void free(String field){
        this.fields.remove(fieldsAddress.get(field));
        this.fieldsAddress.remove(field);
    }
}