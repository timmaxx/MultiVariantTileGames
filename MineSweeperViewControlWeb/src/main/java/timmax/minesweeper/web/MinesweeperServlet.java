package timmax.minesweeper.web;

import org.slf4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

public class MinesweeperServlet extends HttpServlet {
    private static final Logger log = getLogger(MinesweeperServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("forward to mvtg");
        request.getRequestDispatcher("/mvtg.jsp").forward(request, response);
    }
}