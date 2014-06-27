/*
 * $Id: Auth.java 4 2011-12-13 15:37:54Z ldilley $
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

import java.io.Console;

class Auth
{
  private static final int TIMEOUT=60;
  private static final int ATTEMPTS=3;
  private static final String DEFAULT_USER="root";
  private static final String DEFAULT_PASSWORD="secret";
  private static Console console;
  // User will eventually have to be non-static in multi-user mode (once networking is implemented)
  public static User user=new User();

  Auth()
  {
    // default constructor
  }

  public static void consoleLogin(User user)
  {
    int failures=0;
    boolean success=false;
    String username;
    char[] inputPassword;

    if(!LinSim.graphicalMode)
    {
      console=java.lang.System.console();
      while(!success)
      {
        if(failures>=ATTEMPTS)
        {
          java.lang.System.out.println("Locked out for " + TIMEOUT + " seconds.");
          try
          {
            Thread.sleep(TIMEOUT*1000);
          }
          catch(InterruptedException ie)
          {
            java.lang.System.exit(0);
          }
          failures=0;
        }
        username=console.readLine("\nLogin: ");
        inputPassword=console.readPassword("Password: ");
        String password=new String(inputPassword);
        // ToDo: Check passwd file for user/password information in a later release.
        if(username.equals(DEFAULT_USER) && password.equals(DEFAULT_PASSWORD))
        {
          user.setUsername(DEFAULT_USER);
          success=true;
          java.lang.System.out.println();
          break;
        }
        else
        {
          java.lang.System.out.println("Authentication failed!");
          failures++;
        }
      }
    }

    else
    {
      java.lang.System.out.println("GUI login not implemented.");
      java.lang.System.exit(0);
    }

    return;
  }
}
