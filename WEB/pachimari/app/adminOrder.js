module.exports = function(app, models){

	var listCat;

	// =====================================
	// adminOrder ==============================
	// =====================================
	app.get('/adminOrder', function(req, res, next) {
		
		if(!req.session.type || (req.session.type && req.session.type!="admin")){
			res.redirect("/");
		}else{
			rp("http://"+api.host+"/categories/" ).then(function(body){
				listCat = JSON.parse(body);
			}).then(setTimeout(function(){rp("http://"+api.host+"/orders/" ).then(function(body){
				res.render('adminORder.ejs', {msgError:"", msgValidation : msgValidation, listOrder :  JSON.parse(body), listCat : listCat, session : req.session});
			})},500)
			);
		}
	});
}