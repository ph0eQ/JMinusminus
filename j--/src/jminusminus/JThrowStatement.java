// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package jminusminus;

import jminusminus.CLEmitter;
import static jminusminus.CLConstants.*;

/**
 * The AST node for an if-statement.
 */

class JThrowStatement extends JStatement {

    /** Test expression. */
    private JExpression condition;


    /**
     * Construct an AST node for an if-statement given its line number, the test
     * expression, the consequent, and the alternate.
     * 
     * @param line
     *            line in which the if-statement occurs in the source file.
     * @param condition
     *            test expression.
     */

    public JThrowStatement(int line, JExpression condition) {
        super(line);
        this.condition = condition;
       
    }

    /**
     * A
     * 
     * @param context
     *            context in which names are resolved.
     * @return the analyzed (and possibly rewritten) AST subtree.
     */

    public JStatement analyze(Context context) {
    	condition = condition.analyze(context);
        return this;
    }

    /**
     * Code generation for an if-statement. We generate code to branch over the
     * consequent if !test; the consequent is followed by an unconditonal branch
     * over (any) alternate.
     * 
     * @param output
     *            the code emitter (basically an abstraction for producing the
     *            .class file).
     */

    public void codegen(CLEmitter output) {
        condition.codegen(output);
    	output.addNoArgInstruction(ATHROW);
    }

    /**
     * @inheritDoc
     */

    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JThrowStatement line=\"%d\">\n", line());
        p.indentRight();
        p.printf("<TestExpression>\n");
        p.indentRight();
        condition.writeToStdOut(p);
        p.indentLeft();
        p.printf("</TestExpression>\n");
        
        p.printf("</JThrowStatement>\n");
    }

}
