<h2 align='center'><b> NLP to AADL Architecture </b></h2>

<h4 align='center'> Project Description </h4> 
This project is continuation of 'Toward Generating System Architecture and Formal Functional Description in the Architecture Analysis & Design Language (AADL) With Structured Natural Language'. Incorporation of more NLP in order to make sentences from English Language more acceptable (earlier too specific), assigning proper Electromechanical notations and flows, and much more is done. <br><br>

### Technical Skills 
![Python](https://img.shields.io/badge/python-3670A0?style=for-the-badge&logo=python&logoColor=ffdd54)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
![ANTLR4](https://img.shields.io/badge/ANTLR4-red?style=for-the-badge)
![Visual Studio Code](https://img.shields.io/badge/Visual%20Studio%20Code-0078d7.svg?style=for-the-badge&logo=visual-studio-code&logoColor=white)
![Jupyter Notebook](https://img.shields.io/badge/jupyter-%23FA0F00.svg?style=for-the-badge&logo=jupyter&logoColor=white)

### File Description and Content 
* NLPFormatter.py : Natural Language Processing done on the text to get it into proper compiler accepted format
* src/data/ : This folder contains all the English language inputs used for the testing of the system
* src/lib/ : Contains all the jar files and the compiler files (Lexer, Parser and Visitor)
* src/main/GrammarVerifier.java : Verifies whether the input text is according to the syntax
* src/main/Main.java : Execution file that takes the input file and generates the AADL code as an output
* src/main/Visitor.java : Compiler that does the conversion of parsed text input to corresponding AADL code 
