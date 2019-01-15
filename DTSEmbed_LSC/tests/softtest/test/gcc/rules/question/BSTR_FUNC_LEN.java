package softtest.test.gcc.rules.question;

import java.util.Arrays;
import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import softtest.fsm.c.FSMLoader;
import softtest.fsm.c.FSMMachine;
import softtest.fsmanalysis.c.FSMAnalysisVisitor;
import softtest.test.c.rules.ModelTestBase;

@RunWith(Parameterized.class)
public class BSTR_FUNC_LEN extends ModelTestBase{
	public BSTR_FUNC_LEN(String source,String compiletype, String result)
	{
		super(source, compiletype, result);
	}
	
	@BeforeClass
	public static void setUpBaseChild()
	{
		fsmPath="softtest/rules/gcc/question/BSTR_FUNC_LEN-0.1.xml";
		FSMMachine fsm = FSMLoader.loadXML(fsmPath);
		fsm.setType("question");
		//ÿ�μ����Զ���ǰ�����һ��ԭ����fsms
		FSMAnalysisVisitor.clearFSMS();
		FSMAnalysisVisitor.addFSMS(fsm);
	}
	 @Parameters
	 public static Collection<Object[]> testcaseAndResults()
	 {
		 return Arrays.asList(new Object[][] {
	/////////////////  0   ///////////////////	
		            {
		            "#include<wtypes.h>"                                                   +"\n"+
		            "void bstr_len() {"                                                    +"\n"+
		            "	 wchar_t *a = L\"abc\";"                                               +"\n"+
		            "	 int l = SysStringLen(a); // defect"                                 +"\n"+
		            "}"                                                                    
		            ,
		            "gcc"
		            ,
		            "BSTR_FUNC_LEN"
		            ,
		            },

		 });
	 }
}
