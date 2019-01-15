/* Generated By:JJTree: Do not edit this line. ASTPRAGMA.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=true,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package softtest.ast.c;

import java.util.ArrayList;
import java.util.List;

public class ASTPRAGMA extends SimpleNode {
	List<String> pragmaInfo=new ArrayList<String>();
  public ASTPRAGMA(int id) {
    super(id);
  }

  public ASTPRAGMA(CParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(CParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }

public List<String> getPragmaInfo() {
	return pragmaInfo;
}

public String pragmaInfoStringList()
{
	StringBuffer sb=new StringBuffer();
	for(String s:pragmaInfo)
	{
		sb.append(s+" ");
	}
	return sb.toString();
}

public void setPragmaInfo(String pragmaInfo) {
	this.pragmaInfo.add(pragmaInfo);
}
}
/* JavaCC - OriginalChecksum=304581c810812a02918a5d08fa2580bb (do not edit this line) */