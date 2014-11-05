<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Spring Tic Tac Toe</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  </head>

  <body>
    <h1>Tic Tac Toe</h1>

    <form:form method="post" action="${pageContext.request.contextPath}/game/new">
      <fieldset>
        <legend>Board Size</legend>

        <c:forEach items="${model.boardSizes}" var="size" varStatus="sizeStatus">
          <c:set var="checked" value=""></c:set>
          <c:if test="${sizeStatus.first}">
            <c:set var="checked" value="checked"></c:set>
          </c:if>

          <label>
            <input type="radio" name="board_size" value="${size.key}" ${checked}/>
            <span>${size.value}</span>
          </label>
        </c:forEach>
      </fieldset>

      <fieldset>
        <legend>Game Mode</legend>

        <c:forEach items="${model.playerPairs}" var="pair" varStatus="pairStatus">
          <c:set var="checked" value=""></c:set>
          <c:if test="${pairStatus.first}">
            <c:set var="checked" value="checked"></c:set>
          </c:if>

          <label>
            <input type="radio" name="player_pair" value="${pair.key}" ${checked}/>
            <span>${pair.value}</span>
          </label>
        </c:forEach>
      </fieldset>

      <button type="submit">Play!</button>
    </form:form>
  </body>
</html>
