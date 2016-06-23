package jammazwan.xba;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;

public class XbaRoutes extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("direct:testConstant").transform(constant("replacement value")).to("mock:assert");

		from("direct:testPrepend").transform(body().prepend("prepend ")).to("mock:assert");

		from("direct:testAppend").transform(body().append(" appended")).to("mock:assert");

		from("direct:testHeader").transform(simple("Transform with ${header.headerNameHere} done.")).to("mock:assert");

		from("direct:testDateNow").transform(simple("${date:now:yyyyMMdd}-${body} "));

		from("direct:testId").transform(simple("${id}-${body} ")).log("BODY WAS:\n\n${body}\n\n").to("mock:assert");

		from("direct:testSysenv").transform().constant("{{env:PATH}}").to("mock:assert");

		from("direct:testRandom").transform(simple("random(7)")).to("mock:assert");

		from("direct:testObjectGetter").transform(simple("${body.city}")).to("mock:assert");

		from("direct:testSecondLine").transform(simple("${body.lineData[1]}")).to("mock:assert");

		from("direct:testNextToLastLine").transform(simple("${body.lineData[last-1]}")).to("mock:assert");

		from("direct:testMessageHistory").transform().simple("${messageHistory}").log("\n\n${body}").to("mock:assert");
	}
}
