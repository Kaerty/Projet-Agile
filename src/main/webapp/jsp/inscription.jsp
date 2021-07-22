<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ANPEP - Inscription</title>
<link rel="stylesheet" type="text/css" href="css/inscription.css">
</head>
<body>

<h1>ANPEP</h1>

<div class="${cansub?'showinscription':'notshow'}">
 
<h2>Inscription</h2>

<form action='inscription?to=inscription' method="POST" id="inscription">

	<div class="inscription_field">
	<label for="surname" id="surname">Nom de Famille</label>
	<input type="text" name="surname" id="surname">
	</div>
	
	<div class="inscription_field">
	<label for="name" id="name">Prénom</label>
	<input type="text" name="name" id="name">
	</div>
	
	<div class="inscription_field">
	<label for="email" id="email">Email</label>
	<input type="text" name="email" id="email">
	</div>
	
	<div class="inscription_field">
	<label for="password" id="password">Mot de Passe</label>
	<input type="password" name="password" id="password">
	</div>
	
	<div class="inscription_field">
	<label for="phone" id="phone">Téléphone</label>
	<input type="text" name="phone" id="phone">
	</div>
	
	<div class="inscription_field">
	<label for="adress" id="adress">Adresse</label>
	<input type="text" name="adress" id="adress">
	</div>
	
	
	<div class="inscription_field">
	<p>Genre</p>
	<input type="radio" name="gender" id="homme" value="M">
	<label for="homme" id="homme">Homme</label>
	<input type="radio" name="gender" id="femme" value="F">
	<label for="femme" id="femme">Femme</label>
	<input type="radio" name="gender" id="autre" value="O" checked>
	<label for="autre" id="autre">Autre</label>
	</div>
	<input type="hidden" name="idformation" value="${typeinscription}">
	
	<input type="submit" value="Envoyer">

</form>

</div>

<div class="${cansub?'notshow':'showinscription'}">
<h1>Bien Essayé JJ mais tu as échoué !</h1>
</div>

	<script type="text/javascript" src="js/inscription.js"></script>

</body>
</html>