package com.invprof.cameras.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.IOUtils;

import com.invprof.cameras.model.Document;
import com.invprof.cameras.service.DocumentService;
import com.invprof.cameras.service.DocumentServiceImpl;
import com.invprof.cameras.util.Util;

public class AdminDocumentAction extends ActionAdapter {
	private DocumentService service = new DocumentServiceImpl();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		if (request.getParameter("download") != null) {
			return download(request, response);
		}
		
		return super.execute(request, response);
	}
	
	@Override
	public String list(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		List<Document> list = service.list();
		request.setAttribute("list", list);
		return "/admin/document.jsp";
	}

	@Override
	public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException  {
		String aux = request.getParameter("id");
		Long id = Util.tryParseLong(aux);
		Document item = service.load(id);
		request.setAttribute("item", item);
		return "/admin/document.jsp";
	}

	@Override
	public String save(HttpServletRequest request, HttpServletResponse response) throws ServletException  {
		Map<String, String> map = new HashMap<>();
		
		try {
			ServletFileUpload upload = new ServletFileUpload();
			FileItemIterator iter = upload.getItemIterator(request);
			String charset = "UTF-8";
			
			while (iter.hasNext()) {
				FileItemStream item = iter.next();
				InputStream stream = item.openStream();
				//String type = item.getContentType();
				String name = item.getFieldName();
				String value = null;
				
				if (item.isFormField()) {
					value = Streams.asString(stream, charset);
				}
				else {
					value = IOUtils.toString(stream, charset);
				}
				
				map.put(name, value);
			}
		} catch (Exception e) {
			throw new ServletException(e.getMessage());
		}
		
		String aux = map.get("id");
		Long id = Util.tryParseLong(aux);
		String name = map.get("name");
		String description = map.get("description");
		aux = map.get("position");
		Integer position = Util.tryParseInt(aux);
		String file = map.get("file");
		if (file != null && !file.isEmpty()) description = file;
		Document item = new Document(id, name, description, position);
		service.save(item);
		return "redirect:/admin/document";
	}

	@Override
	public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException  {
		String aux = request.getParameter("id");
		Long id = Util.tryParseLong(aux);
		service.delete(id);
		return "redirect:/admin/document";
	}
	
	private String download(HttpServletRequest request, HttpServletResponse response) {
		String aux = request.getParameter("id");
		Long id = Util.tryParseLong(aux);
		Document item = service.load(id);
		String name = item.getName();
		String description = item.getDescription();

		response.setContentType("application/octet-stream");
		response.setContentLength((int) description.length());
        response.setHeader("Content-disposition","attachment; filename=\"" + name + ".html\"");
        
		try {
			
			OutputStream out = response.getOutputStream();
			out.write(description.getBytes());
			out.flush();
			
		} catch (IOException e) {
			// TODO: handle exception
		}
		
		return null;
	}
}