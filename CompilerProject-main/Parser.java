import java.util.List;

public class Parser {
    private List<Token> tokens;
    private int currentTokenIndex = 0;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public void parse() throws Exception {
        while (currentTokenIndex < tokens.size()) {
            statement();  // Parse each statement
        }
    }

    // Parse a statement (assignment, loop, switch, logical expression, etc.)
    private void statement() throws Exception {
        Token token = getCurrentToken();

        if (token.type.equals("identifier")) {
            assignment();  // Parse assignment statements
        } else if (token.value.equals("while")) {
            whileLoop();  // Parse while loops
        } else if (token.value.equals("for")) {
            forLoop();  // Parse for loops
        } else if (token.value.equals("if")) {
            ifStatement();  // Parse if-else statements
        } else if (token.value.equals("switch")) {
            switchStatement();  // Parse switch statements
        } else if (token.value.equals("print")) {
            printStatement();  // Parse print statements
        } else if (token.type.equals("integer") || token.type.equals("double")) {
            expression();}  // Handle numeric expressions
//        } else {
//            throw new Exception("Syntax Error: Unexpected token " + token.value);
//        }
    }

    private void forLoop() {
		// TODO Auto-generated method stub
		
	}

	private void printStatement() {
		// TODO Auto-generated method stub
		
	}

	private void whileLoop() {
		// TODO Auto-generated method stub
		
	}

	// Parse an assignment like ?i = 0;
    private void assignment() throws Exception {
        Token identifier = getCurrentToken();
        if (!identifier.type.equals("identifier")) {
            throw new Exception("Syntax Error: Expected identifier, but found " + identifier.value);
        }
        advance(); // Move to '='

        Token equalsToken = getCurrentToken();
        if (!equalsToken.value.equals("=")) {
            throw new Exception("Syntax Error: Expected '=', but found " + equalsToken.value);
        }
        advance(); // Move to value (e.g., 0 or ?var)

        expression(); // The right-hand side expression (e.g., integer, identifier, etc.)

        Token semicolon = getCurrentToken();
        if (!semicolon.value.equals(";")) {
            throw new Exception("Syntax Error: Expected ';' at the end of assignment");
        }
        advance(); // Move to the next statement
    }

    // Parse if-else statement: if (condition) { ... } else { ... }
    private void ifStatement() throws Exception {
        Token ifToken = getCurrentToken();
        if (!ifToken.value.equals("if")) {
            throw new Exception("Syntax Error: Expected 'if'");
        }
        advance();  // Move to the condition part

        Token openParen = getCurrentToken(); // should be '('
        if (!openParen.value.equals("(")) {
            throw new Exception("Syntax Error: Expected '(' after 'if'");
        }
        advance();  // Move to the condition expression

        expression();  // Parse the condition inside parentheses

        Token closeParen = getCurrentToken(); // should be ')'
        if (!closeParen.value.equals(")")) {
            throw new Exception("Syntax Error: Expected ')' after condition");
        }
        advance();  // Move to the if block

        Token openBrace = getCurrentToken(); // should be '{'
        if (!openBrace.value.equals("{")) {
            throw new Exception("Syntax Error: Expected '{' to start if block");
        }
        advance();  // Parse the if block

        // Parse the body of the if block
        while (!getCurrentToken().value.equals("}")) {
            statement();  // Process statements inside the if block
        }
        advance();  // Move past the closing brace of the if block

        // Check if there is an else block
        if (getCurrentToken().value.equals("else")) {
            advance();  // Move to else

            Token elseOpenBrace = getCurrentToken(); // should be '{'
            if (!elseOpenBrace.value.equals("{")) {
                throw new Exception("Syntax Error: Expected '{' to start else block");
            }
            advance();  // Parse the else block

            while (!getCurrentToken().value.equals("}")) {
                statement();  // Process statements inside the else block
            }
            advance();  // Move past the closing brace of the else block
        }
    }

    // Parse a switch statement: switch (expression) { case value: ... }
    private void switchStatement() throws Exception {
        Token switchToken = getCurrentToken();
        if (!switchToken.value.equals("switch")) {
            throw new Exception("Syntax Error: Expected 'switch'");
        }
        advance();  // Move to the condition part

        Token openParen = getCurrentToken(); // should be '('
        if (!openParen.value.equals("(")) {
            throw new Exception("Syntax Error: Expected '(' after 'switch'");
        }
        advance();  // Move to the expression

        expression();  // Parse the expression inside parentheses

        Token closeParen = getCurrentToken(); // should be ')'
        if (!closeParen.value.equals(")")) {
            throw new Exception("Syntax Error: Expected ')' after expression");
        }
        advance();  // Move to the switch block

        Token openBrace = getCurrentToken(); // should be '{'
        if (!openBrace.value.equals("{")) {
            throw new Exception("Syntax Error: Expected '{' to start switch block");
        }
        advance();  // Parse the switch block

        // Parse cases
        while (!getCurrentToken().value.equals("}")) {
            Token caseToken = getCurrentToken();

            if (caseToken.value.equals("case")) {
                advance();  // Move to case value

                expression();  // Parse case value

                Token colon = getCurrentToken(); // should be ':'
                if (!colon.value.equals(":")) {
                    throw new Exception("Syntax Error: Expected ':' after case value");
                }
                advance();  // Move to the case body

                // Parse the body of the case
                while (!getCurrentToken().value.equals("case") && !getCurrentToken().value.equals("default") && !getCurrentToken().value.equals("}")) {
                    statement();  // Process statements inside the case
                }
            } else if (caseToken.value.equals("default")) {
                advance();  // Move past 'default'

                Token colon = getCurrentToken(); // should be ':'
                if (!colon.value.equals(":")) {
                    throw new Exception("Syntax Error: Expected ':' after 'default'");
                }
                advance();  // Move to the default case body

                // Parse the body of the default case
                while (!getCurrentToken().value.equals("}")) {
                    statement();  // Process statements inside the default case
                }
            } else {
                throw new Exception("Syntax Error: Unexpected token " + caseToken.value);
            }
        }

        Token closeBrace = getCurrentToken(); // should be '}'
        if (!closeBrace.value.equals("}")) {
            throw new Exception("Syntax Error: Expected '}' to end switch block");
        }
        advance();  // Move to the next statement after the switch
    }

    // Other parsing methods remain the same (for, while, print, etc.)

    // Helper methods
    private Token getCurrentToken() {
        return tokens.get(currentTokenIndex);
    }

    private void advance() {
        currentTokenIndex++;
    }

    // Parse logical expressions or values (like ?i > 1 or ?j = 0)
    private void expression() throws Exception {
        Token token = getCurrentToken();

        // Handle identifiers, integers, or doubles
        if (!token.type.equals("identifier") && !token.type.equals("integer") && !token.type.equals("double")) {
            throw new Exception("Syntax Error: Invalid expression " + token.value);
        }
        advance(); // Move to the next token

        // Optionally handle comparison operators like >, <, >=, <=
        Token nextToken = getCurrentToken();
        if (isComparisonOperator(nextToken)) {
            advance();  // Move past the comparison operator
            expression();  // Parse the right-hand side of the comparison
        }
    }

    private boolean isComparisonOperator(Token token) {
        return token.value.equals(">") || token.value.equals("<") || token.value.equals(">=") || token.value.equals("<=");
    }
}