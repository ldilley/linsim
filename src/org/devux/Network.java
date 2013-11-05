/*
 * $Id: Network.java 3 2011-10-19 00:17:37Z ldilley $
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

import java.util.ArrayList;

public class Network
{
  Network()
  {
    // default constructor
  }

  Network(String ipAddress, String netmask)
  {
    this.ipAddress=ipAddress;
    this.netmask=netmask;
  }

  Network(String ipAddress, String netmask, String broadcast)
  {
    this.ipAddress=ipAddress;
    this.netmask=netmask;
    this.broadcast=broadcast;
  }

  private boolean isUp;
  private String ipAddress;
  private String macAddress;
  private String netmask;
  private String broadcast;
  private ArrayList<String> routes=new ArrayList<String>();

  public boolean getStatus()
  {
    return isUp;
  }

  public void setStatus(boolean status)
  {
    isUp=status;
    if(isUp)
      // Add route to routing table if it does not exist.
     ;
    return;
  }

  public String getIpAddress()
  {
    return ipAddress;
  }

  public void setIpAddress(String ipAddress)
  {
    this.ipAddress=ipAddress;
    return;
  }

  public String getMacAddress()
  {
    return macAddress;
  }

  public void setMacAddress(String macAddress)
  {
    this.macAddress=macAddress;
    return;
  }

  public String getNetmask()
  {
    return netmask;
  }

  public void setNetmask(String netmask)
  {
    this.netmask=netmask;
    return;
  }

  public String getBroadcast()
  {
    return broadcast;
  }

  public void setBroadcast(String broadcast)
  {
    this.broadcast=broadcast;
    return;
  }
}
