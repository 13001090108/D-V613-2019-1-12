/* Generated By:JJTree: Do not edit this line. ASTIterationStatement.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=true,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package softtest.ast.c;

public
class ASTIterationStatement extends SimpleNode {
  public ASTIterationStatement(int id) {
    super(id);
  }

  public ASTIterationStatement(CParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(CParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=eb73614cf999ab7af037f1280b9b6700 (do not edit this line) */