package cfml.parsing.cfmentat.tag;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.IOException;

import net.htmlparser.jericho.Source;

import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.ParserRuleReturnScope;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.junit.Before;
import org.junit.Test;

import cfml.parsing.cfscript.ANTLRNoCaseReaderStream;
import cfml.parsing.cfscript.CFAssignmentExpression;
import cfml.parsing.cfscript.CFExpression;
import cfml.parsing.cfscript.CFScriptLexer;
import cfml.parsing.cfscript.CFScriptParser;
import cfml.parsing.cfscript.CFScriptTree;
import cfml.parsing.cfscript.poundSignFilterStream;
import cfml.parsing.cfscript.poundSignFilterStreamException;
import cfml.parsing.cfscript.sourceReader;
import cfml.parsing.cfscript.script.CFScriptStatement;

/**
 * 
 */

/**
 * @author denny
 * 
 */
public class TestAntlrParser {
	private static final String sourceUrlString = "file:test/data/tag/attribute/simpleTests.xml";
	private Source fSource;
	private CFExpression cfexpression;
	private CFScriptStatement scriptstatement;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
	}
	
	@Test
	public void testCFScriptStatement() {
		CFScriptStatement scriptStatement;
		String script = "var x = 1; y = 5; createObject('java','java.lang.String'); createObject('java','java.lang.String');";
		char[] scriptWithEndTag = script.toCharArray();
		
		try {
			poundSignFilterStream psfstream = new poundSignFilterStream(new CharArrayReader(scriptWithEndTag));
			ANTLRNoCaseReaderStream input = new ANTLRNoCaseReaderStream(psfstream); // +
			// "</CFSCRIPT>")
			// )
			// );
			
			CFScriptLexer lexer = new CFScriptLexer(input);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			CFScriptParser parser = new CFScriptParser(tokens);
			ParserRuleReturnScope r = parser.scriptBlock();
			CommonTree tree = (CommonTree) r.getTree();
			
			CommonTreeNodeStream nodes = new CommonTreeNodeStream(tree);
			nodes.setTokenStream(tokens);
			CFScriptTree p2 = new CFScriptTree(nodes);
			scriptStatement = p2.scriptBlock();
			
			// find special cases of "#varName#"="value";
			sourceReader sr = new sourceReader(new BufferedReader(new CharArrayReader(script.toCharArray())));
			scriptStatement.checkIndirectAssignments(sr.getLines());
			
		} catch (RecognitionException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (poundSignFilterStreamException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCFExpression() {
		CFExpression expression = cfexpression.getCFExpression("a=\"a\" & \"wee\"");
		assertTrue(expression instanceof CFAssignmentExpression);
	}
	
}
