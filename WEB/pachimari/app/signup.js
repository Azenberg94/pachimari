module.exports = function(app, models){

    var passwordHash = require('password-hash');
    var msgError="";
    var bcrypt = require('bcrypt-nodejs');
	var request = require('request');

 
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
        }else if(!req.body.age){
             msgError = "Veuillez saisir votre age ! "  
        }else{

					
			request.get(
				"http://localhost:8090/user/login/"+req.body.username ,
				function (error, response, body) {
					if (!error && response.statusCode == 200) {
						console.log("my body : " + body)
						msgError="Ce nom de compte est déjà utilisé ! ";
					}else{
						request({
							url: "http://localhost:8090/user/" ,
							method: "POST",
						 headers:{ 
								'Content-Type': 'application/json'
							},
							json:{ 
							  "id": 6,
							  "name": "aboullll",
							  "login": "gfd",
							  "email": "tegfdst",
							  "type": "user"

								}, function (error, response, body) { 
									if (!error && response.statusCode == 200) {
										console.log("OK");
									}else{
										console.log("Pas ok");
									}
								}
						})
					}
				}
			);

        }


        if(msgError == ""){
            res.redirect('/');
        }else{
            res.render('signup.ejs',{msgError: msgError});
        }

	});





};