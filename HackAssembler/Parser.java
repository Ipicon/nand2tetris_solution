import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {
    private final Scanner scanner;
    private String nextInstruction;

    public Parser(File fileToParse) throws FileNotFoundException {
        this.scanner = new Scanner(fileToParse);
    }

    public boolean hasMoreLines() {
        final boolean hasNextLine = this.scanner.hasNextLine();

        if (!hasNextLine) {
            this.scanner.close();
        }

        return hasNextLine;
    }

    public void advance() throws NoMoreLines {
        boolean foundInstruction = false;

        while (!foundInstruction) {
            String currLine = this.scanner.nextLine();
            currLine = currLine.replaceAll("\\s+", "");

            if (!(currLine.startsWith("//") || currLine.equals(""))) {
                foundInstruction = true;

                if(currLine.contains("//")) {
                    this.nextInstruction = currLine.substring(0, currLine.indexOf('/'));
                } else {
                    this.nextInstruction = currLine;
                }
            }

            if ((!this.scanner.hasNextLine()) && (!foundInstruction)) {
                this.scanner.close();
                throw new NoMoreLines();
            }
        }
    }

    public Instruction instructionType() {
        return switch (this.nextInstruction.charAt(0)) {
            case '(' -> Instruction.L_INSTRUCTION;
            case '@' -> Instruction.A_INSTRUCTION;
            default -> Instruction.C_INSTRUCTION;
        };
    }

    public String symbol() {
        if (this.instructionType() == Instruction.L_INSTRUCTION) {
            return this.nextInstruction.substring(1, this.nextInstruction.length() - 1);
        }

        return this.nextInstruction.substring(1);
    }

    public String dest() {
        if (this.nextInstruction.contains("=")) {
            return this.nextInstruction.substring(0, this.nextInstruction.indexOf('='));
        }

        return "null";
    }

    public String comp() {
        int startString = 0;
        int endString = this.nextInstruction.length();

        if (this.nextInstruction.contains("=")) {
            startString = this.nextInstruction.indexOf('=') + 1;
        }

        if (this.nextInstruction.contains(";")) {
            endString = this.nextInstruction.indexOf(';');
        }

        return this.nextInstruction.substring(startString, endString);
    }

    public String jump() {
        if (this.nextInstruction.contains(";")) {
            return this.nextInstruction.substring(this.nextInstruction.indexOf(';') + 1);
        }

        return "null";
    }
}
