/*
 * $Id: Directory.java 2 2011-10-18 00:42:44Z ldilley $
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

// imports
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Directory
{
  public Directory()
  {
    // default constructor
  }

  // Implement custom constructor below to handle easy directory creation with mkdir
  //public Directory(...)
  //{
  //  ...
  //}

  /** The unique numerical identifier of the directory. */
  private int inode;
  /** The directory size in bytes. */
  private long size;
  /** The directory name. */
  private String name;
  /** The full path to the directory including its name. */
  private String absoPath;
  /** The user owner of the directory. */
  private String owner;
  /** The group owner of the directory. */
  private String group;
  /** The last time the directory was accessed. (Date + Time.toString()) */
  private String atime;
  /** The creation time of the directory. */
  private String ctime;
  /** The last time the directory was modified. */
  private String mtime;
  /** The directory permissions in bits as a string read in from the data file. */
  private String tmpPerms;
  /** The symbolic permissions of the directory. */
  private String symPerms;
  /** The permissions of the directory in bits. */
  private BitSet bitPerms;
  /** The contents of the directory. */
  private ArrayList contents;

  /**
   * Contains a list of Directory objects.
   * @see Directory
   */
  public static ArrayList<Directory> directories=new ArrayList<Directory>();

  /**
   * Gets the unique numerical identifier of the directory.
   * @return yep.
   */
  public int getInode()
  {
    return inode;
  }

  public void setInode(int inode)
  {
    this.inode=inode;
    return;
  }

  public long getSize()
  {
    return size;
  }

  public void setSize(long size)
  {
    this.size=size; // ToDo: calculate directory size
    return;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name=name;
    return;
  }

  public String getAbsolutePath()
  {
    return absoPath;
  }

  public void setAbsolutePath(String absoPath)
  {
    this.absoPath=absoPath;
    return;
  }

  public String getOwner()
  {
    return owner;
  }

  public void setOwner(String owner)
  {
    this.owner=owner;
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

  public String getAtime()
  {
    return atime;
  }

  public void setAtime(String atime)
  {
    this.atime=atime;
    return;
  }

  public String getCtime()
  {
    return ctime;
  }

  public void setCtime(String ctime)
  {
    this.ctime=ctime;
    return;
  }

  public String getMtime()
  {
    return mtime;
  }

  public void setMtime(String mtime)
  {
    this.mtime=mtime;
    return;
  }

  public String getTemporaryPermissions()
  {
    return tmpPerms;
  }

  public void setTemporaryPermissions(String tmpPerms)
  {
    this.tmpPerms=tmpPerms;
    return;
  }

  public String getSymbolicPermissions()
  {
    return symPerms;
  }

  public void setSymbolicPermissions(String symPerms)
  {
    this.symPerms=symPerms;
    return;
  }

  public BitSet getBitPermissions()
  {
    return bitPerms;
  }

  public void setBitPermissions(BitSet bitPerms)
  {
    this.bitPerms=bitPerms; // ToDo: copy/clone elements
    return;
  }

  public ArrayList getContents()
  {
    return contents; // ToDo: list each element
  }

  public void setContents(ArrayList contents)
  {
    this.contents=contents; // ToDo: copy/clone elements
    return;
  }

  // Reads the directory structure from a file on disk into memory
  public static void readDirectoryStructure() throws FileNotFoundException
  {
    try
    {
      int i=0; // counter
      Scanner dirFile=new Scanner(new FileReader("directories.dat"));
      while(dirFile.hasNext())
      {
        try
        {
          directories.add(new Directory());
          directories.get(i).setAbsolutePath(dirFile.next());
          directories.get(i).setTemporaryPermissions(dirFile.next());
          directories.get(i).setOwner(dirFile.next());
          directories.get(i).setGroup(dirFile.next());
          i++;
        }
        catch(InputMismatchException IME)
        {
          java.lang.System.out.println("The format of the directories.dat file is corrupt.");
          java.lang.System.exit(1);
        }
        catch(NoSuchElementException NSEE)
        {
          java.lang.System.out.println("The format of the directories.dat file is corrupt.");
          java.lang.System.exit(1);
        }
      } // end while
      dirFile.close(); // close data file
    } // end outer try
    catch(FileNotFoundException FNFE)
    {
      java.lang.System.out.println("The directories.dat file could not be found.");
      java.lang.System.exit(1);
    }
    //catch(IOException IOE)
    //{
    //  java.lang.System.out.println("The directories.dat file could not be read.");
    //  java.lang.System.exit(1);
    //}
    return;
  }
}
