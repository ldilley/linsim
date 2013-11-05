/*
 * $Id: Filesystem.java 6 2011-12-21 03:44:13Z ldilley $
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
 * @version $Revision: 14 $
 */

package org.devux;

// Imports
import java.util.BitSet; // Needed for file/directory permissions
import java.util.Vector; // Needed for storing directory objects and free inodes

class Filesystem
{
  // Constants
  public static final short DIRECTORY_SIZE=4096;
  public static final int MAX_INODES=4000000;

  /** The free inode pool. */
  private Vector<Integer> freeInodes=new Vector<Integer>();

  /** Default constructor. */
  public Filesystem()
  {
    // Null
  }

  /** Reads in the inode pool from disk or generates one if it does not exist. */
  private void initInodePool()
  {
    // ToDo: read inode information in from file on disk
    // If the inode data file cannot be read in from disk, initialize the pool with new values...
    for(int i=1; i<MAX_INODES; i++)
    {
      // ToDo: add friendly message to inform user what is going on
      freeInodes.addElement((int)i);
    }
  }

  /** This class represents both files and directories. */
  abstract class StoredObject
  {
    /** The name of the stored object. */
    protected String name;
    /** The unique numerical identifier of the stored object. */
    protected long inode;
    /** The size of the stored object in bytes. */
    protected long size;
    /** The full path to the stored object. */
    protected String absoPath;
    /** The user owner of the stored object. */
    protected String owner;
    /** The group owner of the stored object. */
    protected String group;
    /** The last time the stored object was accessed. (Date + Time.toString()) */
    protected String atime;
    /** The creation time of the stored object. */
    protected String ctime;
    /** The last time the stored object was modified. */
    protected String mtime;
    /** The permissions of the stored object in bits as a string read in from the data file. */
    protected String tmpPerms;
    /** The symbolic permissions of the stored object. */
    protected String symPerms;
    /** The permissions of the stored object in bits. */
    protected BitSet bitPerms;

    /** Default constructor. */
    public StoredObject()
    {
      // Null
    }

    /**
     * Gets the unique numerical identifier of the stored object.
     * @return the unique numerical identifier of the stored object.
     */
    public long getInode()
    {
      return inode;
    }

    /**
     * Sets the unique numerical identifier of the stored object.
     * @param inode the unique numerical identifier of the stored object.
     */
    public void setInode(long inode)
    {
      this.inode=inode;
      return;
    }

    /**
     * Gets the size of the stored object.
     * @return size of the stored object.
     */
    public long getSize()
    {
      // ToDo: calculate file size based on number of chars in a file for commands like ls
      return size;
    }

    /**
     * Sets the size of the stored object.
     * @param size the size of the stored object. This method can be used by commands such as
     * dd and touch to set arbitrary file sizes.
     */
    public void setSize(long size)
    {
      // ToDo: populate file with number of chars equating size
      this.size=size;
      return;
    }

    /**
     * Gets the name of the stored object.
     * @return the name of the stored object.
     */
    public String getName()
    {
      return name;
    }

    /**
     * Sets the name of the stored object.
     * @param name the name of the stored object.
     */
    public void setName(String name)
    {
      this.name=name;
      return;
    }

    /**
     * Gets the absolute path of the stored object.
     * @return the absolute path of the stored object.
     */
    public String getAbsolutePath()
    {
      return absoPath;
    }

    /**
     * Sets the absolute path of the stored object.
     * @param absoPath the absolute path of the stored object.
     */
    public void setAbsolutePath(String absoPath)
    {
      this.absoPath=absoPath;
      return;
    }

    /**
     * Gets the owner of the stored object.
     * @return the owner of the stored object.
     */
    public String getOwner()
    {
      return owner;
    }

    /**
     * Sets the owner of the stored object.
     * @param owner the owner of the stored object.
     */
    public void setOwner(String owner)
    {
      this.owner=owner;
      return;
    }

    /**
     * Gets the group of the stored object.
     * @return the group of the stored object.
     */
    public String getGroup()
    {
      return group;
    }

    /**
     * Sets the group of the stored object.
     * @param group the group of the stored object.
     */
    public void setGroup(String group)
    {
      this.group=group;
      return;
    }

    /**
     * Gets the access time of the stored object.
     * @return the access time of the stored object.
     */
    public String getAtime()
    {
      return atime;
    }

    /**
     * Sets the access time of the stored object.
     * @param atime the access time of the stored object.
     */
    public void setAtime(String atime)
    {
      this.atime=atime;
      return;
    }

    /**
     * Gets the create time of the stored object.
     * @return the create time of the stored object.
     */
    public String getCtime()
    {
      return ctime;
    }

    /**
     * Sets the create time of the stored object.
     * @param ctime the create time of the stored object.
     */
    public void setCtime(String ctime)
    {
      this.ctime=ctime;
      return;
    }

    /**
     * Gets the modify time of the stored object.
     * @return the modify time of the stored object.
     */
    public String getMtime()
    {
      return mtime;
    }

    /**
     * Sets the modify time of the stored object.
     * @param mtime the modify time of the stored object.
     */
    public void setMtime(String mtime)
    {
      this.mtime=mtime;
      return;
    }

    /**
     * Gets the temporary permissions of the stored object while it is being read in from
     * disk.
     * @return the temporary permissions of the stored object.
     */
    public String getTemporaryPermissions()
    {
      return tmpPerms;
    }

    /**
     * Sets the temporary permissions of the stored object.
     * @param tmpPerms the temporary permissions of the stored object.
     */
    public void setTemporaryPermissions(String tmpPerms)
    {
      this.tmpPerms=tmpPerms;
      return;
    }

    /**
     * Gets the symbolic permissions of the stored object.
     * @return the symbolic permissions of the stored object.
     */
    public String getSymbolicPermissions()
    {
      return symPerms;
    }

    /**
     * Sets the symbolic permissions of the stored object.
     * @param symPerms the symbolic permissions of the stored object.
     */
    public void setSymbolicPermissions(String symPerms)
    {
      this.symPerms=symPerms;
      return;
    }

    /**
     * Gets the permissions in bits of the stored object.
     * @return the permissions in bits of the stored object.
     */
    public BitSet getBitPermissions()
    {
      return bitPerms;
    }

    /**
     * Sets the permissions in bits of the stored object.
     * @param bitPerms the permissions in bits of the stored object.
     */
    public void setBitPermissions(BitSet bitPerms)
    {
      this.bitPerms=bitPerms; // ToDo: copy/clone elements
      return;
    }
  }

  /** This class represents directories */
  class Directory extends StoredObject
  {
    /** The parent of this directory. */
    private Directory parent;
    /** Child directories of this directory. */
    private Vector<Directory> children;
    /** Files contained in this directory. */
    private Vector<File> files;

    /** Default constructor. */
    public Directory()
    {
      // Null
    }

    /** Constructor to initialize new directories. */
    public Directory(String directoryName)
    {
      this.name=directoryName;
      this.size=DIRECTORY_SIZE;
      this.children=new Vector<Directory>();
      this.files=new Vector<File>();
    }

    /**
     * Gets the current working directory.
     * @return the current working directory.
     */
    public String getCWD()
    {
      return this.getName();
    }

    /**
     * Gets the parent of the directory.
     * @return the parent of the directory.
     */
    public String getParent()
    {
      return parent.getName();
    }

    /**
     * Sets the parent of the directory.
     * @param parent the parent of the directory.
     */
    public void setParent(Directory parent)
    {
      this.parent=parent;
      return;
    }

    /**
     * Adds a directory to this directory.
     * @param name the name of the new directory.
     */
    public void addDirectory(String name)
    {
      Directory child=new Directory();
      children.addElement(child);
      return;
    }
  }

  /** This class represents files */
  class File extends StoredObject
  {
    /** Contents as characters contained within this file. */
    private String contents;
    /** The type/magic number of this file (ASCII, binary, character, pipe, etc.) */
    private byte type;

    /** Default constructor. */
    public File()
    {
      // Null
    }

    /**
     * Gets the contents of the file.
     * @return the contents of the file.
     */
    public String getContents()
    {
      return contents;
    }

    /**
     * Sets the contents of the file.
     * @params contents the contents in chars of the file.
     */
    public void setContents(String contents)
    {
      this.contents=contents;
      return;
    }
  }
}
