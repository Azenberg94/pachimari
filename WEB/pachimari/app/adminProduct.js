module.exports = function(app, models){

    var msgError="";
	var msgValidation = "";
	var rp = require('request-promise');
	var request = require('request');
	var api = models.myApi; 
	

				
	// =====================================
	// adminProduct ==============================
	// =====================================
	// show the adminProduct form
	app.get('/adminProduct', function(req, res, next) {
		msgError="";
		rp("http://"+api.host+"/product/" ).then(function(body){
			res.render('adminProduct.ejs', {msgError:"", msgValidation : msgValidation, listProduct :  JSON.parse(body), session : req.session});
		});
			
	});
	


	// process the signup form
	app.post('/adminProduct', function (req, res, next) {
		msgError="";
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
			
				rp({
					url: "http://"+api.host+"/product/" ,
					method: "POST",
					headers:{ 
						'Content-Type': 'application/json'
					},
					json:{ 
					  "name": req.body.name,
					  "brand": req.body.brand,
					  "typeId": req.body.typeId,
					  "price": req.body.price,
					  "imageURL": req.body.imageURL
					}
					
					
				}).then(function(body){
					msgValidation = "Produit ajouté ! Merci de réessayer. !"
					res.render('adminProduct.ejs', {msgError:msgError, msgValidation : msgValidation,session : req.session});	
				}).catch(function (err) {
					msgError = "Erreur lors de la création du produit ! Merci de réessayer. !"
					res.render('adminProduct.ejs', {msgError:msgError, msgValidation : msgValidation, session : req.session});	
				});
			/*
			
			request.get(
				"http://"+api.host+"/product/"+req.body.name+"/"+req.body.brand+"/"+req.body.typeId ,
				function (error, response, body) {
					if (!error) {
						console.log("Exist product");
						msgError="Ce nom de produit est déjà utilisé pour cette marque et ce type! ";
					}else{
						
						
					}
				}
			);*/
			//listProduct = loadProductList();
        }
		
        
       
        

	});





};