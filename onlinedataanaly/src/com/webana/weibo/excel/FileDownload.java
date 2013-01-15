package com.webana.weibo.excel;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileDownload extends HttpServlet {

  protected void service(HttpServletRequest req, HttpServletResponse res)
      throws ServletException, IOException {

    String path = req.getParameter("path").replace("/", File.separator);
    path = getServletContext().getRealPath("/") + path;

    File file = new File(path);
    if (!file.exists()) {
      res.setContentType("text/html;charset=UTF-8");
      res.getWriter().print("can not find file.");
      return;
    }
    String fileName = file.getName();
    ServletOutputStream out = res.getOutputStream();
    res.setHeader("Content-disposition", "attachment;filename=" + fileName);
    BufferedInputStream bis = null;
    BufferedOutputStream bos = null;
    try {
      bis = new BufferedInputStream(new FileInputStream(path));
      bos = new BufferedOutputStream(out);
      byte[] buff = new byte[2048];
      int bytesRead;
      while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
        bos.write(buff, 0, bytesRead);
      }
    } catch (IOException e) {
      throw e;
    } finally {
      if (bis != null)
        bis.close();
      if (bos != null)
        bos.close();
    }
  }

}