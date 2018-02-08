package member.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberController extends HttpServlet {
	
	private Map<String, MemberCommandHandler> commandHandlerMap = new HashMap<>();
	
	public void init() throws ServletException {
		String configFile = getInitParameter("configFile");
		Properties prop = new Properties();
		String configFilePath = getServletContext().getRealPath(configFile);
		
		try (FileReader fs = new FileReader(configFilePath)) {
			prop.load(fs);
		} catch (FileNotFoundException e) {
			throw new ServletException(e);
		} catch (IOException e) {
			throw new ServletException(e);
		}
		
		Iterator keyIter = prop.keySet().iterator();
		
		while (keyIter.hasNext()) {
			String command = (String) keyIter.next();
			String handlerClassName = prop.getProperty(command);
			
			try {
				Class<?> handlerClass = Class.forName(handlerClassName);
				MemberCommandHandler handlerInstance = (MemberCommandHandler) handlerClass.newInstance();
				commandHandlerMap.put(command, handlerInstance);
			} catch (ClassNotFoundException e) {
				throw new ServletException(e);
			} catch (InstantiationException e) {
				throw new ServletException(e);
			} catch (IllegalAccessException e) {
				throw new ServletException(e);
			}
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberCommandHandler handler = null;
		String viewPage = null;
		
		String command = request.getRequestURI();
		if (command.indexOf(request.getContextPath()) == 0) {
			command = command.substring(request.getContextPath().length());
		}
		handler = commandHandlerMap.get(command);
		if (handler == null) {
			handler = new NullHandler();
		}
		
		try {
			viewPage = handler.process(request, response);
		} catch (Throwable e) {
			throw new ServletException(e);
		}
		if (viewPage != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}
		
	}
	
}
