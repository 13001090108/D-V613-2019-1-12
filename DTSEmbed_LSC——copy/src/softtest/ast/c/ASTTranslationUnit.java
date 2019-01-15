/* Generated By:JJTree: Do not edit this line. ASTTranslationUnit.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=true,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package softtest.ast.c;

import softtest.cfg.c.Graph;
import softtest.callgraph.c.CGraph;

public
class ASTTranslationUnit extends SimpleNode {
	//liuli 2010.4.13
  CGraph cgraph;
  
  public ASTTranslationUnit(int id) {
    super(id);
  }

  public ASTTranslationUnit(CParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(CParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
  
	public CGraph getCGraph() {
		return cgraph;
	}

	public void setCGraph(CGraph cgraph) {
		this.cgraph = cgraph;
	}
}
/* JavaCC - OriginalChecksum=303b0198747558aeceb82f2067891947 (do not edit this line) */
