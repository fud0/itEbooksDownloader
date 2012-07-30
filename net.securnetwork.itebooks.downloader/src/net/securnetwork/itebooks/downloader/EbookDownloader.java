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

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.List;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

import org.apache.commons.io.FileUtils;

/**
 * Main class with the program entry point.
 * 
 * @author Massimo Rabbi <mrabbi@users.sourceforge.net>
 *
 */
public final class EbookDownloader {
	// Program version
	private static final String VERSION="0.1"; //$NON-NLS-1$
	// Program arguments
	private static final String END_INDEX_ARG = "-end="; //$NON-NLS-1$
	private static final String START_INDEX_ARG = "-start="; //$NON-NLS-1$
	private static final String OUTPUT_FOLDER_ARG = "-outputFolder="; //$NON-NLS-1$
	// Ebooks site related constants
	private static final int MIN_EBOOK_INDEX=1;
	private static final int MAX_EBOOK_INDEX=860;
	private static final String BASE_URL="http://it-ebooks.info"; //$NON-NLS-1$
	private static final String BASE_EBOOK_URL=BASE_URL+"/book/"; //$NON-NLS-1$
	private static final String SLASH="/"; //$NON-NLS-1$

	/**
	 * Program main method.
	 * 
	 * @param args the program arguments
	 */
	public static void main(String[] args) {
		if(validateArguments(args)){
			String destinationFolder=getDestinationFolder(args);
			int start=MIN_EBOOK_INDEX;
			int end=MAX_EBOOK_INDEX;
			if(args.length==3){
				start=getStartIndex(args);
				end=getEndIndex(args);
			}
			try {
				for (int i=start;i<=end;i++){
					Source sourceHTML=new Source(new URL(BASE_EBOOK_URL + i + SLASH));
					List<Element> allTables = sourceHTML.getAllElements(HTMLElementName.TABLE);
					Element detailsTable = allTables.get(0);
					// Try to build an info bean for the ebook
					String bookTitle = EbookPageParseUtils.getTitle(detailsTable);
					if(bookTitle!=null){
						EbookInfo ebook=createEbookInfo(bookTitle, i, detailsTable);
						String filename =
								destinationFolder + Misc.getValidFilename(
										ebook.getTitle(), ebook.getSubTitle(),
										ebook.getYear(), ebook.getFileFormat());
						System.out.print(MessageFormat.format(Messages.getString("EbookDownloader.InfoDownloading"), new Object[]{ebook.getSiteId()})); //$NON-NLS-1$
						try {
							FileUtils.copyURLToFile(new URL(ebook.getDownloadLink()), new File(filename));
							System.out.println(Messages.getString("EbookDownloader.DownloadingOK")); //$NON-NLS-1$
						} catch (Exception e) {
							System.out.println(Messages.getString("EbookDownloader.DownloadingKO")); //$NON-NLS-1$
						}
					}
				}
				
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else{
			printHelp();
		}
	}

	/*
	 * Prints program help.
	 */
	private static void printHelp() {
		System.out.println("#################################################################"); //$NON-NLS-1$
		System.out.println(MessageFormat.format("# Ebook Downloader version {0}\t\t\t\t\t#", new Object[]{VERSION})); //$NON-NLS-1$
		System.out.println(MessageFormat.format("# Dumps ebook files from {0}\t\t\t#", new Object[]{BASE_URL})); //$NON-NLS-1$
		System.out.println("# Author: Massimo Rabbi (fud0) <mrabbi@users.sourceforge.net> \t#"); //$NON-NLS-1$
		System.out.println("#################################################################"); //$NON-NLS-1$
		System.out.println("Example of usage (1) - download all possible ebooks to the specified folder: "); //$NON-NLS-1$
		System.out.println("\tjava -jar ebookdownloader.jar -outputFolder=<folderLocation>"); //$NON-NLS-1$
		System.out.println("Example of usage (2) - download selected ebooks, using a range id, to the specified folder: "); //$NON-NLS-1$
		System.out.println("\tjava -jar ebookdownloader.jar -outputFolder=<folderLocation> -start=<startIndex> -end=<endIndex>"); //$NON-NLS-1$
	}

	/*
	 * Checks if the format and number of the arguments is good.
	 */
	private static boolean validateArguments(String[] args) {
		if(args.length==1 && 
				args[0].startsWith(EbookDownloader.OUTPUT_FOLDER_ARG)){
			return true;
		}
		if(args.length==3 &&
				args[0].startsWith(EbookDownloader.OUTPUT_FOLDER_ARG) &&
				args[1].startsWith(EbookDownloader.START_INDEX_ARG) &&
				args[2].startsWith(EbookDownloader.END_INDEX_ARG)){
			return true;
		}
		return false;
	}

	/*
	 * Reads program arguments for destination folder info.
	 */
	private static String getDestinationFolder(String[] args) {
		return args[0].substring(EbookDownloader.OUTPUT_FOLDER_ARG.length());
	}
	
	/*
	 * Reads program arguments for the index of the first ebook to download.
	 */
	private static Integer getStartIndex(String[] args){
		return Integer.parseInt(args[1].substring(EbookDownloader.START_INDEX_ARG.length()));
	}
	
	/*
	 * Reads program arguments for the index of the last ebook to download.
	 */
	private static Integer getEndIndex(String[] args){
		return Integer.parseInt(args[2].substring(EbookDownloader.END_INDEX_ARG.length()));
	}

	/*
	 * Creates an information bean with details on the specified ebook.
	 */
	private static EbookInfo createEbookInfo(String bookTitle, int id, Element detailsTable){
		EbookInfo ebook = new EbookInfo();
		ebook.setTitle(bookTitle);
		ebook.setSiteId(id);
		ebook.setSiteURL(BASE_EBOOK_URL+id+SLASH);
		ebook.setSubTitle(EbookPageParseUtils.getSubtitle(detailsTable));
		ebook.setPublisher(EbookPageParseUtils.getPublisher(detailsTable));
		ebook.setAuthor(EbookPageParseUtils.getAuthor(detailsTable));
		ebook.setIsbn(EbookPageParseUtils.getISBN(detailsTable));
		ebook.setYear(EbookPageParseUtils.getYear(detailsTable));
		ebook.setPages(EbookPageParseUtils.getPages(detailsTable));
		ebook.setLanguage(EbookPageParseUtils.getLanguage(detailsTable));
		ebook.setFileSizeMB(EbookPageParseUtils.getFileSize((detailsTable)));
		ebook.setFileFormat(EbookPageParseUtils.getFileFormat(detailsTable));
		ebook.setDownloadLink(BASE_URL + EbookPageParseUtils.getDownloadLink(detailsTable));
		return ebook;
	}
	
}
