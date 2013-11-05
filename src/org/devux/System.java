/*
 * $Id: System.java 4 2011-12-13 15:37:54Z ldilley $
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

import java.util.Vector;

// This class takes care of holding system state information
public class System
{
  static final byte STDIN=0;
  static final byte STDOUT=1;
  static final byte STDERR=2;
  static final int MIN_PID=500; // PIDs <500 are reserved
  static final int MAX_PID=32768;
  static final short MAX_PID_TRIES=10000;
  static String hostname="host";
  static Vector<Process> processes=new Vector<Process>();

  static int getFreePID()
  {
    boolean notFree=true;
    int freePID=MIN_PID+(int)(Math.random()*((MAX_PID-MIN_PID)+1));
    short tries=0;

    while(notFree)
    {
      for(short i=0; i<processes.size(); i++)
      {
        if(freePID!=processes.get(i).getPid() && i!=processes.size()-1)
          continue;
        if(freePID!=processes.get(i).getPid() && i==processes.size()-1)
        {
          notFree=false; // we've got a free PID
          break;
        }
        else // we've got a collision
        {
          if(tries>=MAX_PID_TRIES)
          {
            if(!LinSim.graphicalMode)
            {
              java.lang.System.out.println("Exhausted attempts to find a free PID. Kill off some processes or restart the system.");
              return -1; // be sure to handle negative PIDs in the caller so a process is not added with one
            }
            // handle GUI mode output later
          }
          freePID++;
          tries++;
          break;
        }
      } // end for
    } // end while
    return freePID;
  }
}
