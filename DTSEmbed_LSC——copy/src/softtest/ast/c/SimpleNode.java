/* Generated By:JJTree: Do not edit this line. SimpleNode.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=true,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package softtest.ast.c;

import java.util.*;

import org.jaxen.*;

import softtest.cfg.c.VexNode;
import softtest.symboltable.c.*;
import softtest.tools.c.jaxen.*;

public class SimpleNode implements Node {

	protected Node parent;

	protected Node[] children;

	protected int id;

	protected Object value;

	protected CParser parser;

	protected String image="";

	protected ArrayList<String> OperatorTypes = new ArrayList<String>();

	protected int beginLine = -1;

	protected int endLine;

	protected int beginFileLine = -1;

	protected int endFileLine;

	protected int beginColumn = -1;

	protected int endColumn;
	
	/**auto-2012-06-11*/
	/*
	 * protected String fileName;
	 */
	public String fileName;
	/**auto-end*/

	//protected String fileName;
	
	public boolean[] forChild = new boolean[3];
	
	protected Scope scope;
	
	protected List<Node> funcDefinitionList;
	
	/**
	 * 加入NSH中相关模式
	 */
	// added by pan
	protected ArrayList<String> images = new ArrayList(3);
	
	public String getImagesString(){
		String str="";
		for(int i=0;i<images.size();i++){
			str=str+","+images.get(i);
		}
		if(str.length()>0)
			str=str.substring(1);
		return str;
	}
	// added by pan

	public SimpleNode(int i) {
		id = i;
	}

	public SimpleNode(CParser p, int i) {
		this(i);
		parser = p;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	public String getImage() {
		return image;
	}
	
    public void setScope(Scope scope) {
        this.scope = scope;
    }

    public Scope getScope() {
        if (scope == null && parent!=null) {
            return ((SimpleNode) parent).getScope();
        }
        return scope;
    }
	
	public void setOperatorType(String str)
	{
		this.OperatorTypes.add(str);
	}
	
	public String getOperators(){
		StringBuffer buff=new StringBuffer();
		for(String s:OperatorTypes){
			buff.append(s+" ");
		}
		return buff.toString().trim();
	}

	public ArrayList<String> getOperatorType()
	{
		return this.OperatorTypes;
	}

	//ZYS:有时间看看什么原理	3.12
	public void jjtOpen() {
		if (beginLine == -1 && parser.token.next != null) {
			beginLine = parser.token.next.beginLine;
			beginColumn = parser.token.next.beginColumn;
			
			// added by qipeng
			beginFileLine = parser.token.next.beginFileLine;
			
			fileName = parser.token.next.filename.trim();
			fileName = fileName.replace("\"", "");
		}
	}

	public void jjtClose() {
		if (beginLine == -1 && (children == null || children.length == 0))
		{
			beginColumn = parser.token.beginColumn;
		}
		if (beginLine == -1)
		{
			beginLine = parser.token.beginLine;
		}
		endLine = parser.token.endLine;
		endColumn = parser.token.endColumn;
		endFileLine = parser.token.endFileLine;
	}

	public void jjtSetParent(Node n) {
		parent = n;
	}

	public Node jjtGetParent() {
		return parent;
	}

	public void jjtAddChild(Node n, int i) {
		if (children == null) {
			children = new Node[i + 1];
		} else if (i >= children.length) {
			Node c[] = new Node[i + 1];
			System.arraycopy(children, 0, c, 0, children.length);
			children = c;
		}
		children[i] = n;
	}

	public Node jjtGetChild(int i) {
		return children[i];
	}

	public int jjtGetNumChildren() {
		return (children == null) ? 0 : children.length;
	}

	public void jjtSetValue(Object value) {
		this.value = value;
	}

	public Object jjtGetValue() {
		return value;
	}

	/** Accept the visitor. **/
	public Object jjtAccept(CParserVisitor visitor, Object data) {
		return visitor.visit(this, data);
	}

	/** Accept the visitor. **/
	public Object childrenAccept(CParserVisitor visitor, Object data) {
		if (children != null) {
			for (int i = 0; i < children.length; ++i) {
				children[i].jjtAccept(visitor, data);
			}
		}
		return data;
	}

	public String toString() {
		if (CParser.getType().equalsIgnoreCase("gcc"))
			return softtest.ast.gccparser.c.CParser_GCCTreeConstants.jjtNodeName[id];
		else if (CParser.getType().equalsIgnoreCase("keil"))
			return softtest.ast.keilparser.c.CParser_KeilTreeConstants.jjtNodeName[id];
		else
			return null;
	}

	public String toString(String prefix) {
		return prefix + toString();
	}

	public void dump(String prefix) {
		System.out.println(toString(prefix));
		if (children != null) {
			for (int i = 0; i < children.length; ++i) {
				SimpleNode n = (SimpleNode) children[i];
				if (n != null) {
					n.dump(prefix + " ");
				}
			}
		}
	}

	public int getBeginColumn() {
		return beginColumn;
	}

	public void setBeginColumn(int beginColumn) {
		this.beginColumn = beginColumn;
	}

	public int getBeginFileLine() {
		return beginFileLine;
	}

	public void setBeginFileLine(int beginFileLine) {
		this.beginFileLine = beginFileLine;
	}

	public int getBeginLine() {
		return beginLine;
	}

	public void setBeginLine(int beginLine) {
		this.beginLine = beginLine;
	}

	public int getEndColumn() {
		return endColumn;
	}

	public void setEndColumn(int endColumn) {
		this.endColumn = endColumn;
	}

	public int getEndFileLine() {
		return endFileLine;
	}

	public void setEndFileLine(int endFileLine) {
		this.endFileLine = endFileLine;
	}

	public int getEndLine() {
		return endLine;
	}

	public void setEndLine(int endLine) {
		this.endLine = endLine;
	}

	public String getFileName() {
		return fileName;
	}
	
    public Node getFirstChildOfType(Class childType) {
        return getFirstChildOfType(childType, this);
    }
    
    public Node getFirstChildInstanceofType(Class childType) {
        return getFirstChildInstanceofType(childType, this);
    }
    
    //added by cmershen,2016.5.3
    public List<Node> findChildOfTypes(Class[] childTypes) {
		List<Node> list = new ArrayList<Node>();
		for(Class type:childTypes) {
			list.addAll(findChildrenOfType(type));
		}
		return list;
	}
    public boolean isLeaf() {
    	return children==null||children.length==0;
    }
    
    private Node getFirstChildInstanceofType(Class childType, Node node) {
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            Node n = node.jjtGetChild(i);
            if (n != null) {
                if (childType.isInstance(n))
                    return n;
                Node n2 = getFirstChildInstanceofType(childType, n);
                if (n2 != null)
                    return n2;
            }
        }
        return null;
    }

    private Node getFirstChildOfType(Class childType, Node node) {
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            Node n = node.jjtGetChild(i);
            if (n != null) {
                if (n.getClass().equals(childType))
                    return n;
                Node n2 = getFirstChildOfType(childType, n);
                if (n2 != null)
                    return n2;
            }
        }
        return null;
    }
    //2010.12.02 liuli
    public List<Node> getfuncDefinitionList(SimpleNode temp){
    	if(funcDefinitionList == null){
    		funcDefinitionList = temp.findChildrenOfType(ASTFunctionDefinition.class);	
    	}
    	return funcDefinitionList;
    }
    /**
     * Traverses up the tree to find the first parent instance of type parentType
     *
     * @param parentType class which you want to find.
     * @return Node of type parentType.  Returns null if none found.
     */
    public Node getFirstParentOfType(Class parentType) {
        Node parentNode = jjtGetParent();
        while (parentNode != null && parentNode.getClass() != parentType) {
            parentNode = parentNode.jjtGetParent();
        }
        return parentNode;
    }

    /**
     * Traverses up the tree to find all of the parent instances of type parentType
     *
     * @param parentType classes which you want to find.
     * @return List of parentType instances found.
     */
    public List getParentsOfType(Class parentType) {
        List<Node> parents = new ArrayList<Node>();
        Node parentNode = jjtGetParent();
        while (parentNode != null) {
            if (parentNode.getClass() == parentType) {
                parents.add(parentNode);
            }
            parentNode = parentNode.jjtGetParent();
        }
        return parents;
    }
    public List<Node> findChildrenOfType(Class targetType) {
        List<Node> list = new ArrayList<Node>();
        findChildrenOfType(targetType, list);
        return list;
    }
    
    public void findChildrenOfType(Class targetType, List<Node> results) {
        findChildrenOfType(this, targetType, results);
    }
    //寻找叶子节点,add by cmershen,2016.9.20
	public List<Node> findLeafChildrenOfType(Class targetType) {
        List<Node> list = new ArrayList<Node>();
        findChildrenOfType(targetType, list);
        List<Node> list2 = new ArrayList<Node>();
        for(Node node:list) {
        	if(!((SimpleNode)node).isLeaf())
        		list2.add(node);
        }
        list.removeAll(list2);
		return list;
	}
    private void findChildrenOfType(Node node, Class targetType, List<Node> results) {
        if (node.getClass().equals(targetType)) {
            results.add(node);
        }

        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            Node child = node.jjtGetChild(i);
            if (child.jjtGetNumChildren() > 0) {
                findChildrenOfType(child, targetType, results);
            } else {
                if (child.getClass().equals(targetType)) {
                    results.add(child);
                }
            }
        }
    }
    
    
    public final boolean containsChildOfType(Class type) {
        return !findChildrenOfType(type).isEmpty();
    }
    //add by zhouhb
    public final boolean containsParentOfType(Class type) {
        return !getParentsOfType(type).isEmpty();
    }

    public List findChildNodesWithXPath(String xpathString) throws JaxenException {
        return new BaseXPath(xpathString, new DocumentNavigator()).selectNodes(this);
    }
     
	public List findXpath(String xPath){
		List evaluationResults = null;
		try {
			XPath xpath = new BaseXPath(xPath, new DocumentNavigator());
			evaluationResults = xpath.selectNodes(this);
		} catch (JaxenException e) {
			e.printStackTrace();
			throw new RuntimeException("xpath error");
		}
		return evaluationResults;
	}
	
	public final List findDirectChildOfType(Class type) {
		List<Node> results = new ArrayList<Node>();
		for (int i = 0; i < jjtGetNumChildren(); i++) {
			Node child = jjtGetChild(i);
			if (child.getClass().equals(type)) {
				results.add(child);
			}
		}
		return results;	
	}
	
	//dongyk 20120415 获得当前节点第index（index从0开始）个类型为type的直接孩子节点
	public final Node findDirectChildOfType(Class type,int index) {
		int num=0;
		for (int i = 0; i < jjtGetNumChildren(); i++) {
			Node child = jjtGetChild(i);
			if (child.getClass().equals(type)) {
				if(num==index)
				{
					return child; 
				}
				else
				{
					num++;
				}
			}
		}
		return null;	
	}
	

	/** 获得第一个类型为targetType的直接孩子节点 */
	public Object getFirstDirectChildOfType(Class targetType) {
		List list = findDirectChildOfType(targetType);
		Object target = null;
		if (!list.isEmpty()) {
			target = list.get(0);
		}
		return target;
	}
	
	/** 查找第一个parentType的祖先，但是搜索不超过searchend以上，searchend必须为当前节点得祖先 */
	public Node getFirstParentOfType(Class parentType,Node searchend) {
        Node parentNode = jjtGetParent();
        while (parentNode != searchend && parentNode.getClass() != parentType) {
            parentNode = parentNode.jjtGetParent();
        }
        if(parentNode==searchend&&parentNode.getClass() != parentType){
        	return null;
        }
        return parentNode;
    }
	
	/** 获得第类型为targetType的单支子孙节点 */
	public Object getSingleChildofType(Class targetType){
		Object target=null;
		if(getClass().equals(targetType)){
			return this;
		}
		if(jjtGetNumChildren()==1){
			return ((SimpleNode)jjtGetChild(0)).getSingleChildofType(targetType);
		}
		return target;
	}
	
	/** 获得第类型为targetType的单支子孙节点,ASTCastExpression可以有两个孩子 */
	public Object getSingleOrCastChildofType(Class targetType){
		Object target=null;
		if(getClass().equals(targetType)){
			return this;
		}
		if(jjtGetNumChildren()==1){
			return ((SimpleNode)jjtGetChild(0)).getSingleOrCastChildofType(targetType);
		}
		if(this instanceof ASTCastExpression){
			return ((SimpleNode)jjtGetChild(1)).getSingleOrCastChildofType(targetType);
		}
		return target;
	}
	
	/** 获得第一个类型为targetType的单支祖先节点 */
	public Object getSingleParentofType(Class targetType){
		Node parentNode = jjtGetParent();
        while (parentNode != null && parentNode.getClass() != targetType && parentNode.jjtGetNumChildren()==1) {
            parentNode = parentNode.jjtGetParent();
        }
        if(parentNode!=null&&parentNode.jjtGetNumChildren()==1&&parentNode.getClass() == targetType){
        	return parentNode;
        }else{
        	return null;
        }
	}
	
    /**
     * 获得第一个类型为parentTypes数组中元素之一类型的祖先节点
     * @param parentTypes 要查找的可能类型数组
     * @return 第一个类型出现在parentTypes中的祖先节点
     */
    public Node getFirstParentOfTypes(Class[] parentTypes) {
        Node parentNode = jjtGetParent();
        out:while (parentNode != null ) {
        	for(Class type:parentTypes){
        		if(parentNode.getClass() == type){
        			break out;
        		}
        	}
            parentNode = parentNode.jjtGetParent();
        }
        return parentNode;
    }
	
	/** 判断ancestor是否为当前节点自己或当前节点的祖先 */
	public boolean isSelOrAncestor(SimpleNode ancestor) {
		boolean bret = false;
		SimpleNode parentNode = this;
		while (parentNode != null) {
			if (parentNode == ancestor) {
				bret = true;
				break;
			}
			parentNode = (SimpleNode)parentNode.jjtGetParent();
		}
		return bret;
	}
	
	public Node getLastSibling(){
		Node parent = jjtGetParent();
		if(parent!=null){
			int index = -1;
			for(int ret = 0 ;ret < parent.jjtGetNumChildren() ; ++ret){
				if(parent.jjtGetChild(ret)==this){
					index = ret;
					break;
				}
			}
			if (index == 0 || index == -1) {
				return null;
			} else {
				return parent.jjtGetChild(index-1);
			}
		}		
		return null;
	}
	
	public Node getNextSibling(){
		Node parent = jjtGetParent();
		if(parent!=null){
			boolean find=false;
			for(int ret = 0 ;ret < parent.jjtGetNumChildren() ; ++ret){
				if(parent.jjtGetChild(ret)==this){
					find=true;					
				} else if (find) {
					return parent.jjtGetChild(ret);
				}
			}
		}		
		return null;
	}
	
	public Node getPrevSibling(){
		Node parent = jjtGetParent();
		if(parent!=null){
			boolean find=false;
			int ret = 0;
			for(ret =0 ;ret < parent.jjtGetNumChildren() ; ++ret){
				if(parent.jjtGetChild(ret)==this){
					find=true;				
					break;
				} 
			}
			if (find&&ret>0) {
				return parent.jjtGetChild(ret-1);
			}
		}		
		return null;
	}
	
	public int getIndexOfParent(){
		int ret =-1;
		Node parent=jjtGetParent();
		if(parent!=null){
			boolean find=false;
			for(ret = 0 ;ret<parent.jjtGetNumChildren();ret++ ){
				if(parent.jjtGetChild(ret)==this){
					find=true;
					break;
				}
			}
			if(!find){
				ret=-1;
			}
		}
		
		return ret;
	}
	
	/** 控制流图节点列表，一个语法树节点可能对应多个控制流节点，请参看ControlFlowVisitor代码，通常第一个节点是主要头节点 */
	private List<VexNode> vexlist = null;
	
	/** 添加控制流节点*/
	public void addVexNode(VexNode vex) {
		if (vexlist == null) {
			vexlist = new LinkedList<VexNode>();
		}
		vexlist.add(vex);
	}
    
    /**获得第一个控制流节点*/
    public VexNode getFirstVexNode(){
    	VexNode vex=null;
    	if(!vexlist.isEmpty()){
    		vex=vexlist.get(0);
    	}
    	return vex;
    }

	public List<VexNode> getVexlist() {
		return vexlist;
	}

	public void setVexlist(List<VexNode> vexlist) {
		this.vexlist = vexlist;
	}
	
	 public VexNode getCurrentVexNode(){
		 List<VexNode> list=getCurrentVexList();
		 if(list!=null&&!list.isEmpty()){
			 return list.get(0);
		 }
		 return null;
	 }
	 
	 /**获得当前的控制流节点列表，通常为当前正在处理的语法树或其祖先对应的控制流节点*/
	 public List<VexNode> getCurrentVexList() {
		if(vexlist!=null&&!vexlist.isEmpty()){
			return vexlist;
		}
		if(parent!=null){
			return ((SimpleNode) parent).getCurrentVexList();
		}
		return null;
	}
	 
	 /** 返回实际能实际计算的节点 */
		public SimpleNode getConcreteNode() {
			if (this instanceof ASTSelectionStatement) {
				if (this.getImage().equals("if")) {
					return (SimpleNode)this.jjtGetChild(0);
				} else if (this.getImage().equals("switch")) {
					return (SimpleNode)this.jjtGetChild(0);
				}
				return (SimpleNode) this.jjtGetChild(0);
			} else if (this instanceof ASTIterationStatement) {
				if (this.getImage().equals("while")) {
					return (SimpleNode)this.jjtGetChild(0);
				} else if (this.getImage().equals("do")) {
					return null;
				} else if (this.getImage().equals("for")) {
					List results = this.findDirectChildOfType(ASTExpression.class);
					if (!results.isEmpty()) {
						return (SimpleNode) results.get(0);
					}else{
						return null;
					}
				}
				return null;
			} else if (this instanceof ASTDeclaration && !((SimpleNode)jjtGetParent()).getImage().equals("for")) {
				return (SimpleNode) this.jjtGetParent();
			} 
			return this;
		}
		
	//zys
		public VariableNameDeclaration getVariableNameDeclaration() {
			SimpleNode n = this;
			
			//zys:2010.4.15	如果是数组类型声明 code unsigned char nn[14];  
			
			while (n.jjtGetNumChildren() == 1) {
				//chh 对于数组声明，不考虑其子节点，直接返回数组变量
				if(n instanceof ASTDirectDeclarator&&n.getOperators()!="[")
				{
					if (!(((ASTDirectDeclarator) n).getDecl() instanceof VariableNameDeclaration)) {
						return null;
					}
					return (VariableNameDeclaration)((ASTDirectDeclarator) n).getDecl();
				}
				//chh end
				else n = (SimpleNode) n.jjtGetChild(0);
			}
			if(n instanceof ASTPostfixExpression&&n.jjtGetNumChildren()>0) {
				n=(SimpleNode) n.jjtGetChild(n.jjtGetNumChildren()-1);
			}
			if (n.jjtGetNumChildren() == 0 && n instanceof ASTPrimaryExpression) {
				return ((ASTPrimaryExpression) n).getVariableDecl();
			}
			if (n.jjtGetNumChildren() == 0 && n instanceof ASTDirectDeclarator) {
				if (!(((ASTDirectDeclarator) n).getDecl() instanceof VariableNameDeclaration)) {
					return null;
				}
				return (VariableNameDeclaration)((ASTDirectDeclarator) n).getDecl();
			}
			// Add the support for array, added by wangyf
			if (n.jjtGetNumChildren() >= 2 && n instanceof ASTDirectDeclarator) {
				//liuli：此处存在两种情况，一种为(*x)[4]情况时，一种为二维数组时,二维数组时未处理
				if(n.jjtGetChild(0) instanceof ASTDeclarator)
					return (VariableNameDeclaration) ((ASTDeclarator) ((ASTDirectDeclarator) n)
						.jjtGetChild(0)).getVariableNameDeclaration();	
				//zys:处理多维数组的情况，如int a[5][5];
				if(n.jjtGetChild(0) instanceof ASTConstantExpression)
					return (VariableNameDeclaration) ((ASTDirectDeclarator)n).getDecl();
			}
			// added end
			
			//zys:2010.3.21
			if (n.jjtGetNumChildren() == 2 && n instanceof ASTParameterDeclaration) {
				ASTDirectDeclarator direct=(ASTDirectDeclarator) (n.getFirstChildOfType(ASTDirectDeclarator.class));
				if(direct!=null){
					return (VariableNameDeclaration) direct.getVariableNameDeclaration();
				}
			}
			
			//zys:2010.4.15	添加指针变量支持
			if (n.jjtGetNumChildren() == 2 && n instanceof ASTDeclarator) {
				ASTDirectDeclarator direct=(ASTDirectDeclarator) (n.getFirstChildOfType(ASTDirectDeclarator.class));
				if(direct!=null){
					return (VariableNameDeclaration) direct.getVariableNameDeclaration();
				}
			}
			return null;
		}
				
		
		
		/** 获取当前节点到其所有叶子结点的最大深度，一般用于判断if while条件的复杂程度，以决定在区间运算时是否
		 * 有必要继续遍历其孩子结点*/
		public int getDescendantDepth()
		{
			int depth=0;
			if(jjtGetNumChildren()==0){
				depth=0;
			}else{
				for(int i=0;i<jjtGetNumChildren();i++){
					int temp=((SimpleNode)jjtGetChild(i)).getDescendantDepth()+1;
					if(temp>depth)
						depth=temp;
				}
			}
			return depth;
		}
		
		/**auto-2012-06-11*/
		public List<SimpleNode> bodys = null;
		public List<SimpleNode> clauses = null;
		public List<SimpleNode> thens = null;
		public List<SimpleNode> elses = null;
		/**auto-end*/
		
		/** 判断当前节点和node间是否存在局部函数声明节点 ，node为当前节点祖先 */
		public boolean hasLocalMethod(SimpleNode node)
		{
			SimpleNode current = this;
			while (current != null && current != node)
			{
				if ((current instanceof ASTFunctionDefinition))
				{
					return true;
				}
				current = (SimpleNode) current.jjtGetParent();
			}
			return false;
		}

		
		//add by lsc 2018/11/12
		public CParser getParser() {
			return this.parser;
		}
}

/* JavaCC - OriginalChecksum=f7d763dac10510333921105f021f70f3 (do not edit this line) */
