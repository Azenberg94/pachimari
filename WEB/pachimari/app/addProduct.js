module.exports = function(app, models){

    var msgError="";
	var rp = require('request-promise');
	var request = require('request');
	var api = models.myApi; 
	

	
				
	// =====================================
	// addProduct ==============================
	// =====================================
	// show the addproduct form
	app.get('/addProduct', function(req, res, next) {
		rp("http://"+api.host+"/product/" ).then(function(body){
			res.render('addProduct.ejs', {msgError:"", listProduct :  JSON.parse(body)});
		});
			
	});
	


	// process the signup form
	app.post('/addProduct', function (req, res, next) {
		
        if (!req.body.name){
            msgError = "Veuillez saisir un nom !"
        }else if (!req.body.brand){
            msgError = "Veuillez saisir une marque !"
        }else if(!req.body.typeId){
            msgError = "Veuillez choisir un type !"
		}else if(!req.body.price){
            msgError = "Veuillez saisir un prix ! "
        }else{
			
				request({
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
					,function (error, response, body) { 
						if(!error && response.statusCode == 200) {
							msgError = "Produit Ajouté"
						} else {
							msgError = "Erreur lors de l'ajout. Merci de réessayer."
						}
					}
				})
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
		
        
        res.redirect('/addProduct');
        

	});





};