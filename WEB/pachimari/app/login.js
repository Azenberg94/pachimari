module.exports = function(app, models){

	var api = models.myApi; 
	var passwordHash = require('password-hash');
    var msgError="";
    var bcrypt = require('bcrypt-nodejs');
	var request = require('request');
	
	
	// =====================================
	// LOGIN ===============================
	// =====================================
	// show the login form
	app.get('/login', function(req, res) {
		res.render('login.ejs', { msgError: "" });
	});

	// process the login form
	app.post('/login', function (req, res, next) {
        
		if(!req.body.username){
             msgError = "Veuillez saisir votre identifiant ! "  
        }else if(!req.body.password){
             msgError = "Veuillez saisir votre mot de passe ! "  
        }else{
			
			request({
				url: "http://"+api.host+"/auth/" ,
				method: "POST",
				headers:{ 
					'Content-Type': 'application/json'
				},
				json:{ 
				  "login": req.body.username,
				  "pwd": bcrypt.hashSync(req.body.password, null, null)

				}
				,function (error, response, body) { 
					if(!error && response.statusCode == 200) {
						
					} else {
						msgError = "Erreur combinaison identifiant/mot de passe !"
					}
				}
			})
		}
	   
	   if(msgError==""){
		   res.redirect('/');
	   }else{
		   res.render('login.ejs', { msgError: "" });
	   }
			
    });
	
	
	
}