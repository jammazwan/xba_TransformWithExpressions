package jammazwan.xba;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import jammazwan.entity.City;
import jammazwan.entity.TextData;
import jammazwan.util.HoldContextOpenUntilDone;

public class XbaTest extends CamelSpringTestSupport {

	@Override
	protected AbstractXmlApplicationContext createApplicationContext() {
		return new ClassPathXmlApplicationContext("META-INF/spring/camel-context.xml");
	}

	
	 @Test
	 public void testConstant() throws Exception {
	
	 MockEndpoint mock = getMockEndpoint("mock:assert");
	 mock.expectedBodiesReceived("replacement value");
	 String reply = template.requestBody("direct:testConstant", "seed",
	 String.class);
	 mock.assertIsSatisfied();
	 }
	
	 @Test
	 public void testPrepend() throws Exception {
	
	 MockEndpoint mock = getMockEndpoint("mock:assert");
	 mock.expectedBodiesReceived("prepend seed");
	 String reply = template.requestBody("direct:testPrepend", "seed",
	 String.class);
	
	 mock.assertIsSatisfied();
	 }
	
	 @Test
	 public void testAppend() throws Exception {
	
	 MockEndpoint mock = getMockEndpoint("mock:assert");
	 mock.expectedBodiesReceived("seed appended");
	 String reply = template.requestBody("direct:testAppend", "seed",
	 String.class);
	
	 mock.assertIsSatisfied();
	 }
	
	 @Test
	 public void testHeader() throws Exception {
	
	 MockEndpoint mock = getMockEndpoint("mock:assert");
	 mock.expectedBodiesReceived("Transform with headerValue done.");
	 String reply = template.requestBodyAndHeader("direct:testHeader", "seed",
	 "headerNameHere", "headerValue",
	 String.class);
	
	 mock.assertIsSatisfied();
	 }
	
	 @Test
	 public void testDateNow() throws Exception {
	 String reply = template.requestBody("direct:testDateNow", "seed",
	 String.class);
	 assertTrue(reply.startsWith("201"));
	 assertTrue(reply.endsWith("-seed"));
	 }
	
	 @Test
	 public void testId() throws Exception {
	
	 MockEndpoint mock = getMockEndpoint("mock:assert");
	 mock.expectedMessageCount(1);
	 // too lazy to do a legitimate test, see log to inspect Id
	 String reply = template.requestBody("direct:testId", "seed",
	 String.class);
	 mock.assertIsSatisfied();
	 }
	
	 @Test
	 public void testSysenv() throws Exception {
	
	 MockEndpoint mock = getMockEndpoint("mock:assert");
	 mock.expectedMessageCount(1);;
	 String reply = template.requestBody("direct:testSysenv", "seed",
	 String.class);
	 assertTrue(reply.contains(":"));
	 mock.assertIsSatisfied();
	 }
	
	 @Test
	 public void testRandom() throws Exception {
	
	 MockEndpoint mock = getMockEndpoint("mock:assert");
	 mock.expectedMessageCount(1);
	 String reply = template.requestBody("direct:testRandom", "seed",
	 String.class);
	 Integer.valueOf(reply); // throws exception if not integer
	 mock.assertIsSatisfied();
	 }
	
	 @Test
	 public void testObjectGetter() throws Exception {
	
	 City city = new City(7, "Austin, TX", "USA", 1000000, 2385, 1474);
	 MockEndpoint mock = getMockEndpoint("mock:assert");
	 mock.expectedBodiesReceived(city.getCity());
	 String reply = template.requestBody("direct:testObjectGetter", city,
	 String.class);
	 mock.assertIsSatisfied();
	
	 }
	
	 @Test
	 public void testSecondLine() throws Exception {
	
	 String valid2 = "A stitch in time saves nine";
	 List<String> lineData = new ArrayList<String>();
	 lineData.add("Every good boy does fine");
	 lineData.add(valid2);
	 lineData.add("A penny saved is a penny earned");
	 lineData.add("No time like the present");
	 TextData textData = new TextData(lineData);
	 MockEndpoint mock = getMockEndpoint("mock:assert");
	 mock.expectedBodiesReceived(valid2);
	 String reply = template.requestBody("direct:testSecondLine", textData,
	 String.class);
	
	 mock.assertIsSatisfied();
	 }
	
	 @Test
	 public void testNextToLastLine() throws Exception {
	
	
	 String valid3 = "A stitch in time saves nine";
	 List<String> lineData = new ArrayList<String>();
	 lineData.add("Every good boy does fine");
	 lineData.add("A penny saved is a penny earned");
	 lineData.add(valid3);
	 lineData.add("No time like the present");
	 TextData textData = new TextData(lineData);
	 MockEndpoint mock = getMockEndpoint("mock:assert");
	 mock.expectedBodiesReceived(valid3);
	 String reply = template.requestBody("direct:testNextToLastLine",
	 textData, String.class);
	
	 mock.assertIsSatisfied();
	 }
	

	@Test
	public void testMessageHistory() throws Exception {

		MockEndpoint mock = getMockEndpoint("mock:assert");
		mock.expectedMessageCount(1);
		String reply = template.requestBody("direct:testMessageHistory", "seed", String.class);
		assertTrue(reply.contains("[transform[simple{${messageHistory}}]"));
		mock.assertIsSatisfied();
	}

}
