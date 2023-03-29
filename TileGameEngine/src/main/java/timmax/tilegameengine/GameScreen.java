package timmax.tilegameengine;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public interface GameScreen {
    void setScreenSize(int var1, int var2);
    void showGrid(boolean var1);

    void showCoordinates(boolean var1);

    void setCellColor(int x, int y, Color color);

    void setCellTextSize(int x, int y, int var3);

    void setCellValue(int x, int y, String var3);

    void setCellNumber(int x, int y, int var3);

    void setCellTextColor(int x, int y, Color color);

    void setCellValueEx(int x, int y, Color color, String string);

    void setCellValueEx(int x, int y, Color cellGeColor, String string, Color textGeColor);

    void setCellValueEx(int x, int y, Color cellGeColor, String string, Color textGeColor, int var6);

    void showMessageDialog(Color var1, String var2, Color var3, int var4);

    void setScore(int var1);

    void setLives(int var1);

    void setTurnTimer(int var1);

    void stopTurnTimer();

    int getRandomNumber(int var1);

    int getRandomNumber(int var1, int var2);

    void initialize();

    void onMouseLeftClick(int x, int y);

    void onMouseRightClick(int x, int y);

    void onKeyPress(KeyCode keyCode);

    void onKeyReleased(KeyCode keyCode);

    void onTurn(int var1);
}