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

public class UrlUtils {

	// Unreserved punctuation mark/symbols
	private static String mark = "-_.!~*'()\";/?:@&=+$,&%";

	/**
	 * Converts Hex digit to a UTF-8 "Hex" character
	 * @param digitValue digit to convert to Hex
	 * @return the converted Hex digit
	 */
	static private char toHexChar(int digitValue) {
		if (digitValue < 10)
			// Convert value 0-9 to char 0-9 hex char
			return (char) ('0' + digitValue);
		else
			// Convert value 10-15 to A-F hex char
			return (char) ('A' + (digitValue - 10));
	}

	/**
	 * Encodes a URL - This method assumes UTF-8
	 * @param url URL to encode
	 * @return the encoded URL
	 */
	static public String encodeURL(String url) {
		StringBuffer encodedUrl = new StringBuffer(); // Encoded URL
		int len = url.length();
		// Encode each URL character
		for (int i = 0; i < len; i++) {
			char c = url.charAt(i); // Get next character
			if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z')
					|| (c >= 'A' && c <= 'Z'))
				// Alphanumeric characters require no encoding, append as is
				encodedUrl.append(c);
			else {
				int imark = mark.indexOf(c);
				if (imark >= 0) {
					// Unreserved punctuation marks and symbols require
					//  no encoding, append as is
					encodedUrl.append(c);
				} else {
					// Encode all other characters to Hex, using the format "%XX",
					//  where XX are the hex digits
					encodedUrl.append('%'); // Add % character
					// Encode the character's high-order nibble to Hex
					encodedUrl.append(toHexChar((c & 0xF0) >> 4));
					// Encode the character's low-order nibble to Hex
					encodedUrl.append(toHexChar(c & 0x0F));
				}
			}
		}
		return encodedUrl.toString(); // Return encoded URL
	}
}
