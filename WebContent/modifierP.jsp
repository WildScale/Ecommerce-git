<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Aroma Shop - Checkout</title>
<link rel="icon" href="img/Fevicon.png" type="image/png">

<link rel="stylesheet" href="vendors/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="vendors/fontawesome/css/all.min.css">
<link rel="stylesheet" href="vendors/themify-icons/themify-icons.css">
<link rel="stylesheet" href="vendors/linericon/style.css">
<link rel="stylesheet"
	href="vendors/owl-carousel/owl.theme.default.min.css">
<link rel="stylesheet" href="vendors/owl-carousel/owl.carousel.min.css">
<link rel="stylesheet" href="vendors/nice-select/nice-select.css">
<link rel="stylesheet" href="vendors/nouislider/nouislider.min.css">

<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<!--================ Start Header Menu Area =================-->
	<header class="header_area">
		<div class="main_menu">
			<nav class="navbar navbar-expand-lg navbar-light">
				<div class="container">
					<a class="navbar-brand logo_h" href="/Ecommerce/index"><img
						src="img/logo.png" alt=""></a>
					<button class="navbar-toggler" type="button" data-toggle="collapse"
						data-target="#navbarSupportedContent"
						aria-controls="navbarSupportedContent" aria-expanded="false"
						aria-label="Toggle navigation">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<div class="collapse navbar-collapse offset"
						id="navbarSupportedContent">
						<ul class="nav navbar-nav menu_nav ml-auto mr-auto">
							<li class="nav-item active"><a class="nav-link"
								href="/Ecommerce/index">Accueil</a></li>
							<li class="nav-item submenu dropdown"><a href="#"
								class="nav-link dropdown-toggle" data-toggle="dropdown"
								role="button" aria-haspopup="true" aria-expanded="false">Catégories</a>
								<ul class="dropdown-menu">
									<c:forEach var="famille" items="${familles}">

										<li class="nav-item"><a class="nav-link"
											href="/Ecommerce/famille?id=${famille.id}">${famille.nom}</a></li>
									</c:forEach>


								</ul></li>

							<li class="nav-item submenu dropdown"><a href="#"
								class="nav-link dropdown-toggle" data-toggle="dropdown"
								role="button" aria-haspopup="true" aria-expanded="false">Authentification</a>
								<ul class="dropdown-menu">
									<li class="nav-item"><a class="nav-link"
										href="/Ecommerce/login">Connexion</a></li>
									<li class="nav-item"><a class="nav-link"
										href="/Ecommerce/register">Inscription</a></li>
								</ul></li>
							<li class="nav-item"><a class="nav-link"
								href="/Ecommerce/contact">Contact</a></li>
							<c:if test="${sessionScope.client.isAdmin==true}">
								<li class="nav-item submenu dropdown"><a href="#"
									class="nav-link dropdown-toggle" data-toggle="dropdown"
									role="button" aria-haspopup="true" aria-expanded="false">Gestion</a>
									<ul class="dropdown-menu">
										<li class="nav-item"><a class="nav-link"
											href="/Ecommerce/ajouterProduit">Ajouter Produit</a></li>
										<li class="nav-item"><a class="nav-link"
											href="/Ecommerce/listeProduits">Liste Produits</a></li>
										<li class="nav-item"><a class="nav-link"
											href="/Ecommerce/ajouterFamille">Ajouter Famille</a></li>
										<li class="nav-item"><a class="nav-link"
											href="/Ecommerce/listeFamille">Liste Famille</a></li>
										<li class="nav-item"><a class="nav-link"
											href="/Ecommerce/listeClient">Liste Clients</a></li>
									</ul></li>
							</c:if>

						</ul>

						<ul class="nav-shop">

							<li class="nav-item"><button>
									<i class="ti-shopping-cart"></i><span class="nav-shop__circle">${sessionScope.produits.size }</span>
								</button></li>

						</ul>
					</div>
				</div>
			</nav>
		</div>
	</header>
	<!--================ End Header Menu Area =================-->

	<!-- ================ start banner area ================= -->
	<section class="blog-banner-area" id="category">
		<div class="container h-100">
			<div class="blog-banner">
				<div class="text-center">
					<h1>Modifier un produit</h1>
					<nav aria-label="breadcrumb" class="banner-breadcrumb">
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="#">Accueil</a></li>
							<li class="breadcrumb-item active" aria-current="page">Modifier
								un produit</li>
						</ol>
					</nav>
				</div>
			</div>
		</div>
	</section>
	<!-- ================ end banner area ================= -->


	<!--================Checkout Area =================-->
	<section class="checkout_area section-margin--small">
		<div class="container">


			<div class="billing_details">
				<div class="row">
					<div class="col-lg-8">
						<h3>Informations du produit</h3>
						<form class="row contact_form"
							action="/Ecommerce/produit-modifier?id=${produit.id}"
							method="post" novalidate="novalidate">
							<div class="col-md-6 form-group p_star">
								<input type="text" class="form-control"
									placeholder="Nom du produit" id="first" value="${produit.nom}"
									name="nom_produit"> <span class="placeholder"
									data-placeholder="First name" required></span>
							</div>
							<div class="col-md-6 form-group p_star">
								<input type="number" class="form-control"
									placeholder="Prix du produit" id="last" value="${produit.prix}"
									name="prix_produit"> <span class="placeholder"
									data-placeholder="Last name" required></span>
							</div>

							<div class="col-md-12 form-group p_star">
								<select class="country_select" name="famille_produit" required>

									<c:forEach var="famille" items="${familles}">
										<c:choose>
											<c:when test="${famille.id==produit.famille.id}">
												<option value="${famille.id}" selected>${famille.nom}</option>

											</c:when>
											<c:otherwise>
												<option value="${famille.id}">${famille.nom}</option>
											</c:otherwise>

										</c:choose>


									</c:forEach>
								</select>

							</div>
							<div class="col-md-12 form-group p_star">
								<textarea class="form-control different-control w-100"
									name="description" id="message" cols="30" rows="5"
									placeholder="Description">${produit.description}</textarea>
							</div>
							<button style="margin-left: 20px" type="submit" value="Ajouter"
								class="button button-login">Modifier</button>


						</form>
					</div>

				</div>
			</div>
		</div>
	</section>
	<!--================End Checkout Area =================-->



	<!--================ Start footer Area  =================-->
	<footer>
		<div class="footer-area footer-only">
			<div class="container">
				<div class="row section_gap">
					<div class="col-lg-3 col-md-6 col-sm-6">
						<div class="single-footer-widget tp_widgets ">
							<h4 class="footer_title large_title">Our Mission</h4>
							<p>So seed seed green that winged cattle in. Gathering thing
								made fly you're no divided deep moved us lan Gathering thing us
								land years living.</p>
							<p>So seed seed green that winged cattle in. Gathering thing
								made fly you're no divided deep moved</p>
						</div>
					</div>
					<div class="offset-lg-1 col-lg-2 col-md-6 col-sm-6">
						<div class="single-footer-widget tp_widgets">
							<h4 class="footer_title">Quick Links</h4>
							<ul class="list">
								<li><a href="#">Home</a></li>
								<li><a href="#">Shop</a></li>
								<li><a href="#">Blog</a></li>
								<li><a href="#">Product</a></li>
								<li><a href="#">Brand</a></li>
								<li><a href="#">Contact</a></li>
							</ul>
						</div>
					</div>
					<div class="col-lg-2 col-md-6 col-sm-6">
						<div class="single-footer-widget instafeed">
							<h4 class="footer_title">Gallery</h4>
							<ul class="list instafeed d-flex flex-wrap">
								<li><img src="img/gallery/r1.jpg" alt=""></li>
								<li><img src="img/gallery/r2.jpg" alt=""></li>
								<li><img src="img/gallery/r3.jpg" alt=""></li>
								<li><img src="img/gallery/r5.jpg" alt=""></li>
								<li><img src="img/gallery/r7.jpg" alt=""></li>
								<li><img src="img/gallery/r8.jpg" alt=""></li>
							</ul>
						</div>
					</div>
					<div class="offset-lg-1 col-lg-3 col-md-6 col-sm-6">
						<div class="single-footer-widget tp_widgets">
							<h4 class="footer_title">Contact Us</h4>
							<div class="ml-40">
								<p class="sm-head">
									<span class="fa fa-location-arrow"></span> Head Office
								</p>
								<p>123, Main Street, Your City</p>

								<p class="sm-head">
									<span class="fa fa-phone"></span> Phone Number
								</p>
								<p>
									+123 456 7890 <br> +123 456 7890
								</p>

								<p class="sm-head">
									<span class="fa fa-envelope"></span> Email
								</p>
								<p>
									free@infoexample.com <br> www.infoexample.com
								</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="footer-bottom">
			<div class="container">
				<div class="row d-flex">
					<p class="col-lg-12 footer-text text-center">
						<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
						Copyright &copy;
						<script>document.write(new Date().getFullYear());</script>
						All rights reserved | This template is made with <i
							class="fa fa-heart" aria-hidden="true"></i> by <a
							href="https://colorlib.com" target="_blank">Colorlib</a>
						<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
					</p>
				</div>
			</div>
		</div>
	</footer>
	<!--================ End footer Area  =================-->



	<script src="vendors/jquery/jquery-3.2.1.min.js"></script>
	<script src="vendors/bootstrap/bootstrap.bundle.min.js"></script>
	<script src="vendors/skrollr.min.js"></script>
	<script src="vendors/owl-carousel/owl.carousel.min.js"></script>
	<script src="vendors/nice-select/jquery.nice-select.min.js"></script>
	<script src="vendors/jquery.ajaxchimp.min.js"></script>
	<script src="vendors/mail-script.js"></script>
	<script src="js/main.js"></script>
</body>
</html>