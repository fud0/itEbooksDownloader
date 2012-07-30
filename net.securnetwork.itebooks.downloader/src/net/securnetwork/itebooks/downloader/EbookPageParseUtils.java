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

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;

/**
 * Utility class that allows to retrieve ebook information
 * from specified HTML page elements.
 * 
 * @author Massimo Rabbi <mrabbi@users.sourceforge.net>
 *
 */
public class EbookPageParseUtils {

	public static final int FIRST_EL_INDEX=0;
	public static final int SECOND_EL_INDEX=1;
	
	public static final int TITLE_ROW_INDEX=3;
	public static final int SUBTITLE_ROW_INDEX=3;
	public static final int PUBLISHER_ROW_INDEX=6;
	public static final int AUTHOR_ROW_INDEX=7;
	public static final int ISBN_ROW_INDEX=8;
	public static final int YEAR_ROW_INDEX=9;
	public static final int PAGES_ROW_INDEX=10;
	public static final int LANGUAGE_ROW_INDEX=11;
	public static final int FILESIZE_ROW_INDEX=12;
	public static final int FILEFORMAT_ROW_INDEX=13;
	public static final int DOWNLOAD_ROW_INDEX=15;
	
	public static String getTitle(Element table){
		return getEbookDetail(table, TITLE_ROW_INDEX, HTMLElementName.H1, FIRST_EL_INDEX);
	}

	public static String getSubtitle(Element table){
		return getEbookDetail(table, TITLE_ROW_INDEX, HTMLElementName.H3, FIRST_EL_INDEX);
	}
	
	public static String getPublisher(Element table){
		return getEbookDetail(table, PUBLISHER_ROW_INDEX, HTMLElementName.TD, SECOND_EL_INDEX);
	}
	
	public static String getAuthor(Element table){
		return getEbookDetail(table, AUTHOR_ROW_INDEX, HTMLElementName.TD, SECOND_EL_INDEX);
	}
	
	public static String getISBN(Element table){
		return getEbookDetail(table, ISBN_ROW_INDEX, HTMLElementName.TD, SECOND_EL_INDEX);
	}
	
	public static String getYear(Element table){
		return getEbookDetail(table, YEAR_ROW_INDEX, HTMLElementName.TD, SECOND_EL_INDEX);
	}
	
	public static String getPages(Element table){
		return getEbookDetail(table, PAGES_ROW_INDEX, HTMLElementName.TD, SECOND_EL_INDEX);
	}
	
	public static String getLanguage(Element table){
		return getEbookDetail(table, LANGUAGE_ROW_INDEX, HTMLElementName.TD, SECOND_EL_INDEX);
	}
	
	public static String getFileSize(Element table){
		return getEbookDetail(table, FILESIZE_ROW_INDEX, HTMLElementName.TD, SECOND_EL_INDEX);
	}
	
	public static String getFileFormat(Element table){
		return getEbookDetail(table, FILEFORMAT_ROW_INDEX, HTMLElementName.TD, SECOND_EL_INDEX);
	}
	
	public static String getDownloadLink(Element table){
		try {
			Element row = table.getAllElements(HTMLElementName.TR).get(DOWNLOAD_ROW_INDEX);
			Element detailEl = row.getAllElements(HTMLElementName.A).get(FIRST_EL_INDEX);
			return detailEl.getAttributeValue("href"); //$NON-NLS-1$
		} catch (Exception e) {
			return null;
		}
	}
	
	private static String getEbookDetail(Element table, int rowIndex, String detailTag, int detailIndex) {
		try {
			Element row = table.getAllElements(HTMLElementName.TR).get(rowIndex);
			Element detailEl = row.getAllElements(detailTag).get(detailIndex);
			return detailEl.getTextExtractor().toString();
		} catch (Exception e) {
			return null;
		}
	}
}
