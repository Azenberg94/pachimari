module.exports = function(app, models){

	var api = models.myApi; 
	var passwordHash = require('password-hash');
    var msgError="";
    var bcrypt = require('bcrypt-nodejs');
	var rp = require('request-promise')
	
	// =====================================
	// LOGIN ===============================
	// =====================================
	// show the login form
	app.get('/login', function(req, res) {
		res.render('login.ejs', { msgError: "", session : req.session });
	});

	// process the login form
	app.post('/login', function (req, res, next) {
        msgError="";
		if(!req.body.username){
            msgError = "Veuillez saisir votre identifiant ! "  
			res.render('login.ejs', {msgError:msgError, session : req.session});
        }else if(!req.body.password){
            msgError = "Veuillez saisir votre mot de passe ! " 
			res.render('login.ejs', {msgError:msgError, session : req.session});			
        }else{
			
			rp({
				url: "http://"+api.host+"/auth/" ,
				method: "POST",
				headers:{ 
					'Content-Type': 'application/json'
				},
				json:{ 
				  "login": req.body.username,
				  "pwd": req.body.password

				}
			}).then(function(body){
				rp("http://"+api.host+"/user/"+req.body.username).then(function(body){
					if(body){
						var myJsonObject = JSON.parse(body);
						
						req.session.cookie.maxAge = 1000 * 60 * 3;
						req.session.login = req.body.username;
						req.session.admin = myJsonObject.type;
						res.redirect('/');	
					}else{
						msgError = "Erreur combinaison login/mot de passe !"
						res.render('login.ejs', {msgError:msgError, session : req.session});	
					}
				}).catch(function (err) {
					msgError = "Erreur combinaion Identifiant/mot de passe ! Merci de réessayer." 
					res.render('login.ejs', {msgError:msgError, session : req.session});			
				})
			}).catch(function (err) {
				msgError = "Erreur lors de la connexion ! Merci de réessayer. !"
				res.render('login.ejs', {msgError:msgError, session : req.session});	
			});
				
			
		}	
    });
	
	
	
}