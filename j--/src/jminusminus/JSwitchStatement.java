// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package jminusminus;

import static jminusminus.CLConstants.*;

import java.util.ArrayList;

/**
 * The AST node for an if-statement.
 */

class JSwitchStatement extends JStatement {

    /** Test expression. */
    private JExpression condition;
    
    private ArrayList<JBlock> switchBlocks;
    private ArrayList<String> cases;
    
    /**
     * Construct an AST node for a switch-statement given its line number, the test
     * expression, the consequent, and the alternate.
     * 
     * @param line
     *            line in which the if-statement occurs in the source file.
     * @param condition
     *            the switch test expression.
     
     */

    public JSwitchStatement(int line, JExpression condition, JSwitchBlock body) {
        super(line);
        this.condition = condition;
        this.cases = body.cases;
        this.switchBlocks = body.switchBlocks;
    }

    /**
     * Analyzing the if-statement means analyzing its components and checking
     * that the test is boolean.
     * 
     * @param context
     *            context in which names are resolved.
     * @return the analyzed (and possibly rewritten) AST subtree.
     */

    public JStatement analyze(Context context) {
       // condition.type().mustMatchExpected(line(), Type.BOOLEAN);
        //switchBody = (JSwitchBlock)switchBody.analyze(context);
        
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
        
    	
    	
    	String elseLabel = output.createLabel();
        String endLabel = output.createLabel();
      //  condition.codegen(output, elseLabel, false);
      //  thenPart.codegen(output);
      //  if (elsePart != null) {
      //      output.addBranchInstruction(GOTO, endLabel);
     //   }
     //   output.addLabel(elseLabel);
     //   if (elsePart != null) {
     //       elsePart.codegen(output);
     //       output.addLabel(endLabel);
     //   }
    }

    /**
     * @inheritDoc
     */

    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JSwitchStatement line=\"%d\">\n", line());
        p.indentRight();
        p.printf("<TestExpression>\n");
        p.indentRight();
        condition.writeToStdOut(p);
        p.indentLeft();
        p.printf("</TestExpression>\n");
        p.printf("<CaseClause>\n");
        p.indentRight();
        for(int i =0; i < cases.size(); i++){
            p.printf("<CaseClause "+cases.get(i)+" >\n");
            p.indentRight();
            switchBlocks.get(i).writeToStdOut(p);
            p.indentLeft();
            p.printf("</CaseClause "+cases.get(i)+" >\n");
        }
  
        p.indentLeft();
      
        p.indentLeft();
        p.printf("</JSwitchStatement>\n");
    }

}
