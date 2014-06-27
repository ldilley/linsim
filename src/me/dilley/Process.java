/*
 * $Id: Process.java 2 2011-10-18 00:42:44Z ldilley $
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

public class Process
{
  Process()
  {
    // default constructor
  }

  Process(int pid, String command, String user)
  {
    this.pid=pid;
    this.command=command;
    this.user=user;
  }

  private int pid;
  private int ppid;
  private long rss; // resident set size
  private long vsz; // virtual memory size
  private float cpu; // can be used to calculate C column in ps -ef output also
  private float memory;
  private String args;
  private String command;
  private String user;
  private String group;
  private String startTime;
  private String time;
  private String tty;

  public int getPid()
  {
    return pid;
  }

  public void setPid(int pid)
  {
    this.pid=pid;
    return;
  }

  public int getParentPid()
  {
    return ppid;
  }

  public void setParentPid(int ppid)
  {
    this.ppid=ppid;
    return;
  }

  public long getRss()
  {
    return rss;
  }

  public void setRss(long rss)
  {
    this.rss=rss;
    return;
  }

  public long getVsz()
  {
    return vsz;
  }

  public void setVsz(long vsz)
  {
    this.vsz=vsz;
    return;
  }

  public float getCpu()
  {
    return cpu;
  }

  public void setCpu(float cpu)
  {
    this.cpu=cpu;
    return;
  }

  public float getMemory()
  {
   return memory;
  }

  public void setMemory(float memory)
  {
    this.memory=memory;
    return;
  }

  public String getArgs()
  {
    return args;
  }

  public void setArgs(String args)
  {
    this.args=args;
    return;
  }

  public String getCommand()
  {
    return command;
  }

  public void setCommand(String command)
  {
    this.command=command;
    return;
  }

  public String getUser()
  {
    return user;
  }

  public void setUser(String user)
  {
    this.user=user;
    return;
  }

  public String getGroup()
  {
    return group;
  }

  public void setGroup(String group)
  {
    this.group=group;
    return;
  }

  public String getStartTime()
  {
    return startTime;
  }

  public void setStartTime(String startTime)
  {
    this.startTime=startTime;
    return;
  }

  public String getTime()
  {
    return time;
  }

  public void setTime(String time)
  {
    this.time=time;
    return;
  }

  public String getTty()
  {
    return tty;
  }

  public void setTty(String tty)
  {
    this.tty=tty;
    return;
  }
}
