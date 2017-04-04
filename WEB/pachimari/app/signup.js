module.exports = function(app, models){

    var passwordHash = require('password-hash');
    var msgError="";
    var bcrypt = require('bcrypt-nodejs');
	var request = require('request');
	var api = models.myApi; 
 
	// =====================================
	// SIGNUP ==============================
	// =====================================
	// show the signup form
	app.get('/signup', function(req, res, next) {
		var api = models.myApi; 
		
		// render the page and pass in any flash data if it exists
		res.render('signup.ejs', {msgError:""});
	});

	// process the signup form
	app.post('/signup', function (req, res, next) {

        if (!req.body.username){
            msgError = "Veuillez saisir un login !"
        }else if (!req.body.password){
            msgError = "Veuillez saisir un mot de passe !"
        }else if(!req.body.passwordConfirm){
            msgError = "Veuillez retaper votre mot de passe"
        }else if(req.body.password!=req.body.passwordConfirm){
            msgError = "Les mots de passe saisient ne sont pas identiques !"
        }else if(!req.body.firstName){
            msgError = "Veuillez saisir votre nom ! "
        }else if(!req.body.name){
             msgError = "Veuillez saisir votre prénom ! "
        }else if(!req.body.mail){
             msgError = "Veuillez saisir votre mail ! "  
        }else if(!req.body.adresse){
             msgError = "Veuillez saisir votre adresse ! "  
        }else if(!req.body.cp){
             msgError = "Veuillez saisir votre CP ! "  
        }else if(!req.body.ville){
             msgError = "Veuillez saisir votre ville ! "  
        }else{
			request.get(
				"http://"+api.host+"/user/login/"+req.body.username ,
				function (error, response, body) {
					if (!error && response.statusCode == 200) {
						msgError="Ce nom de compte est déjà utilisé ! ";
					}else{
						/*request({
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

								}, function (error, response, body) { 
									if (!error && response.statusCode == 200) {
										console.log("OK");
									}else{
										console.log("Pas ok");
									}
								}
						})*/
						
						request({
							url: "http://"+api.host+"/auth/add/" ,
							method: "POST",
							headers:{ 
								'Content-Type': 'application/json'
							},
							json:{ 
							  "login": req.body.username,
							  "pwd": req.body.password

								}
							,function (error, response, body) { 
								if(!error && response.statusCode == 200) {
									msgError = "Inscription Validé"
								} else {
									msgError = "Erreur lors de l'inscription. Merci de réessayer."
								}
							}
						})
					}
				}
			);

        }


        
        res.render('signup.ejs',{msgError: msgError});
        

	});





};