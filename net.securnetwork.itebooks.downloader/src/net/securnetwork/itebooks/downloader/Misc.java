/*******************************************************************************
 * Copyright (c) 2012 Massimo Rabbi <mrabbi@users.sourceforge.net>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Massimo Rabbi <mrabbi@users.sourceforge.net> - initial API and implementation
 ******************************************************************************/
package net.securnetwork.itebooks.downloader;

/**
 * Miscellaneous utility methods.
 * 
 * @author Massimo Rabbi <mrabbi@users.sourceforge.net>
 *
 */
public class Misc {

	/**
	 * Replaces a possible null-value string with an empty string.
	 * 
	 * @param str the string to be examined
	 * @return the original string if not <code>null</code>, an empty one otherwise
	 */
	public static String nvl(String str){
		return nvl(str,""); //$NON-NLS-1$
	}
	
	/**
	 * Replaces a possible null-value string with a default one.
	 * 
	 * @param str the string to be examined
	 * @param defaultStr the default string to be used as fall-back solution
	 * @return the original string if not <code>null</code>, the default one otherwise
	 */
	public static String nvl(String str,String defaultStr){
		if(str==null){
			return defaultStr;
		}
		else{
			return str;
		}
	}
	
	/**
	 * Returns a valid filename for the ebook file to be saved.
	 * 
	 * @param title the ebook title
	 * @param subTitle the ebook subtitle
	 * @param year the ebook publishing year
	 * @param fileFormat the ebook file format
	 * @return a valid filename
	 */
	public static String getValidFilename(String title, String subTitle, String year, String fileFormat){
		StringBuffer sb=new StringBuffer();
		sb.append(sanitizeTitle(title));
		if(subTitle!=null && !subTitle.isEmpty()){
			sb.append("-").append(sanitizeTitle(subTitle)); //$NON-NLS-1$
		}
		sb.append("-[").append(year).append("]"); //$NON-NLS-1$ //$NON-NLS-2$
		sb.append(".").append(fileFormat.toLowerCase()); //$NON-NLS-1$
		return sb.toString();
	}
	
	/*
	 * Sanitizes a title string keeping only characters that 
	 * are digits or letters. All other chars are replaced with
	 * the underscore separator char. Every word is capitalized.
	 */
	private static String sanitizeTitle(String title){
		StringBuffer sb=new StringBuffer();
		boolean capitalizeNext=true;
		for (char ch : title.toCharArray()){
			if(Character.isLetterOrDigit(ch)){
				if(capitalizeNext){
					sb.append(Character.toUpperCase(ch));
					capitalizeNext=false;
				}
				else{
					sb.append(ch);
				}
			}
			else{
				sb.append("_"); //$NON-NLS-1$
				capitalizeNext=true;
			}
		}
		return sb.toString();
	}
}
