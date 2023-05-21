package timmax.minesweeper.web;

import org.slf4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import timmax.minesweeper.model.MinesweeperModel;

import java.io.IOException;
import java.util.Arrays;

import static org.slf4j.LoggerFactory.getLogger;

public class MinesweeperServlet extends HttpServlet {
    private static final Logger log = getLogger(MinesweeperServlet.class);

    MinesweeperModel minesweeperModel;
    private final static int SIDE_OF_WIDTH = 10;
    private final static int SIDE_OF_HEIGHT = 10;

    private static final int REST_OF_MINE_INSTALLATION_IN_PERCENTS = 20;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("forward to mvtg");
        AbstractApplicationContext appCtx = new ClassPathXmlApplicationContext("applicationContext.xml");
        System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
        minesweeperModel = appCtx.getBean(MinesweeperModel.class);
        minesweeperModel.createNewGame( SIDE_OF_WIDTH, SIDE_OF_HEIGHT, REST_OF_MINE_INSTALLATION_IN_PERCENTS);
        request.setAttribute("minesweeperModel", minesweeperModel);
        request.getRequestDispatcher("/mvtg.jsp").forward(request, response);
/*

        log.debug("redirect to mvtg");

        AbstractApplicationContext appCtx = new ClassPathXmlApplicationContext("applicationContext.xml");
        // System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
        minesweeperModel = appCtx.getBean(MinesweeperModel.class);
        request.setAttribute("minesweeperModel", minesweeperModel);

        response.sendRedirect("mvtg.jsp");
*/
    }
}