<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Spring Tic Tac Toe</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <c:if test="${model.isOngoing}">
      <meta http-equiv="refresh" content="1;URL=/game/play"/>
    </c:if>
    <style type="text/css">
      table {
        border-collapse: collapse;
        font-size: 16pt;
      }
      td {
        width: 30px;
        text-align: center;
        border: 1px solid #000;
      }
    </style>
  </head>

  <body>
    <h1>Tic Tac Toe</h1>

    <table>
      <c:forEach items="${model.board.marks}" var="mark" varStatus="markStatus">
        <c:if test="${markStatus.index % 3 == 0}">
          <tr>
        </c:if>

        <td>
          <c:choose>
            <c:when test="${mark.value != null}">
              <span>${mark.value}</span>
            </c:when>
            <c:otherwise>
              <a href="/game/play?move=${mark.key}">${mark.key}</a>
            </c:otherwise>
          </c:choose>
        </td>

        <c:if test="${markStatus.count % 3 == 0}">
          </tr>
        </c:if>
      </c:forEach>
    </table>

    <c:if test="${not empty model.status}">
      <p>
        <span>${model.status}</span>
      </p>
    </c:if>

    <c:if test="${not empty model.nextPlayer}">
      <p>
        Next Player: <span>${model.nextPlayer}</span>
      </p>
    </c:if>
    <p><a href="/">Back to Menu</a></p>
  </body>
</html>
