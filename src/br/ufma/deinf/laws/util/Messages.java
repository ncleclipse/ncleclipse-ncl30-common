/*******************************************************************************
 * This file is part of the NCL authoring environment - NCL Eclipse.
 *
 * Copyright (C) 2007-2012, LAWS/UFMA.
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License version 2 as published by
 * the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License version 2 for
 * more details. You should have received a copy of the GNU General Public 
 * License version 2 along with this program; if not, write to the Free 
 * Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 
 * 02110-1301, USA.
 *
 * For further information contact:
 * - ncleclipse@laws.deinf.ufma.br
 * - http://www.laws.deinf.ufma.br/ncleclipse
 * - http://www.laws.deinf.ufma.br
 *
 ******************************************************************************/
package br.ufma.deinf.laws.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author Roberto Azevedo <roberto@laws.deinf.ufma.br>
 * 
 */
public abstract class Messages {
	protected ResourceBundle fgResourceBundle;

	public String getString(String key) {
		try {
			return fgResourceBundle.getString(key);
		} catch (MissingResourceException e) {
			return "!" + key + "!";//$NON-NLS-2$ //$NON-NLS-1$
		}
	}

	public String getString(String key, Object[] messageArguments) {
		try {
			String temp = fgResourceBundle.getString(key);
			java.text.MessageFormat formatter = new java.text.MessageFormat("");
			formatter.applyPattern(temp);
			String output = formatter.format(messageArguments);
			return output;
		} catch (Exception e) {
			return "!" + key + "!";
		}
	}

	public ResourceBundle getResourceBundle() {
		return fgResourceBundle;
	}

	protected void setResourceBundle(String resourceBundle) {
		fgResourceBundle = ResourceBundle.getBundle(resourceBundle);
	}
}
