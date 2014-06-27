/*
 * $Id: Command.java 4 2011-12-13 15:37:54Z ldilley $
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

import java.util.StringTokenizer;

// Note that ALL command methods must be public in this class so that getMethod() can see them from the CommandParser class
public class Command
{
  public static String cd(String args, User user)
  {
    if(args.matches("^[ \t]*cd[ \t]*$"))
      return "Not implemented.\n";
    return LinSim.EMPTY_STRING;
  }

  public static String env(String args, User user)
  {
    if(args.matches("^[ \t]*env[ \t]*$"))
    {
      String output="";
      output+="USER=" + user.getUsername() + '\n';
      output+="LOGNAME=" + user.getUsername() + '\n';
      output+="PATH=" + user.getPath() + '\n';
      output+="MAIL=/var/mail" + user.getUsername() + '\n';
      output+="SHELL=/bin/bash\n";
      output+="TERM=vt100\n";
      output+="LANG=en_US.UTF-8\n";
      output+="HOSTTYPE=x86_64-linux\n";
      output+="VENDOR=unknown\n";
      output+="MACHTYPE=x86_64\n";
      output+="PWD=" + user.getCwd() + '\n';
      // ToDo: Populate primary here
      output+="HOST=" + System.hostname + '\n';
      output+="EDITOR=nano\n";
      output+="PAGER=more\n";
      output+="BLOCKSIZE=K\n";
      return output;
    }
    else if(args.matches("^[ \t]*env .*$"))
      return "Not implemented\n";
    return LinSim.EMPTY_STRING;
  }

  public static String exit(String args, User user)
  {
    // ToDo: handle exiting from su or other interactive shells later
    if(args.matches("^[ \t]*exit[ \t]*$"))
      java.lang.System.exit(0);
    else if(args.matches("^[ \t]*exit .*$"))
      return "This command takes no arguments. Just use \"exit\"!\n";
    return LinSim.EMPTY_STRING;
  }

  public static String hostname(String args, User user)
  {
    if(args.matches("^[ \t]*hostname[ \t]*$"))
      return System.hostname + "\n";
    else if(args.matches("^[ \t]*hostname[ \t]+.*$"))
    {
      StringTokenizer token=new StringTokenizer(args, " \t");
      token.nextToken();
      String newHostname=token.nextToken();
      if(token.hasMoreElements())
      {
        return "hostname: too many arguments.\n";
      }
      System.hostname=newHostname;
      Auth.user.updatePrompt();
      return LinSim.EMPTY_STRING;
    }
    return LinSim.EMPTY_STRING;
  }

  public static String logout(String args, User user)
  {
    if(args.matches("^[ \t]*logout[ \t]*$"))
      java.lang.System.exit(0);
    else if(args.matches("^[ \t]*logout .*$"))
      return "This command takes no arguments. Just use \"logout\"!\n";
    return LinSim.EMPTY_STRING;
  }

  public static String ls(String args, User user)
  {
    if(args.matches("^[ \t]*ls[ \t]+-la[ \t]*$") || args.matches("^[ \t]*ls[ \t]+-al[ \t]*$"))
    {
      String output="";
      for(int i=0; i<Directory.directories.size(); i++)
        output+=Directory.directories.get(i).getTemporaryPermissions() + "  " +
                Directory.directories.get(i).getOwner() + "  " +
                Directory.directories.get(i).getGroup() + "\t" +
                Directory.directories.get(i).getAbsolutePath() + "\n";
      return output;
    }
    else if(args.matches("^[ \t]*ls[ \t]+-l[ \t]*$"))
    {
      String output="";
      for(int i=0; i<Directory.directories.size(); i++)
        output+=Directory.directories.get(i).getAbsolutePath() + "\n";
      return output;
    }
    else if(args.matches("^[ \t]*ls[ \t]*$"))
    {
      String listing="";
      String output="";
      for(int i=0; i<Directory.directories.size(); i++)
      {
        if(listing.length()+Directory.directories.get(i).getAbsolutePath().length()>=75)
        {
          listing+="\n";
          output+=listing;
          listing="";
        }
        listing+=Directory.directories.get(i).getAbsolutePath() + "\t";       
      }
      return output+listing + "\n";
    }
    else if(args.matches("^[ \t]*ls .*$"))
      return "Use \"ls\", \"ls -l\", or \"ls -la\" instead.\n";
    return LinSim.EMPTY_STRING;
  }

  public static String ps(String args, User user)
  {
    if(args.matches("^[ \t]*ps[ \t]*$"))
    {
      String output="UID\tPID\tCMD\n";
      for(int i=0; i<System.processes.size(); i++)
      {
        output+=System.processes.elementAt(i).getUser() + "\t";
        output+=System.processes.elementAt(i).getPid() + "\t";
        output+=System.processes.elementAt(i).getCommand() + "\n";
      }
      return output;
    }
    return LinSim.EMPTY_STRING;
  }

  public static String uname(String args, User user)
  {
    if(args.matches("^[ \t]*uname[ \t]*$"))
      return "Linux\n";
    else if(args.matches("^[ \t]*uname[ \t]+-a[ \t]*$"))
      return "Linux host 2.6.39.1-JVM #1 SMP Mon Jun 13 23:17:34 UTC 2011 JVM JVM JVM GNU/Linux\n";
    else if(args.matches("^[ \t]*uname .*$"))
      return "Use \"uname\" or \"uname -a\".\n";
    return LinSim.EMPTY_STRING;
  }
}
