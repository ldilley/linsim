/*
 * $Id: User.java 14 2013-11-05 10:22:22Z ldilley $
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

// This class holds user properties
public class User
{
  private String username="user"; // ToDo: populate from /etc/passwd
  private String cwd="/"; // current working directory -- ToDo: populate as user navigates filesystem
  private String path="/bin:/usr/bin:/usr/local/bin";
  private String prompt=username + '@' + System.hostname + ':' + cwd + "$ ";

  public String getCwd()
  {
    return cwd;
  }

  public String getPath()
  {
    return path;
  }

  public String getPrompt()
  {
    return prompt;
  }

  public String getUsername()
  {
    return username;
  }

  public void setUsername(String username)
  {
    this.username=username;
    return;
  }

  public void updatePrompt()
  {
    prompt=username + '@' + System.hostname + ':' + cwd + "$ ";
    return;
  }
}
