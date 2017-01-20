package jminusminus;

import java.util.ArrayList;

import static jminusminus.CLConstants.*;


public class JForStatement extends JStatement {
	int line;
	protected boolean enhanced;
	//protected ArrayList<JVariableDeclarator> init;
	protected JVariableDeclaration init;
	//protected JStatement init;

	protected JExpression term;
	protected JStatement inc;
	protected JStatement body;
    /**
    * Basic for-statement for(init, term, inc){body}
    * Enhanced for-statement for( Type init: (Type) collec){body} using iterators
     * @param line line number
     * @param init initializer expression
     * @param term terminate expression
     * @param inc increment expression 
     * @param body the main body of the for statement
     */
	public JForStatement(int line, JVariableDeclaration init, JExpression term, JStatement inc, JStatement body){
		super(line);
		this.enhanced = inc == null ? true:false;
		this.init = init;
		this.term = term;
		this.inc = inc;
		this.body = body;
	}

	
	/**
     * Analysis involves analyzing the test, checking its type and analyzing the
     * body statement.
     * 
     * @param context
     *            context in which names are resolved.
     * @return the analyzed (and possibly rewritten) AST subtree.
     */

    public JForStatement analyze(Context context) {
    	if(enhanced){
    		//JStatement init_stm = init.analyze(context);
    		term = term.analyze(context);
    		//init.expr.type().mustMatchExpected(line(), collec.type());
    		body = (JStatement) body.analyze(context);
    	}else{
    		   		//JStatement init_stm =  init.analyze(context);
    		init.analyze(context);
    		term = (JExpression) term.analyze(context);
    		inc = (JStatement) inc.analyze(context);
    		//init.type().mustMatchExpected(line(), Type.INT);
    		term.type().mustMatchExpected(line(), Type.BOOLEAN);
    		//inc.expr.type().mustMatchExpected(line(), Type.INT);
    		body = (JStatement) body.analyze(context);
    		
    	}
    
        return this;
    }

    /**
     * Generate code for the while loop.
     * 
     * @param output
     *            the code emitter (basically an abstraction for producing the
     *            .class file).
     */

    public void codegen(CLEmitter output) {
        // Need two labels
        String test = output.createLabel();
        String out = output.createLabel();

        // Branch out of the loop on the test condition
        // being false
        //init.codegen(output);
    	init.codegen(output);
        output.addLabel(test);
        if(enhanced){
        	//code gen for the collection
        	//term.codegen(output, out, false);
        }else {
        	//code gen for terminate to branch out of loop on test condition
        	term.codegen(output, out, false);
        	inc.codegen(output);
        	// Codegen body
            body.codegen(output);
        	
       }
        

        // Unconditional jump back up to test
        output.addBranchInstruction(GOTO, test);

        // The label below and outside the loop
        output.addLabel(out);
    }

    /**
     * @inheritDoc
     */

    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JForStatement line=\"%d\">\n", line());
        p.indentRight();
        
        p.printf(!enhanced ? "<InitializerExpression>\n": "<IteratorExpression>\n");
        p.indentRight();
      //  for(JVariableDeclarator j : init){
        //	j.writeToStdOut(p);
       // }
        //init.writeToStdOut(p);
        p.indentLeft();
        p.printf(!enhanced ? "</InitializerExpression>\n": "</IteratorExpression>\n");
       
        p.printf(enhanced ? "<CollectionExpression>\n":  "<TerminatorExpression>\n");
        p.indentRight();
        if(enhanced){ term.writeToStdOut(p);} else { term.writeToStdOut(p);}
        p.indentLeft();
        p.printf(enhanced ? "</CollectionExpression>\n":  "</TerminatorExpression>\n");
       
        if(!enhanced){
        	p.printf( "<IncrementExpression>\n");
        	p.indentRight();
        	inc.writeToStdOut(p);
        	p.indentLeft();
        	p.printf( "</IncrementExpression>\n");
        }
        p.printf("<Body>\n");
        p.indentRight();
        if(body != null){
        	body.writeToStdOut(p);
        }
        p.indentLeft();
        p.printf("</Body>\n");
        p.indentLeft();
        p.printf("</JForStatement>\n");
    }
	
}
