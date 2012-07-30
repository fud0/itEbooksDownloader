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
 * Bean containing all the information about an ebook.
 * 
 * @author Massimo Rabbi <mrabbi@users.sourceforge.net>
 *
 */
public class EbookInfo {

	// Fields
	private Integer siteId;
	private String siteURL;
	private String title;
	private String subTitle;
	private String description;
	private String publisher;
	private String author;
	private String isbn;
	private String year;
	private String pages;
	private String language;
	private String fileSizeMB;
	private String fileFormat;
	private String downloadLink;
	
	// Getters and setters
	public Integer getSiteId() {
		return siteId;
	}
	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}
	public String getSiteURL() {
		return siteURL;
	}
	public void setSiteURL(String siteURL) {
		this.siteURL = siteURL;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getPages() {
		return pages;
	}
	public void setPages(String pages) {
		this.pages = pages;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getFileSizeMB() {
		return fileSizeMB;
	}
	public void setFileSizeMB(String fileSizeMB) {
		this.fileSizeMB = fileSizeMB;
	}
	public String getFileFormat() {
		return fileFormat;
	}
	public void setFileFormat(String fileFormat) {
		this.fileFormat = fileFormat;
	}
	public String getDownloadLink() {
		return downloadLink;
	}
	public void setDownloadLink(String downloadLink) {
		this.downloadLink = downloadLink;
	}

	/**
	 * Prints the ebook details.
	 * Useful for debug purposes.
	 */
	public String display(){
		StringBuffer sb=new StringBuffer();
		sb.append("========== EBOOK ").append(getSiteId()) //$NON-NLS-1$
			.append(" - URL: ").append(getSiteURL()).append(" ==========").append("\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		sb.append("Title:\t").append(Misc.nvl(getTitle())).append("\n"); //$NON-NLS-1$ //$NON-NLS-2$
		sb.append("Subtitle:\t").append(Misc.nvl(getSubTitle())).append("\n"); //$NON-NLS-1$ //$NON-NLS-2$
		sb.append("Publisher:\t").append(Misc.nvl(getPublisher())).append("\n"); //$NON-NLS-1$ //$NON-NLS-2$
		sb.append("Author:\t").append(Misc.nvl(getAuthor())).append("\n"); //$NON-NLS-1$ //$NON-NLS-2$
		sb.append("ISBN:\t").append(Misc.nvl(getIsbn())).append("\n"); //$NON-NLS-1$ //$NON-NLS-2$
		sb.append("Year:\t").append(Misc.nvl(getYear())).append("\n"); //$NON-NLS-1$ //$NON-NLS-2$
		sb.append("Pages:\t").append(Misc.nvl(getPages())).append("\n"); //$NON-NLS-1$ //$NON-NLS-2$
		sb.append("Language:\t").append(Misc.nvl(getLanguage())).append("\n"); //$NON-NLS-1$ //$NON-NLS-2$
		sb.append("File size:\t").append(Misc.nvl(getFileSizeMB())).append("\n"); //$NON-NLS-1$ //$NON-NLS-2$
		sb.append("File format:\t").append(Misc.nvl(getFileFormat())).append("\n"); //$NON-NLS-1$ //$NON-NLS-2$
		return sb.toString();
	}
}
