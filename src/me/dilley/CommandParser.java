/*
 * $Id: CommandParser.java 4 2011-12-13 15:37:54Z ldilley $
 * LinSim - The Linux Simulator
 * Copyright (C) 2011 Lloyd S. Dilley <lloyd@dilley.me>
 * https://github.com/ldilley/linsim
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

/**
 * @author Lloyd S. Dilley
 */

package me.dilley;

import java.lang.reflect.Method;
import java.util.StringTokenizer;

class CommandParser
{
  static String parse(String input, User user)
  {
    // handle carriage return and other empty input
    if(input.length()==0 || input==null || input.matches("^[ \t]*$"))
      return LinSim.EMPTY_STRING;

    // set the base command
    StringTokenizer token=new StringTokenizer(input, " \t");
    String baseCommand=token.nextToken();

    // Let's use some introspection magic...
    try
    {
      Class<?> cmdClass=Class.forName("org.linsim.Command");
      // Note that ALL commands in the Command class must be public in order to be seen by getMethod() below
      Method method=cmdClass.getMethod(baseCommand, String.class);
      Object output=method.invoke(null, input, user);
      if(!LinSim.graphicalMode)
        java.lang.System.out.print(output.toString());
      // ToDo: use setText() for GUI later
    }
    catch(Throwable e)
    {
      return baseCommand + ": command not found.\n";
    }
    // after the command finishes execution, return to the input loop
    return LinSim.EMPTY_STRING;
  }
}
