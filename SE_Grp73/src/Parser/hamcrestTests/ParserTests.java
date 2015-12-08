package Parser.hamcrestTests;

import static org.hamcrest.MatcherAssert.assertThat;
import org.hamcrest.*;

import org.junit.Test;

import Parser.Expression;
import Parser.PostfixParser;
import junit.framework.TestCase;


public class ParserTests extends TestCase {

	public void simpleExpression() {
		String[][] exprs = {
				{"a", "b", "|"},
				{"a"},
		};
		
		for (String[] strings : exprs) {
			Expression e = null;
			e = PostfixParser.process(strings);
			assertThat("Sollte nicht null sein", e, notNullValue());
		}
	}
	
	public void complexExpression() {
		
	}
	
	@Test
	public void simpleInvalidExpression() {
		String[] invalidExprOne = {};
		assertThat(PostfixParser.process(invalidExprOne), equalTo(null));
		String[] invalidExprTwo = {"|"};
		assertThat(PostfixParser.process(invalidExprTwo), equalTo(null));
		String[] invalidExprThree = {"&", "|"};
		assertThat(PostfixParser.process(invalidExprThree), equalTo(null));
	}
	
	public void complexInvalidExpression() {
		
	}
}
