// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Mult.asm

// Multiplies R0 and R1 and stores the result in R2.
// (R0, R1, R2 refer to RAM[0], RAM[1], and RAM[2], respectively.)
//
// This program only needs to handle arguments that satisfy
// R0 >= 0, R1 >= 0, and R0*R1 < 32768.

// Put your code here.

// init loop counter + init R2
@R2
M=0
@i
M=0
D=M

// loop through R1 time
(LOOP)
    // if i == R1 goto END
    @R1
    D=D-M
    @END
    D;JEQ

    // add R0 to the value + increment counter
    @R0
    D=M
    @R2
    M=M+D
    @i
    M=M+1
    D=M
    @LOOP
    0;JMP

// infinte loop
(END)
    @END
    0;JMP