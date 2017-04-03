module.exports = function(app, models){

	// =====================================
	// LOGIN ===============================
	// =====================================
	// show the login form
	app.get('/login', function(req, res) {
   /* if (req.isAuthenticated())
      res.redirect('/profile');
		// render the page and pass in any flash data if it exists
    else*/
		res.render('login.ejs', { msgError: "" });
	});

	// process the login form
	/*app.post('/login', passport.authenticate('local-login', {
            successRedirect : '/home', // redirect to the secure profile section
            failureRedirect : '/login', // redirect back to the signup page if there is an error
            failureFlash : true // allow flash messages
		}),
        function(req, res) {

            if (req.body.remember) {
              req.session.cookie.maxAge = 1000 * 60 * 3;
            } else {
              req.session.cookie.expires = false;
            }
        res.redirect('/');
    });*/
	
	
	
}