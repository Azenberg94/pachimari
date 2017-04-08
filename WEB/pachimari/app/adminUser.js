module.exports = function(app, models){

    var msgError="";
	var msgValidation = "";
	var rp = require('request-promise');
	var request = require('request');
	var api = models.myApi; 
	var bcrypt = require('bcrypt-nodejs');

				
	// =====================================
	// adminProduct ==============================
	// =====================================
	// show the adminProduct form
	app.get('/adminUser', function(req, res, next) {
		msgError="";
		msgValidation="";
		/*if(!req.session.type || (req.session.type && req.session.type!="admin")){
			res.redirect("/");
		}else{*/
			rp("http://"+api.host+"/user/" ).then(function(body){
				res.render('adminUser.ejs', {msgError:"", msgValidation : msgValidation, listUser :  JSON.parse(body), session : req.session});
			});
		//}
			
	});
	
	app.get('/adminUser/valide', function(req, res, next) {
		msgError="";
		msgValidation="Utilisateur ajouté !";
		/*if(!req.session.type || (req.session.type && req.session.type!="admin")){
			res.redirect("/");
		}else{*/
			rp("http://"+api.host+"/user/" ).then(function(body){
				res.render('adminUser.ejs', {msgError:"", msgValidation : msgValidation, listUser :  JSON.parse(body), session : req.session});
			});
		//}
			
	});


	// process the signup form
	app.post('/adminUser', function (req, res, next) {
		msgError="";
		msgValidation="";
		
		// if(!req.session.type || (req.session.type && req.session.type!="admin")){
			// res.redirect("/");
		// }else{
				msgError="";
			if (!req.body.username){
				msgError = "Veuillez saisir un login !"
				res.redirect('/adminUser');
				
			}else if (!req.body.password){
				msgError = "Veuillez saisir un mot de passe !"
				res.redirect('/adminUser');
				
			}else if(!req.body.passwordConfirm){
				msgError = "Veuillez retaper votre mot de passe"
				res.redirect('/adminUser');
			}else if(req.body.password!=req.body.passwordConfirm){
				msgError = "Les mots de passe saisient ne sont pas identiques !"
				res.redirect('/adminUser');
			}else if(!req.body.lastName){
				msgError = "Veuillez saisir votre nom ! "
				res.redirect('/adminUser');
			}else if(!req.body.name){
				 msgError = "Veuillez saisir votre prénom ! "
				 res.redirect('/adminUser');
			}else if(!req.body.mail){
				 msgError = "Veuillez saisir votre mail ! "  
				res.redirect('/adminUser');
			}else if(!req.body.adresse){
				msgError = "Veuillez saisir votre adresse ! "  
				res.redirect('/adminUser'); 
			}else if(!req.body.cp){
				msgError = "Veuillez saisir votre CP ! "  
				res.redirect('/adminUser');
			}else if(!req.body.ville){
				msgError = "Veuillez saisir votre ville ! "  
				res.redirect('/adminUser');
			}else{
				
				rp("http://"+api.host+"/user/"+req.body.username).then(function(body){
					if(body){
						msgError="Cet utilisateur existe déjà !";
					}
				}).catch(function (err) {
						//console.log("Error rp1 : " + err)
				}).then(function(){
					if (msgError==""){
						rp({
							url: "http://"+api.host+"/user/" ,
							method: "POST",
							headers:{ 
								'Content-Type': 'application/json'
							},
							json:{ 
							  "lastName": req.body.lastName,
							  "name":req.body.name,
							  "login": req.body.username,
							  "email": req.body.mail,
							  "addresse" : req.body.adresse,
							  "cp" : req.body.cp,
							  "ville" : req.body.ville,
							  "type": req.body.type

								}
						}).then(function(body){
							//console.log("Body 1 : " + body)
						}).catch(function (err) {
							msgError="Erreur veuillez lors de l'inscription. Veuillez recommmencer !";
							
						});
						
						rp({
							url: "http://"+api.host+"/auth/add/" ,
							method: "POST",
							headers:{ 
								'Content-Type': 'application/json'
							},
							json:{ 
							  "login": req.body.username,
							  "pwd": bcrypt.hashSync(req.body.password, null, null),
							}
						}).then(function(body){
							//console.log("Body 2 : " + body)
						}).catch(function (err) {
							msgError="Erreur veuillez lors de l'inscription. Veuillez recommmencer !";
							rp("http://"+api.host+"/user/" ).then(function(body){
								res.render('adminUser.ejs', {msgError:msgError, msgValidation : msgValidation, listUser :  JSON.parse(body), session : req.session});
							});
							
						});
					}
					
				}).then(function(){
					if(msgError==""){
						msgValidation = "Utilisateur ajouté !"
						res.redirect("/adminUser/valide");
					}else{
						rp("http://"+api.host+"/user/" ).then(function(body){
							res.render('adminUser.ejs', {msgError:msgError, msgValidation : msgValidation, listUser :  JSON.parse(body), session : req.session});
						});
					}
					
				});
				

			}  
		//}
	});
	
	// process the signup form
	app.post('/adminProduct/update', function (req, res, next) {
		msgError="";
		msgValidation="";
		
	
		
		// if(!req.session.type || (req.session.type && req.session.type!="admin")){
			// res.redirect("/");
		// }else{
			if (!req.body.name){
				msgError = "Veuillez saisir un nom !"
				 res.redirect('/adminProduct');
			}else if (!req.body.brand){
				msgError = "Veuillez saisir une marque !"
				 res.redirect('/adminProduct');
			}else if(!req.body.typeId){
				msgError = "Veuillez choisir un type !"
				 res.redirect('/adminProduct');
			}else if(!req.body.price){
				msgError = "Veuillez saisir un prix ! "
				res.redirect('/adminProduct');
			}else{
				if(req.body.name == req.body.nameOld && req.body.brand == req.body.brandOld && req.body.type == req.body.typeOld && req.body.price == req.body.priceOld && req.body.imageURL == req.body.imageURLOld ){
					res.redirect("adminProduct/update/valide")
				}else{
					rp("http://"+api.host+"/product/find/"+req.body.name+"/"+req.body.brand+"/"+req.body.typeId).then(function(body){
						if(JSON.parse(body).length>0 && (req.body.name != req.body.nameOld || req.body.brand != req.body.brandOld || req.body.type == req.body.typeOld)){
							msgError="Ce nom de produit est déjà utilisé pour cette marque et ce type! ";
						}
					}).catch(function (err) {
						//console.log("Error rp1 : " + err)
					}).then(function(){
						if(msgError==""){
							rp({
								url: "http://"+api.host+"/product/" ,
								method: "PUT",
								headers:{ 
									'Content-Type': 'application/json'
								},
								json:{ 
								  "id" : req.body.id,
								  "name": req.body.name,
								  "brand": req.body.brand,
								  "typeId": req.body.typeId,
								  "price": req.body.price,
								  "imageURL": req.body.imageURL
								}	
							}).then(function(body){
								//console.log("Body 2 : " + body)
							}).catch(function (err) {
								msgError = "Erreur lors de la création du produit ! Merci de réessayer. !"
								rp("http://"+api.host+"/product/" ).then(function(body){
									res.render('adminProductUpdate.ejs', {msgError:msgError, msgValidation : msgValidation, listProduct :  JSON.parse(body), session : req.session});
								});
								
							});
							
						}
				
					}).then(function(body){
						if(msgError==""){
							msgValidation = "Produit ajouté !"
							res.redirect("/adminProduct/update/valide");
						}else{
							rp("http://"+api.host+"/product/" ).then(function(body){
								res.redirect('/adminProduct/update/error/'+req.body.id);
							});
						}
					});
				}

			}
		// }
	});

	app.get('/adminProduct/update/valide', function(req, res, next) {
		msgError="";
		msgValidation="";
		/*if(!req.session.type || (req.session.type && req.session.type!="admin")){
			res.redirect("/");
		}else{*/
			rp("http://"+api.host+"/product/" ).then(function(body){
				res.render('adminProduct.ejs', {msgError:"", msgValidation : "Modification enregistrée !", listProduct :  JSON.parse(body), session : req.session});
			});
		//}
			
	});
	
	app.get('/adminProduct/update/:tagId', function(req, res, next) {
		msgError="";
		msgValidation="";
		/*if(!req.session.type || (req.session.type && req.session.type!="admin")){
			res.redirect("/");
		}else{*/
			rp("http://"+api.host+"/product/"+req.params.tagId ).then(function(body){
				res.render('adminProductUpdate.ejs', {msgError:"", msgValidation : msgValidation, listProduct :  JSON.parse(body), session : req.session});
			});
		//}
			
	});
	
	app.get('/adminProduct/update/error/:tagId', function(req, res, next) {
		msgError="";
		msgValidation="";
		/*if(!req.session.type || (req.session.type && req.session.type!="admin")){
			res.redirect("/");
		}else{*/
			rp("http://"+api.host+"/product/"+req.params.tagId ).then(function(body){
				res.render('adminProductUpdate.ejs', {msgError:"Ce nom de produit est déjà utilisé pour cette marque et ce type!", msgValidation : msgValidation, listProduct :  JSON.parse(body), session : req.session});
			});
		//}
			
	});
	
	app.get('/adminUser/delete/:tagId:/:tagLogin', function(req, res, next) {
		msgError="";
		msgValidation=""
		/*if(!req.session.type || (req.session.type && req.session.type!="admin")){
			res.redirect("/");
		}else{*/
			rp({
				url: "http://"+api.host+"/product/" ,
				method: "DELETE",
				
				body: [req.params.tagId]
			}).then(
			
			rp({
				url: "http://"+api.host+"/product/" ,
				method: "DELETE",
				
				body: [req.params.tagId]
			})).then(function(body){
				res.redirect("/adminUser/delete/")
			}).catch(function (err) {
				msgError = "Erreur lors du Delete ! Merci de réessayer. !"
				console.log("err : " +err)
				rp("http://"+api.host+"/product/" ).then(function(body){
					res.render('adminUser.ejs', {msgError:msgError, msgValidation : msgValidation, listUser :  JSON.parse(body), session : req.session});
				});
				
			});
		//}
			
	});
	
	app.get('/adminUser/delete/', function(req, res, next) {
		msgError="";
		msgValidation=""
		/*if(!req.session.type || (req.session.type && req.session.type!="admin")){
			res.redirect("/");
		}else{*/
			rp("http://"+api.host+"/product/" ).then(function(body){
				res.render('adminUser.ejs', {msgError:"", msgValidation : "Produit supprimé !", listUser :  JSON.parse(body), session : req.session});
			});
		//}
			
	});



};