/* Generated By:JJTree: Do not edit this line. ASTEnumerator.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=true,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package softtest.ast.c;

import softtest.symboltable.c.Type.CType;

public class ASTEnumerator extends SimpleNode {
	CType type = null;

	public CType getType() {
		return type;
	}

	public void setType(CType type) {
		this.type = type;
	}

	public ASTEnumerator(int id) {
		super(id);
	}

	public ASTEnumerator(CParser p, int id) {
		super(p, id);
	}

	/** Accept the visitor. **/
	public Object jjtAccept(CParserVisitor visitor, Object data) {
		return visitor.visit(this, data);
	}
}
/* JavaCC - OriginalChecksum=c929122a6d3f4c3b58e9a3b73569f778 (do not edit this line) */