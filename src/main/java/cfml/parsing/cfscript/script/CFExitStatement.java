/* 
 * Copyright (C) 2000 - 2008 TagServlet Ltd
 *
 * This file is part of the BlueDragon Java Open Source Project.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */

package cfml.parsing.cfscript.script;

import cfml.parsing.cfscript.CFExpression;

public class CFExitStatement extends CFParsedStatement implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private CFExpression methodArg;
	
	public CFExitStatement(org.antlr.runtime.Token t, CFExpression _method) {
		super(t);
		methodArg = _method;
	}
	
	@Override
	public String Decompile(int indent) {
		if (methodArg != null) {
			return "exit \"" + methodArg + "\"";
		} else {
			return "exit";
		}
	}
}
