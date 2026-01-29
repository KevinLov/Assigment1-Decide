# Decide - Launch Interceptor Program
This repo contains our Java implementation of the requirement specification where the method DECIDE() will generate a boolean signal, which determines whether an interceptor should be
launched based upon input radar tracking information.

By evaluating the input radar tracking information, 15 different "Launch Interceptor Conditions" (LIC)will be evaluated and used in junction with a "Logical Connector Matrix" which tells what conditions matters in combination with others.
Through combination of these two we get the "Preliminary Unlocking Matrix" (PUM). And with the use of a "Preliminary Unlocking Vector" in junction with the PUM, the program will decide what LIC's actually matters in this specific launch determination and the "Final Unlocking Vector" will be created.
If all elements of the "FUV" is true, DECIDE will generate a "true" signal, signifying that there should be a launch, otherwise no launch will be performed.

## How to use it
The program is run by compiling and running Main.java. But you'd probably want to change the input to the LCM, PUV, parameters and input radar tracking information. This is done by changing the following:  
- LCM: in LCM.java there is a switch statement with different "modes". Users can implement their own mode or modify previous ones.  
- PUV: in PUV.java there is a switch statement with different "modes". Users can implement their own mode or modify previous ones.  
- Parameters: Can be changed in Parameters.java through the same means as above. There is a switch statement with different modes.  
- Points: A little trickier, currently you have to define an array with points according to the Point.java class, and then insert that array into the verifyAllLics method like the following (as params1): ```boolean[] cmvArray = cmv.verifyAllLics(params, points1, points1.length)```
Based on the input, the method DECIDE() will either set the variable launch in FUV as true or false, depending on the progress.  

The program uses asserts for error handling that throw AssertionError. Asserts are not enabled by default. To enable them you must run Main.java using the -ea flag.  

From ```..\Assigment1-Decide\src\main\java```

you can compile:
```javac se\kth\DD2480\Main.java```

and run:
```java -ea se.kth.DD2480.Main```

Running the above will generate the .class files within the same \src folder. To put them where they belong we can instead:  
```javac -d ..\..\..\out se\kth\DD2480\Java.Main```  
```java -ea -cp ..\..\..\out se.kth.DD2480.Main```

The -d flag ensures that the .class files end up in the specified directory, and the -cp flag tells the JVM to look for the .class files in that specified directory.
## Structure of the repo
/docs: contains the document "Way_of_Working.md"  
/src contains  
I---/main/java/se/kth/DD2480 - implementation code (LICs, CMV, LCM, PUM, PUV, FUV, etc.)  
I---/test/java/se/kth/DD2480 - JUnit tests  
README.md – project overview, how to use, structure, requirements and statement of contributions

## Requirements
Java JDK version 21  
Maven version 3.9.12

## Statement of Contribution
Adam Egnell:       LIC 1,6,11 and related tests, FUV and related tests.  
Ahmed Baccar:      LIC 0,5,10 and related tests, PUM, Parts of Point.  
Kevin Löv:         LIC 2,7,12 and related tests, Lead on Way-of-Working and README, LCM and related tests, DECIDE() tests  
Samuel Peetre:     LIC 3,8,13 and related tests, Related method in point.  
Teoman Köylüoglu:  LIC 4,9,14 and related tests, Tests for PUM, setup Maven and Github Actions, program-flow, DECIDE().  