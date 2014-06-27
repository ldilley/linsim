/*
 * $Id: Console.java 4 2011-12-13 15:37:54Z ldilley $
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

class Console
{
  Console()
  {
    // default constructor
  }

  Console(boolean enableColors)
  {
    this.enableColors=enableColors;
  }

  static boolean enableColors=false; // for terminal color support later

  public static void launch()
  {
    User user=new User();
    Auth.consoleLogin(user);

    java.io.Console console=java.lang.System.console();
    boolean done=false;

    while(!done)
    {
      String input=console.readLine(Auth.user.getPrompt());
      java.lang.System.out.print(CommandParser.parse(input, user));
    }
  }
}
