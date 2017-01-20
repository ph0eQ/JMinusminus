package jminusminus;

import static jminusminus.CLConstants.*;

abstract class JConditionalExpression extends JExpression {
	protected JExpression condition;
	protected JExpression thenPart;
	protected JExpression elsePart;
	
	public JConditionalExpression(int line, JExpression condition, JExpression thenPart, JExpression elsePart){
		super(line);
		this.condition = condition;
		this.thenPart = thenPart;
		this.elsePart = elsePart;
	}
	/**
     * @inheritDoc
     */
	
    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JConditionalExpression line=\"%d\">\n", line());
        p.indentRight();
        p.printf("<TestExpression>\n");
        p.indentRight();
        condition.writeToStdOut(p);
        p.indentLeft();
        p.printf("</TestExpression>\n");
        p.printf("<ThenClause>\n");
        p.indentRight();
        thenPart.writeToStdOut(p);
        p.indentLeft();
        p.printf("</ThenClause>\n");
        if (elsePart != null) {
            p.printf("<ElseClause>\n");
            p.indentRight();
            elsePart.writeToStdOut(p);
            p.indentLeft();
            p.printf("</ElseClause>\n");
        }
        p.indentLeft();
        p.printf("</JConditionalExpression>\n");
    }
	
}
class JTernaryOp extends JConditionalExpression {
	
	public JTernaryOp(int line, JExpression condition, JExpression thenPart, JExpression elsePart){
			super(line, condition, thenPart, elsePart);
	}
	/**
     * Analyzing the if-statement means analyzing its components and checking
     * that the test is boolean.
     * 
     * @param context
     *            context in which names are resolved.
     * @return the analyzed (and possibly rewritten) AST subtree.
     */

    public JExpression analyze(Context context) {
        condition = (JExpression) condition.analyze(context);
        condition.type().mustMatchExpected(line(), Type.BOOLEAN);
        thenPart = (JExpression) thenPart.analyze(context);
        elsePart = (JExpression) elsePart.analyze(context);
        
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
        condition.codegen(output, elseLabel, false);
      //  if (elsePart != null) {
        thenPart.codegen(output);
        output.addBranchInstruction(GOTO, endLabel);
       // }
        output.addLabel(elseLabel);
       // if (elsePart != null) {
        elsePart.codegen(output);
        output.addBranchInstruction(GOTO, endLabel);
        output.addLabel(endLabel);
        //}
    }
	
}
