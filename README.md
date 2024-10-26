# Java Compiler Construction Project

This repository contains the source code for a basic compiler in Java. The compiler can scan, parse, and analyze a custom language with unique syntax conventions. It demonstrates key compiler construction principles, such as tokenization (lexical analysis), syntax analysis, and error handling.

## Table of Contents

1. [Project Overview](#project-overview)
2. [Features](#features)
3. [Getting Started](#getting-started)
4. [Usage](#usage)
5. [Code Structure](#code-structure)
6. [Future Enhancements](#future-enhancements)

## Project Overview

This compiler project is developed in Java to handle a custom-defined syntax for simple programming structures like assignments, loops, and conditional statements. The code includes three primary components:

- **Lexer (comp.java)**: Scans the source code and breaks it down into tokens, which represent the smallest units of meaning, like keywords, operators, and identifiers.
- **Parser (Parser.java)**: Analyzes the token stream for correct syntax structure, identifying statements and structures such as loops, conditions, and assignments.
- **Token Representation (Token.java)**: Defines the structure of tokens, categorizing each with a `type` and `value`.

## Features

- **Lexical Analysis**: Recognizes identifiers, integers, double numbers, keywords, operators, comments, and string literals.
- **Syntax Analysis**: Validates and processes language structures including assignment, if-else, while, for, switch-case, and print statements.
- **Error Handling**: Provides clear syntax error messages for unexpected tokens, missing braces, and invalid expressions.

## Getting Started

### Prerequisites

- **Java 8+**
- **JDK** and **JRE** installed
- Any Java IDE (such as IntelliJ IDEA, Eclipse) or a text editor with Java support

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/<username>/<repo_name>.git
Usage
Modify the filename variable in comp.java to point to your source file for lexical analysis (e.g., test1.txt).
Run the main method in comp.java to perform lexical and syntax analysis on the input file.

The compiler will display tokens for each lexeme identified and indicate successful or erroneous syntax analysis in the output.
# Code Structure

## comp.java:
 Main file for lexical analysis.
## scan():
 Reads and tokenizes each line of the input file.
## evaluate():
 Categorizes lexemes into appropriate token types (e.g., identifier, keyword, integer).
## Parser.java: 
Handles syntax analysis.
## parse():
 Processes the list of tokens to identify and validate statements.
## statement():
 Recognizes and calls functions for different structures, like assignment and control statements.
## Helper methods:
 Validates expressions and syntax rules, such as checking for matching parentheses and braces.
## Token.java: 
Represents each token with type and value attributes.
## DFA Implementation
The code contains a DFA to manage state transitions for identifiers and numeric values. This DFA model aids in identifying and categorizing each lexeme effectively.

# Future Enhancements
Add support for nested loops and conditionals.
Implement additional data types and type checking.
Expand error reporting to include line numbers and more detailed context.
Integrate semantic analysis to validate meaning in addition to structure.


