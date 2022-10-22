import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HackAssembler {
    public static void main(String[] args) throws IOException {
        final SymbolTable symbolTable = new SymbolTable();
        final File inputFile = new File(args[0]);
        Parser parser = new Parser(inputFile);

        int currInstructionNum = 0;
        try {
            while (parser.hasMoreLines()) {
                parser.advance();

                if (parser.instructionType() == Instruction.L_INSTRUCTION) {
                    symbolTable.addEntry(parser.symbol(), currInstructionNum);
                } else {
                    currInstructionNum++;
                }

            }
        } catch (NoMoreLines ignored) {
        }

        final List<String> bitLines = new ArrayList<>();
        final Code code = new Code();

        parser = new Parser(inputFile);
        int variableAddress = 16;

        try {
            while (parser.hasMoreLines()) {
                parser.advance();

                switch (parser.instructionType()) {
                    case A_INSTRUCTION -> {
                        String currSymbol = parser.symbol();
                        int address = 0;

                        try {
                            address = Integer.parseInt(currSymbol);
                        } catch (Exception exception) {
                            if (!symbolTable.contains(currSymbol)) {
                                symbolTable.addEntry(currSymbol, variableAddress);
                                variableAddress++;
                            }
                            address = symbolTable.getAddress(currSymbol);
                        }

                        String result = Integer.toBinaryString(address);
                        String resultWithPadding = String.format("%16s", result).replaceAll(" ", "0");
                        bitLines.add(resultWithPadding);
                    }

                    case C_INSTRUCTION -> {
                        String leftBits = "111";
                        String compBits = code.comp(parser.comp());
                        String destBits = code.dest(parser.dest());
                        String jumpBits = code.jump(parser.jump());
                        bitLines.add(leftBits + compBits + destBits + jumpBits);
                    }

                    default -> {
                    }
                }

            }
        } catch (NoMoreLines ignored) {
        }

        final String outPutFileName = inputFile.getParentFile().toString() + '\\' + inputFile.getName().replace(".asm", ".hack");
        final File outputFile = new File(outPutFileName);

        if (outputFile.createNewFile()) {
            try (final FileWriter fw = new FileWriter(outputFile);
                 final PrintWriter writer = new PrintWriter(fw)) {
                for (String bitLine : bitLines) {
                    writer.println(bitLine);
                }
            }
        }
    }
}
