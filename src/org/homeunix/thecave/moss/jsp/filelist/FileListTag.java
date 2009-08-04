package org.homeunix.thecave.moss.jsp.filelist;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

public class FileListTag implements Tag {
	private PageContext pageContext = null;
	private Tag parent = null;
	private String folder; //Relative to / on webapp
	private String regex;

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}
	
	public String getRegex() {
		return regex;
	}
	
	public void setRegex(String extensionRegex) {
		this.regex = extensionRegex;
	}

	public void setPageContext(PageContext arg0) {
		this.pageContext = arg0;
	}

	public Tag getParent() {
		return parent;
	}

	public void setParent(Tag arg0) {
		this.parent = arg0;
	}

	public int doStartTag() throws JspException {
		try {			
			pageContext.getOut().println("\n\n<div class='filelist'>");

//			pageContext.getOut().println("<ul>");
			recurseFolder(getFolder(), true);
//			pageContext.getOut().println("</ul>");

		} catch (Exception e) {
			throw new JspException(e);
		}

		return EVAL_BODY_INCLUDE;
	}

	@SuppressWarnings("unchecked")
	/**
	 * Recurse through a folder.  If the item is a folder (and has children), return true; 
	 * otherwise, return false.
	 */
	private boolean recurseFolder(String folder, boolean firstLevel) throws Exception{
		List<String> files = new ArrayList<String>(pageContext.getServletContext().getResourcePaths(folder));
		Collections.sort(files);

		if (files == null || files.size() == 0)
			return false;
		
		if (!firstLevel)
			pageContext.getOut().println("<li>" + folder);
		pageContext.getOut().println("<ul>");
		for (String fileString : files) {
			if (fileString.matches(getRegex())){
				URL url = pageContext.getServletContext().getResource(fileString);
				String location = url.getFile().replaceFirst("/[\\.a-zA-Z0-9]+", "");
				String fileName = url.getFile().replaceFirst("/.*/", "");
				pageContext.getOut().println("<li><a href='" + location + "'>" + fileName + "</a></li>");				
			}
			recurseFolder(fileString, false);
		}
		pageContext.getOut().println("</ul>");
		if (!firstLevel)
			pageContext.getOut().println("</li>");

		return true;
	}

	public int doEndTag() throws JspException {
		try {
			pageContext.getOut().println("</div> <!-- filelist -->\n");
		} catch (IOException e) {
			throw new JspException(e);
		}
		return SKIP_BODY;
	}

	public void release() {
		pageContext = null;
		parent = null;
	}
}
