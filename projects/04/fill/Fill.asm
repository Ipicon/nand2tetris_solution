// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Fill.asm

// Runs an infinite loop that listens to the keyboard input.
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel;
// the screen should remain fully black as long as the key is pressed. 
// When no key is pressed, the program clears the screen, i.e. writes
// "white" in every pixel;
// the screen should remain fully clear as long as no key is pressed.

// Put your code here.
(KBD_PROCESS)
    // check wheter is pressed or not and based on that compute logic
    @KBD
    D=M
    @TURN_WHITE
    D;JEQ

    // turn on black indication
    @is_black
    M=1
    @SCREEN_PROC
    0;JMP

(TURN_WHITE)
    @is_black
    M=0
    @SCREEN_PROC
    0;JMP    

(SCREEN_PROC)
    // init screen scounter
    @i
    M=0
    D=M

    // iterate through screen
    (LOOP)
        // if we iterate throught all the screen go back to listening to kbd input
        @8192
        D=A-D
        @KBD_PROCESS
        D;JEQ

        // check wheter or not turn the screen to black
        @is_black
        D=M
        @SET_WHITE
        D;JEQ
        
        // color all to black
        @i
        D=M
        @SCREEN
        A=A+D
        M=-1
        @i
        M=M+1
        D=M
        @LOOP
        0;JMP

        

(SET_WHITE)
    @i
    D=M
    @SCREEN
    A=A+D
    M=0
    @i
    M=M+1
    D=M
    @LOOP
    0;JMP    
        
