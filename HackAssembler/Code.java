import java.util.HashMap;

public class Code {
    private HashMap<String, String> compMap;
    private HashMap<String, String> jumpMap;

    public Code() {
        this.initComp();
        this.initJump();
    }

    private void initJump() {
        this.jumpMap = new HashMap<>();
        this.jumpMap.put("null", "000");
        this.jumpMap.put("JGT", "001");
        this.jumpMap.put("JEQ", "010");
        this.jumpMap.put("JGE", "011");
        this.jumpMap.put("JLT", "100");
        this.jumpMap.put("JNE", "101");
        this.jumpMap.put("JLE", "110");
        this.jumpMap.put("JMP", "111");
    }

    private void initComp() {
        this.compMap = new HashMap<>();
        this.compMap.put("0", "101010");
        this.compMap.put("1", "111111");
        this.compMap.put("-1", "111010");
        this.compMap.put("D", "001100");
        this.compMap.put("A", "110000");
        this.compMap.put("!D", "001101");
        this.compMap.put("!A", "110001");
        this.compMap.put("-D", "001111");
        this.compMap.put("-A", "110011");
        this.compMap.put("D+1", "011111");
        this.compMap.put("A+1", "110111");
        this.compMap.put("D-1", "001110");
        this.compMap.put("A-1", "110010");
        this.compMap.put("D+A", "000010");
        this.compMap.put("D-A", "010011");
        this.compMap.put("A-D", "000111");
        this.compMap.put("D&A", "000000");
        this.compMap.put("D|A", "010101");
    }

    public String dest(String dest) {
        char leftBit = '0';
        char midBit = '0';
        char rightBit = '0';

        if (dest.contains("M")) {
            rightBit = '1';
        }

        if (dest.contains("D")) {
            midBit = '1';
        }

        if (dest.contains("A")) {
            leftBit = '1';
        }

        return leftBit + String.valueOf(midBit) + rightBit;
    }

    public String comp(String comp) {
        char aBit = '0';

        if(comp.contains("M")) {
            aBit = '1';
            comp = comp.replace('M', 'A');
        }

        return aBit + this.compMap.get(comp);
    }

    public String jump(String jump) {
        return this.jumpMap.get(jump);
    }
}
