import java.util.HashMap;

public class SymbolTable {
    private final HashMap<String, Integer> hashMap;

    public SymbolTable() {
        this.hashMap = new HashMap<>();

        for (int i = 0; i < 16; i++) {
            this.hashMap.put("R" + i, i);
        }

        this.hashMap.put("SCREEN", 16384);
        this.hashMap.put("KBD", 24576);
        this.hashMap.put("SP", 0);
        this.hashMap.put("LCL", 1);
        this.hashMap.put("ARG", 2);
        this.hashMap.put("THIS", 3);
        this.hashMap.put("THAT", 4);
    }

    public void addEntry(String symbol, int address) {
        this.hashMap.put(symbol, address);
    }

    public boolean contains(String symbol) {
        return this.hashMap.containsKey(symbol);
    }

    public int getAddress(String symbol) {
        return this.hashMap.get(symbol);
    }
}
