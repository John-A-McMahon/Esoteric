# Esoteric
This is my attempt at making an esoteric programming language.



To "program" in this language simply make a string in java containing the "code" and then use the provided Java functions to 'fake compile' (turn keywords into symbols)

I think it is turing complete but I am unsure how to prove it is turning complete

Some code samples are included in the java file. 

Documentation is in the Java file keywords and symbols meanings. 


Sumary of the "language":
meant to mimic a turning machine.
under the hood it uses an arraylist as an "*infinite turing machine tape" (*not really infinite... due to limited RAM)

The esoteric Raw function has the syntax but if you are short on time:
		 +=Move right
		 -=Move left
		 W# = Write where # = 0 or 1 
		 C...E = Condition (runs if standing on 1 in the turing machine) (NCN can be used to check for zero (0) instead of 1)
		 E = END (basicly acts like a closing brase or similar bash statements)
		 J#E=Jump to code index # if the current tape value is 
		 H=halt
		 F= flag command
     f= flag marker
     N = NOT (flips the bit it is standing on)



you can use flags and jumps to manuver the code
flags jump to the nth flag (added because jumps made it too difficult to use even for esoteric standards)
jumps jump to the nth character (awful to use because every edit you have to manually could what character you want to jump to)

It includes conditionals
C...E   (runs if tape is on a 1)
NCN...E (runs if tape is on a 0)



********************************************************************************************
To compile run:

go to src directory

java esoteric.Esoteric <codeFileName>
(the code file is provided to test it)
if no code file is provided then it will run some demos

*******************************************************************************************

HAVE FUN!!!
