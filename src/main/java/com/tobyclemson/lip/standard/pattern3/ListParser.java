package com.tobyclemson.lip.standard.pattern3;

import com.tobyclemson.lip.standard.pattern2.Lexer;
import com.tobyclemson.lip.standard.pattern2.ListLexer;

public class ListParser extends Parser {
    public ListParser(Lexer input) {
        super(input);
    }

    /** list : '[' elements ']' ; // match bracketed list */
    public void list() {
        match(ListLexer.LBRACK);
        elements();
        match(ListLexer.RBRACK);
    }

    /** elements : element (',' element)* ; */
    void elements() {
        element();
        while (lookahead.type == ListLexer.COMMA) {
            match(ListLexer.COMMA);
            element();
        }
    }

    /** element : NAME | list ; // element is NAME or nested list */
    void element() {
        if (lookahead.type == ListLexer.NAME) {
            match(ListLexer.NAME);
        } else if (lookahead.type == ListLexer.LBRACK) {
            list();
        } else {
            throw new Error("expecting name or list; found " + lookahead);
        }
    }
}
