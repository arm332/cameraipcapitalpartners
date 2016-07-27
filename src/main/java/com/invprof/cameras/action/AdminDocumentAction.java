package com.invprof.cameras.action;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
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
		ServletFileUpload upload = new ServletFileUpload();
		
		try {
			FileItemIterator iter = upload.getItemIterator(request);
			
			while (iter.hasNext()) {
				FileItemStream item = iter.next();
				InputStream stream = item.openStream();
				//String type = item.getContentType();
				String name = item.getFieldName();
				String value = null;
				
				if (item.isFormField()) {
					value = Streams.asString(stream, "UTF-8");
				}
				else {
					value = IOUtils.toString(stream);
				}
				
				map.put(name, value);
			}
			
		} catch (FileUploadException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String aux = map.get("id");
		Long id = Util.tryParseLong(aux);
		String name = map.get("name");
		String description = map.get("description");
		aux = map.get("position");
		Integer position = Util.tryParseInt(aux);
		String file = map.get("file");
		if (file != null) description = file;
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
	
}