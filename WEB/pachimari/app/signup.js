module.exports = function(app, models){

    var passwordHash = require('password-hash');
    var msgError="";
    var bcrypt = require('bcrypt-nodejs');
	var rp = require('request-promise')
	var request = require('request');
	var requestoption1;
	var requestoption2;
	
	var api = models.myApi; 
 
	// =====================================
	// SIGNUP ==============================
	// =====================================
	// show the signup form
	
	app.get('/signup', function(req, res, next) {
		msgError="";
		// render the page and pass in any flash data if it exists
		res.render('signup.ejs', {msgError:"", session : req.session});
	});

	// process the signup form
	app.post('/signup', function (req, res, next) {
		msgError="";
        if (!req.body.username){
            msgError = "Veuillez saisir un login !"
			res.render('signup.ejs', {msgError:msgError,session : req.session});
			
        }else if (!req.body.password){
            msgError = "Veuillez saisir un mot de passe !"
			res.render('signup.ejs', {msgError:msgError, session : req.session});
			
        }else if(!req.body.passwordConfirm){
            msgError = "Veuillez retaper votre mot de passe"
			res.render('signup.ejs', {msgError:msgError, session : req.session});
			
        }else if(req.body.password!=req.body.passwordConfirm){
            msgError = "Les mots de passe saisient ne sont pas identiques !"
			res.render('signup.ejs', {msgError:msgError, session : req.session});
			
        }else if(!req.body.firstName){
            msgError = "Veuillez saisir votre nom ! "
			res.render('signup.ejs', {msgError:msgError, session : req.session});
			
        }else if(!req.body.name){
             msgError = "Veuillez saisir votre prénom ! "
			 res.render('signup.ejs', {msgError:msgError, session : req.session});
        }else if(!req.body.mail){
             msgError = "Veuillez saisir votre mail ! "  
			 res.render('signup.ejs', {msgError:msgError, session : req.session});
			 
        }else if(!req.body.adresse){
             msgError = "Veuillez saisir votre adresse ! "  
			 res.render('signup.ejs', {msgError:msgError, session : req.session});
			 
        }else if(!req.body.cp){
             msgError = "Veuillez saisir votre CP ! "  
			 res.render('signup.ejs', {msgError:msgError, session : req.session});
			 
        }else if(!req.body.ville){
             msgError = "Veuillez saisir votre ville ! "  
			 res.render('signup.ejs', {msgError:msgError, session : req.session});
			 
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
						  "lastName": req.body.name,
						  "name":req.body.firstName,
						  "login": req.body.username,
						  "email": req.body.mail,
						  "addresse" : req.body.adresse,
						  "cp" : req.body.cp,
						  "ville" : req.body.ville,
						  "type": "user"

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
						  "pwd": bcrypt.hashSync(req.body.password)
						}
					}).then(function(body){
						//console.log("Body 2 : " + body)
					}).catch(function (err) {
						msgError="Erreur veuillez lors de l'inscription. Veuillez recommmencer !";
						res.render('signup.ejs', {msgError:"Erreur veuillez lors de l'inscription. Veuillez recommmencer !", session : req.session});
						
					});
				}
				
			}).then(function(){
				if(msgError==""){
					res.render('signup.ejs', {msgError:"Inscription validé !", session : req.session});
				}else{
					res.render('signup.ejs', {msgError:msgError, session : req.session});
				}
				
				
			});
			

        }  
	});





};