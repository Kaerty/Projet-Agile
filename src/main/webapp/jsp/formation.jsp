<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="beans.Formation"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ANPEP - ${ formation.name }</title>
<link rel="stylesheet" type="text/css" href="css/inscription.css">
</head>
<body>
<h1>ANPEP</h1>

<h2>Emargements - ${ formation.name }</h2>

<table class="emargement">

<tr>
	<th colspan = 5>Stagiaire</th>
	<th colspan = 2>Formateur</th>
</tr>

<tr>
	<th>Nom</th>
	<th>Prénom</th>
	<th>Date</th>
	<th>Matin</th>
	<th>Après-midi</th>
	<th>Matin</th>
	<th>Après-midi</th>
</tr>
	<c:forEach var="d" items="${ days }">
	<tr>
		<td colspan = 7>Formateur Intervenant = ${ d.surnameFormer } ${ d.nameFormer }</td>
	</tr>
		<c:forEach var="i" items="${ interns }">
		
		<tr>
			<td>${ i.surname }</td>
			<td>${ i.name }</td>
			<td>${ d.date }</td>
			
			<c:forEach var="hd" items="${ i.halfdays }">
			<c:if test="${ hd.date == d.date }">
			
			<c:choose>
				<c:when test="${ hd.ichecked }">
				<td>YES</td>
				</c:when>
				<c:when test="${ !hd.ichecked }">
				<td>NOPE</td>
				</c:when>
				<c:otherwise>
				<td>NOT YET</td>
				</c:otherwise>
			</c:choose>
			
			</c:if>
			</c:forEach>
			
			<c:forEach var="hd" items="${ i.halfdays }">
			<c:if test="${ hd.date == d.date }">
			
			<c:choose>
				<c:when test="${ hd.fchecked }">
				<td>YES</td>
				</c:when>
				<c:when test="${ !hd.fchecked }">
				<td>NOPE</td>
				</c:when>
				<c:otherwise>
				<td>NOT YET</td>
				</c:otherwise>
			</c:choose>
			
			</c:if>
			</c:forEach>
		</tr>
		</c:forEach>
	</c:forEach>	
</table>
	<div>
	
<div id="emailsent">
	<h2>L'inscription a été envoyé</h2>
</div>

<form action="" method="POST" id="add_user">
	<h2>Ajouter un utilisateur</h2>
	
	<div class="field">
	<label for="surname" id="surname">Nom</label>
	<input type="text" name="surname" id="surname">
	</div>
	
	<div class="field">
	<label for="name" id="name">Prénom</label>
	<input type="text" name="name" id="name">
	</div>
	
	<div class="field">
	<label for="email" id="email">Email</label>
	<input type="text" name="email" id="email">
	</div>

	<div class="inscription_field">
	<p>Type</p>
	<input type="radio" name="typeuser" id="intern" value="2">
	<label for="intern" >Stagiaire</label>
	<input type="radio" name="typeuser" id="former" value="1">
	<label for="former" >Formateur</label>
	</div>
	<input type="hidden" name="idformation" value="${idformation}">

	<input type="submit" value="Ajouter">
</form>
	
	
	
<form id="form">
	<h2>Récupérer les émargements :</h2>
	<label for="datedebut">Du :</label>
	<input type="date" id="datedebut" name="datedebut" value="<fmt:formatDate pattern = "yyyy-MM-dd" 
         value = "${datelocale}" />" min ="2001-10-08" max = "2022-10-10">
	<label for="datefin">Au :</label>
	<input type="date" id="datefin" name="datefin"value="<fmt:formatDate pattern = "yyyy-MM-dd" 
         value = "${datelocalep1}" />" min ="2001-10-08" max = "2022-10-10">
	
	
	

 <input type="radio" id="allinterns" name="internchoice" value="allinterns" checked>
  <label for="allinterns">Pour tous les stagiaires</label>

  <input type="radio" id="oneintern" name="internchoice" value="oneintern">
  <label for="oneintern">Pour ce stagiaire:</label>
  <div id="showinterns">
  <select name="intern" id="intern">
    <option value="">--Stagiaire :--</option>
    <c:forEach var="i" items="${ interns }">
    <option value="${i.idUser }">${i.surname} ${i.name}</option>
	</c:forEach>
	</select>
</div>	
  <input type="submit" value="Récupérer les données">
</form>





	<script type="text/javascript" src="js/formation.js"></script>

</body>
</html>