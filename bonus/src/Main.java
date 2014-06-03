import java.io.*;
import java.util.Scanner;

import symbols.*;

public class Main {

    public static void main(String[] args) {

        Expression testExpr =
               new BinaryExpression(
                   new BinaryExpression(
                          new BinaryExpression(
                                 new ConstantExpression( 1 ),
                                 SymbolTypes.PLUS,
                                 new ConstantExpression( 2 ) ),
                          SymbolTypes.EXP,
                          new ConstantExpression( 3 ) ),
                   SymbolTypes.MINUS,
                   new BinaryExpression(
                          new ConstantExpression( 4 ),
                          SymbolTypes.MULT,
                          new ConstantExpression( 5 ) ) );
         
                 
         testExpr.prettyInfix();         System.out.println(); 
         testExpr.prettyPostfix();       System.out.println(); 
         testExpr.prettyPrefix();        System.out.println();

         System.out.println(testExpr.getValue());
         
         Parser p = new Parser( new InputStreamReader(System.in) );
         try {
			Expression e = p.parse();
			e.prettyInfix();
			System.out.println(" = " + e.getValue());
		} catch (SyntaxError e) {
			e.printStackTrace();
		}
    }
}
