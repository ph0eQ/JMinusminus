// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package jminusminus;

import static jminusminus.CLConstants.*;

import java.util.ArrayList;

/**
 * The AST node for an try-catch-finally statement
 */

class JTryCatchFinallyStatement extends JStatement {

	/** Test expression. */
	JStatement tryBlock;
	ArrayList<JStatement> catches;
	ArrayList<JVariableDeclaration> exceptions;
	JStatement finallyBlock;


	/**
	 * Construct an AST node for a try-catch-finally statement given its line number, the test
	 * expression, the consequent, and the alternate.
	 * 
	 * @param line
	 *            line in which the if-statement occurs in the source file.
	 * @param condition
	 *            the switch test expression.

	 */

	public JTryCatchFinallyStatement(int line, JStatement tryBlock, 
			ArrayList<JStatement> catches, ArrayList<JVariableDeclaration> exceptions, JStatement finallyBlock) {
		super(line);
		this.tryBlock = tryBlock;
		this.catches = catches;
		this.exceptions = exceptions;
		this.finallyBlock = finallyBlock;
	}
	/**
	 * Construct an AST node for a try-catch-finally statement given its line number, the test
	 * expression, the consequent, and the alternate.
	 * 
	 * @param line
	 *            line in which the if-statement occurs in the source file.
	 * @param condition
	 *            the switch test expression.

	 */

	public JTryCatchFinallyStatement(int line, JStatement tryBlock, 
			ArrayList<JStatement> catches, ArrayList<JVariableDeclaration> exceptions) {
		super(line);
		this.tryBlock = tryBlock;
		this.catches = catches;
		this.exceptions = exceptions;
		this.finallyBlock = null;
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
		p.printf("<JTryCatchFinallyStatement line=\"%d\">\n", line());
		p.indentRight();
		p.printf("<TryBlock>\n");
		p.indentRight();
		tryBlock.writeToStdOut(p);
		p.indentLeft();
		p.printf("</TryBlock>\n");
		p.printf("<CatchBlock>\n");
		p.indentRight();
		for(int i =0; i < catches.size(); i++){
			p.printf("<CatchClause >\n");
			p.indentRight();

			p.printf("<ExceptionClause>\n");
			p.indentRight();
			exceptions.get(i).writeToStdOut(p);
			p.indentLeft();
			p.printf("</ExceptionClause>\n");

			p.indentRight();
			p.printf("<CatchClauseBlock>\n");
			catches.get(i).writeToStdOut(p);
			p.indentLeft();
			p.print("</CatchClauseBlock>\n");
			p.indentLeft();

			p.printf("</CatchClause >\n");
		}
		p.indentLeft();
		p.printf("</CatchBlock>\n");
		if(finallyBlock != null){
			p.indentLeft();
			p.printf("<FinallyBlock>\n");
			p.indentRight();
			p.printf("<FinallyClause>\n");
			p.indentRight();
			finallyBlock.writeToStdOut(p);
			p.indentLeft();
			p.printf("</FinallyClause>\n");
			p.indentLeft();
			p.printf("</FinallyBlock>\n");
		}
		p.indentLeft();
		p.printf("</JTryCatchFinallyStatement>\n");
	}

}

