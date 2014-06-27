/*
 * $Id: Permissions.java 2 2011-10-18 00:42:44Z ldilley $
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

import java.util.BitSet;

/*
 * The BitSet represents the following:
 * - --- --- ---
 * The bit fields in order are:
 * 1.) special bit
 * 2.) user owner
 * 3.) group owner
 * 4.) others
 * For example, the user owner field set to 010 would mean that the user can write to the file
 * 111 would mean rwx. So, 0111111111 would be 0777 octal
 */

public class Permissions
{
  public static String calcSymPerms(BitSet bitPerms)
  {
    String symPerms=null;

    return symPerms;
  }

  public static BitSet calcBitPerms(String symPerms)
  {
    BitSet bitPerms=null;

    return bitPerms;
  }

  public static BitSet calcUmaskPerms(BitSet umask)
  {
    BitSet newBitPerms=null;

    return newBitPerms;
  }
}
