package unirio.trie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
    name = "TrieInterface",
    urlPatterns = {"/insertWord"}
)
public class TrieInterface extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException {

	PrintWriter out = response.getWriter();
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    out.println("Hello App Engine!\r\n");

  }
}