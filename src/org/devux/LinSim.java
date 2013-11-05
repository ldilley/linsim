/*
 * $Id: LinSim.java 2 2011-10-18 00:42:44Z ldilley $
 * LinSim - The Linux Simulator
 * Copyright (C) 2011 Lloyd S. Dilley <lloyd@dilley.me>
 * http://www.devux.org/projects/linsim/
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

package org.devux;

import java.io.FileNotFoundException;

public class LinSim
{
  static boolean graphicalMode=false;
  static final String VERSION="0.1a";
  static final String EMPTY_STRING="";

  public static void main(String[] args) throws FileNotFoundException
  {
    if(args.length>1 || (args.length==1 && !args[0].equals("-gui")))
    {
      java.lang.System.out.println("LinSim only accepts \"-gui\" as an argument which enables GUI mode.\n");
      java.lang.System.exit(1);
    }

    // Populate the virtual filesystem
    Directory.readDirectoryStructure();

    // Populate the process table with some common psuedo processes
    System.processes.addElement(new Process(1, "init", "root")); // init
    System.processes.addElement(new Process(1673, "/usr/sbin/rsyslogd", "root")); // rsyslogd
    System.processes.addElement(new Process(1740, "/usr/sbin/cron", "root")); // cron

    // Use GUI mode if requested
    if(args.length==1 && args[0].equals("-gui"))
    {
      graphicalMode=true;
      //GUI.init(); // do init stuff here
      GUI.launch();
    }
    else
    {
      // Use console mode by default
      Console.launch();
    }

    return;
  }
}
