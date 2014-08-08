package cfml.parsing.cfmentat.tag;

import net.htmlparser.jericho.EndTagType;
import net.htmlparser.jericho.Source;
import net.htmlparser.jericho.StartTag;
import net.htmlparser.jericho.StartTagTypeGenericImplementation;
import net.htmlparser.jericho.Tag;

final class StartTagTypeCFMail extends StartTagTypeGenericImplementation {
	protected static final StartTagTypeCFMail INSTANCE = new StartTagTypeCFMail();
	
	private StartTagTypeCFMail() {
		super("CFML mail", "<cfmail", ">", EndTagType.NORMAL, true, true, true);
	}
	
	protected Tag constructTagAt(final Source source, final int pos) {
		final StartTag startTag = (StartTag) super.constructTagAt(source, pos);
		if (startTag == null)
			return null;
		// A CFML script element requires the attribute language="php".
		// if
		// (!"php".equalsIgnoreCase(startTag.getAttributes().getValue("language")))
		// return null;
		return startTag;
	}
}
