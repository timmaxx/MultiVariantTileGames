<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="ru">

<head>
    <title>Minesweeper</title>
</head>

<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Game</h2>
${requestScope.minesweeperModel}
<br>
${requestScope.minesweeperModel.getAllMinesweeperObjects()}
<%--${requestScope.minesweeperModel.getAllMinesweeperObjects().getMinesweeperObjects()}--%>
<br>

<%--
<c:forEach items="${requestScope.minesweeperModel.allMinesweeperObjects.getListOfMinesweeperObjects()}" var="gameObject">
    <jsp:useBean id="gameObject" type="timmax.minesweeper.model.gameobject.MinesweeperObject"/>
        ${gameObject.x} ${gameObject.y} ${gameObject.isOpen()}
    <br>
</c:forEach>
--%>

<form method="post" action="meals">
    <table>
        <c:forEach items="${requestScope.minesweeperModel.allMinesweeperObjects.getListOfListOfMinesweeperObjects()}" var="ListOfgameObject">
            <jsp:useBean id="ListOfgameObject" type="java.util.List <timmax.minesweeper.model.gameobject.MinesweeperObject>"/>
            <tr>
                <c:forEach items="${ListOfgameObject}" var="gameObject">
                    <jsp:useBean id="gameObject" type="timmax.minesweeper.model.gameobject.MinesweeperObject"/>
                    <td>
                        <button type="submit">${gameObject.x} ${gameObject.y} ${gameObject.isOpen()}</button>
                    </td>
                </c:forEach>
            </tr>
        </c:forEach>
    </table>
</form>

</body>

</html>